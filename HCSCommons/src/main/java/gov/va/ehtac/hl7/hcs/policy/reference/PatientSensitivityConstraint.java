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
 *         &lt;element ref="{urn:hl7-org:v3}ActInformationSensitivityPolicy"/>
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
    "actInformationSensitivityPolicy"
})
@XmlRootElement(name = "PatientSensitivityConstraint")
public class PatientSensitivityConstraint {

    @XmlElement(name = "ActInformationSensitivityPolicy", required = true)
    protected ActInformationSensitivityPolicy actInformationSensitivityPolicy;

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

}