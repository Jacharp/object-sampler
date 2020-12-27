package org.ykone.object.sampler.samplers;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.exception.NoDefaultConstructorFoundException;
import org.ykone.object.sampler.exception.NoImplementationDefinedException;
import org.ykone.object.sampler.samplers.examples.ClassToSample;
import org.ykone.object.sampler.samplers.examples.ClassToSampleWithClassAsField;
import org.ykone.object.sampler.samplers.examples.ClassWithNoDefaultConstructor;
import org.ykone.object.sampler.samplers.examples.InterfaceToSample;
import org.ykone.object.sampler.samplers.examples.InterfaceToSampleImpl;

public class ObjectSamplerTest {

	private static Map<Class<?>, Class<?>> interfaceImplClasses;

	@BeforeClass
	public static void init() {
		interfaceImplClasses = new HashMap<>();
		interfaceImplClasses.put(InterfaceToSample.class, InterfaceToSampleImpl.class);
	}

	@Test
	public void shouldCreateAnObjectWithDefaultContext() throws InstantiationException, IllegalAccessException {

		// Given ClassToSample
		ISampleContext sampleContext = new ISampleContext() {
			@Override
			public Map<Class<?>, Class<?>> getInterfaceImplementationToUse() {
				return interfaceImplClasses;
			}
		};

		// When
		ClassToSample aSmartObject = ObjectSampler.sample(ClassToSample.class, sampleContext);

		// Then
		verify(aSmartObject);
	}

	@Test
	public void shouldRecursiveFillClass() throws InstantiationException, IllegalAccessException {
		// Given a class containing another bean and Following Sample Context
		ISampleContext sampleContext = new ISampleContext() {

			@Override
			public int getCollectionOrArraySize() {
				return 3;
			}

			@Override
			public Map<Class<?>, Class<?>> getInterfaceImplementationToUse() {
				return interfaceImplClasses;
			}
		};

		// When
		ClassToSampleWithClassAsField aSampledBean = ObjectSampler.sample(ClassToSampleWithClassAsField.class,
				sampleContext);

		// Then
		verify(aSampledBean.getClassField());
		assertThat(aSampledBean.getByteField()).isNotNull();
		assertThat(aSampledBean.getDoubleField()).isGreaterThanOrEqualTo(1);
		assertThat(aSampledBean.getFloatField()).isGreaterThanOrEqualTo(0);
		assertThat(aSampledBean.getLongField()).isNotNull();
		// assertThat(aSampledBean.getShortField()).isGreaterThanOrEqualTo(1);
		assertThat(aSampledBean.getBigDecimalField()).isNotNull();
		assertThat(aSampledBean.getBigIntegerField()).isNotNull();
		assertThat(aSampledBean.getSqlDateField()).isToday();
		assertThat(aSampledBean.getCalendarField()).isNotNull();
		assertThat(aSampledBean.getStringArray()).hasSize(3);
		assertThat(aSampledBean.getListField()).hasSize(3);
		assertThat(aSampledBean.getSetField()).hasSize(3);
		assertThat(aSampledBean.getMapField()).hasSize(3);
		assertThat(aSampledBean.getMapField().values()).hasSize(3);

		for (BigDecimal value : aSampledBean.getMapField().values()) {
			assertThat(value).isNotNull();
		}

		// even if we want to insert 3 items in array and collection, for item
		// like Date, it is not possible because the same date will be generated
		// due to date setting in SampleContexts
		assertThat(aSampledBean.getLocalDateSetField()).hasSize(1);
		assertThat(aSampledBean.getDateMapField()).hasSize(1);
	}

	@Test
	public void shouldNotSetIgnoredFields() throws Exception {

		// Given a ClassToSample and following sample context

		ISampleContext sampleContext = new ISampleContext() {

			@Override
			public Set<String> getIgnoreFields() {
				Set<String> fieldsToIgnore = new HashSet<>();

				fieldsToIgnore.add("integerField");
				fieldsToIgnore.add("enumField");

				return fieldsToIgnore;
			}
			
			@Override
			public Map<Class<?>, Class<?>> getInterfaceImplementationToUse() {
				return interfaceImplClasses;
			}
		};

		// When
		ClassToSample aSmartObject = ObjectSampler.sample(ClassToSample.class, sampleContext);

		// Then
		assertThat(aSmartObject.getIntegerField()).isNull();
		assertThat(aSmartObject.getEnumField()).isNull();

	}

	@Test(expected=NoImplementationDefinedException.class)
	public void shouldFailIfNoImplementationISetForAnInterface() throws Exception{
		
		// Given ISampler
		
		//When
		ObjectSampler.sample(InterfaceToSample.class, new ISampleContext(){});
		
		//Then ==> NoImplementationDefinedException
		
	}
	
	@Test
	public void shouldSampleArray() throws Exception{
		
		// Given 
		Class<String[]> arrayToSample = String[].class;
		
		//When
		String[] sampledObject = ObjectSampler.sample(arrayToSample, new ISampleContext(){});
		
		//Then
		assertThat(sampledObject).isNotNull();
		assertThat(sampledObject).hasSizeGreaterThan(0);
		Arrays.stream(sampledObject).forEach(
				valueInArray->assertThat(valueInArray.getClass()).isNotExactlyInstanceOf(String.class)
		);
	}
	
	@Test(expected=NoDefaultConstructorFoundException.class)
	public void shouldThrowExceptionIfNoClassHasNoDefaultConstruction() throws Exception{
		// Given => ClassWithNoDefaultConstructor
		
		// When
		ObjectSampler.sample(ClassWithNoDefaultConstructor.class, new ISampleContext(){});
		
		// Then ==> NoDefaultConstructorFoundException
		
	}
	
	private void verify(ClassToSample classToSample) {

		assertThat(classToSample.getIntField()).isGreaterThanOrEqualTo(1);
		assertThat(classToSample.getStringField()).isNotNull();
		assertThat(classToSample.getCharField()).isNotNull();
		assertThat(classToSample.getBooleanField()).isNotNull();
		assertThat(classToSample.getLocalDateField()).isToday();
		assertThat(classToSample.getLocalDateTimeField()).isNotNull();
		assertThat(classToSample.getDateField()).isToday();
		assertThat(classToSample.getIntegerField()).isGreaterThanOrEqualTo(0);
		assertThat(classToSample.getEnumField()).isNotNull();
		assertThat(classToSample.getFileField()).isNotNull();
		assertThat(classToSample.getListField()).isNotNull();
		assertThat(classToSample.getListField()).hasSizeBetween(2, 5);
		assertThat(classToSample.getSetField()).hasSizeBetween(2, 5);
		assertThat(classToSample.getMapField()).hasSizeBetween(2, 5);
		assertThat(classToSample.getByteField()).isNotNull();
		// Check that interface implementation is well fill
		InterfaceToSampleImpl interfaceImplSample = (InterfaceToSampleImpl) classToSample.getInterfaceField();
		assertThat(interfaceImplSample).isNotNull();
		assertThat(interfaceImplSample.getFinalField()).isEqualTo(Integer.MAX_VALUE); 
		assertThat(interfaceImplSample.getShorField()).isNotNull();
		assertThat(InterfaceToSampleImpl.getSTATIC_FIELD()).isNotEqualTo(2020.09);
	}

}
