/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.session;

import ca.uhn.fhir.context.FhirContext;
import gov.va.ehtac.appsonfhir.dataresults.PoFPatient;

/**
 *
 * @author Duane DeCouteau
 */
public class SessionAttributes {
    //local smart-api
    //private String baseURL = "http://192.168.1.149:8080/";
    private String baseURL = "http://tricare.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/";
    private String transmitURL;
    private String patientId = "1134281"; //"1482713"; //"1134281";
    private String patientNameAgeGenderDisplay = "No Patient Selected";
    private String getConnectedServer = "Tricare";
    private String userId = "drbob";
    private String providerId = "drbob@tricare.edmondsci.com";
    private String purposeOfUse = "TREAT";
    private String securityLevel = "V";
    private PoFPatient pofPatient;
    private FhirContext context = FhirContext.forDstu2();
    private String hcsEncounters;
    private String hcsAllergies;
    private String hcsConditions;
    private String hcsMedications;
    private String hcsObservations;
    private String hcsImmunizations;

    /**
     * @return the baseURL
     */
    public String getBaseURL() {
        return baseURL;
    }

    /**
     * @param baseURL the baseURL to set
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * @return the patientId
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the patientNameAgeGenderDisplay
     */
    public String getPatientNameAgeGenderDisplay() {
        return patientNameAgeGenderDisplay;
    }

    /**
     * @param patientNameAgeGenderDisplay the patientNameAgeGenderDisplay to set
     */
    public void setPatientNameAgeGenderDisplay(String patientNameAgeGenderDisplay) {
        this.patientNameAgeGenderDisplay = patientNameAgeGenderDisplay;
    }

    /**
     * @return the getConnectedServer
     */
    public String getGetConnectedServer() {
        return getConnectedServer;
    }

    /**
     * @param getConnectedServer the getConnectedServer to set
     */
    public void setGetConnectedServer(String getConnectedServer) {
        this.getConnectedServer = getConnectedServer;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    /**
     * @return the purposeOfUse
     */
    public String getPurposeOfUse() {
        return purposeOfUse;
    }

    /**
     * @param purposeOfUse the purposeOfUse to set
     */
    public void setPurposeOfUse(String purposeOfUse) {
        this.purposeOfUse = purposeOfUse;
    }

    /**
     * @return the securityLevel
     */
    public String getSecurityLevel() {
        return securityLevel;
    }

    /**
     * @param securityLevel the securityLevel to set
     */
    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    /**
     * @return the pofPatient
     */
    public PoFPatient getPofPatient() {
        return pofPatient;
    }

    /**
     * @param pofPatient the pofPatient to set
     */
    public void setPofPatient(PoFPatient pofPatient) {
        this.pofPatient = pofPatient;
    }

    /**
     * @return the context
     */
    public FhirContext getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(FhirContext context) {
        this.context = context;
    }

    /**
     * @return the hcsEncounters
     */
    public String getHcsEncounters() {
        return hcsEncounters;
    }

    /**
     * @param hcsEncounters the hcsEncounters to set
     */
    public void setHcsEncounters(String hcsEncounters) {
        this.hcsEncounters = hcsEncounters;
    }

    /**
     * @return the hcsAllergies
     */
    public String getHcsAllergies() {
        return hcsAllergies;
    }

    /**
     * @param hcsAllergies the hcsAllergies to set
     */
    public void setHcsAllergies(String hcsAllergies) {
        this.hcsAllergies = hcsAllergies;
    }

    /**
     * @return the hcsConditions
     */
    public String getHcsConditions() {
        return hcsConditions;
    }

    /**
     * @param hcsConditions the hcsConditions to set
     */
    public void setHcsConditions(String hcsConditions) {
        this.hcsConditions = hcsConditions;
    }

    /**
     * @return the hcsMedications
     */
    public String getHcsMedications() {
        return hcsMedications;
    }

    /**
     * @param hcsMedications the hcsMedications to set
     */
    public void setHcsMedications(String hcsMedications) {
        this.hcsMedications = hcsMedications;
    }

    /**
     * @return the hcsObservations
     */
    public String getHcsObservations() {
        return hcsObservations;
    }

    /**
     * @param hcsObservations the hcsObservations to set
     */
    public void setHcsObservations(String hcsObservations) {
        this.hcsObservations = hcsObservations;
    }

    /**
     * @return the hcsImmunizations
     */
    public String getHcsImmunizations() {
        return hcsImmunizations;
    }

    /**
     * @param hcsImmunizations the hcsImmunizations to set
     */
    public void setHcsImmunizations(String hcsImmunizations) {
        this.hcsImmunizations = hcsImmunizations;
    }

    /**
     * @return the transmitURL
     */
    public String getTransmitURL() {
        return transmitURL;
    }

    /**
     * @param transmitURL the transmitURL to set
     */
    public void setTransmitURL(String transmitURL) {
        this.transmitURL = transmitURL;
    }

}
