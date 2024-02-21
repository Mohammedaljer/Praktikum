package io.sapl.assertj;

import static io.sapl.assertj.IsValError.assertThatValError;

import org.junit.jupiter.api.Test;

import io.sapl.api.interpreter.Val;

public class IsValErrorTest {

	 private static final String MESSAGE = "test message";
	
	 @Test
	    public void testType() {
	        Val sut = Val.error("error message");
	        assertThatValError(Val.UNDEFINED).isNotEqualTo(sut);
	    }
	    @Test
	    void testTypeFalse() {
	    	Val sut = Val.error("error message");
	        assertThatValError(sut).isError();
	    }

	    @Test
	    void testMessageTrue() {
	    	Val sut = Val.error(MESSAGE);
	    	assertThatValError(Val.error(MESSAGE)).isEqualTo(sut);
	    }

	    @Test
	    void testMessageFalse() {
	    	Val sut = Val.error(MESSAGE);
	    	assertThatValError(Val.error("X")).isNotEqualTo(sut);
	    }

	   

	}
