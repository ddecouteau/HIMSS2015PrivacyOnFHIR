/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Socraticgrid Staff
 */
public class PolicyEnforcementRequest {
    private String document;
    private String purposeOfUse;
    private String subjectId;
    private String role;
    private List<String> sensitivityPermissions = new ArrayList();
    
    public PolicyEnforcementRequest(String document, String purposeOfUse, String subjectId, String role, List<String> sensitivityPermissions) {
        this.document = document;
        this.purposeOfUse = purposeOfUse;
        this.subjectId = subjectId;
        this.role = role;
        this.sensitivityPermissions = sensitivityPermissions;
    }
    
    public PolicyEnforcementRequest() {
        
    }

    /**
     * @return the document
     */
    public String getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(String document) {
        this.document = document;
    }

    /**
     * @return the purposeOfUse
     */
    public String getPurposeOfUse() {
        return purposeOfUse;
    }

    /**
     * @param purposeOfUse the purposeOfUse to set
     */
    public void setPurposeOfUse(String purposeOfUse) {
        this.purposeOfUse = purposeOfUse;
    }

    /**
     * @return the subjectId
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the sensitivityPermissions
     */
    public List<String> getSensitivityPermissions() {
        return sensitivityPermissions;
    }

    /**
     * @param sensitivityPermissions the sensitivityPermissions to set
     */
    public void setSensitivityPermissions(List<String> sensitivityPermissions) {
        this.sensitivityPermissions = sensitivityPermissions;
    }
    
    
}
