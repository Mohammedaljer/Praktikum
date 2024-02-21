package io.sapl.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.sapl.api.interpreter.Val;

public class IsValUndefinedTest {
	@Test
    void testTypeFalseError() {
        Val sut = Val.UNDEFINED;
        IsVal.assertThatVal(Val.error()).isNotEqualTo(sut);
    }

	 @Test
	    void testTypeFalseValue() {
	        Val sut = Val.UNDEFINED;
	        assertThat(sut).isNotEqualTo(Val.TRUE);
	    }
	 @Test
	 void testType() {
	     Val sut = Val.UNDEFINED;
	     assertThat(sut.isUndefined());
	 }
    
    
}