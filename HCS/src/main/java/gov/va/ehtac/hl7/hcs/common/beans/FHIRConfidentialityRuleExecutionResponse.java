/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.common.beans;

/**
 *
 * @author Socraticgrid Staff
 */
public class FHIRConfidentialityRuleExecutionResponse {
    private String code;
    private String codeSystem;
    private String displayName;
    private String sensitivity;
    private String impliedConfidentiality;
    private String confidentialityLabel;
    private String resourceId;
    
    public FHIRConfidentialityRuleExecutionResponse(){
        
    }
    
    public FHIRConfidentialityRuleExecutionResponse(String code, String codeSystem, String displayName, String sensitivity, String impliedConfidentiality, String confidentialityLabel, String resourceId) {
        this.code = code;
        this.codeSystem = codeSystem;
        this.confidentialityLabel = confidentialityLabel;
        this.impliedConfidentiality = impliedConfidentiality;
        this.displayName = displayName;
        this.sensitivity = sensitivity;
        this.resourceId = resourceId;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the codeSystem
     */
    public String getCodeSystem() {
        return codeSystem;
    }

    /**
     * @param codeSystem the codeSystem to set
     */
    public void setCodeSystem(String codeSystem) {
        this.codeSystem = codeSystem;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the sensitivity
     */
    public String getSensitivity() {
        return sensitivity;
    }

    /**
     * @param sensitivity the sensitivity to set
     */
    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }

    /**
     * @return the impliedConfidentiality
     */
    public String getImpliedConfidentiality() {
        return impliedConfidentiality;
    }

    /**
     * @param impliedConfidentiality the impliedConfidentiality to set
     */
    public void setImpliedConfidentiality(String impliedConfidentiality) {
        this.impliedConfidentiality = impliedConfidentiality;
    }

    /**
     * @return the confidentialityLabel
     */
    public String getConfidentialityLabel() {
        return confidentialityLabel;
    }

    /**
     * @param confidentialityLabel the confidentialityLabel to set
     */
    public void setConfidentialityLabel(String confidentialityLabel) {
        this.confidentialityLabel = confidentialityLabel;
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
    
}
