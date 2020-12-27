package org.ykone.object.sampler.samplers;

import static org.ykone.object.sampler.util.ClassHelper.firstNonNull;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.exception.NoDefaultConstructorFoundException;
import org.ykone.object.sampler.exception.NoImplementationDefinedException;
import org.ykone.object.sampler.exception.NoSamplerFoundException;
import org.ykone.object.sampler.samplers.impl.SamplerFactory;
import org.ykone.object.sampler.util.ClassHelper;

/**
 * The sampler entry point: Use the static method <code>sample</code> to create an fill an instance of a given class
 * /!\ The class to sample must have default constructor ( constructor without parameter)
 *
 */
public class ObjectSampler {

	private static final int NUMBER_0F_ATTEMPT_TO_ADD_SET_ITEM = 5;
	
	private ObjectSampler(){
		
	}

	@SuppressWarnings("unchecked")
	public static <T> T sample(Class<T> classToSample, ISampleContext sampleContext)
			throws InstantiationException, IllegalAccessException {

		Objects.requireNonNull(classToSample, "classToSample must not be null");
		Objects.requireNonNull(sampleContext, "sampleContext must not be null");

		T sampledObject = null;
		try {
			
			if(SamplerFactory.canSample(classToSample, sampleContext)){
				return SamplerFactory.generate(classToSample, sampleContext);
			} else if (classToSample.isArray()){
				return (T)constructArrayItems(classToSample, sampleContext);
			}else if (org.ykone.object.sampler.util.ClassHelper.isInterfaceOrAbstract(classToSample)) {
				classToSample = (Class<T>) getInterfaceImplementation(classToSample, sampleContext);
			}
			
			sampledObject = SamplerFactory.generate(classToSample, sampleContext);

		} catch (NoSamplerFoundException nsf) {
			// ie cannot generate a sample for this class maybe it is a complex
			// class (i.e no sample exist for this class. So we must create a
			// new instance and fill all fields of this instance
			sampledObject = createAndfillInstance(classToSample, sampleContext);
		}

		return sampledObject;
	}

	private static <T> T createAndfillInstance(Class<T> classToSample, ISampleContext sampleContext)
			throws InstantiationException, IllegalAccessException {
		// Before constructing an object check the type (i.e is a interface

		 if (!hasDefaultPublicConstructor(classToSample)) {
			throw new NoDefaultConstructorFoundException(
					String.format("The class to sample ['%s'] does not have a default constructor", classToSample));
		}
		T sampledInstance = classToSample.newInstance();
		
		Field[] declaredFields = classToSample.getDeclaredFields();

		for (Field field : declaredFields) {
			if (!field.isSynthetic() && !Modifier.isFinal(field.getModifiers())
					&& !sampleContext.getIgnoreFields().contains(field.getName())) {
				field.setAccessible(true);
				Class<?> fieldClass = field.getType();
				Object fieldValue = null;

				if (SamplerFactory.canSample(fieldClass, sampleContext)) {
					fieldValue = SamplerFactory.generate(fieldClass, sampleContext);
				} else {
					if (fieldClass.isArray()) {
						fieldValue = constructArrayItems(fieldClass, sampleContext);
					} 
					else if (ClassHelper.isInterfaceOrAbstract(fieldClass)) {
						fieldValue = fillInterface(field, sampleContext);
					} else if (List.class.isAssignableFrom(fieldClass)) {
						fieldValue = buildListItems(field, sampleContext, fieldClass);
					} else if (Set.class.isAssignableFrom(fieldClass)) {
						fieldValue = buildSetItems(field, sampleContext, fieldClass);
					} else if (Map.class.isAssignableFrom(fieldClass)) {
						fieldValue = buildMapItems(field, sampleContext, fieldClass);
					} else {
						// it means it is not a know class So fill its fields it
						// because all simples classes are supposed to have a
						// sampler
						fieldValue = createAndfillInstance(fieldClass, sampleContext);
					}
				}
				field.set(sampledInstance, fieldValue);
			}
		}
		return sampledInstance;
	}

	@SuppressWarnings("unchecked")
	private static Class<?> getInterfaceImplementation(Class<?> classToSample, ISampleContext sampleContext){
		
		Class<?> interfaceImplemntation = sampleContext.getInterfaceImplementationToUse().get(classToSample);
		if(interfaceImplemntation!=null){
			return interfaceImplemntation;
		}
		if (List.class.isAssignableFrom(classToSample)) {
			return  firstNonNull(interfaceImplemntation, ArrayList.class);
		} else if (Set.class.isAssignableFrom(classToSample)) {
			return firstNonNull(interfaceImplemntation, HashSet.class);
		} else if (Map.class.isAssignableFrom(classToSample)) {
			return  firstNonNull(interfaceImplemntation, HashMap.class);
		}

		throw new NoImplementationDefinedException("There is no implementation defined for interface " + classToSample);
	}
	
