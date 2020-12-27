package org.ykone.object.sampler.samplers.util;

import java.io.Closeable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.util.ClassHelper;

public class ClassHelperTest {
	
	@Test
	public void shouldReturnTrueIfClassIsAbstract(){
		
		// GIVEN
		Class<?> aClassToTest = Calendar.class;
		
		// WHEN
		boolean expected = ClassHelper.isInterfaceOrAbstract(aClassToTest);
		
		//THEN
		Assertions.assertThat(expected).isTrue();
	}
	
	@Test
	public void shouldReturnTrueIfClassIsInterface(){
		
		// GIVEN
		Class<Serializable> aClassToTest = Serializable.class;
		
		// WHEN
		boolean expected = ClassHelper.isInterfaceOrAbstract(aClassToTest);
		
		//THEN
		Assertions.assertThat(expected).isTrue();
	}
	
	@Test
	public void shouldReturnFalseIfClassIsNeitherInterfaceNorAbstract(){
		
		// GIVEN
		Class<String> aClassToTest = String.class;
		
		// WHEN
		boolean expected = ClassHelper.isInterfaceOrAbstract(aClassToTest);
		
		//THEN
		Assertions.assertThat(expected).isFalse();
	}
	
	@Test
	public void shouldReturnFalseIfClassIsArray(){
		
		// GIVEN
		Class<Closeable[]> aClassToTest = Closeable[].class;
		
		// WHEN
		boolean expected = ClassHelper.isInterfaceOrAbstract(aClassToTest);
		
		//THEN
		Assertions.assertThat(expected).isFalse();
	}
	
	@Test
	public void shouldReturnFirstNonNullClass(){
		
		// GIVEN
		
		// WHEN
		Class<?> expectedDateClass = ClassHelper.firstNonNull(null, String.class, LocalDate.class);
		
		//THEN
		Assertions.assertThat(expectedDateClass).isNotNull();
		Assertions.assertThat(expectedDateClass).isEqualTo(String.class);
	}
	
	@Test
	public void shouldReturnNullIfAllIsNull(){
		
		// GIVEN
		
		// WHEN
		Class<?> expectedDateClass = ClassHelper.firstNonNull(null, null, null);
		
		//THEN
		Assertions.assertThat(expectedDateClass).isNull();
	}

}
