/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.dataresults;

import ca.uhn.fhir.model.dstu2.resource.Observation;
import com.vaadin.ui.CheckBox;

/**
 *
 * @author Duane DeCouteau
 */
public class ObservationResults {
    private CheckBox selectForTransfer = new CheckBox();
    private String displayName;
    private String codeSystem;
    private String code;
    private String value;
    private String unitOfMeasure;
    private String dateObserved;
    private String status;
    private String observationType;
    private String associatedEncounter;
    private Observation payload;
    private String refLow;
    private String refHigh;

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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the observationType
     */
    public String getObservationType() {
        return observationType;
    }

    /**
     * @param observationType the observationType to set
     */
    public void setObservationType(String observationType) {
        this.observationType = observationType;
    }

    /**
     * @return the associatedEncounter
     */
    public String getAssociatedEncounter() {
        return associatedEncounter;
    }

    /**
     * @param associatedEncounter the associatedEncounter to set
     */
    public void setAssociatedEncounter(String associatedEncounter) {
        this.associatedEncounter = associatedEncounter;
    }

    /**
     * @return the payload
     */
    public Observation getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Observation payload) {
        this.payload = payload;
    }

    /**
     * @return the unitOfMeasure
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * @param unitOfMeasure the unitOfMeasure to set
     */
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the dateObserved
     */
    public String getDateObserved() {
        return dateObserved;
    }

    /**
     * @param dateObserved the dateObserved to set
     */
    public void setDateObserved(String dateObserved) {
        this.dateObserved = dateObserved;
    }

    /**
     * @return the refLow
     */
    public String getRefLow() {
        return refLow;
    }

    /**
     * @param refLow the refLow to set
     */
    public void setRefLow(String refLow) {
        this.refLow = refLow;
    }

    /**
     * @return the refHigh
     */
    public String getRefHigh() {
        return refHigh;
    }

    /**
     * @param refHigh the refHigh to set
     */
    public void setRefHigh(String refHigh) {
        this.refHigh = refHigh;
    }

    /**
     * @return the selectForTransfer
     */
    public CheckBox getSelectForTransfer() {
        return selectForTransfer;
    }

    /**
     * @param selectForTransfer the selectForTransfer to set
     */
    public void setSelectForTransfer(CheckBox selectForTransfer) {
        this.selectForTransfer = selectForTransfer;
    }
    
}
