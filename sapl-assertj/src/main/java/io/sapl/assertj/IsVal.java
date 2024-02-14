package io.sapl.assertj;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assertions;

import io.sapl.api.interpreter.Val;


public class IsVal extends AbstractAssert<IsVal, Val> {

	    protected IsVal(Val actual) {
	        super(actual, IsVal.class);
	    }
	    public static IsVal assertThatVal(Val actual) {
	        return new IsVal(actual);
	    }
	    
	    public AbstractStringAssert<?> withMessage() {
	        isNotNull();
	        
	        if (!actual.isError()) {
	            failWithMessage("Val isn't an Error");
	        }
	        return Assertions.assertThat(actual.getMessage());
	   
	   }
	    
	    public IsVal isError() {
	        isNotNull();
	        
	        if (!actual.isError()) {
	            failWithMessage("Val isn't an Error");
	        }
	        return this;
	    }
	    
	    public IsVal isUndefined() {
	        isNotNull();

	        if (actual.isUndefined()) {
	            failWithMessage("Val is undefined");
	        }
	        return this;
	    } 
	    public static Val val() {
	        return Val.of("test value");
	    }
	    public static Val valTrue() {
	        return Val.TRUE;
	    }
	    public static Val valFalse() {
	        return Val.FALSE;
	    }
	    public static Val val(String text) {
	        return Val.of(text);
	    }
	    public static Val val(int x) {
	        return Val.of(x);
	    }
	    public static Val valNull() {
	        return Val.NULL;
	    }

	}
