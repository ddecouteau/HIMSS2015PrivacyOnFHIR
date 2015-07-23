/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.dataresults;

import ca.uhn.fhir.model.dstu.resource.AdverseReaction;

/**
 *
 * @author Duane DeCouteau
 */
public class AdverseReactionsResults {
    private String reactionName;
    private String severity;
    private String date;
    private AdverseReaction payload;

    /**
     * @return the reactionName
     */
    public String getReactionName() {
        return reactionName;
    }

    /**
     * @param reactionName the reactionName to set
     */
    public void setReactionName(String reactionName) {
        this.reactionName = reactionName;
    }

    /**
     * @return the severity
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * @param severity the severity to set
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the payload
     */
    public AdverseReaction getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(AdverseReaction payload) {
        this.payload = payload;
    }
    
}
