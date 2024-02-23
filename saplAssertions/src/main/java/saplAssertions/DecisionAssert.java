package saplAssertions;

import java.util.Optional;

import org.assertj.core.api.AbstractAssert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import io.sapl.api.pdp.AuthorizationDecision;
import io.sapl.api.pdp.Decision;

public class DecisionAssert extends AbstractAssert<DecisionAssert, AuthorizationDecision>{
	
		public DecisionAssert(AuthorizationDecision actual) {
	        super(actual, DecisionAssert.class);
	    }
	 	public static DecisionAssert assertThatDecision(AuthorizationDecision actual) {
	        return new DecisionAssert(actual);
	    }
	 
	   
	    public DecisionAssert isPermit() {
	        isNotNull();

	        if (!Decision.PERMIT.equals(actual.getDecision())) {
	            failWithMessage("Permit and is", actual.getDecision());
	        }
	        return this;
	    }
	    
	    public DecisionAssert isDeny() {
	        isNotNull();

	        if (!Decision.DENY.equals(actual.getDecision())) {
	            failWithMessage("Deny and is", actual.getDecision());
	        }
	        return this;
	    }
	   
	    public DecisionAssert isNotApplicable() {
	        isNotNull();

	        if (!Decision.NOT_APPLICABLE.equals(actual.getDecision())) {
	            failWithMessage("Not Applicable and is", actual.getDecision());
	        }
	        return this;
	    }
	    
	    
	    public DecisionAssert isIndeterminate() {
	        isNotNull();

	        if (!Decision.INDETERMINATE.equals(actual.getDecision())) {
	            failWithMessage("Indeterminate and is", actual.getDecision());
	        }
	        return this;
	    }
	    public DecisionAssert isAnyDecision() {
	        isNotNull();
	        return this;
	    }
	    
	    public DecisionAssert hasObligations() {
	        isNotNull();
	     
	        Optional<ArrayNode> obligations = actual.getObligations();
	        if (!obligations.isPresent() || obligations.isEmpty()) {
	            failWithMessage("obligations is empty or not present");
	        }
	        
	        return this;
	    }
	    public DecisionAssert hasNoObligation(JsonNode expectedObligation) {
	    	hasObligations();

	        ArrayNode actualObligations = actual.getObligations().get();
	        boolean expectedObligationFound = false;

	        for (JsonNode obligation : actualObligations) {
	            if (obligation.equals(expectedObligation)) {
	                expectedObligationFound = true;
	                break;
	            }
	        }
	        if (expectedObligationFound) {
	            failWithMessage("no obligation");
	        }

	        return this;
	    }
	    
	    public DecisionAssert hasAdvice() {
	        isNotNull();
	        if (!actual.getAdvice().isPresent() || actual.getAdvice().isEmpty()) {
	            failWithMessage("Advice is empty or not present");
	        }
	        return this;
	    }
	    
	    public DecisionAssert hasResource() {
	        isNotNull();
	        if (!actual.getResource().isPresent() || actual.getResource().isEmpty()) {
	            failWithMessage("Resource is empty or not present");
	        }
	        return this;
	    }
	    

	}


