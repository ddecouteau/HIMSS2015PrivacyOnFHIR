//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.04 at 08:21:37 AM MDT 
//


package gov.va.ehtac.hl7.hcs.policy.reference;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:hl7-org:v3}ActReason"/>
 *         &lt;element ref="{urn:hl7-org:v3}ActInformationSensitivityPolicy"/>
 *         &lt;element ref="{urn:hl7-org:v3}PatientSensitivityConstraint"/>
 *         &lt;element ref="{urn:hl7-org:v3}PatientRequestedAction"/>
 *         &lt;element ref="{urn:hl7-org:v3}ActUSPrivacyLaw"/>
 *         &lt;element ref="{urn:hl7-org:v3}OrgObligationPolicyEntry"/>
 *         &lt;element ref="{urn:hl7-org:v3}OrgObligationPolicyDocument"/>
 *         &lt;element ref="{urn:hl7-org:v3}RefrainPolicy"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "actReason",
    "actInformationSensitivityPolicy",
    "patientSensitivityConstraint",
    "patientRequestedAction",
    "actUSPrivacyLaw",
    "orgObligationPolicyEntry",
    "orgObligationPolicyDocument",
    "refrainPolicy"
})
@XmlRootElement(name = "OrganizationTaggingRules")
public class OrganizationTaggingRules {

    @XmlElement(name = "ActReason", required = true)
    protected ActReason actReason;
    @XmlElement(name = "ActInformationSensitivityPolicy", required = true)
    protected ActInformationSensitivityPolicy actInformationSensitivityPolicy;
    @XmlElement(name = "PatientSensitivityConstraint", required = true)
    protected PatientSensitivityConstraint patientSensitivityConstraint;
    @XmlElement(name = "PatientRequestedAction", required = true)
    protected PatientRequestedAction patientRequestedAction;
    @XmlElement(name = "ActUSPrivacyLaw", required = true)
    protected ActUSPrivacyLaw actUSPrivacyLaw;
    @XmlElement(name = "OrgObligationPolicyEntry", required = true)
    protected OrgObligationPolicyEntry orgObligationPolicyEntry;
    @XmlElement(name = "OrgObligationPolicyDocument", required = true)
    protected OrgObligationPolicyDocument orgObligationPolicyDocument;
    @XmlElement(name = "RefrainPolicy", required = true)
    protected RefrainPolicy refrainPolicy;

    /**
     * Gets the value of the actReason property.
     * 
     * @return
     *     possible object is
     *     {@link ActReason }
     *     
     */
    public ActReason getActReason() {
        return actReason;
    }

    /**
     * Sets the value of the actReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActReason }
     *     
     */
    public void setActReason(ActReason value) {
        this.actReason = value;
    }

    /**
     * Gets the value of the actInformationSensitivityPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link ActInformationSensitivityPolicy }
     *     
     */
    public ActInformationSensitivityPolicy getActInformationSensitivityPolicy() {
        return actInformationSensitivityPolicy;
    }

    /**
     * Sets the value of the actInformationSensitivityPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActInformationSensitivityPolicy }
     *     
     */
    public void setActInformationSensitivityPolicy(ActInformationSensitivityPolicy value) {
        this.actInformationSensitivityPolicy = value;
    }

    /**
     * Gets the value of the patientSensitivityConstraint property.
     * 
     * @return
     *     possible object is
     *     {@link PatientSensitivityConstraint }
     *     
     */
    public PatientSensitivityConstraint getPatientSensitivityConstraint() {
        return patientSensitivityConstraint;
    }

    /**
     * Sets the value of the patientSensitivityConstraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientSensitivityConstraint }
     *     
     */
    public void setPatientSensitivityConstraint(PatientSensitivityConstraint value) {
        this.patientSensitivityConstraint = value;
    }

    /**
     * Gets the value of the patientRequestedAction property.
     * 
     * @return
     *     possible object is
     *     {@link PatientRequestedAction }
     *     
     */
    public PatientRequestedAction getPatientRequestedAction() {
        return patientRequestedAction;
    }

    /**
     * Sets the value of the patientRequestedAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientRequestedAction }
     *     
     */
    public void setPatientRequestedAction(PatientRequestedAction value) {
        this.patientRequestedAction = value;
    }

    /**
     * Gets the value of the actUSPrivacyLaw property.
     * 
     * @return
     *     possible object is
     *     {@link ActUSPrivacyLaw }
     *     
     */
    public ActUSPrivacyLaw getActUSPrivacyLaw() {
        return actUSPrivacyLaw;
    }

    /**
     * Sets the value of the actUSPrivacyLaw property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActUSPrivacyLaw }
     *     
     */
    public void setActUSPrivacyLaw(ActUSPrivacyLaw value) {
        this.actUSPrivacyLaw = value;
    }

    /**
     * Gets the value of the orgObligationPolicyEntry property.
     * 
     * @return
     *     possible object is
     *     {@link OrgObligationPolicyEntry }
     *     
     */
    public OrgObligationPolicyEntry getOrgObligationPolicyEntry() {
        return orgObligationPolicyEntry;
    }

    /**
     * Sets the value of the orgObligationPolicyEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgObligationPolicyEntry }
     *     
     */
    public void setOrgObligationPolicyEntry(OrgObligationPolicyEntry value) {
        this.orgObligationPolicyEntry = value;
    }

    /**
     * Gets the value of the orgObligationPolicyDocument property.
     * 
     * @return
     *     possible object is
     *     {@link OrgObligationPolicyDocument }
     *     
     */
    public OrgObligationPolicyDocument getOrgObligationPolicyDocument() {
        return orgObligationPolicyDocument;
    }

    /**
     * Sets the value of the orgObligationPolicyDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrgObligationPolicyDocument }
     *     
     */
    public void setOrgObligationPolicyDocument(OrgObligationPolicyDocument value) {
        this.orgObligationPolicyDocument = value;
    }

    /**
     * Gets the value of the refrainPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link RefrainPolicy }
     *     
     */
    public RefrainPolicy getRefrainPolicy() {
        return refrainPolicy;
    }

    /**
     * Sets the value of the refrainPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefrainPolicy }
     *     
     */
    public void setRefrainPolicy(RefrainPolicy value) {
        this.refrainPolicy = value;
    }

}
