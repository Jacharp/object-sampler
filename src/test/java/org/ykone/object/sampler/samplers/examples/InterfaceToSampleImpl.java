package org.ykone.object.sampler.samplers.examples;

public class InterfaceToSampleImpl implements InterfaceToSample {
	
	private Short shorField;
	private final Integer finalField=Integer.MAX_VALUE;
	private static Float STATIC_FIELD=2020.09f;
	
	@Override
	public void implementMe() {
		System.out.println("Do nothing. Just to illustrate interface purpose");
	}

	public Short getShorField() {
		return shorField;
	}

	public Integer getFinalField() {
		return finalField;
	}

	public static Float getSTATIC_FIELD() {
		return STATIC_FIELD;
	}

}
