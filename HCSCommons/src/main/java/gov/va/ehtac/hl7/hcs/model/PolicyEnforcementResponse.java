/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Socraticgrid Staff
 *  
 **/

public class PolicyEnforcementResponse {
    private String decision;
    private List<String> obligationsOrAdvice = new ArrayList();

    /**
     * @return the decision
     */
    
    public PolicyEnforcementResponse(String decision, List<String> obligationsOrAdvice) {
        this.decision = decision;
        this.obligationsOrAdvice = obligationsOrAdvice;
        
    }
    
    public PolicyEnforcementResponse() {
        
    }
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
     * @return the obligationsOrAdvice
     */
    public List<String> getObligationsOrAdvice() {
        return obligationsOrAdvice;
    }

    /**
     * @param obligationsOrAdvice the obligationsOrAdvice to set
     */
    public void setObligationsOrAdvice(List<String> obligationsOrAdvice) {
        this.obligationsOrAdvice = obligationsOrAdvice;
    }
    
    
    
}
