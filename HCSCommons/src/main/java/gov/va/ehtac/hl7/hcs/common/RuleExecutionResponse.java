/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.common;

/**
 *
 * @author Socraticgrid Staff
 */
public class RuleExecutionResponse {

    /**
     * @return the ImpliedConfSection
     */
    public Confidentiality getImpliedConfSection() {
        return ImpliedConfSection;
    }

    /**
     * @param ImpliedConfSection the ImpliedConfSection to set
     */
    public void setImpliedConfSection(Confidentiality ImpliedConfSection) {
        this.ImpliedConfSection = ImpliedConfSection;
    }

    /**
     * @return the USPrivacyLaw
     */
    public String getUSPrivacyLaw() {
        return USPrivacyLaw;
    }

    /**
     * @param USPrivacyLaw the USPrivacyLaw to set
     */
    public void setUSPrivacyLaw(String USPrivacyLaw) {
        this.USPrivacyLaw = USPrivacyLaw;
    }

    /**
     * @return the DocumentObligationPolicy
     */
    public String getDocumentObligationPolicy() {
        return DocumentObligationPolicy;
    }

    /**
     * @param DocumentObligationPolicy the DocumentObligationPolicy to set
     */
    public void setDocumentObligationPolicy(String DocumentObligationPolicy) {
        this.DocumentObligationPolicy = DocumentObligationPolicy;
    }

    /**
     * @return the DocumentRefrainPolicy
     */
    public String getDocumentRefrainPolicy() {
        return DocumentRefrainPolicy;
    }

    /**
     * @param DocumentRefrainPolicy the DocumentRefrainPolicy to set
     */
    public void setDocumentRefrainPolicy(String DocumentRefrainPolicy) {
        this.DocumentRefrainPolicy = DocumentRefrainPolicy;
    }

    /**
     * @return the c32SectionTitle
     */
    public String getC32SectionTitle() {
        return c32SectionTitle;
    }

    /**
     * @param c32SectionTitle the c32SectionTitle to set
     */
    public void setC32SectionTitle(String c32SectionTitle) {
        this.c32SectionTitle = c32SectionTitle;
    }

    /**
     * @return the c32SectionLoincCode
     */
    public String getC32SectionLoincCode() {
        return c32SectionLoincCode;
    }

    /**
     * @param c32SectionLoincCode the c32SectionLoincCode to set
     */
    public void setC32SectionLoincCode(String c32SectionLoincCode) {
        this.c32SectionLoincCode = c32SectionLoincCode;
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

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the codeSystemName
     */
    public String getCodeSystemName() {
        return codeSystemName;
    }

    /**
     * @param codeSystemName the codeSystemName to set
     */
    public void setCodeSystemName(String codeSystemName) {
        this.codeSystemName = codeSystemName;
    }
    
    public enum Confidentiality {
 
     V(3), R(2), N(1);
 
     private int priority;
 
     Confidentiality(int p) {priority = p;}
 
     int getPriority() {return priority;}
    }

    private Confidentiality ImpliedConfSection;

    private String Sensitivity;
    private String ItemAction;
    private String USPrivacyLaw;
    private String DocumentObligationPolicy;
    private String DocumentRefrainPolicy;

    private String c32SectionTitle;
    private String c32SectionLoincCode;
    private String observationId;
    
    //new additions
    private String code;
    private String displayName;
    private String codeSystemName;
    
    public RuleExecutionResponse() {
        
    }
    
    public RuleExecutionResponse(String sensitivity, String itemaction, String impliedconf, String usprivacylaw, String documentobligationpolicy, String documentrefrainpolicy, String c32sectiontitle, String c32sectionloinccode, String observationid, String code, String displayName,String codeSystemName) {
        this.Sensitivity = sensitivity;
        this.ItemAction = itemaction;
        if (impliedconf.equals("N")) this.ImpliedConfSection = Confidentiality.N;
        if (impliedconf.equals("R")) this.ImpliedConfSection = Confidentiality.R;
        if (impliedconf.equals("V")) this.ImpliedConfSection = Confidentiality.V;
        this.DocumentObligationPolicy = documentobligationpolicy;
        this.DocumentRefrainPolicy = documentrefrainpolicy;
        this.USPrivacyLaw = usprivacylaw;
        this.c32SectionLoincCode = c32sectionloinccode;
        this.c32SectionTitle = c32sectiontitle;
        this.observationId = observationid;
        this.code = code;
        this.displayName = displayName;
        this.codeSystemName = codeSystemName;
    }

    /**
     * @return the Sensitivity
     */
    public String getSensitivity() {
        return Sensitivity;
    }

    /**
     * @param Sensitivity the Sensitivity to set
     */
    public void setSensitivity(String Sensitivity) {
        this.Sensitivity = Sensitivity;
    }

    /**
     * @return the ItemAction
     */
    public String getItemAction() {
        return ItemAction;
    }

    /**
     * @param ItemAction the ItemAction to set
     */
    public void setItemAction(String ItemAction) {
        this.ItemAction = ItemAction;
    }


    /**
     * @return the observationId
     */
    public String getObservationId() {
        return observationId;
    }

    /**
     * @param observationId the observationId to set
     */
    public void setObservationId(String observationId) {
        this.observationId = observationId;
    }

    
}
