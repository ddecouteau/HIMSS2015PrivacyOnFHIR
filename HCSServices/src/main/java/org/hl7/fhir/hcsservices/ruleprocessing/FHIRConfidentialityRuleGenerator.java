/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir.hcsservices.ruleprocessing;

import java.util.Iterator;
import java.util.List;
import org.hl7.fhir.hcsservices.jpa.Clinicallabels;
import org.hl7.fhir.hcsservices.ws.HCSAdaptiveRulesInterface;

/**
 *
 * @author Socraticgrid Staff
 */
public class FHIRConfidentialityRuleGenerator {
    private HCSAdaptiveRulesInterface rIntf = new HCSAdaptiveRulesInterface();
    private StringBuffer gSB = new StringBuffer();
    
    private Clinicallabels currentClinicalRule;
    private String currentSensitivity;
    private String impliedConfidentiality;
    private String confidentiality;
    private String UNRESTRICTED = "unrestricted";
    private String LOW = "low";
    private String MODERATE = "moderate";
    private String NORMAL = "normal";
    private String RESTRICTED = "restricted";
    private String VERY_RESTRICTED = "very restricted";
    private String UNRESTRICTED_VALUE = "U";
    private String LOW_VALUE = "L";
    private String MODERATE_VALUE = "M";
    private String NORMAL_VALUE = "N";
    private String RESTRICTED_VALUE = "R";
    private String VERY_RESTRICTED_VALUE = "V";
    
    
            
    
    public String GenerateRule() {
                
        gSB.append(getHeader());
        String rule = "";

        getClinicalRules();
        
        rule = gSB.toString();
        return rule;
    }
    
    private void getClinicalRules() {
        List<Clinicallabels> dbrule;
        dbrule = rIntf.getAllClinicalRules();

        Iterator iter = dbrule.iterator();

        while(iter.hasNext()) {
            currentClinicalRule = (Clinicallabels)iter.next();            
            gSB.append(getRuleStringForConfidentiality());
        }        
    }
    
    private String getHeader() {
        StringBuffer sb = new StringBuffer();
        sb.append("package org.socraticgrid.hl7.hcs.rules;\n");
        sb.append("\n");
        sb.append("import org.socraticgrid.hl7.hcs.beans.FHIRClinicalFact;\n");
        sb.append("import org.socraticgrid.hl7.hcs.common.beans.FHIRConfidentialityRuleExecutionResponse;\n");
        sb.append("\n");
        sb.append("global org.socraticgrid.hl7.hcs.common.beans.FHIRConfidentialityRuleExecutionContainer ruleExecutionContainer;\n");
        sb.append("\n");
        return sb.toString();
    }
            
    
    private String getRuleStringForConfidentiality() {
        StringBuffer sb = new StringBuffer();
        sb.append("rule \"Clinical Rule "+currentClinicalRule.getFhirdisplayname()+"\"\n");
        sb.append("dialect \"mvel\"\n");
        sb.append("when\n");
        sb.append("\n");
        sb.append("\t $cd : FHIRClinicalFact(codeSystem == \""+currentClinicalRule.getFhirpath()+"\", code == \""+currentClinicalRule.getFhircode()+"\")\n");
        sb.append("\n");
        sb.append("then\n");
        sb.append("\n");
        sb.append("\t ruleExecutionContainer.addExecutionResponse(new FHIRConfidentialityRuleExecutionResponse(\""+
                currentClinicalRule.getFhircode()+
                "\", \""+currentClinicalRule.getFhirpath()+
                "\", \""+currentClinicalRule.getFhirdisplayname()+
                "\", \""+currentClinicalRule.getSensitivitycode()+
                "\", \""+currentClinicalRule.getConfidentialitycode()+
                "\", \""+currentClinicalRule.getConfidentialityLabel()+
                "\", $cd.resourceId))\n");
        sb.append("\n");
        sb.append("end\n");
        sb.append("\n");
        return sb.toString();
    }
    
}
