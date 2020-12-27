package org.ykone.object.sampler.exception;

public class NoDefaultConstructorFoundException extends RuntimeException {

	private static final long serialVersionUID = -8871500905780620270L;

	public NoDefaultConstructorFoundException(String message) {
		super(message);
	}

}
