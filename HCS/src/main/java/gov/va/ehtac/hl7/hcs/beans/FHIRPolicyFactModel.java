/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Socraticgrid Staff
 */
public class FHIRPolicyFactModel {
    private String subjectId;
    private String role;
    private String organization;
    private String organizationId;
    private String purposeOfUse;
    private List<String> sensitivityPermissions = new ArrayList();
    private List<PatientIdentifier> resourceId;
    private String familyName;
    private String givenName;
    private List<String> requiredSensitivityPermissions = new ArrayList();

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

    /**
     * @return the resourceId
     */
    public List<PatientIdentifier> getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId the resourceId to set
     */
    public void setResourceId(List<PatientIdentifier> resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return the requiredSensitivityPermissions
     */
    public List<String> getRequiredSensitivityPermissions() {
        return requiredSensitivityPermissions;
    }

    /**
     * @param requiredSensitivityPermissions the requiredSensitivityPermissions to set
     */
    public void setRequiredSensitivityPermissions(List<String> requiredSensitivityPermissions) {
        this.requiredSensitivityPermissions = requiredSensitivityPermissions;
    }

    /**
     * @return the familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * @param familyName the familyName to set
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * @return the givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * @param givenName the givenName to set
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId the organizationId to set
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    
}
