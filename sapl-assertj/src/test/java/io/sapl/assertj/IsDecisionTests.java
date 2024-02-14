package io.sapl.assertj;


import org.junit.jupiter.api.Test;

import io.sapl.api.pdp.AuthorizationDecision;
import io.sapl.api.pdp.Decision;

class IsDecisionTests {

	@Test
    void testPermit() {
		var sut = new AuthorizationDecision(Decision.PERMIT);
		
        IsDecision.assertThatDecision(sut).isPermit();
        IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.DENY));
        IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.NOT_APPLICABLE));
        IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.INDETERMINATE));
    }

    @Test
    void testDeny() {
    	var sut = new AuthorizationDecision(Decision.DENY);
    	IsDecision.assertThatDecision(sut).isDeny();
    	IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.PERMIT));
        IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.NOT_APPLICABLE));
        IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.INDETERMINATE));
    }

    @Test
    void testIsNotApplicable() {
    	var sut = new AuthorizationDecision(Decision.NOT_APPLICABLE);
    	IsDecision.assertThatDecision(sut).isNotApplicable();
    	IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.PERMIT));
        IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.DENY));
        IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.INDETERMINATE));
    }

    @Test
    void testIndeterminate() {
    	var sut = new AuthorizationDecision(Decision.INDETERMINATE);
    	IsDecision.assertThatDecision(sut).isIndeterminate();
    	IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.PERMIT));
        IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.DENY));
        IsDecision.assertThatDecision(sut).isNotEqualTo(new AuthorizationDecision(Decision.NOT_APPLICABLE));
    }

    @Test
    void testAnyDec() {
    	IsDecision.assertThatDecision(new AuthorizationDecision(Decision.PERMIT)).isAnyDecision();
    	IsDecision.assertThatDecision(new AuthorizationDecision(Decision.DENY)).isAnyDecision();
    	IsDecision.assertThatDecision(new AuthorizationDecision(Decision.NOT_APPLICABLE)).isAnyDecision();
    	IsDecision.assertThatDecision(new AuthorizationDecision(Decision.INDETERMINATE)).isAnyDecision();
    }

}
