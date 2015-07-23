/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir.hcsservices.ws;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.api.Tag;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Condition;
import ca.uhn.fhir.model.dstu2.resource.ImagingStudy;
import ca.uhn.fhir.model.dstu2.resource.Immunization;
import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationPrescription;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Procedure;
import ca.uhn.fhir.model.dstu.composite.ContainedDt;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.parser.XmlParser;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.server.EncodingEnum;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.hl7.fhir.HCSCategory;
import org.hl7.fhir.HCSDataFilter;
import org.hl7.fhir.HCSPolicyDecision;
import org.hl7.fhir.HCSTaggingResponse;
import org.hl7.fhir.consent.JerichoConsentDirective;
import org.hl7.fhir.hcsservices.jpa.Hcslogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.va.ehtac.hl7.hcs.beans.FHIRClinicalFact;
import gov.va.ehtac.hl7.hcs.beans.FHIRFactModel;
import gov.va.ehtac.hl7.hcs.constants.DS4PConstants;
import gov.va.ehtac.hl7.hcs.model.HCSLabelingResponse;
import org.hl7.fhir.hcsservices.ruleprocessing.FHIRConfidentialityRuleGenerator;
import org.hl7.fhir.hcsservices.ruleprocessing.PPSRuleGenerator;
import gov.va.ehtac.hl7.hcs.beans.PPSFact;
import gov.va.ehtac.hl7.hcs.beans.PPSFactModel;
import gov.va.ehtac.hl7.hcs.services.PPSProcessingFHIR;
import gov.va.ehtac.hl7.hcs.services.SecurityLabelingFHIR;
import gov.va.ehtac.hl7.hcs.utils.FileHelper;


/**
 *
 * @author Socraticgrid Staff
 */
@WebService(name = "HCSOrchestrator", targetNamespace = "ws.hcs.hl7.socraticgrid.org")
public class HCSOrchestrator {
    private final Logger logger = LoggerFactory
			.getLogger(HCSOrchestrator.class);    
    private static final String DATA_SOURCE_CDA="CDA";
    private static final String DATA_SOURCE_FHIR="FHIR";

    private String FHIR_RESOURCE_TEST_TYPE = "Encounter";
    private List<String> obligationsArray = new ArrayList();
    private String baseURIObligationsRedact = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:";
    private String baseURIObligationsMask = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:";
    
    private String homeCommunityId;
    private String purposeOfUse;
    private String messageId;
    private String PERMIT = "Permit";
    private String xacmlResponseString;
    private SecurityLabelingFHIR slsService = new SecurityLabelingFHIR();
    private List<HCSCategory> currentTags;
    private List<HCSCategory> added = new ArrayList<HCSCategory>();
    private List<HCSCategory> deleted = new ArrayList<HCSCategory>();
    private List<HCSCategory> tempList = new ArrayList<HCSCategory>();
    private String fhirRules;
    private final FhirContext fhirContext = FhirContext.forDstu2();
    private Collection<Tag> tagArray = new ArrayList();
    private List<HCSCategory> sLabels = new ArrayList();
    private String internalResource;
    private HCSLogger hcslogger = new HCSLogger();
    private String urlPart;
    private String DEFAULT_PURPOSE_OF_USE = "TREAT";
    private List<String> authorizedList = new ArrayList();
    
