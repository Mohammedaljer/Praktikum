package io.sapl.assertj;

import static io.sapl.assertj.IsVal.val;
import static io.sapl.assertj.IsVal.valFalse;
import static io.sapl.assertj.IsVal.valTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.sapl.api.interpreter.Val;
import net.javacrumbs.jsonunit.assertj.JsonAssertions;

class IsValTests {
	
    private static final Val VALUE = Val.of("test value");
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testTypeError() {
    	 Val sut = val();
    	 IsVal.assertThatVal(sut).isNotEqualTo(Val.error());
    }
    @Test
    void testTypeUndefined() {
    	 Val sut = val();
    	 IsVal.assertThatVal(sut).isNotEqualTo(Val.UNDEFINED);
    }


    @Test
    void testType() {
    	var sut = val();
    	IsVal.assertThatVal(VALUE).isEqualTo(sut);
    }

    @Test
    void testTrue() {
    	var sut = valTrue();
    	IsVal.assertThatVal(sut).isEqualTo(Val.TRUE);
    }

    @Test
    void testFalse() {
    	var sut = valFalse();
    	IsVal.assertThatVal(sut).isEqualTo(Val.FALSE);
    }

    @Test
    void testText() {
    	JsonNode node = mapper.valueToTree("XXX");
        Val sut = Val.of(node);
        JsonAssertions.assertThatJson(sut.getJsonNode()).isEqualTo("XXX");
    }

    @Test
    void testInt() {
    	JsonNode node = mapper.valueToTree(1);
    	Val sut = Val.of(node);
    	JsonAssertions.assertThatJson(sut.getJsonNode()).isEqualTo(1);
    }
    

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

  
   
}
