package org.ykone.object.sampler.samplers.impl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.ISampleContext;

public class CharacterSamplerTest {
	private CharacterSampler characterSampler = CharacterSampler.getInstance();
	
	@Test
	public void shouldGenerateCharacter(){
		// Given stringSampler 
		
		// When
		Character generatedChar = characterSampler.generate(Character.class, new ISampleContext(){});
		
		// Then
		Assertions.assertThat(generatedChar).isNotNull();
	}
}
