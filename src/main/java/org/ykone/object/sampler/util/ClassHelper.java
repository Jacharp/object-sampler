package org.ykone.object.sampler.util;

import java.lang.reflect.Modifier;

public class ClassHelper {
	
	private ClassHelper(){}

	
	public static boolean isInterfaceOrAbstract(Class<?> clazz){
		return !clazz.isArray() && (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers()));
	}
	
	@SafeVarargs
	public static <T> T firstNonNull(T... objects) {
		for (T obj : objects) {
			if (obj != null) {
				return obj;
			}
		}
		return objects[0];
	}

}
