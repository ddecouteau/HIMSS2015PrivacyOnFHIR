//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.04 at 08:21:37 AM MDT 
//


package gov.va.ehtac.hl7.hcs.policy.reference;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.va.ds4p.policy.reference package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _State_QNAME = new QName("urn:hl7-org:v3", "state");
    private final static QName _City_QNAME = new QName("urn:hl7-org:v3", "city");
    private final static QName _Country_QNAME = new QName("urn:hl7-org:v3", "country");
    private final static QName _PatientTelcom_QNAME = new QName("urn:hl7-org:v3", "patientTelcom");
    private final static QName _StreetAddressLine_QNAME = new QName("urn:hl7-org:v3", "streetAddressLine");
    private final static QName _Description_QNAME = new QName("urn:hl7-org:v3", "Description");
    private final static QName _PatientGender_QNAME = new QName("urn:hl7-org:v3", "patientGender");
    private final static QName _OrganizationName_QNAME = new QName("urn:hl7-org:v3", "organizationName");
    private final static QName _PatientAuthor_QNAME = new QName("urn:hl7-org:v3", "patientAuthor");
    private final static QName _Telcom_QNAME = new QName("urn:hl7-org:v3", "telcom");
    private final static QName _ImageURL_QNAME = new QName("urn:hl7-org:v3", "imageURL");
    private final static QName _PatientConsentConstraint_QNAME = new QName("urn:hl7-org:v3", "PatientConsentConstraint");
    private final static QName _DefaultPatientBirthDate_QNAME = new QName("urn:hl7-org:v3", "defaultPatientBirthDate");
    private final static QName _PostalCode_QNAME = new QName("urn:hl7-org:v3", "postalCode");
    private final static QName _Family_QNAME = new QName("urn:hl7-org:v3", "family");
    private final static QName _County_QNAME = new QName("urn:hl7-org:v3", "county");
    private final static QName _DisplayText_QNAME = new QName("urn:hl7-org:v3", "displayText");
    private final static QName _Given_QNAME = new QName("urn:hl7-org:v3", "given");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.va.ds4p.policy.reference
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApplicableUSLaws }
     * 
     */
    public ApplicableUSLaws createApplicableUSLaws() {
        return new ApplicableUSLaws();
    }

    /**
     * Create an instance of {@link ActUSPrivacyLaw }
     * 
     */
    public ActUSPrivacyLaw createActUSPrivacyLaw() {
        return new ActUSPrivacyLaw();
    }

    /**
     * Create an instance of {@link DefaultCustodianInfo }
     * 
     */
    public DefaultCustodianInfo createDefaultCustodianInfo() {
        return new DefaultCustodianInfo();
    }

    /**
     * Create an instance of {@link Addr }
     * 
     */
    public Addr createAddr() {
        return new Addr();
    }

    /**
     * Create an instance of {@link DefaultPatientDemographics }
     * 
     */
    public DefaultPatientDemographics createDefaultPatientDemographics() {
        return new DefaultPatientDemographics();
    }

    /**
     * Create an instance of {@link Name }
     * 
     */
    public Name createName() {
        return new Name();
    }

    /**
     * Create an instance of {@link ImpliedConfidentiality }
     * 
     */
    public ImpliedConfidentiality createImpliedConfidentiality() {
        return new ImpliedConfidentiality();
    }

    /**
     * Create an instance of {@link Confidentiality }
     * 
     */
    public Confidentiality createConfidentiality() {
        return new Confidentiality();
    }

    /**
     * Create an instance of {@link ManufacturingModelName }
     * 
     */
    public ManufacturingModelName createManufacturingModelName() {
        return new ManufacturingModelName();
    }

    /**
     * Create an instance of {@link OrganizationConsentPolicyInfo }
     * 
     */
    public OrganizationConsentPolicyInfo createOrganizationConsentPolicyInfo() {
        return new OrganizationConsentPolicyInfo();
    }

    /**
     * Create an instance of {@link AssignedAuthoringDevice }
     * 
     */
    public AssignedAuthoringDevice createAssignedAuthoringDevice() {
        return new AssignedAuthoringDevice();
    }

    /**
     * Create an instance of {@link SoftwareName }
     * 
     */
    public SoftwareName createSoftwareName() {
        return new SoftwareName();
    }

    /**
     * Create an instance of {@link HumanReadibleText }
     * 
     */
    public HumanReadibleText createHumanReadibleText() {
        return new HumanReadibleText();
    }

    /**
     * Create an instance of {@link AssignedAuthor }
     * 
     */
    public AssignedAuthor createAssignedAuthor() {
        return new AssignedAuthor();
    }

    /**
     * Create an instance of {@link AssignedPerson }
     * 
     */
    public AssignedPerson createAssignedPerson() {
        return new AssignedPerson();
    }

    /**
     * Create an instance of {@link XspaPatientObligations }
     * 
     */
    public XspaPatientObligations createXspaPatientObligations() {
        return new XspaPatientObligations();
    }

    /**
     * Create an instance of {@link XspaPatientObligation }
     * 
     */
    public XspaPatientObligation createXspaPatientObligation() {
        return new XspaPatientObligation();
    }

    /**
     * Create an instance of {@link OrgObligationPolicyEntry }
     * 
     */
    public OrgObligationPolicyEntry createOrgObligationPolicyEntry() {
        return new OrgObligationPolicyEntry();
    }

    /**
     * Create an instance of {@link ObligationPolicy }
     * 
     */
    public ObligationPolicy createObligationPolicy() {
        return new ObligationPolicy();
    }

    /**
     * Create an instance of {@link AllergyListSensitivityRules }
     * 
     */
    public AllergyListSensitivityRules createAllergyListSensitivityRules() {
        return new AllergyListSensitivityRules();
    }

    /**
     * Create an instance of {@link ClinicalTaggingRule }
     * 
     */
    public ClinicalTaggingRule createClinicalTaggingRule() {
        return new ClinicalTaggingRule();
    }

    /**
     * Create an instance of {@link ClinicalFact }
     * 
     */
    public ClinicalFact createClinicalFact() {
        return new ClinicalFact();
    }

    /**
     * Create an instance of {@link ActReason }
     * 
     */
    public ActReason createActReason() {
        return new ActReason();
    }

    /**
     * Create an instance of {@link ActInformationSensitivityPolicy }
     * 
     */
    public ActInformationSensitivityPolicy createActInformationSensitivityPolicy() {
        return new ActInformationSensitivityPolicy();
    }

    /**
     * Create an instance of {@link OrgObligationPolicyDocument }
     * 
     */
    public OrgObligationPolicyDocument createOrgObligationPolicyDocument() {
        return new OrgObligationPolicyDocument();
    }

    /**
     * Create an instance of {@link ClinicalDomain }
     * 
     */
    public ClinicalDomain createClinicalDomain() {
        return new ClinicalDomain();
    }

    /**
     * Create an instance of {@link ApplicableObligationPolicies }
     * 
     */
    public ApplicableObligationPolicies createApplicableObligationPolicies() {
        return new ApplicableObligationPolicies();
    }

    /**
     * Create an instance of {@link ApplicableConfidentialities }
     * 
     */
    public ApplicableConfidentialities createApplicableConfidentialities() {
        return new ApplicableConfidentialities();
    }

    /**
     * Create an instance of {@link PatientRequestedAction }
     * 
     */
    public PatientRequestedAction createPatientRequestedAction() {
        return new PatientRequestedAction();
    }

    /**
     * Create an instance of {@link PatientSensitivityConstraint }
     * 
     */
    public PatientSensitivityConstraint createPatientSensitivityConstraint() {
        return new PatientSensitivityConstraint();
    }

    /**
     * Create an instance of {@link OrganizationPolicy }
     * 
     */
    public OrganizationPolicy createOrganizationPolicy() {
        return new OrganizationPolicy();
    }

    /**
     * Create an instance of {@link OrganizationTaggingRules }
     * 
     */
    public OrganizationTaggingRules createOrganizationTaggingRules() {
        return new OrganizationTaggingRules();
    }

    /**
     * Create an instance of {@link RefrainPolicy }
     * 
     */
    public RefrainPolicy createRefrainPolicy() {
        return new RefrainPolicy();
    }

    /**
     * Create an instance of {@link PurposeOfUse }
     * 
     */
    public PurposeOfUse createPurposeOfUse() {
        return new PurposeOfUse();
    }

    /**
     * Create an instance of {@link Author }
     * 
     */
    public Author createAuthor() {
        return new Author();
    }

    /**
     * Create an instance of {@link LabResultsSensitivityRules }
     * 
     */
    public LabResultsSensitivityRules createLabResultsSensitivityRules() {
        return new LabResultsSensitivityRules();
    }

    /**
     * Create an instance of {@link ImmunizationsSensitivityRules }
     * 
     */
    public ImmunizationsSensitivityRules createImmunizationsSensitivityRules() {
        return new ImmunizationsSensitivityRules();
    }

    /**
     * Create an instance of {@link ProblemListSensitivityRules }
     * 
     */
    public ProblemListSensitivityRules createProblemListSensitivityRules() {
        return new ProblemListSensitivityRules();
    }

    /**
     * Create an instance of {@link ApplicableSensitivityCodes }
     * 
     */
    public ApplicableSensitivityCodes createApplicableSensitivityCodes() {
        return new ApplicableSensitivityCodes();
    }

    /**
     * Create an instance of {@link DefaultAuthorInfo }
     * 
     */
    public DefaultAuthorInfo createDefaultAuthorInfo() {
        return new DefaultAuthorInfo();
    }

    /**
     * Create an instance of {@link MedicationListSensitivityRules }
     * 
     */
    public MedicationListSensitivityRules createMedicationListSensitivityRules() {
        return new MedicationListSensitivityRules();
    }

    /**
     * Create an instance of {@link ApplicableRefrainPolicies }
     * 
     */
    public ApplicableRefrainPolicies createApplicableRefrainPolicies() {
        return new ApplicableRefrainPolicies();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "state")
    public JAXBElement<String> createState(String value) {
        return new JAXBElement<String>(_State_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "city")
    public JAXBElement<String> createCity(String value) {
        return new JAXBElement<String>(_City_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "country")
    public JAXBElement<String> createCountry(String value) {
        return new JAXBElement<String>(_Country_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "patientTelcom")
    public JAXBElement<String> createPatientTelcom(String value) {
        return new JAXBElement<String>(_PatientTelcom_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "streetAddressLine")
    public JAXBElement<String> createStreetAddressLine(String value) {
        return new JAXBElement<String>(_StreetAddressLine_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "Description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "patientGender")
    public JAXBElement<String> createPatientGender(String value) {
        return new JAXBElement<String>(_PatientGender_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "organizationName")
    public JAXBElement<String> createOrganizationName(String value) {
        return new JAXBElement<String>(_OrganizationName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "patientAuthor")
    public JAXBElement<Boolean> createPatientAuthor(Boolean value) {
        return new JAXBElement<Boolean>(_PatientAuthor_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "telcom")
    public JAXBElement<String> createTelcom(String value) {
        return new JAXBElement<String>(_Telcom_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "imageURL")
    public JAXBElement<String> createImageURL(String value) {
        return new JAXBElement<String>(_ImageURL_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "PatientConsentConstraint")
    public JAXBElement<Object> createPatientConsentConstraint(Object value) {
        return new JAXBElement<Object>(_PatientConsentConstraint_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "defaultPatientBirthDate")
    public JAXBElement<XMLGregorianCalendar> createDefaultPatientBirthDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DefaultPatientBirthDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "postalCode")
    public JAXBElement<String> createPostalCode(String value) {
        return new JAXBElement<String>(_PostalCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "family")
    public JAXBElement<String> createFamily(String value) {
        return new JAXBElement<String>(_Family_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "county")
    public JAXBElement<String> createCounty(String value) {
        return new JAXBElement<String>(_County_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "displayText")
    public JAXBElement<String> createDisplayText(String value) {
        return new JAXBElement<String>(_DisplayText_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "given")
    public JAXBElement<String> createGiven(String value) {
        return new JAXBElement<String>(_Given_QNAME, String.class, null, value);
    }

}
