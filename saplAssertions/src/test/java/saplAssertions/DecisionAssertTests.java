package saplAssertions;

import static saplAssertions.DecisionAssert.assertThatDecision;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import io.sapl.api.pdp.AuthorizationDecision;
import io.sapl.api.pdp.Decision;

public class DecisionAssertTests {
private static final ObjectMapper mapper = new ObjectMapper();
	
	@Test
    void testPermit() {
		var sut = new AuthorizationDecision(Decision.PERMIT);
		
       assertThatDecision(sut).isPermit();
       assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.DENY));
       assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.NOT_APPLICABLE));
       assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.INDETERMINATE));
    }

    @Test
    void testDeny() {
    	var sut = new AuthorizationDecision(Decision.DENY);
    	assertThatDecision(sut).isDeny();
    	assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.PERMIT));
        assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.NOT_APPLICABLE));
        assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.INDETERMINATE));
    }

    @Test
    void testIsNotApplicable() {
    	var sut = new AuthorizationDecision(Decision.NOT_APPLICABLE);
    	assertThatDecision(sut).isNotApplicable();
    	assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.PERMIT));
        assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.DENY));
        assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.INDETERMINATE));
    }

    @Test
    void testIndeterminate() {
    	var sut = new AuthorizationDecision(Decision.INDETERMINATE);
    	assertThatDecision(sut).isIndeterminate();
    	assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.PERMIT));
        assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.DENY));
        assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.NOT_APPLICABLE));
    }

    @Test
    void testAnyDec() {
    	assertThatDecision(new AuthorizationDecision(Decision.PERMIT)).isAnyDecision();
    	assertThatDecision(new AuthorizationDecision(Decision.DENY)).isAnyDecision();
    	assertThatDecision(new AuthorizationDecision(Decision.NOT_APPLICABLE)).isAnyDecision();
    	assertThatDecision(new AuthorizationDecision(Decision.INDETERMINATE)).isAnyDecision();
    }
    @Test
    void testEmptyObligations() {
        ArrayNode obligation = mapper.createArrayNode();
        obligation.addObject().put("foo", "bar");
        AuthorizationDecision dec = new AuthorizationDecision(Decision.PERMIT).withObligations(obligation);
        assertThatDecision(dec).hasObligations();
    }
    @Test
    void testNegativeObligation() {
        ArrayNode obligation = mapper.createArrayNode();
        obligation.addObject().put("foo", "bar");
        ArrayNode expectedObligation = mapper.createArrayNode();
        expectedObligation.addObject().put("xxx", "xxx");
        AuthorizationDecision dec = new AuthorizationDecision(Decision.PERMIT).withObligations(obligation);
        assertThatDecision(dec).hasNoObligation(expectedObligation);
    }
    @Test
    void testDecisions() {
        ArrayNode obligation1 = mapper.createArrayNode();
        obligation1.add(mapper.createObjectNode().put("foo", "bar"));
        AuthorizationDecision dec1 = new AuthorizationDecision(Decision.PERMIT).withObligations(obligation1);
        ArrayNode obligation2 = mapper.createArrayNode();
        obligation2.add(mapper.createObjectNode().put("XXX", "XXX"));
        AuthorizationDecision dec2 = new AuthorizationDecision(Decision.PERMIT).withObligations(obligation2);
        assertThatDecision(dec1).isNotEqualTo(dec2);
    }
    @Test
    void testEmptyAdvice() {
        ArrayNode Advice = mapper.createArrayNode();
        Advice.addObject().put("foo", "bar");
        AuthorizationDecision dec = new AuthorizationDecision(Decision.PERMIT).withAdvice(Advice);
        assertThatDecision(dec).hasAdvice();
    }
    
    @Test
    void testEmptyResource() {
        ArrayNode Resource = mapper.createArrayNode();
        Resource.addObject().put("foo", "bar");
        AuthorizationDecision dec = new AuthorizationDecision(Decision.PERMIT).withResource(Resource);
        assertThatDecision(dec).hasResource();
    }
    

}