	private static Object fillInterface(Field field, ISampleContext sampleContext)
			throws InstantiationException, IllegalAccessException {

		Class<?> interfaceImplemntation = getInterfaceImplementation(field.getType(), sampleContext);

		if (List.class.isAssignableFrom(field.getType())) {
			return buildListItems(field, sampleContext, interfaceImplemntation);
		} else if (Set.class.isAssignableFrom(field.getType())) {
			return buildSetItems(field, sampleContext, interfaceImplemntation);
		} else if (Map.class.isAssignableFrom(field.getType())) {
			return buildMapItems(field, sampleContext, interfaceImplemntation);
		}

		return sample(interfaceImplemntation, sampleContext);
	}

	private static Object[] constructArrayItems(Class<?> fieldClass, ISampleContext sampleContext)
			throws InstantiationException, IllegalAccessException {

		int arraySize = sampleContext.getCollectionOrArraySize();

		Class<?> arrayItemClass = fieldClass.getComponentType();
		Object[] generatedArray = (Object[]) Array.newInstance(arrayItemClass, arraySize);

		for (int i = 0; i < arraySize; i++) {
			generatedArray[i] = sample(arrayItemClass, sampleContext);
		}

		return generatedArray;
	}

	private static List<?> buildListItems(Field field, ISampleContext sampleContext, Class<?> listImplementer)
			throws InstantiationException, IllegalAccessException {

		int listSize = sampleContext.getCollectionOrArraySize();
		List<Object> resultList = (List<Object>) listImplementer.newInstance();

		Class<?> listItemClazz = (Class<?>) (((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);

		for (int i = 0; i < listSize; i++) {
			Object sampledItem = sample(listItemClazz, sampleContext);
			resultList.add(sampledItem);
		}
		return resultList;
	}

	private static Set<?> buildSetItems(Field field, ISampleContext sampleContext, Class<?> setImplementer)
			throws InstantiationException, IllegalAccessException {

		int setSize = sampleContext.getCollectionOrArraySize();

		Set<Object> result = (Set<Object>) setImplementer.newInstance();

		Class<?> setItemClazz = (Class<?>) (((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);

		int countGeneratedItem = 0;

		// To avoid infinity trying to add an item in set if it is already present
		int countDown = NUMBER_0F_ATTEMPT_TO_ADD_SET_ITEM;

		while (countGeneratedItem < setSize && countDown > 0) {
			Object sampledItem = sample(setItemClazz, sampleContext);
			boolean isAdded = result.add(sampledItem);

			if (isAdded) {
				countGeneratedItem++;
				countDown = NUMBER_0F_ATTEMPT_TO_ADD_SET_ITEM; // reinitialize it
			} else {
				countDown--;
			}
		}

		return result;
	}

	private static Map<Object, Object> buildMapItems(Field field, ISampleContext sampleContext, Class<?> mapImplementer)
			throws InstantiationException, IllegalAccessException {

		int mapSize = sampleContext.getCollectionOrArraySize();

		Map<Object, Object> result = (Map<Object, Object>) mapImplementer.newInstance();

		ParameterizedType type = (ParameterizedType) field.getGenericType();

		Class<?> keyClass = (Class<?>) type.getActualTypeArguments()[0];
		Class<?> valueClass = (Class<?>) type.getActualTypeArguments()[1];

		int countGeneratedItem = 0;
		// To avoid infinity trying to add an item in set if it is alreadyÒ
		// present
		int countDown = NUMBER_0F_ATTEMPT_TO_ADD_SET_ITEM;

		while (countGeneratedItem < mapSize && countDown > 0) {
			Object key = sample(keyClass, sampleContext);
			Object valueAssociatedToKey = sample(valueClass, sampleContext);
			if (result.containsKey(key)) {
				countDown--;
			} else {
				result.put(key, valueAssociatedToKey);
				countGeneratedItem++;
			}
		}

		return result;
	}

	private static boolean hasDefaultPublicConstructor(Class<?> clazz) {
		Optional<Constructor<?>> defaultConstructor = Arrays.stream(clazz.getConstructors())
				.filter(constructor -> constructor.getParameterCount() == 0).findFirst();
		return defaultConstructor.isPresent();
	}

}
