/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.services;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import gov.va.ehtac.hl7.hcs.beans.FHIRPolicyFactModel;
import gov.va.ehtac.hl7.hcs.common.beans.FHIRConfidentialityRuleExecutionContainer;
import gov.va.ehtac.hl7.hcs.model.PolicyEnforcementResponse;

/**
 *
 * @author Socraticgrid Staff
 */
public class PolicyDecisioningFHIR {
        
	/** The knowledge session. */
	private StatefulKnowledgeSession session;
	
	/** The knowledge base. */
	private KnowledgeBase knowledgeBase;

	private String firedRuleNames = "";
        private String fhirRules;
        private FHIRPolicyFactModel factModel;
        private PolicyEnforcementResponse response;
        
    public PolicyDecisioningFHIR() {
        
    }
    
    public PolicyEnforcementResponse FHIRPolicyDecision(FHIRPolicyFactModel factModel, String fhirPolicyRules) {
        this.response = new PolicyEnforcementResponse();
        this.fhirRules = fhirPolicyRules;
        this.factModel = factModel;
        
        try {
            assertAndExecuteFHIRPolicies();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }        
        return this.response;
    }
    
    private void assertAndExecuteFHIRPolicies() {
        
    }
    
    private void createStatefulKnowledgeSession() {

            try {
                    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
                                    .newKnowledgeBuilder();

                    kbuilder.add(
                                    ResourceFactory.newByteArrayResource(fhirRules.getBytes()),
                                    ResourceType.DRL);

                    KnowledgeBuilderErrors errors = kbuilder.getErrors();
                    if (errors.size() > 0) {
                            for (KnowledgeBuilderError error : errors) {
                                    System.err.println(error);
                            }
                    }

                    knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
                    knowledgeBase.addKnowledgePackages(kbuilder.getKnowledgePackages());

                    session = knowledgeBase.newStatefulKnowledgeSession();
                    session.setGlobal("ruleExecutionContainer",
                                    new FHIRConfidentialityRuleExecutionContainer());
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    
    private void addRuleName(String ruleName) {
            firedRuleNames = (!firedRuleNames.equals("")) ? firedRuleNames + ", "
                            + ruleName : ruleName;
    }    
    
}
