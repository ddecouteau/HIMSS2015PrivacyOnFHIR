/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.dataresults;

import ca.uhn.fhir.model.dstu2.resource.MedicationPrescription;
import com.vaadin.ui.CheckBox;

/**
 *
 * @author Duane DeCouteau
 */
public class MedicationPrescriptionResults {
    private CheckBox selectForTransfer = new CheckBox();
    private String displayName;
    private String status;
    private String startDate;
    private String quantityDispensed;
    private String refills;
    private String instructions;
    private MedicationPrescription payload;
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
     * @return the payload
     */
    public MedicationPrescription getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(MedicationPrescription payload) {
        this.payload = payload;
    }

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
     * @return the quantityDispensed
     */
    public String getQuantityDispensed() {
        return quantityDispensed;
    }

    /**
     * @param quantityDispensed the quantityDispensed to set
     */
    public void setQuantityDispensed(String quantityDispensed) {
        this.quantityDispensed = quantityDispensed;
    }

    /**
     * @return the refills
     */
    public String getRefills() {
        return refills;
    }

    /**
     * @param refills the refills to set
     */
    public void setRefills(String refills) {
        this.refills = refills;
    }

    /**
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * @param instructions the instructions to set
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
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
