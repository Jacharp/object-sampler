package org.ykone.object.sampler.samplers.context;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.Range;

public class RangeTest {
	
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfMinValueIsNegative(){
		
		// Given
		
		int minRangeValue = -4;
		int maxRangeValue = 5;
		
		// When
		 Range.of(minRangeValue, maxRangeValue);
		
		// Then ==> throw IllegalArgumentException
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfMinValueIsGreaterThanMax(){
		
		// Given
		int minRangeValue = 6;
		int maxRangeValue = 5;
		
		// When
		 Range.of(minRangeValue, maxRangeValue);
		
		// Then ==> throw IllegalArgumentException
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfMinValueIsEqualToMax(){
		
		// Given
		int minRangeValue = 3;
		int maxRangeValue = 3;
		
		// When
		 Range.of(minRangeValue, maxRangeValue);
		
		// Then ==> throw IllegalArgumentException
	}
	

	@Test
	public void shouldInstantiateRangeIfMinAndMaxAreRight(){
		
		// Given
		int minRangeValue = 2;
		int maxRangeValue = 5;
		
		// When
		 Range aRange = Range.of(minRangeValue, maxRangeValue);
		
		// Then
		 Assertions.assertThat(aRange).isNotNull();
		 Assertions.assertThat(aRange.getMin()).isEqualTo(minRangeValue);
		 Assertions.assertThat(aRange.getMax()).isEqualTo(maxRangeValue);
	}

}
