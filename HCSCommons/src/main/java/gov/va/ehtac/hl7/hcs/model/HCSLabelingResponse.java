/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Socraticgrid Staff
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "processedAtomFeed",
    "altered"
})
@XmlRootElement(name = "HCSLabelingResponse")
public class HCSLabelingResponse {
    @XmlElement(name = "processedAtomFeed", required = true)
    private String processedAtomFeed;
    @XmlElement(name = "altered", required = true)
    private boolean altered = false;

    /**
     * @return the processedAtomFeed
     */
    public String getProcessedAtomFeed() {
        return processedAtomFeed;
    }

    /**
     * @param processedAtomFeed the processedAtomFeed to set
     */
    public void setProcessedAtomFeed(String processedAtomFeed) {
        this.processedAtomFeed = processedAtomFeed;
    }

    /**
     * @return the altered
     */
    public boolean isAltered() {
        return altered;
    }

    /**
     * @param altered the altered to set
     */
    public void setAltered(boolean altered) {
        this.altered = altered;
    }
    
}
