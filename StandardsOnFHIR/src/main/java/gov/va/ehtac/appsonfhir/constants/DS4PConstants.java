/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Duane DeCouteau
 */
public class DS4PConstants {

    public static final String PROBLEM_LIST_LOINC_CODE = "11450-4";
    public static final String MEDICATION_LIST_LOINC_CODE = "10160-0";
    public static final String ALLERGY_ADVERSE_REACTIONS_LOINC_CODE = "48765-2";
    public static final String LAB_RESULTS_LOINC_CODE = "30954-2";
    
    public static final String PATIENT_OBLIGATIONS = "urn:oasis:names:tc:xspa:2.0:resource:patient:obligations";
    
    public static final String PATIENT_REDACT_ETH = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:ETH";
    public static final String PATIENT_MASK_ETH = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:ETH";
    public static final String PATIENT_REDACT_PSY = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:PSY";
    public static final String PATIENT_MASK_PSY = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:PSY";
    public static final String PATIENT_REDACT_HIV = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:HIV";
    public static final String PATIENT_MASK_HIV = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:HIV";
    public static final String PATIENT_REDACT_GDIS = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:GDIS";
    public static final String PATIENT_MASK_GDIS = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:GDIS";
    public static final String PATIENT_REDACT_SDV = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:SDV";
    public static final String PATIENT_MASK_SDV = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:SDV";
    public static final String PATIENT_REDACT_SEX = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:SEX";
    public static final String PATIENT_MASK_SEX = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:SEX";
    public static final String PATIENT_REDACT_STD = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:STD";
    public static final String PATIENT_MASK_STD = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:STD";
    public static final String PATIENT_REDACT_TBOO = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:TBOO";
    public static final String PATIENT_MASK_TBOO = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:TBOO";
    
    public static final String PATIENT_REDACT_CONSTRUCT = "urn:oasis:names:tc:xspa:2.0:resource:patient:redact:";
    public static final String PATIENT_MASK_CONSTRUCT = "urn:oasis:names:tc:xspa:2.0:resource:patient:mask:";

    public static final String ORG_OBLIGATIONS = "urn:oasis:names:tc:xspa:2.0:resource:org:obligations";
    public static final String ORG_PRIVACY_LAW_CONSTRUCT = "urn:oasis:names:tc:xspa:2.0:resource:org:us-privacy-law:";
    public static final String ORG_REFRAIN_POLICY_CONSTRUCT = "urn:oasis:names:tc:xspa:2.0:resource:org:refrain-policy:";
    public static final String ORG_DOCUMENT_HANDLING_CONSTRUCT = "urn:oasis:names:tc:xspa:2.0:resource:org:document-handling:";
            
    public static final String MITRE_PATIENT_AUTHORIZATION = "urn:oasis:names:tc:xspa:2.0:resource:patient:authorization";
    
    //XSPA CONSTANTS FOR DS4P PUSH
    public static String RESOURCE_NWHIN_SERVICE_NS = "urn:gov:hhs:fha:nhinc:service-type";

    //As defined by XSPA and UnderlyingStandards
    public static final String PERMIT = "Permit";
    public static final String DENY   = "Deny";
    public static final String INDETERMINATE   = "Indeterminate";
    public static final String NOT_APPLICABLE = "NotApplicable";
    public static final String STRING = "http://www.w3.org/2001/XMLSchema#string";
    public static final String TIME = "http://www.w3.org/2001/XMLSchema#time";
    public static String ACCESS_SUBJECT = "urn:oasis:names:tc:xacml:1.0:subject-category:access-subject";

    public static String SUBJECT_ID_NS = "urn:oasis:names:tc:xacml:2.0:subject:subject-id";
    public static String SUBJECT_LOCALITY_NS = "urn:oasis:names:tc:xacml:2.0:subject:locality";
    public static String SUBJECT_PURPOSE_OF_USE_NS = "urn:oasis:names:tc:xspa:1.0:subject:purposeofuse";
    public static String SUBJECT_ORGANIZATION_NS = "urn:oasis:names:tc:xspa:1.0:subject:organization";
    public static String SUBJECT_ORGANIZATION_ID_NS = "urn:oasis:names:tc:xspa:1.0:subject:organization-id";
    public static String SUBJECT_STRUCTURED_ROLE_NS = "urn:oasis:names:tc:xacml:2.0:subject:role";
        
