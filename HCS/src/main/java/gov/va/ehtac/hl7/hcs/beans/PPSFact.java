/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Duane DeCouteau
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PPSFact {
    private String resourceId;
    private String sensitivityCode;
    private String patientAuthorization;

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
     * @return the sensitivityCode
     */
    public String getSensitivityCode() {
        return sensitivityCode;
    }

    /**
     * @param sensitivityCode the sensitivityCode to set
     */
    public void setSensitivityCode(String sensitivityCode) {
        this.sensitivityCode = sensitivityCode;
    }

    /**
     * @return the patientAuthorization
     */
    public String getPatientAuthorization() {
        return patientAuthorization;
    }

    /**
     * @param patientAuthorization the patientAuthorization to set
     */
    public void setPatientAuthorization(String patientAuthorization) {
        this.patientAuthorization = patientAuthorization;
    }

}
