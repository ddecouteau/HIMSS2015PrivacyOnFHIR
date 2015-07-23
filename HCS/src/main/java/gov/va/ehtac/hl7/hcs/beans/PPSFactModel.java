/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Duane DeCouteau
 */
@XmlRootElement(name="PPSFactModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class PPSFactModel {
	/** The clinical fact list. */
	@XmlElementWrapper(name="PPSFacts")
	@XmlElement(name="PPSFact")
	private List<PPSFact> PPSFactList = new ArrayList<PPSFact>();

    /**
     * @return the PPSFactList
     */
    public List<PPSFact> getPPSFactList() {
        return PPSFactList;
    }

    /**
     * @param PPSFactList the PPSFactList to set
     */
    public void setPPSFactList(List<PPSFact> PPSFactList) {
        this.PPSFactList = PPSFactList;
    }
    
    
}
