/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.dataresults;

import ca.uhn.fhir.model.dstu2.resource.Condition;
import com.vaadin.ui.CheckBox;

/**
 *
 * @author Duane DeCouteau
 */
public class ConditionResults {
    private CheckBox selectForTransfer = new CheckBox();
    private String displayName;
    private String status;
    private String onsetDate;
    private Condition payload;
    private String sls;

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
     * @return the onsetData
     */
    public String getOnsetDate() {
        return onsetDate;
    }

    /**
     * @param onsetData the onsetData to set
     */
    public void setOnsetDate(String onsetDate) {
        this.onsetDate = onsetDate;
    }

    /**
     * @return the payload
     */
    public Condition getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Condition payload) {
        this.payload = payload;
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
