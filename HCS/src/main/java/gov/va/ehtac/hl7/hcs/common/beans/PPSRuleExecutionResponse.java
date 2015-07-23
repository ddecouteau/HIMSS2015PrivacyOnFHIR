/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.common.beans;

/**
 *
 * @author Socraticgrid Staff
 */
public class PPSRuleExecutionResponse {
    private String resourceId;
    private String ppsAction;
    
    public PPSRuleExecutionResponse(){
        
    }
    
    public PPSRuleExecutionResponse(String ppsAction, String resourceId) {
        this.resourceId = resourceId;
        this.ppsAction = ppsAction;
    }

    /**
     * @return the resourceId
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId the resourceId to set
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return the ppsAction
     */
    public String getPpsAction() {
        return ppsAction;
    }

    /**
     * @param ppsAction the ppsAction to set
     */
    public void setPpsAction(String ppsAction) {
        this.ppsAction = ppsAction;
    }
    
}
