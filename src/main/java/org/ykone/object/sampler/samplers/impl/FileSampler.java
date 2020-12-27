package org.ykone.object.sampler.samplers.impl;

import java.io.File;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class FileSampler implements ISampler<File> {

private static final FileSampler INSTANCE = new FileSampler();
	
	private FileSampler(){
	}
	
	public static FileSampler getInstance(){
		return INSTANCE;
	}
	
	@Override
	public File generate(Class<?> classToSample, ISampleContext sampleContext) {
		
		String sampleFilePath = FileSampler.class.getClassLoader().getResource("fileSample.txt").getPath();
		return new File(sampleFilePath);
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return File.class.equals(objectToSampleClass);
	}
	
}
