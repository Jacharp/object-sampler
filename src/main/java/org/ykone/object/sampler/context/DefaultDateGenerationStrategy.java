package org.ykone.object.sampler.context;

public class DefaultDateGenerationStrategy implements DateGenerationStrategy {
	
	private static final DefaultDateGenerationStrategy INSTANCE = new DefaultDateGenerationStrategy();
	
	private DefaultDateGenerationStrategy(){}
	
	public static DefaultDateGenerationStrategy getInstance(){
		return INSTANCE;
	}
}
