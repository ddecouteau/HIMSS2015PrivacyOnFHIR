/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.beans;

/**
 *
 * @author Socraticgrid Staff
 */
public class PatientIdentifier {
    private String system;
    private String code;

    /**
     * @return the system
     */
    public String getSystem() {
        return system;
    }

    /**
     * @param system the system to set
     */
    public void setSystem(String system) {
        this.system = system;
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
}
