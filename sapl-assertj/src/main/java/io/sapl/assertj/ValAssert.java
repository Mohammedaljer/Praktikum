package io.sapl.assertj;

import org.assertj.core.api.AbstractAssert;

import io.sapl.api.interpreter.Val;
import net.javacrumbs.jsonunit.assertj.JsonAssert.ConfigurableJsonAssert;
import net.javacrumbs.jsonunit.assertj.JsonAssertions;

public class ValAssert extends AbstractAssert<ValAssert, Val>{
	
	public ValAssert(Val actual) {
        super(actual, ValAssert.class);
    }
	public static ValAssert assertThatVal(Val actual) {
        return new ValAssert(actual);
    }
    public ValAssert isError() {
        isNotNull();
        
        if (!actual.isError()) {
            failWithMessage("Val isn't an Error");
        }
        return this;
    }
    public ValAssert noError() {
        isNotNull();
        if (actual.isError()) {
            failWithMessage("Val is an Error");
        }
        return this;
    }
    public ValAssert isUndefined() {
        isNotNull();
        if (!actual.isUndefined()) {
            failWithMessage("Val is not undefined");
        }
        return this;
    }
    public ValAssert isSecret() {
        isNotNull();

        if (!actual.isSecret()) {
            failWithMessage("Val is not secret");
        }

        return this;
    }
    
    public ValAssert hasMessage(String expectedText) {
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
}
