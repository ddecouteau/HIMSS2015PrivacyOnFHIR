/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.session;

import ca.uhn.fhir.context.FhirContext;
import gov.va.ehtac.myappsonfhir.dataresults.PoFPatient;

/**
 *
 * @author Duane DeCouteau
 */
public class SessionAttributes {
    private String baseURL = "http://tricare.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/";
    private String patientNameGenderDisplay = "SamplePatient, Alice female 1959-02-28";
    private String patientId = "ONC-VA-POF-P-001";
    private PoFPatient pofPatient;
    private FhirContext context = FhirContext.forDstu2();
    

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
     * @return the patientNameGenderDisplay
     */
    public String getPatientNameGenderDisplay() {
        return patientNameGenderDisplay;
    }

    /**
     * @param patientNameGenderDisplay the patientNameGenderDisplay to set
     */
    public void setPatientNameGenderDisplay(String patientNameGenderDisplay) {
        this.patientNameGenderDisplay = patientNameGenderDisplay;
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
    
}
