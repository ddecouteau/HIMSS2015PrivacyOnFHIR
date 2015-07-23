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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hl7.fhir.hcsservices.jpa.Ppsactions;
import org.hl7.fhir.hcsservices.ws.HCSAdaptiveRulesInterface;

/**
 *
 * @author Socraticgrid Staff
 */
public class PPSRuleGenerator {
    private HCSAdaptiveRulesInterface rIntf = new HCSAdaptiveRulesInterface();
    private StringBuffer gSB = new StringBuffer();
    
    private Ppsactions currentClinicalRule;
    private String currentSensitivity;
    private String currentResourceId; 
    
    private String purposeOfUse;
    private int ruleNumber = 1;
            
    
    public String GenerateRule(String purposeOfUse) {
        this.purposeOfUse = purposeOfUse;

        
        gSB.append(getHeader());
        String rule = "";

        getPPSRules();
        
        rule = gSB.toString();
        return rule;
    }
    
    private void getPPSRules() {
        List<Ppsactions> dbrule;
        dbrule = rIntf.getAllPPSActionsByPurposeOfUse(purposeOfUse);

        Iterator iter = dbrule.iterator();

        while(iter.hasNext()) {
            currentClinicalRule = (Ppsactions)iter.next(); 
            gSB.append(getRuleStringForConfidentiality());
            ruleNumber++;
        }        
    }
    
    private String getHeader() {
        StringBuffer sb = new StringBuffer();
        sb.append("package org.socraticgrid.hl7.hcs.rules;\n");
        sb.append("\n");
        sb.append("import org.socraticgrid.hl7.hcs.beans.PPSFact;\n");
        sb.append("import org.socraticgrid.hl7.hcs.common.beans.PPSRuleExecutionResponse;\n");
        sb.append("\n");
        sb.append("global org.socraticgrid.hl7.hcs.common.beans.PPSRuleExecutionContainer ruleExecutionContainer;\n");
        sb.append("\n");
        return sb.toString();
    }
            
    
    private String getRuleStringForConfidentiality() {
        StringBuffer sb = new StringBuffer();
        sb.append("rule \"PPS Rule "+ruleNumber+"\"\n");
        sb.append("dialect \"mvel\"\n");
        sb.append("when\n");
        sb.append("\n");
        sb.append("\t $cd : PPSFact(sensitivityCode == \""+currentClinicalRule.getSensitivity()+"\", patientAuthorization == \""+currentClinicalRule.getPatientAuthorization()+"\")\n");
        sb.append("\n");
        sb.append("then\n");
        sb.append("\n");
        sb.append("\t ruleExecutionContainer.addExecutionResponse(new PPSRuleExecutionResponse(\""+
                currentClinicalRule.getPpsAction()+
                "\", $cd.resourceId))\n");
        sb.append("\n");
        sb.append("end\n");
        sb.append("\n");
        return sb.toString();
    }
    
}
