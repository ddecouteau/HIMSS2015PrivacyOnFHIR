/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.services;

/**
 *
 * @author Socraticgrid Staff
 */
public class SecurityLabelingFHIRConstants {
    //security policies
    private String SENSITIVITY_POLICY_URL = "http://hl7.org/implement/standards/fhir/v3/ActCode/ActInformationSensitivityPolicy/";
    private String ENTITY_SENSITIVITY_URL = "http://hl7.org/implement/standards/fhir/v3/ActCode/EntitySensitivityPolicyType/";
    private String ROLE_SENSITIVITY_URL = "http://hl7.org/implement/standards/fhir/v3/ActCode/RoleSensitivityPolicy/";
    
    private String CONSENT_DIRECTIVE_URL = "http://hl7.org/implement/standards/fhir/v3/ActCode/ActConsentDirective";
    private String SECURITY_INTEGRITY_URL = "http://hl7.org/implement/standards/fhir/v3/ObservationValue/SECINTCONOBV/";
    //not implemented in v1.0
    private String SECURITY_DIG_SIG_URL = "http://hl7.org/implement/standards/fhir/v3/ObservationValue/SECDATINTOBV/";
    private String MASKED_URL = "http://hl7.org/implement/standards/fhir/v3/ObservationValue/SECLINTOBVV/";
    
    private String PRV_PATIENT_URL = "http://hl7.org/implement/standards/fhir/v3/ObservationValue/SECINTPRVABOBV/";
    private String PRV_PROVIDER_URL = "http://hl7.org/implement/standards/fhir/v3/ObservationValue/";
    private String PURPOSE_OF_USE_URL = "http://hl7.org/implement/standards/fhir/v3/ActReason/";
    private String OBLIGATION_URL = "http://hl7.org/implement/standards/fhir/v3/ActCode/ObligationPolicy/";
    private String REFRAIN_POLICY_URL = "http://hl7.org/implement/standards/fhir/v3/ActCode/RefrainPolicy/";
    
    private String ACTREASON_PAGE = "http://hl7.org/implement/standards/fhir/v3/ActReason/index.htm";
    private String ACTCODE_PAGE = "http://hl7.org/implement/standards/fhir/v3/ActCode/index.htm";
    private String OBSERVATION_VALUE_PAGE = "http://hl7.org/implement/standards/fhir/v3/ObservationValue/";
    private String CONFIDENTIALITY_PAGE = "http://hl7.org/implement/standards/fhir/v3/Confidentiality/index.htm";

    /**
     * @return the SENSITIVITY_POLICY_URL
     */
    public String getSENSITIVITY_POLICY_URL() {
        return SENSITIVITY_POLICY_URL;
    }

    /**
     * @param SENSITIVITY_POLICY_URL the SENSITIVITY_POLICY_URL to set
     */
    public void setSENSITIVITY_POLICY_URL(String SENSITIVITY_POLICY_URL) {
        this.SENSITIVITY_POLICY_URL = SENSITIVITY_POLICY_URL;
    }

    /**
     * @return the ENTITY_SENSITIVITY_URL
     */
    public String getENTITY_SENSITIVITY_URL() {
        return ENTITY_SENSITIVITY_URL;
    }

    /**
     * @param ENTITY_SENSITIVITY_URL the ENTITY_SENSITIVITY_URL to set
     */
    public void setENTITY_SENSITIVITY_URL(String ENTITY_SENSITIVITY_URL) {
        this.ENTITY_SENSITIVITY_URL = ENTITY_SENSITIVITY_URL;
    }

    /**
     * @return the ROLE_SENSITIVITY_URL
     */
    public String getROLE_SENSITIVITY_URL() {
        return ROLE_SENSITIVITY_URL;
    }

    /**
     * @param ROLE_SENSITIVITY_URL the ROLE_SENSITIVITY_URL to set
     */
    public void setROLE_SENSITIVITY_URL(String ROLE_SENSITIVITY_URL) {
        this.ROLE_SENSITIVITY_URL = ROLE_SENSITIVITY_URL;
    }

    /**
     * @return the CONSENT_DIRECTIVE_URL
     */
    public String getCONSENT_DIRECTIVE_URL() {
        return CONSENT_DIRECTIVE_URL;
    }

    /**
     * @param CONSENT_DIRECTIVE_URL the CONSENT_DIRECTIVE_URL to set
     */
    public void setCONSENT_DIRECTIVE_URL(String CONSENT_DIRECTIVE_URL) {
        this.CONSENT_DIRECTIVE_URL = CONSENT_DIRECTIVE_URL;
    }

    /**
     * @return the SECURITY_INTEGRITY_URL
     */
    public String getSECURITY_INTEGRITY_URL() {
        return SECURITY_INTEGRITY_URL;
    }

