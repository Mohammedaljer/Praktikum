package io.sapl.assertj;

import static io.sapl.assertj.IsVal.val;
import static io.sapl.assertj.IsVal.valFalse;
import static io.sapl.assertj.IsVal.valTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import io.sapl.api.interpreter.Val;

class IsValTests {
	
    private static final Val VALUE = Val.of("test value");

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
    	Val sut = Val.of("XXX");
        assertThat(sut.getJsonNode().asText()).isEqualTo("XXX");
    }

    @Test
    void testInt() {
    	Val sut = Val.of(JsonNodeFactory.instance.numberNode(1));
       assertThat(sut.getJsonNode().asInt()).isEqualTo(1);
    }
    

    @Test
    void testLong() {
    	Val sut = Val.of(JsonNodeFactory.instance.numberNode(2L));
        assertThat(sut.getJsonNode().asLong()).isEqualTo(2L);
    }

    @Test
    void testDouble() {
    	Val sut = Val.of(JsonNodeFactory.instance.numberNode(2.0D));
        assertThat(sut.getJsonNode().asDouble()).isEqualTo(2.0D);
        
    }

    @Test
    void testFloat() {
    	Val sut = Val.of(JsonNodeFactory.instance.numberNode(3.0F));
        assertThat(sut.getJsonNode().floatValue()).isEqualTo(3.0F);
        
    }

    @Test
    void testBigDecimal() {
    	Val sut = Val.of(JsonNodeFactory.instance.numberNode(3.12D));
        assertThat(sut.getJsonNode().decimalValue()).isEqualTo(BigDecimal.valueOf(3.12D));
        
    }

    @Test
    void testBigInteger() {
    	Val sut = Val.of(JsonNodeFactory.instance.numberNode(3L));
       assertThat(sut.getJsonNode().bigIntegerValue()).isEqualTo(BigInteger.valueOf(3L));
     
    }

    @Test
    void testNull() {
    	Val sut = IsVal.valNull();
    	IsVal.assertThatVal(sut).isEqualTo(Val.NULL);
    }

  
   
}
