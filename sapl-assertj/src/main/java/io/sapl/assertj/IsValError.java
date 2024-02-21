package io.sapl.assertj;

import org.assertj.core.api.AbstractAssert;

import io.sapl.api.interpreter.Val;

public class IsValError extends AbstractAssert<IsValError, Val>{

	
	 public IsValError(Val actual) {
	        super(actual, IsValError.class);
	    }
	    public static IsValError assertThatValError(Val actual) {
	        return new IsValError(actual);
	    }
	
	public IsValError isError() {
        isNotNull();

        if (!actual.isError()) {
            failWithMessage("no error");
        }

        return this;
    }
	
}
