/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.common;

/**
 *
 * @author Socraticgrid Staff
 */
public class DataSegmentationDirective {
    private String actInformationSensitivityCode;
    private String actInformationSensitivityCodeAction;

    /**
     * @return the actInformationSensitivityCode
     */
    public String getActInformationSensitivityCode() {
        return actInformationSensitivityCode;
    }

    /**
     * @param actInformationSensitivityCode the actInformationSensitivityCode to set
     */
    public void setActInformationSensitivityCode(String actInformationSensitivityCode) {
        this.actInformationSensitivityCode = actInformationSensitivityCode;
    }

    /**
     * @return the actInformationSensitivityCodeAction
     */
    public String getActInformationSensitivityCodeAction() {
        return actInformationSensitivityCodeAction;
    }

    /**
     * @param actInformationSensitivityCodeAction the actInformationSensitivityCodeAction to set
     */
    public void setActInformationSensitivityCodeAction(String actInformationSensitivityCodeAction) {
        this.actInformationSensitivityCodeAction = actInformationSensitivityCodeAction;
    }
}
