/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.services;

import gov.va.ehtac.hl7.hcs.utils.FileHelper;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
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
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.MedicationPrescription;
import org.hl7.fhir.HCSDataFilter;
import gov.va.ehtac.hl7.hcs.beans.PPSFact;
import gov.va.ehtac.hl7.hcs.beans.PPSFactModel;
import gov.va.ehtac.hl7.hcs.common.beans.PPSRuleExecutionContainer;
import gov.va.ehtac.hl7.hcs.common.beans.PPSRuleExecutionResponse;

/**
 *
 * @author Socraticgrid Staff
 */
public class PPSProcessingFHIR {
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
        
        
        public PPSProcessingFHIR() {
            
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
        
    public List<HCSDataFilter> determineResourceFilters(PPSFactModel factModel, String fhirRules) {
        List<HCSDataFilter> res = new ArrayList<HCSDataFilter>();
        this.fhirRules = fhirRules;
        try {
            res = assertAndExecuteClinicalFacts(factModel);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
    private List<HCSDataFilter> assertAndExecuteClinicalFacts(PPSFactModel factModel) {
        PPSRuleExecutionContainer executionResponseContainer = null;
        List<HCSDataFilter> res = new ArrayList<HCSDataFilter>();
        StringWriter executionResponseContainerXML = new StringWriter();
        
        try {
            createStatefulKnowledgeSession();
            for (PPSFact clinicalFact : factModel.getPPSFactList()) {
                    System.out.println("ASSERTING: "+clinicalFact.getResourceId()+" "+clinicalFact.getSensitivityCode()+" "+clinicalFact.getPatientAuthorization());
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
                    
            executionResponseContainer = (PPSRuleExecutionContainer) session
                                    .getGlobal("ruleExecutionContainer");
            // Marshal rule execution response
            JAXBContext jaxbContext = JAXBContext.newInstance(PPSRuleExecutionContainer.class);
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
        List<PPSRuleExecutionResponse> rList = executionResponseContainer.getExecutionResponseList();
        Iterator iter = rList.iterator();
        System.out.println("**NUMBER of PPS Rules Executed: "+rList.size());
        while (iter.hasNext()) {
            PPSRuleExecutionResponse r = (PPSRuleExecutionResponse)iter.next();
            System.out.println("PPS EXECUTION RESPONSE: "+r.getResourceId()+" "+r.getPpsAction());
            HCSDataFilter hcsConf = new HCSDataFilter();
            hcsConf.setAction(r.getPpsAction());
            hcsConf.setResourceId(r.getResourceId());
            res.add(hcsConf);
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
                                    new PPSRuleExecutionContainer());
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    
    private void addRuleName(String ruleName) {
            firedRuleNames = (!firedRuleNames.equals("")) ? firedRuleNames + ", "
                            + ruleName : ruleName;
    }
        
}
