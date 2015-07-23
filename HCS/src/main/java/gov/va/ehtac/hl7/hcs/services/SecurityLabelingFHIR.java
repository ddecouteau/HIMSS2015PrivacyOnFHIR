/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.services;

import gov.va.ehtac.hl7.hcs.common.beans.FHIRConfidentialityRuleExecutionContainer;
import gov.va.ehtac.hl7.hcs.common.beans.FHIRConfidentialityRuleExecutionResponse;
import gov.va.ehtac.hl7.hcs.beans.FHIRClinicalFact;
import gov.va.ehtac.hl7.hcs.beans.FHIRFactModel;
import gov.va.ehtac.hl7.hcs.constants.DS4PConstants;
import gov.va.ehtac.hl7.hcs.utils.FileHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.rule.Rule;
import org.drools.event.rule.AfterActivationFiredEvent;
import org.drools.event.rule.DefaultAgendaEventListener;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.hl7.fhir.HCSCategory;
//import org.hl7.fhir.instance.model.Bundle;
//import org.hl7.fhir.instance.model.Encounter;
//import org.hl7.fhir.instance.model.MedicationPrescription;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.MedicationPrescription;

/**
 *
 * @author Socraticgrid Staff
 */
public class SecurityLabelingFHIR {
        private MedicationPrescription medication;
        private Encounter encounter;
        
	/** The knowledge session. */
	private StatefulKnowledgeSession session;
	
	/** The knowledge base. */
	private KnowledgeBase knowledgeBase;
		
	/** The audit service. */
//	private DS4PAudit auditService;
	
	/** The fired rule names. */
	private String firedRuleNames = "";
        
        private String endpoint = "http://localhost:8080/DS4PACSServices/DS4PClinicallyAdaptiveRulesInterface?wsdl";
        
        private String fhirRules;
        
        
        public SecurityLabelingFHIR() {
            
        }
    
//       public String applySecurityLabelsFHIR(String resource) {
//           String res = "";
//           try {
//               XmlParser xmlP = new XmlParser();
//               InputStream in = new ByteArrayInputStream(resource.getBytes());
//               Encounter r = (Encounter)xmlP.parse(in);
//               this.encounter = r;
//               String medValue = r.getType().get(0).getCoding().get(0).getDisplaySimple();
//               System.out.println("FHIR:MEDICATION:VALUE "+medValue);
//               
//               if (medValue.equals("Chemotherapy")) {
//                   res = tagSelfPay();
//               }
//               else {
//                   res = tag42CFRPart2();
//               }
//           }
//           catch (Exception ex) {
//               ex.printStackTrace();
//           }
//           
//           return res;
//       }
       
       
//    private String composeXMLString(Bundle obj) {
//        String res = "";
//        try {
//            XmlComposer xml = new XmlComposer();
//            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//            xml.compose(bo, obj, true);
//            res = new String(bo.toByteArray());
//            bo.close();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return res;
//    }
        
    public List<HCSCategory> applySecurityLabelsFHIR(FHIRFactModel factModel, String fhirRules) {
        List<HCSCategory> res = new ArrayList<HCSCategory>();
        this.fhirRules = fhirRules;
        try {
            res = assertAndExecuteClinicalFacts(factModel);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
    private List<HCSCategory> assertAndExecuteClinicalFacts(FHIRFactModel factModel) {
        FHIRConfidentialityRuleExecutionContainer executionResponseContainer = null;
        List<HCSCategory> res = new ArrayList<HCSCategory>();
        StringWriter executionResponseContainerXML = new StringWriter();
        
        try {
            createStatefulKnowledgeSession();
            for (FHIRClinicalFact clinicalFact : factModel.getClinicalFactList()) {
                    System.out.println("ASSERTING: "+clinicalFact.getCodeSystem()+" "+clinicalFact.getCode());
                    session.insert(clinicalFact);
            }

            session.addEventListener(new DefaultAgendaEventListener() {
                    @Override
                    public void afterActivationFired(AfterActivationFiredEvent event) {
                            super.afterActivationFired(event);
                            final Rule rule = event.getActivation().getRule();
                            addRuleName(rule.getName());

                    }
            });

            session.fireAllRules();
                    
            executionResponseContainer = (FHIRConfidentialityRuleExecutionContainer) session
                                    .getGlobal("ruleExecutionContainer");
            // Marshal rule execution response
            JAXBContext jaxbContext = JAXBContext.newInstance(FHIRConfidentialityRuleExecutionContainer.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("com.sun.xml.bind.xmlDeclaration",
                            Boolean.FALSE);
            marshaller.marshal(executionResponseContainer,
                            executionResponseContainerXML);
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        } 
        finally {
                    firedRuleNames = "";
                    session.dispose();    
        }
        List<FHIRConfidentialityRuleExecutionResponse> rList = executionResponseContainer.getExecutionResponseList();
        Iterator iter = rList.iterator();
        System.out.println("**NUMBER of Rules Executed: "+rList.size());
        while (iter.hasNext()) {
            FHIRConfidentialityRuleExecutionResponse r = (FHIRConfidentialityRuleExecutionResponse)iter.next();
            System.out.println("EXECUTION RESPONSE: "+r.getDisplayName()+" "+r.getImpliedConfidentiality()+" "+r.getSensitivity()+" "+r.getResourceId());
            HCSCategory hcsConf = new HCSCategory();
            hcsConf.setScheme(DS4PConstants.FHIR_SCHEME_VALUE);
            hcsConf.setTerm(DS4PConstants.FHIR_CONFIDENTIALITY_BASE+r.getImpliedConfidentiality());
            hcsConf.setLabel(r.getConfidentialityLabel());
            hcsConf.setResourceId(r.getResourceId());
            res.add(hcsConf);
            if ("R".equals(r.getImpliedConfidentiality())) {
                HCSCategory hcsSens = new HCSCategory();
                hcsSens.setScheme(DS4PConstants.FHIR_SCHEME_VALUE);
                hcsSens.setTerm(DS4PConstants.FHIR_SENSITIVITY_BASE+r.getSensitivity());
                hcsSens.setLabel(r.getSensitivity());
                hcsSens.setResourceId(r.getResourceId());
                res.add(hcsSens);
            }
        }
        //output rules that fired
        String rRules = executionResponseContainerXML.toString();
        try {
            FileHelper.writeStringToFile(rRules, "ExecutionResponseContainer.xml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        System.out.println("FIRED RULES: "+firedRuleNames);
        
        return res;
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
