package org.ykone.object.sampler.samplers.impl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.ISampleContext;

public class ByteSamplerTest {
	
	private ByteSampler byteSampler = ByteSampler.getInstance();
	
	@Test
	public void shouldGenerateByte(){
		// Given ==> byteSampler 
		
		// When
		Byte generatedByte = byteSampler.generate(Byte.class, new ISampleContext(){});
		
		// Then
		Assertions.assertThat(generatedByte).isNotNull();
	}
}
