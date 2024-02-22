package io.sapl.assertj;

import org.assertj.core.api.AbstractAssert;

import io.sapl.api.interpreter.Val;
import net.javacrumbs.jsonunit.assertj.JsonAssert.ConfigurableJsonAssert;
import net.javacrumbs.jsonunit.assertj.JsonAssertions;


public class IsVal extends AbstractAssert<IsVal, Val> {

	    public IsVal(Val actual) {
	        super(actual, IsVal.class);
	    }
	    public static IsVal assertThatVal(Val actual) {
	        return new IsVal(actual);
	    }
	    
	    public IsVal isError() {
	        isNotNull();
	        
	        if (!actual.isError()) {
	            failWithMessage("Val isn't an Error");
	        }
	        return this;
	    }
	    public IsVal noError() {
	        isNotNull();
	        if (actual.isError()) {
	            failWithMessage("Val is an Error");
	        }
	        return this;
	    }
	    public IsVal isTextual() {
	        isNotNull();
	        if (!actual.isTextual()) {
	            failWithMessage("Expected Val to be textual, but was not");
	        }
	        return this;
	    }
	    public IsVal hasMessage(String expectedText) {
	        isNotNull();
	        if (!actual.getText().equals(expectedText)) {
	            failWithMessage("Val's text has to be <%s> but was <%s>", expectedText, actual.getText());
	        }
	        return this;
	    }
	   
	   
	    public ConfigurableJsonAssert hasValue() {
	        isNotNull();

	        if (actual.isUndefined()) {
	            failWithMessage("Val is undefined");
	        }
	        return JsonAssertions.assertThatJson(actual.get());
	    } 
	    
	    public ConfigurableJsonAssert isTrue() {
	        isNotNull();

	        if (!actual.getBoolean()) {
	            failWithMessage("Val is not True");
	        }
	       return JsonAssertions.assertThatJson(actual.getJsonNode());
	    } 
	    public ConfigurableJsonAssert hasIntValue() {
	        isNotNull();

	        if (!actual.isDefined() || !actual.getJsonNode().isInt()) {
	            failWithMessage("Val isn't an Int");
	        }
	        return JsonAssertions.assertThatJson(actual.get());
	    }
	
	    
	}
