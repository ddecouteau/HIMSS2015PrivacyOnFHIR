/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.dataresults;

import ca.uhn.fhir.model.dstu2.resource.Immunization;
import com.vaadin.ui.CheckBox;

/**
 *
 * @author Duane DeCouteau
 */
public class ImmunizationResults {
    private CheckBox selectForTransfer = new CheckBox();
    private String displayName;
    private Boolean refusedIndicator;
    private String refusalReason;
    private Boolean reported;
    private String administerDate;
    private Immunization payload;
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
     * @return the administerDate
     */
    public String getAdministerDate() {
        return administerDate;
    }

    /**
     * @param administerDate the administerDate to set
     */
    public void setAdministerDate(String administerDate) {
        this.administerDate = administerDate;
    }


    /**
     * @return the payload
     */
    public Immunization getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Immunization payload) {
        this.payload = payload;
    }

    /**
     * @return the refusedIndicator
     */
    public Boolean getRefusedIndicator() {
        return refusedIndicator;
    }

    /**
     * @param refusedIndicator the refusedIndicator to set
     */
    public void setRefusedIndicator(Boolean refusedIndicator) {
        this.refusedIndicator = refusedIndicator;
    }

    /**
     * @return the reported
     */
    public Boolean getReported() {
        return reported;
    }

    /**
     * @param reported the reported to set
     */
    public void setReported(Boolean reported) {
        this.reported = reported;
    }    

    /**
     * @return the refusalReason
     */
    public String getRefusalReason() {
        return refusalReason;
    }

    /**
     * @param refusalReason the refusalReason to set
     */
    public void setRefusalReason(String refusalReason) {
        this.refusalReason = refusalReason;
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
