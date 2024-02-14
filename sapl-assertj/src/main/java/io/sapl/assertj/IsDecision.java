package io.sapl.assertj;

import org.assertj.core.api.AbstractAssert;

import io.sapl.api.pdp.AuthorizationDecision;
import io.sapl.api.pdp.Decision;

public class IsDecision extends AbstractAssert<IsDecision, AuthorizationDecision> {

    public IsDecision(AuthorizationDecision actual) {
        super(actual, IsDecision.class);
    }

    public static IsDecision assertThatDecision(AuthorizationDecision actual) {
        return new IsDecision(actual);
    }
   
    public IsDecision isPermit() {
        isNotNull();

        if (!Decision.PERMIT.equals(actual.getDecision())) {
            failWithMessage("Permit and is", actual.getDecision());
        }
        return this;
    }
    
    public IsDecision isDeny() {
        isNotNull();

        if (!Decision.DENY.equals(actual.getDecision())) {
            failWithMessage("Deny and is", actual.getDecision());
        }
        return this;
    }
   
    public IsDecision isNotApplicable() {
        isNotNull();

        if (!Decision.NOT_APPLICABLE.equals(actual.getDecision())) {
            failWithMessage("Not Applicable and is", actual.getDecision());
        }
        return this;
    }
    
    
    public IsDecision isIndeterminate() {
        isNotNull();

        if (!Decision.INDETERMINATE.equals(actual.getDecision())) {
            failWithMessage("Indeterminate and is", actual.getDecision());
        }
        return this;
    }
    public IsDecision isAnyDecision() {
        isNotNull();
        return this;
    }
}
