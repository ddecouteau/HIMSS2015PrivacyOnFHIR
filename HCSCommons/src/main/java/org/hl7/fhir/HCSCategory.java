/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir;

/**
 *
 * @author Socraticgrid Staff
 */
public class HCSCategory {
    private String resourceId;
    private String scheme;
    private String term;
    private String label;
    
    public HCSCategory() {
        
    }
    
    public HCSCategory(String scheme, String term, String label) {
        super();
        this.scheme = scheme;
        this.term = term;
        this.label = label;
    }

    /**
     * @return the scheme
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * @param scheme the scheme to set
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the resourceId
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId the resourceId to set
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
    
}
