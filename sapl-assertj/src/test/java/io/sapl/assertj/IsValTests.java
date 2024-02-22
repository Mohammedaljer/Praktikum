package io.sapl.assertj;

import static io.sapl.assertj.IsVal.assertThatVal;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.sapl.api.interpreter.Val;

class IsValTests {
	
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
    	assertThatVal(actual).isTextual().hasMessage("test value").noError();
    }
    
	@Test
	void testTypeUndefined() throws JsonProcessingException {
		var actual = Val.UNDEFINED;
		assertThatThrownBy(() -> assertThatVal(actual).hasValue().isObject().containsKey("key"))
				.isInstanceOf(AssertionError.class).hasMessage("Val is undefined");
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
        assertThatVal(actual).noError().isTextual().hasMessage("XXX");
    }

    @Test
    void testInt() {
    	Val actual = Val.of(1);
        assertThatVal(actual).hasIntValue().isEqualTo(1);
       
    }
    @Test
   	void jsonPostive() throws JsonProcessingException {
   		var actual = Val.ofJson("{\"key\":\"value\" }");
   		assertDoesNotThrow(() -> assertThatVal(actual).hasValue().isObject().containsKey("key"));
   	}
    
/*
 * 
	
    @Test
    void testLong() {
    	JsonNode node = mapper.valueToTree(2L);
    	Val sut = Val.of(node);
    	JsonAssertions.assertThatJson(sut.getJsonNode()).isEqualTo(2L);
    }

    @Test
    void testDouble() {
    	JsonNode node = mapper.valueToTree(2.0D);
    	Val sut = Val.of(node);
    	JsonAssertions.assertThatJson(sut.getJsonNode()).isEqualTo(2.0D);
        
    }

    @Test
    void testFloat() {
    	JsonNode node = mapper.valueToTree(3.0F);
    	Val sut = Val.of(node);
    	JsonAssertions.assertThatJson(sut.getJsonNode()).isEqualTo(3.0F);
        
    }

    @Test
    void testBigDecimal() {
    	JsonNode node = mapper.valueToTree(new BigDecimal("3.12"));
        Val sut = Val.of(node);
        JsonAssertions.assertThatJson(sut.getJsonNode()).isEqualTo(new BigDecimal("3.12"));
        
    }

    @Test
    void testBigInteger() {
    	JsonNode node = mapper.valueToTree(new BigInteger("3"));
        Val sut = Val.of(node);
        JsonAssertions.assertThatJson(sut.getJsonNode()).isEqualTo(new BigInteger("3"));
     
    }

    @Test
    void testNull() {
    	Val sut = IsVal.valNull();
    	IsVal.assertThatVal(sut).isEqualTo(Val.NULL);
    }

  */
   
}
