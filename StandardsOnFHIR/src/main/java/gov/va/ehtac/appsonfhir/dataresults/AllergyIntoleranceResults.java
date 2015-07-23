/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.dataresults;

import ca.uhn.fhir.model.dstu2.resource.AllergyIntolerance;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.CheckBox;

/**
 *
 * @author Duane DeCouteau
 */
public class AllergyIntoleranceResults {
    private CheckBox selectForTransfer = new CheckBox();
    private Label allergyName = new Label();
    private String recordedDate;
    private String criticality;
    private String status;
    private AllergyIntolerance payload;


    /**
     * @return the allergyName
     */
    public Label getAllergyName() {
        allergyName.setContentMode(ContentMode.HTML);
        return allergyName;
    }

    /**
     * @param allergyName the allergyName to set
     */
    public void setAllergyName(Label allergyName) {
        this.allergyName = allergyName;
    }

    /**
     * @return the recordedDate
     */
    public String getRecordedDate() {
        return recordedDate;
    }

    /**
     * @param recordedDate the recordedDate to set
     */
    public void setRecordedDate(String recordedDate) {
        this.recordedDate = recordedDate;
    }

    /**
     * @return the criticality
     */
    public String getCriticality() {
        return criticality;
    }

    /**
     * @param criticality the criticality to set
     */
    public void setCriticality(String criticality) {
        this.criticality = criticality;
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
     * @return the payload
     */
    public AllergyIntolerance getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(AllergyIntolerance payload) {
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
    
}
