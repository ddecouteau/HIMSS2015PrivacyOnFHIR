/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Socraticgrid Staff
 */
@XmlRootElement(name="FHIRFactModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class FHIRFactModel {
    
	/** The clinical fact list. */
	@XmlElementWrapper(name="FHIRClinicalFacts")
	@XmlElement(name="FHIRClinicalFact")
	private List<FHIRClinicalFact> clinicalFactList = new ArrayList<FHIRClinicalFact>();

    /**
     * @return the clinicalFactList
     */
    public List<FHIRClinicalFact> getClinicalFactList() {
        return clinicalFactList;
    }

    /**
     * @param clinicalFactList the clinicalFactList to set
     */
    public void setClinicalFactList(List<FHIRClinicalFact> clinicalFactList) {
        this.clinicalFactList = clinicalFactList;
    }
    
    
}
