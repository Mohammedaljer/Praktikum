package saplAssertions;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static saplAssertions.ValAssert.assertThatVal;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.sapl.api.interpreter.Val;

public class ValAssertTests {
	private static final Val VALUE = Val.of("test value");
    
	  
    @Test
    void testTypeError() {
        var actual = Val.error();
        assertThatVal(actual).isError();
   
    }
    @Test
    void testNoError() throws JsonProcessingException{
        var actual = Val.error();
        assertThatThrownBy(() -> assertThatVal(actual).noError())
            .isInstanceOf(AssertionError.class)
            .hasMessageContaining("Val is an Error");
    }
    
    @Test
    void testType() {
    	var actual = VALUE;
    	assertThatVal(actual).hasMessage("test value").noError();
    }
    
    @Test
    public void testUndefined() {
    	var actual = Val.UNDEFINED;
        assertThatVal(actual).isUndefined(); 
    }
    
	@Test
	void testTypeUndefined() throws JsonProcessingException {
		var actual = Val.UNDEFINED;
		assertThatThrownBy(() -> assertThatVal(actual).hasValue().isObject().containsKey("key"))
				.isInstanceOf(AssertionError.class).hasMessage("Val is undefined");
	}
	 @Test
	    public void valShouldBeMarkedAsSecret() {
	        var actual = VALUE.asSecret();
	        assertThatVal(actual).isSecret();
	    }
    @Test
    void testTrue() {
    	var actual = Val.TRUE;
    	assertThatVal(actual).isTrue();
    }
    
    @Test
    void testFalse() throws JsonProcessingException {
        var actual = Val.FALSE;
        assertThatThrownBy(() -> assertThatVal(actual).isTrue())
            .isInstanceOf(AssertionError.class)
            .hasMessageContaining("Val is not True");
    }
    
    @Test
    void testText() {
    	var actual = Val.of("XXX");
        assertThatVal(actual).noError().hasMessage("XXX");
    }

    @Test
    void testInt()throws JsonProcessingException {
    	var actual = Val.of(1);
    	assertThatVal(actual).hasValue().isEqualTo(1);
       
    }
    @Test
   	void jsonPostive() throws JsonProcessingException {
   		var actual = Val.ofJson("{\"key\":\"value\" }");
   		assertDoesNotThrow(() -> assertThatVal(actual).hasValue().isObject().containsKey("key"));
   	}

}