    //pps service
    private String ppsRules;
    private PPSProcessingFHIR ppsService = new PPSProcessingFHIR();
    private List<HCSDataFilter> ppsActions = new ArrayList<HCSDataFilter>();
    private Bundle bundle; //this is the inbound bundle
    private Bundle bundleOut; //this is the bundle that has been manipulated
    private String newPayload;  //fhir bundle as string loaded in response
    private String consent;

    
    private final String CONFIDENTIALITY_SYSTEM="http://hl7.org/fhir/v3/Confidentiality";
    private final String SENSITIVITY_SYSTEM="http://hl7.org/fhir/v3/vs/ActCode";
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "genFHIRConfidentialityLabels")
    public HCSTaggingResponse genFHIRConfidentialityLabels(@WebParam(name = "resource") String resource, @WebParam(name = "currentTags") List<HCSCategory> currentTags) {
        //TODO write your implementation code here: 
        //System.out.println(resource);
        this.currentTags = currentTags;
        added = new ArrayList<HCSCategory>();
        deleted = new ArrayList<HCSCategory>();
        String code = "";
        String codeSystem = "";
        String display = "";
        FHIRFactModel factModel;
        fhirRules = getCASRules();
        Iterator iter = currentTags.iterator();
        while (iter.hasNext()) {
            HCSCategory hcs = (HCSCategory)iter.next();
            //System.out.println(hcs.getScheme()+" "+hcs.getTerm()+" "+hcs.getLabel());
        }
        try {
               XmlParser xmlP = new XmlParser(fhirContext);
               StringReader reader = new StringReader(resource);

               IResource r = (IResource)xmlP.parseResource(reader);
               String rName = r.getResourceName();
               if ("Condition".equals(rName)) {
                   Condition condition = (Condition)r;
                   CodeableConceptDt cC = condition.getCode();
                   List<CodingDt> cD = cC.getCoding();
                   Iterator conditionIter = cD.iterator();
                   factModel = new FHIRFactModel();
                   while (conditionIter.hasNext()) {
                       CodingDt c = (CodingDt)conditionIter.next();
                       code = c.getCode();
                       codeSystem = c.getSystem().toString();
                       display = c.getDisplay();
                       FHIRClinicalFact cFacts = new FHIRClinicalFact();
                       cFacts.setCode(code);
                       cFacts.setCodeSystem(codeSystem);
                       cFacts.setDisplay(display);
                       factModel.getClinicalFactList().add(cFacts);
                       System.out.println("CONDITION: "+codeSystem+" "+code+" "+display);
                   }
                   //test 
                   added = slsService.applySecurityLabelsFHIR(factModel, fhirRules);
                   if (added.isEmpty()) {
                    added.add(new HCSCategory(DS4PConstants.FHIR_SCHEME_VALUE, DS4PConstants.FHIR_CONFIDENTIALITY_BASE+"N", "Normal Restrictions"));
                   }
                   
               }
               else if ("MedicationPrescription".equals(rName)) {
                   MedicationPrescription med = (MedicationPrescription)r;

                   display = med.getMedication().getDisplay().toString();
                   codeSystem = med.getMedication().getReference().getValue();
                   System.out.println("MEDICATION: "+display+" "+codeSystem);
                   factModel = new FHIRFactModel();
                   FHIRClinicalFact cFacts = new FHIRClinicalFact();
                   cFacts.setCodeSystem(codeSystem);
                   cFacts.setDisplay(display);
                   factModel.getClinicalFactList().add(cFacts);
                   added = slsService.applySecurityLabelsFHIR(factModel, fhirRules);
                   if (added.isEmpty()) {
                    added.add(new HCSCategory(DS4PConstants.FHIR_SCHEME_VALUE, DS4PConstants.FHIR_CONFIDENTIALITY_BASE+"N", "Normal Restrictions"));
                   }
               }
               else if ("Immunization".equals(rName)) {
                   Immunization im = (Immunization)r;
                   List<CodingDt> imCoding = im.getVaccineType().getCoding();
                   Iterator imIter = imCoding.iterator();
                   factModel = new FHIRFactModel();
                   while (imIter.hasNext()) {
                       CodingDt c = (CodingDt)imIter.next();
                       code = c.getCode();
                       codeSystem = c.getSystem().toString();
                       display = c.getDisplay();
                       System.out.println("IMMUNIZATIONS: "+codeSystem+" "+code+" "+display);
                       FHIRClinicalFact cFacts = new FHIRClinicalFact();
                       cFacts.setCode(code);
                       cFacts.setCodeSystem(codeSystem);
                       cFacts.setDisplay(display);
                       factModel.getClinicalFactList().add(cFacts);
                   }
                   //test 
                   added = slsService.applySecurityLabelsFHIR(factModel, fhirRules);
                   if (added.isEmpty()) {
                    added.add(new HCSCategory(DS4PConstants.FHIR_SCHEME_VALUE, DS4PConstants.FHIR_CONFIDENTIALITY_BASE+"N", "Normal Restrictions"));
                   }
                   
               }
               else if ("Observation".equals(rName)) {
                   Observation o = (Observation)r;
                   CodeableConceptDt oConcept = o.getCode();
                   List<CodingDt> oCoding = oConcept.getCoding();
                   Iterator oIter = oCoding.iterator();
                   factModel = new FHIRFactModel();
                   while(oIter.hasNext()) {
                       CodingDt c = (CodingDt)oIter.next();
                       code = c.getCode();
                       codeSystem = c.getSystem().toString();
                       display = c.getDisplay();
                       System.out.println("Observations: "+codeSystem+" "+code+" "+display);
                       FHIRClinicalFact cFacts = new FHIRClinicalFact();
                       cFacts.setCode(code);
                       cFacts.setCodeSystem(codeSystem);
                       cFacts.setDisplay(display);
                       factModel.getClinicalFactList().add(cFacts);                       
                   }
                   //test 
                   added = slsService.applySecurityLabelsFHIR(factModel, fhirRules);
                   if (added.isEmpty()) {
                    added.add(new HCSCategory(DS4PConstants.FHIR_SCHEME_VALUE, DS4PConstants.FHIR_CONFIDENTIALITY_BASE+"N", "Normal Restrictions"));
                   }
               }
               else if ("ImagingStudy".equals(rName)) {
                   ImagingStudy i = (ImagingStudy)r;
                   List<CodingDt> rCoding = i.getProcedure();
                   Iterator rIter = rCoding.iterator();
                   factModel = new FHIRFactModel();
                   while(rIter.hasNext()) {
                       CodingDt c = (CodingDt)rIter.next();
                       code = c.getCode();
                       codeSystem = c.getSystem().toString();
                       display = c.getDisplay();
                       System.out.println("IMAGING: "+codeSystem+" "+code+" "+display);
                       FHIRClinicalFact cFacts = new FHIRClinicalFact();
                       cFacts.setCode(code);
                       cFacts.setCodeSystem(codeSystem);
                       cFacts.setDisplay(display);
                       factModel.getClinicalFactList().add(cFacts);                       
                       
                   }
                   //test 
                   added = slsService.applySecurityLabelsFHIR(factModel, fhirRules);
                   if (added.isEmpty()) {
                    added.add(new HCSCategory(DS4PConstants.FHIR_SCHEME_VALUE, DS4PConstants.FHIR_CONFIDENTIALITY_BASE+"N", "Normal Restrictions"));
                   }
                   
               }
               else if ("Procedure".equals(rName)) {
                   Procedure p = (Procedure)r;
                   CodeableConceptDt pConcept = p.getType();
                   List<CodingDt> pCoding = pConcept.getCoding();
                   Iterator pIter = pCoding.iterator();
                   factModel = new FHIRFactModel();
                   while (pIter.hasNext()) {
                       CodingDt c = (CodingDt)pIter.next();
                       code = c.getCode();
                       codeSystem = c.getSystem().toString();
                       display = c.getDisplay();
                       System.out.println("PROCEDURE: "+codeSystem+" "+code+" "+display);
                       FHIRClinicalFact cFacts = new FHIRClinicalFact();
                       cFacts.setCode(code);
                       cFacts.setCodeSystem(codeSystem);
                       cFacts.setDisplay(display);
                       factModel.getClinicalFactList().add(cFacts);                       
                       
                   }
                   //test 
                   added = slsService.applySecurityLabelsFHIR(factModel, fhirRules);
                   if (added.isEmpty()) {
                    added.add(new HCSCategory(DS4PConstants.FHIR_SCHEME_VALUE, DS4PConstants.FHIR_CONFIDENTIALITY_BASE+"N", "Normal Restrictions"));
                   }
                   
               }
               else {
                   //ignore this resource for now..
                    added = new ArrayList();
                    deleted = new ArrayList();
                   
               }
                   
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        removeExistingTags();
        setDeletedTagsConfAndSensitivity();
        HCSTaggingResponse res = new HCSTaggingResponse();
        res.setProcessAdds(added);
        res.setProcessDeletes(deleted);
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "enforceSecurityLabelPolicy")
    public HCSPolicyDecision enforceSecurityLabelPolicy(@WebParam(name = "subjectId") String subjectId, @WebParam(name = "role") String role, @WebParam(name = "organizationId") String organizationId, @WebParam(name = "purposeOfUse") String purposeOfUse ,@WebParam(name = "confidentialityClearance") String confidentialityClearance, @WebParam(name = "sensitivityClearance") List<String> sensitivityClearance, @WebParam(name = "requestedResourceId") String requestedResourceId, @WebParam(name = "securityTags") List<HCSCategory> securityTags) {
        //TODO write your implementation code here:
        HCSPolicyDecision res = new HCSPolicyDecision();
        
        res.setDecision("Permit");
        res.setAdviceOrObligation(new ArrayList<String>());
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "applySecurityLabelsFHIRForResourceRelease")
    public HCSLabelingResponse applySecurityLabelsFHIRForResourceRelease(@WebParam(name = "resource") String resource, @WebParam(name = "organizationId") String organizationId, @WebParam(name = "currendTags") List<HCSCategory> currentTags, @WebParam(name = "purposeOfUse") String purposeOfUse) {
        logger.debug("************HCS APPLY SECURITY LABELS REACHED*************");
        HCSLabelingResponse res = new HCSLabelingResponse();
        this.currentTags = currentTags;
        added = new ArrayList<>();
        deleted = new ArrayList<>();
        String code = "";
        String codeSystem = "";
        String display = "";
        FHIRFactModel factModel = new FHIRFactModel();
        sLabels = new ArrayList();
        fhirRules = getCASRules();
        this.internalResource = resource;
        this.purposeOfUse = purposeOfUse;
        System.out.println("FHIR STREAM: "+resource);
        
        try {               
//            XmlParser xmlParser = new XmlParser();        
//            ParserBase.ResourceOrFeed resourceOrFeed =  xmlParser.parseGeneral(new ByteArrayInputStream(resource.getBytes()));        
//            AtomFeed d = resourceOrFeed.getFeed();
//            List<AtomEntry<? extends Resource>>  test = d.getEntryList();
//            Iterator iter = test.iterator();
            
            XmlParser xmlP = new XmlParser(fhirContext);
            StringReader in = new StringReader(resource);
            bundle = xmlP.parseBundle(in);
            StringDt sDt = bundle.getLinkSelf();
            urlPart = sDt.getValueAsQueryToken();
            in.close();
            List<BundleEntry> lEntries = bundle.getEntries();
            Iterator firstBundle = lEntries.iterator();
            Bundle newBundle = new Bundle();
            int i = 0;
            while (firstBundle.hasNext()) {
                BundleEntry entry = (BundleEntry)firstBundle.next();
                i++;
                // get existing tags
                IResource iR = entry.getResource();
                    BundleEntry newEntry = new BundleEntry();
                    System.out.println("RESOURCE IS "+iR.getResourceName());
                    if (null != iR.getResourceName()) switch (iR.getResourceName()) {
                        case "Condition":
                            Condition cond = (Condition)iR;
                            CodeableConceptDt cDT = cond.getCode();
                            FHIRClinicalFact cF = new FHIRClinicalFact();
                            cF.setResourceId(cond.getId().getValue());
                            List<CodingDt> coding = cDT.getCoding();
                            Iterator codeList = coding.iterator();
                            while (codeList.hasNext()) {
                                CodingDt iCDT = (CodingDt)codeList.next();
                                cF.setCode(iCDT.getCode());
                                cF.setCodeSystem(iCDT.getSystem().toString());
                                cF.setDisplay(iCDT.getDisplay());
                                factModel.getClinicalFactList().add(cF);
                            }   
                            break;
                        case "Observation":
                            Observation obs = (Observation)iR;
                            cDT = obs.getCode();
                            cF = new FHIRClinicalFact();
                            cF.setResourceId(obs.getId().getValue());
                            coding = cDT.getCoding();
                            codeList = coding.iterator();
                            while (codeList.hasNext()) {
                                CodingDt iCDT = (CodingDt)codeList.next();
                                cF.setCode(iCDT.getCode());
                                cF.setCodeSystem(iCDT.getSystem().toString());
                                cF.setDisplay(iCDT.getDisplay());
                                factModel.getClinicalFactList().add(cF);
                            }   
                            break;
                        case "Medication":
                            Medication med = (Medication)iR;
                            String medPrescriptionId = med.getId().getValue();
                            CodeableConceptDt mCDT = med.getCode();
                            cF = new FHIRClinicalFact();
                            coding = mCDT.getCoding();
                            codeList = coding.iterator();
                            while (codeList.hasNext()) {
                                CodingDt iCDT = (CodingDt)codeList.next();
                                cF.setCode(iCDT.getCode());
                                cF.setCodeSystem(iCDT.getSystem().toString());
                                cF.setDisplay(iCDT.getDisplay());
                                cF.setResourceId(medPrescriptionId);
                                factModel.getClinicalFactList().add(cF);
                            }   
                            break;
                        case "Immunization":
                            Immunization imz = (Immunization)iR;
                            
                            cDT = imz.getVaccineType();
                            cF = new FHIRClinicalFact();
                            //no ids are found so gen one for time being
                            cF.setResourceId(imz.getId().getValue());
                            coding = cDT.getCoding();
                            codeList = coding.iterator();
                            while (codeList.hasNext()) {
                                CodingDt iCDT = (CodingDt)codeList.next();
                                cF.setCode(iCDT.getCode());
                                cF.setCodeSystem(iCDT.getSystem().toString());
                                cF.setDisplay(iCDT.getDisplay());
                                factModel.getClinicalFactList().add(cF);
                            }   
                            break;
                        default:
                            System.out.println("NO MATCH FOUND");
                            break;
                    }
            }
            //factModel should now be populated now....
            System.out.println("NUMBER OF FACTS: "+factModel.getClinicalFactList().size());
            sLabels = getSecurityLabels(factModel);
            
            //run it through PPS
            //but first get Consent and Purpose of Use
            PPSFactModel ppsModel = preparePPSFactModel();
            ppsRules = getPPSRules();
            ppsActions = ppsService.determineResourceFilters(ppsModel, ppsRules);
            
            //apply security labels...
            newPayload = redactOrApplyLabelsToMetadata();
            
            res.setAltered(true);
            res.setProcessedAtomFeed(newPayload);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        logHCSEvent();
        return res;
    }
    
    private PPSFactModel preparePPSFactModel() {
        //process through HCSCategoryList
        PPSFactModel ppsModel = new PPSFactModel();
        //handle consent
        getConsent();
        
        Iterator iter = sLabels.iterator();
        while (iter.hasNext()) {
            HCSCategory cat = (HCSCategory)iter.next();
            String action = isSensitivityAllowed(cat.getLabel());
            PPSFact fact = new PPSFact();
            fact.setPatientAuthorization(action);
            fact.setResourceId(cat.getResourceId());
            fact.setSensitivityCode(cat.getLabel());
            ppsModel.getPPSFactList().add(fact);
        }
        return ppsModel;
    }
    
    private void getConsent() {
        //make call to Jerich from here
        //for the time being just allow all
        //todo: get Jericho to change policy to support array and use of obligations
        getConsentDirective();
        if (consent.contains(">HIV<")) authorizedList.add("HIV");
        if (consent.contains(">ETH<")) authorizedList.add("ETH");
        if (consent.contains(">PSY<")) authorizedList.add("PSY");
        if (consent.contains(">SCA<")) authorizedList.add("SCA");
        if (consent.contains(">STD<")) authorizedList.add("STD");
        if (consent.contains(">SEX<")) authorizedList.add("SEX");
        if (consent.contains(">GDIS<")) authorizedList.add("GDIS");

        
        
    }
    
    private String isSensitivityAllowed(String in) {
        String out = "DENY";
        if (authorizedList.contains(in)) {
            out = "PERMIT";
        }
        return out;
    }
    
    private void removeExistingTags() {
        tempList = new ArrayList<HCSCategory>();
        Iterator iter = added.iterator();
        while (iter.hasNext()) {
            HCSCategory hcs = (HCSCategory)iter.next();
            if (!tagHasBeenAlreadyApplied(hcs)) {
                tempList.add(hcs);
            }
        }
        //set results to tempList
        added = tempList;
    }
    
    private boolean tagHasBeenAlreadyApplied(HCSCategory tag) {
        boolean res = false;
        Iterator iter = currentTags.iterator();
        while (iter.hasNext()) {
            HCSCategory hcs = (HCSCategory)iter.next();
            if (hcs.getTerm().equals(tag.getTerm())) {
                res = true;
                break;
            }
        }
        return res;
    }
    
    private void setDeletedTagsConfAndSensitivity() {
        Iterator iter = currentTags.iterator();
        while (iter.hasNext()) {
            HCSCategory hcs = (HCSCategory)iter.next();
            String term = hcs.getTerm();
            if (term.indexOf(DS4PConstants.FHIR_CONFIDENTIALITY_BASE) > -1) {
                if (!inAddedList(hcs)) {
                    deleted.add(hcs);
                }
            }
            else if(term.indexOf(DS4PConstants.FHIR_SENSITIVITY_BASE) > -1) {
                if (!inAddedList(hcs)) {
                    deleted.add(hcs);
                }
            }
            else {
                //do nothing
            }
            
            
        }
    }
    
    private boolean inAddedList(HCSCategory hcs) {
        boolean res = false;
        try {
        Iterator iter = added.iterator();
        while (iter.hasNext()) {
            HCSCategory aHCS = (HCSCategory)iter.next();
            String aURL = aHCS.getTerm().trim();
            String iURL = hcs.getTerm().trim();
            //System.out.println("****IN LIST COMPARISON: "+aTerm+" "+iTerm);
            if (iURL.equals(aURL)) {
                res = true;
                break;
            }
        }
        }
        catch (Exception ex) {
            
        }
        
        return res;
    }
    
    private String getCASRules() {
            String res = "";
            try {
                FHIRConfidentialityRuleGenerator service = new FHIRConfidentialityRuleGenerator();
                res = service.GenerateRule();
                FileHelper.writeStringToFile(res, "fhir_rules.xml");   
                System.out.println(res);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return res;
        
    }
    
    private String getPPSRules() {
        String res = "";
        try {
            PPSRuleGenerator service = new PPSRuleGenerator();
            res = service.GenerateRule(purposeOfUse);
            FileHelper.writeStringToFile(res, "pps_rules.xml");   
            System.out.println(res);
            
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
    private String getLabelNamesForSensitivityCode(String code) {
        String res = "";
        if ("HIV".equals(code)) {
            res = "Human Immunodeficiency Virus";
        }
        else if ("ETH".equals(code)) {
            res = "Substance Abuse";
        }
        else if ("PSY".equals(code)) {
            res = "Mental Health";
        }
        else if ("STD".equals(code)) {
            res = "Sexually Transmitted Disease";
        }
        else if ("GDIS".equals(code)) {
            res = "Genomic Data";
        }
        else if ("SICKLE".equals(code)) {
            res = "Sickle Cell Anemia";
        }
        else if ("TBOO".equals(code)) {
            res = "Taboo";
        }
        else if ("SEX".equals(code)) {
            res = "Sex";
        }
        else {
            res = "Unknown";
        }
        return res;
    }
    
//    private String convertAtomFeedToString(AtomFeed feed) {
//        String res = "";
//
//        try {
//            XmlComposer xml = new XmlComposer();
//            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//            xml.compose(bo, feed, true);
//            res = new String(bo.toByteArray());
//            System.out.println("XML FEED: "+res);
//            bo.close();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        
//        return res;
//    }
    
    //in case I want to look at what was there originally while testing
    private void tempStoreTags(Collection<Tag> collection) {
        tagArray = null;
        tagArray = collection;
    }
    
    private List<HCSCategory> getSecurityLabels(FHIRFactModel factModel) {
        List<HCSCategory> lC = new ArrayList();
        try {
            lC = slsService.applySecurityLabelsFHIR(factModel, fhirRules);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return lC;
    }     
    
    private List<HCSCategory> getSecurityLabel(String id) {
        List<HCSCategory> res = new ArrayList();
        Iterator iter = sLabels.iterator();
        while (iter.hasNext()) {
            HCSCategory hcs = (HCSCategory)iter.next();
            String rId = hcs.getResourceId();
            if (rId.equals(id)) {
                res.add(hcs);
            }
        }
        return res;
    }
    
    private void logHCSEvent() {
        Hcslogs log = new Hcslogs();
        FhirContext ctx = FhirContext.forDstu2();
        IParser xmlParser = new XmlParser(ctx).setPrettyPrint(true);
        
        Bundle origResource = xmlParser.parseBundle(internalResource);
        String origBundle = xmlParser.encodeBundleToString(origResource);
        
        
        log.setFhirbundle(origBundle);
        log.setMsgid(urlPart);
        StringBuffer sb = new StringBuffer();
        Iterator iter = sLabels.iterator();
        while (iter.hasNext()) {
            HCSCategory cat = (HCSCategory)iter.next();
            String id = cat.getResourceId();
            String term = cat.getTerm();
            String scheme = cat.getScheme();
            String label = cat.getLabel();
            String execR = id+" "+term+" "+scheme+" "+label+"\n\n";
            sb.append(execR);
        }
        log.setSlsexecrules(fhirRules);
        log.setSlsoutput(sb.toString());
        Iterator pIter = ppsActions.iterator();
        sb = new StringBuffer();
        while (pIter.hasNext()) {
            HCSDataFilter d = (HCSDataFilter)pIter.next();
            sb.append(d.getResourceId()+" "+d.getAction()+"\n\n");
        }
        log.setPpsexecrules(ppsRules);
        log.setPpsoutput(sb.toString());
        log.setObligations(consent);
        log.setLabeledresource(newPayload);
        log.setPurposeofuse(purposeOfUse);
        log.setMsgdate(new Date());
        try {
            hcslogger.saveHCSEvent(log);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String redactOrApplyLabelsToMetadata() {
        String labeledResource = null;
        bundleOut = new Bundle();
        List<BundleEntry> eList = bundle.getEntries();
        Iterator iter = eList.iterator();
        while (iter.hasNext()) {
            BundleEntry entry = (BundleEntry)iter.next();
            IResource resource = entry.getResource();
            String resourceId = resource.getId().getValue();
            //see if we are redacting it
            if (!setForRedaction(resourceId)) {
                //then apply labels if any
                resource = setLabels(resource);
                BundleEntry newEntry = new BundleEntry();
                newEntry.setResource(resource);
                bundleOut.addEntry(newEntry);
            }
            else {
                //this is being done for demo purpose only --- should actually delete entry and say something in budle
                List<CodingDt> allPopulatedChildElementsOfType = resource.getAllPopulatedChildElementsOfType(CodingDt.class); 
                allPopulatedChildElementsOfType.get(0).setDisplay("<font color=\"#ff0000\">Item has been redacted</font>");
                BundleEntry newEntry = new BundleEntry();
                newEntry.setResource(resource);
                bundleOut.addEntry(newEntry);       
            }
        }
        //convert bundle to string
        FhirContext ctx = FhirContext.forDstu2();
        IParser xmlParser = new XmlParser(ctx).setPrettyPrint(true);
        
        labeledResource = xmlParser.encodeBundleToString(bundleOut);
        return labeledResource;
    }
    
    private IResource setLabels(IResource resource) {
        Iterator iter = sLabels.iterator();
        String pId = resource.getId().getValue();
        List<CodingDt> securityLabels = new ArrayList();
        while (iter.hasNext()) {
            HCSCategory hcs = (HCSCategory)iter.next();
            String id = hcs.getResourceId();
            String term = hcs.getTerm();
            String display = hcs.getLabel();
            StringTokenizer  st = new StringTokenizer(term, "#");
            String base = st.nextToken();
            String code = st.nextToken();
            if (pId.equals(id)) {
                if (base.contains("Confidentiality")) {
                    CodingDt securityLabel = new CodingDt(CONFIDENTIALITY_SYSTEM, code).setDisplay(display);
                    securityLabels.add(securityLabel);
                }
                else if (base.contains("InformationSensitivityPolicy")) {
                    CodingDt securityLabel = new CodingDt(SENSITIVITY_SYSTEM, code).setDisplay(""); // don't want to say much about these
                    securityLabels.add(securityLabel);
                }
                else {
                    //we're only demostrating these 2 for time being
                }
            }
        }
        if (securityLabels.size() > 0) {
            //add labels and set mod date
            ResourceMetadataMap metadataMap = new ResourceMetadataMap();
            metadataMap.put(ResourceMetadataKeyEnum.SECURITY_LABELS, securityLabels);

            //add some other metadata
            InstantDt date = new InstantDt();
            date.setValue(new Date());
            metadataMap.put(ResourceMetadataKeyEnum.UPDATED, date);

            //add all metadata to resource
            resource.setResourceMetadata(metadataMap);
            
            
        }
        return resource;
    }
    
    private boolean setForRedaction(String resourceId) {
        boolean test = false;
        Iterator iter = ppsActions.iterator();
        while (iter.hasNext()) {
            HCSDataFilter f = (HCSDataFilter)iter.next();
            String s = f.getResourceId();
            String action = f.getAction();
            if (s.equals(resourceId)) {
                if (action.equals("REDACT")) {
                    test = true;
                    break;
                }
            }
        }
        return test;
    }
    
    private String getConsentDirective() {
        //for demo purposes this will always be alices consent directive ...
        consent = null;
        try {
            JerichoConsentDirective cd = JerichoConsentDirective.fetchFromServer("www.consentral.com", 11014, "/FHIRService/search", "NonUmaPatient");
            consent = cd.getXacmlContent();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return consent;
    }
}
