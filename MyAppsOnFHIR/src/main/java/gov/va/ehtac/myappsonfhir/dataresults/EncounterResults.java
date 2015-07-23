/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.dataresults;

import ca.uhn.fhir.model.dstu2.resource.Encounter;
import com.vaadin.ui.CheckBox;

/**
 *
 * @author Duane DeCouteau
 */
public class EncounterResults {
    private CheckBox selectForTransfer = new CheckBox();
    private String startDate;
    private String endDate;
    private String encounterType;
    private String displayName;
    private String status;
    private Encounter payload;
    private String sls;

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the encounterType
     */
    public String getEncounterType() {
        return encounterType;
    }

    /**
     * @param encounterType the encounterType to set
     */
    public void setEncounterType(String encounterType) {
        this.encounterType = encounterType;
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
     * @return the payload
     */
    public Encounter getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Encounter payload) {
        this.payload = payload;
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

    /**
     * @return the sls
     */
    public String getSls() {
        return sls;
    }

    /**
     * @param sls the sls to set
     */
    public void setSls(String sls) {
        this.sls = sls;
    }
}