    //XSPA STUFF FOR RESOURCE
    public static String RESOURCE_ID_NS = "urn:oasis:names:tc:xacml:1.0:resource:resource-id";
    public static String RESOURCE_TYPE_NS = "urn:oasis:names:tc:xspa:2.0:resource:type";
    public static String RESOURCE_ACTION_ID_NS = "urn:oasis:names:tc:xacml:1.0:action:action-id";
    public static String RESOURCE_LOCALITY_NS = "urn:oasis:names:tc:xspa:1.0:environment:locality";
    
    //DIRECT RECEIVING ATTRIBUTES
    public static String SUBJECT_SERVICE_PERMISSIONS = "urn:oasis:names:tc:xspa:2.0:subject:service:permissions";
    public static String SUBJECT_SENSITIVITY_PRIVILEGES = "urn:oasis:names:tc:xspa:2.0:subject:sensitivity:privileges";
    
    //DIRECT REQUIRED ATTRIBUTES
    public static String RESOURCE_SERVICE_PERMISSIONS = "urn:oasis:names:tc:xspa:2.0:resource:required:service:permissions";
    public static String RESOURCE_SENSITIVITY_PERMISSIONS = "urn:oasis:names:tc:xspa:2.0:resource:required:sensitivity:permissions";
    public static String RESOURCE_INTENDED_RECIPIENT = "urn:oasis:names:tc:xspa:2.0:resource:intended:recipient";
    public static String RESOURCE_INTENDED_PURPOSEOFUSE = "urn:oasis:names:tc:xspa:2.0:resource:intended:purposeofuse";
    
    //CDA ITEMS
    public static String XACML_POLICY_TEMPLATE_ID = "2.16.840.1.113883.3.445.16";
    
    //Obligation Resource Names
    public static String RESOURCE_DATA_REDACTION = "DS4PRedactAuthorization";
    public static String RESOURCE_DATA_MASKING = "DS4PMaskAuthorization";
    public static String RESOURCE_US_PRIVACY_LAW = "DS4PUSPrivacyLaw";
    public static String RESOURCE_REFRAIN_POLICY = "DS4PRefrainPolicy";
    public static String RESOURCE_DOCUMENT_HANDLING = "DS4PDocumentHandling";
    //Obligation Resource Namespace
    public static String RESOURCE_DATA_REDACTION_NS = "urn:oasis:names:tc:xspa:2.0:resource:redactauthorization";
    public static String RESOURCE_DATA_MASKING_NS = "urn:oasis:names:tc:xspa:2.0:resource:maskauthorization";
    public static String RESOURCE_US_PRIVACY_LAW_NS = "urn:oasis:names:tc:xspa:2.0:resource:org:us-privacy-law";
    public static String RESOURCE_REFRAIN_POLICY_NS = "urn:oasis:names:tc:xspa:2.0:resource:org:refrain-policy";
    public static String RESOURCE_DOCUMENT_HANDLING_NS = "urn:oasis:names:tc:xspa:2.0:resource:org:document-handling";
    
    
    //Exchange Resource Names
    public static String RESOURCE_DOCUMENT_QUERY = "DocumentQuery";
    public static String RESOURCE_DOCUMENT_RETRIEVE = "DocumentRetrieve";
    
    //FHIR TAG VALUES
    public static String FHIR_SCHEME_VALUE = "http://hl7.org/fhir/tag";
    public static String FHIR_CONFIDENTIALITY_BASE = "http://hl7.org/implement/standards/fhir/v3/Confidentiality#";
    public static String FHIR_USPRIVACY_LAW_BASE = "http://hl7.org/implement/standards/fhir/v3/ActUSPrivacyLaw#";
    public static String FHIR_PURPOSE_OF_USE_BASE = "http://hl7.org/implement/standards/fhir/v3/ActReason#";
    public static String FHIR_REFRAIN_POLICY_BASE = "http://hl7.org/implement/standards/fhir/v3/ActCode/RefrainPolicy#";
    public static String FHIR_OBLIGATION_POLICY_BASE = "http://hl7.org/implement/standards/fhir/v3/ActCode/ObligationPolicy#";
    public static String FHIR_INTEGRITY_BASE = "http://hl7.org/implement/standards/fhir/v3/ObservationValue/SECINTOBV#";
    public static String FHIR_SENSITIVITY_BASE = "http://hl7.org/implement/standards/fhir/v3/ActCode/InformationSensitivityPolicy#";
}
