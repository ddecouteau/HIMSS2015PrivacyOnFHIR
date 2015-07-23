/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir.hcsservices.ws;

import ca.uhn.fhir.context.FhirContext;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import ca.uhn.fhir.parser.XmlParser;
import ca.uhn.fhir.parser.BaseParser;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.Tag;
import ca.uhn.fhir.model.api.TagList;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;

import gov.va.ehtac.hl7.hcs.beans.FHIRPolicyFactModel;
import gov.va.ehtac.hl7.hcs.beans.PatientIdentifier;
import gov.va.ehtac.hl7.hcs.constants.DS4PConstants;
import gov.va.ehtac.hl7.hcs.model.PolicyEnforcementRequest;
import gov.va.ehtac.hl7.hcs.model.PolicyEnforcementResponse;

/**
 *
 * @author Socraticgrid Staff
 */
@WebService(name = "HCSPolicyDecisionPoint", targetNamespace = "ws.hcs.hl7.socraticgrid.org")
public class HCSPolicyDecisionPoint {
    private PolicyEnforcementRequest request;
    private PolicyEnforcementResponse response;
    private FHIRPolicyFactModel policyFacts;
    final private FhirContext fhirContext = FhirContext.forDev();    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "enforcePolicy")
    public PolicyEnforcementResponse enforcePolicy(@WebParam(name = "document") String document, @WebParam(name = "purposeOfUse") String purposeOfUse, 
                                                    @WebParam(name = "subjectId") String subjectId, @WebParam(name = "role") String role,
                                                    @WebParam(name = "permissions") List<String> permissions) {
        this.request = new PolicyEnforcementRequest();
        request.setDocument(document);
        request.setPurposeOfUse(purposeOfUse);
        request.setRole(role);
        if (permissions == null) {
                 request.setSensitivityPermissions(null);                                          
        }
        else {
            request.getSensitivityPermissions().addAll(permissions);
        }
        this.response = new PolicyEnforcementResponse();
        System.out.println(request.getDocument());
        //set default which is deny
        //response.setDecision(DENY_DECISION);
        //response.getObligationsOrAdvice().add("No Addtional Guidance");
        try {
            Collection<String> requiredPermissions = new ArrayList();
            //for testing interface till its plugged into back end
            XmlParser xmlParser = new XmlParser(fhirContext);      
            
            Bundle  bundle =  xmlParser.parseBundle(request.getDocument());
            List<BundleEntry> lEntry = bundle.getEntries();
            Iterator iter = lEntry.iterator();
            while (iter.hasNext()) {
                BundleEntry entry = (BundleEntry)iter.next();
                TagList iRes = entry.getCategories();
                Iterator iter2 = iRes.iterator();
                while (iter2.hasNext()) {
                    Tag ac = (Tag)iter2.next();
                    String lbl = ac.getTerm();
                    System.out.println("AtomFeed: "+lbl);
                    if (lbl.indexOf("InformationSensitivityPolicy") > -1) {
                        requiredPermissions.add(lbl);
                    }
                }
            }
            if (request.getPurposeOfUse().equals("TREAT")) {
                if (request.getRole().equals("MD/Allopath")) {
                    if (!requiredPermissions.isEmpty()) {
                        if (request.getSensitivityPermissions() != null) {
                            displayCollectionPermissions(requiredPermissions, request.getSensitivityPermissions());
                            if (request.getSensitivityPermissions().containsAll(requiredPermissions)) {
                                response.setDecision(DS4PConstants.PERMIT);
                                response.getObligationsOrAdvice().add("No Redisclosure Without Consent");                    
                            }
                            else {
                                response.setDecision(DS4PConstants.DENY);
                                response.getObligationsOrAdvice().add("Sensitivity Permissions Not Meet");
                            }
                        }
                        else {
                            response.setDecision("Deny");
                            response.getObligationsOrAdvice().add("No Permissions were Asserted but Required Permissions Exist");
                        }
                    }
                    else {
                        response.setDecision(DS4PConstants.PERMIT);
                        response.getObligationsOrAdvice().add("No Constraints");                                        
                    }
                }
                else {
                    response.setDecision(DS4PConstants.DENY);
                    response.getObligationsOrAdvice().add("Role Not Allowed");  
                }
            }
            else if (request.getPurposeOfUse().equals("ETREAT")) {
                response.setDecision(DS4PConstants.PERMIT);
                response.getObligationsOrAdvice().add("Break the Glass - ETREAT");
            }
            else {
                response.setDecision(DS4PConstants.DENY);
                response.getObligationsOrAdvice().add("POU Not Allowed");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        } 
        return response;
    }
    
    private PolicyEnforcementResponse processPolicyAgainstDrools(String document, String purposeOfUse, String subjectId, String role,
                                                    List<String> permissions) {
        this.response = new PolicyEnforcementResponse();
        policyFacts = new FHIRPolicyFactModel();
        policyFacts.setSubjectId(subjectId);
        policyFacts.setPurposeOfUse(purposeOfUse);
        policyFacts.setRole(role);
        String patientGiven = "";
        String patientFamily = "";
        List<PatientIdentifier> pIdList = new ArrayList();
        if (permissions == null) {
                 policyFacts.setSensitivityPermissions(null);                                          
        }
        else {
            policyFacts.getSensitivityPermissions().addAll(permissions);
        }
        
        try {
            Collection<String> requiredPermissions = new ArrayList();
            //for testing interface till its plugged into back end
            XmlParser xmlParser = new XmlParser(fhirContext);      
            
            Bundle  bundle =  xmlParser.parseBundle(request.getDocument());
            List<BundleEntry> lEntry = bundle.getEntries();
            Iterator iter = lEntry.iterator();
            while (iter.hasNext()) {
                BundleEntry entry = (BundleEntry)iter.next();
                TagList iRes = entry.getCategories();
                Iterator iter2 = iRes.iterator();
                while(iter2.hasNext()) {
                    Tag ac = (Tag)iter2.next();
                    String lbl = ac.getTerm();
                    System.out.println("AtomFeed: "+lbl);
                    if (lbl.indexOf("InformationSensitivityPolicy") > -1) {
                        requiredPermissions.add(lbl);
                    }
                }
                        
            }
            policyFacts.setFamilyName(patientFamily);
            policyFacts.setGivenName(patientGiven);
            policyFacts.getRequiredSensitivityPermissions().addAll(requiredPermissions);
            policyFacts.getResourceId().addAll(pIdList);
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return response;
    }
    
    private void displayCollectionPermissions(Collection requiredPermissions, Collection sensitivityPermissions) {
        Iterator iter = requiredPermissions.iterator();
        System.out.println("Required Permissions");
        while (iter.hasNext()) {
            String permission = (String)iter.next();
            System.out.println(permission);
        }
        System.out.println("Asserts Permissions");
        Iterator iter2 = sensitivityPermissions.iterator();
        while (iter2.hasNext()) {
            String sPermission = (String)iter2.next();
            System.out.println(sPermission);
        }
    }
    
}