    /**
     * @param SECURITY_INTEGRITY_URL the SECURITY_INTEGRITY_URL to set
     */
    public void setSECURITY_INTEGRITY_URL(String SECURITY_INTEGRITY_URL) {
        this.SECURITY_INTEGRITY_URL = SECURITY_INTEGRITY_URL;
    }

    /**
     * @return the SECURITY_DIG_SIG_URL
     */
    public String getSECURITY_DIG_SIG_URL() {
        return SECURITY_DIG_SIG_URL;
    }

    /**
     * @param SECURITY_DIG_SIG_URL the SECURITY_DIG_SIG_URL to set
     */
    public void setSECURITY_DIG_SIG_URL(String SECURITY_DIG_SIG_URL) {
        this.SECURITY_DIG_SIG_URL = SECURITY_DIG_SIG_URL;
    }

    /**
     * @return the MASKED_URL
     */
    public String getMASKED_URL() {
        return MASKED_URL;
    }

    /**
     * @param MASKED_URL the MASKED_URL to set
     */
    public void setMASKED_URL(String MASKED_URL) {
        this.MASKED_URL = MASKED_URL;
    }

    /**
     * @return the PRV_PATIENT_URL
     */
    public String getPRV_PATIENT_URL() {
        return PRV_PATIENT_URL;
    }

    /**
     * @param PRV_PATIENT_URL the PRV_PATIENT_URL to set
     */
    public void setPRV_PATIENT_URL(String PRV_PATIENT_URL) {
        this.PRV_PATIENT_URL = PRV_PATIENT_URL;
    }

    /**
     * @return the PRV_PROVIDER_URL
     */
    public String getPRV_PROVIDER_URL() {
        return PRV_PROVIDER_URL;
    }

    /**
     * @param PRV_PROVIDER_URL the PRV_PROVIDER_URL to set
     */
    public void setPRV_PROVIDER_URL(String PRV_PROVIDER_URL) {
        this.PRV_PROVIDER_URL = PRV_PROVIDER_URL;
    }

    /**
     * @return the PURPOSE_OF_USE_URL
     */
    public String getPURPOSE_OF_USE_URL() {
        return PURPOSE_OF_USE_URL;
    }

    /**
     * @param PURPOSE_OF_USE_URL the PURPOSE_OF_USE_URL to set
     */
    public void setPURPOSE_OF_USE_URL(String PURPOSE_OF_USE_URL) {
        this.PURPOSE_OF_USE_URL = PURPOSE_OF_USE_URL;
    }

    /**
     * @return the OBLIGATION_URL
     */
    public String getOBLIGATION_URL() {
        return OBLIGATION_URL;
    }

    /**
     * @param OBLIGATION_URL the OBLIGATION_URL to set
     */
    public void setOBLIGATION_URL(String OBLIGATION_URL) {
        this.OBLIGATION_URL = OBLIGATION_URL;
    }

    /**
     * @return the REFRAIN_POLICY_URL
     */
    public String getREFRAIN_POLICY_URL() {
        return REFRAIN_POLICY_URL;
    }

    /**
     * @param REFRAIN_POLICY_URL the REFRAIN_POLICY_URL to set
     */
    public void setREFRAIN_POLICY_URL(String REFRAIN_POLICY_URL) {
        this.REFRAIN_POLICY_URL = REFRAIN_POLICY_URL;
    }

    /**
     * @return the ACTREASON_PAGE
     */
    public String getACTREASON_PAGE() {
        return ACTREASON_PAGE;
    }

    /**
     * @param ACTREASON_PAGE the ACTREASON_PAGE to set
     */
    public void setACTREASON_PAGE(String ACTREASON_PAGE) {
        this.ACTREASON_PAGE = ACTREASON_PAGE;
    }

    /**
     * @return the ACTCODE_PAGE
     */
    public String getACTCODE_PAGE() {
        return ACTCODE_PAGE;
    }

    /**
     * @param ACTCODE_PAGE the ACTCODE_PAGE to set
     */
    public void setACTCODE_PAGE(String ACTCODE_PAGE) {
        this.ACTCODE_PAGE = ACTCODE_PAGE;
    }

    /**
     * @return the OBSERVATION_VALUE_PAGE
     */
    public String getOBSERVATION_VALUE_PAGE() {
        return OBSERVATION_VALUE_PAGE;
    }

    /**
     * @param OBSERVATION_VALUE_PAGE the OBSERVATION_VALUE_PAGE to set
     */
    public void setOBSERVATION_VALUE_PAGE(String OBSERVATION_VALUE_PAGE) {
        this.OBSERVATION_VALUE_PAGE = OBSERVATION_VALUE_PAGE;
    }
        
    
}
