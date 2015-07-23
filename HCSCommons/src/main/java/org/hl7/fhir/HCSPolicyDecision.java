/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Socraticgrid Staff
 */
public class HCSPolicyDecision {
    private String decision;
    private List<String> adviceOrObligation = new ArrayList();;
    
    public HCSPolicyDecision() {
        
    }

    /**
     * @return the decision
     */
    public String getDecision() {
        return decision;
    }

    /**
     * @param decision the decision to set
     */
    public void setDecision(String decision) {
        this.decision = decision;
    }

    /**
     * @return the adviceOrObligation
     */
    public List<String> getAdviceOrObligation() {
        return adviceOrObligation;
    }

    /**
     * @param adviceOrObligation the adviceOrObligation to set
     */
    public void setAdviceOrObligation(List<String> adviceOrObligation) {
        this.adviceOrObligation = adviceOrObligation;
    }
}
