package gov.va.ehtac.myappsonfhir.dataresults;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.*;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.parser.XmlParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.server.EncodingEnum;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mochaholic on 26/03/2015.
 */
public class PoFPatient {

    Patient me;

    List<MedicationPrescription> medicationPrescriptions = new ArrayList<>();
    List<Medication> medications = new ArrayList<>();
    List<Immunization> immunizations = new ArrayList<>();
    List<Organization> organizations = new ArrayList<>();
    List<Condition> conditions = new ArrayList<>();
    List<Encounter> encounters = new ArrayList<>();
    FhirContext context = FhirContext.forDstu2();

    public Patient getMe() {
        return me;
    }

    public List<MedicationPrescription> getMedicationPrescriptions() {
        return medicationPrescriptions;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public List<Immunization> getImmunizations() {
        return immunizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }
    
    public Bundle getMedicationBundle() {
        Bundle bundle = new Bundle();
        Iterator iter = medications.iterator();
        while (iter.hasNext()) {
            Medication med = (Medication)iter.next();
            BundleEntry entry = new BundleEntry();
            entry.setResource(med);
            bundle.getEntries().add(entry);
        }
        return bundle;
    }

    private void loadMedicationPrescriptionsAndMedications (FhirContext fhirContext, String fhirBase)
    {
        IGenericClient client = fhirContext.newRestfulGenericClient(fhirBase);
        client.setEncoding(EncodingEnum.XML);

        Bundle results = client.search(new UriDt(fhirBase + "MedicationPrescription?patient=" + me.getId().getIdPart()));


        boolean theresMore;
        do
        {
            theresMore = false;

        for (BundleEntry ent: results.getEntries())
        {
            MedicationPrescription medicationPrescription = (MedicationPrescription) ent.getResource();
            medicationPrescriptions.add(medicationPrescription);
            ResourceReferenceDt medRef = medicationPrescription.getMedication();
            if (medRef.getReference().isLocal())
            {
                Medication med = (Medication) medRef.getResource();
                if (med != null)
                    medications.add(med);

            } else if (!medRef.isEmpty())// if resource is external
            {
                Medication med = client.read(Medication.class, medRef.getReference());
                medications.add(med);

            }
        }
            if (! results.getLinkNext().isEmpty())
            {
                theresMore = true;
                results = client.loadPage().next(results).execute();
    }

        } while (theresMore);

    }

    private void loadConditionsAndEncountersAndServiceProviders (FhirContext fhirContext, String fhirBase)
    {
        IGenericClient client = fhirContext.newRestfulGenericClient(fhirBase);
        client.setEncoding(EncodingEnum.XML);
        Bundle results = client.search(new UriDt(fhirBase + "Condition?patient=" + me.getId().getIdPart()));
        boolean theresMore;
        do
        {
            theresMore = false;
        for (BundleEntry ent: results.getEntries())
        {
            Condition condition = (Condition) ent.getResource();
            if (condition != null)
                conditions.add(condition);
        }

            if (! results.getLinkNext().isEmpty())
            {
                theresMore = true;
                results = client.loadPage().next(results).execute();
            }

        } while (theresMore);

        for (Condition cond : conditions)
        {
            ResourceReferenceDt encRef = cond.getEncounter();
            if (encRef.getReference().isLocal())
            {
                Encounter enc = (Encounter) encRef.getResource();
                if (enc != null)
                    encounters.add(enc);

            } else if (! encRef.isEmpty()) // if resource is external
            {
                Encounter enc = client.read(Encounter.class, encRef.getReference());
                encounters.add(enc);
            }
        }

        for (Encounter enc : encounters)
        {
            ResourceReferenceDt orgRef = enc.getServiceProvider();
            if (orgRef.getReference().isLocal())
            {
                Organization org = (Organization) orgRef.getResource();
                if (org != null)
                    organizations.add(org);
            }
            else if (! orgRef.isEmpty())
            {
                Organization org = client.read(Organization.class, orgRef.getReference());
                organizations.add(org);
            }
        }
    }
    private void loadImmunizations(FhirContext fhirContext, String fhirBase) {

        IGenericClient client = fhirContext.newRestfulGenericClient(fhirBase);
        Bundle results = client.search(new UriDt(fhirBase + "Immunization?subject=" + me.getId().getIdPart()));
        for (BundleEntry ent: results.getEntries())
        {
            Immunization immunization = (Immunization) ent.getResource();
            if (immunization != null)
                immunizations.add(immunization);
        }

    }
    
    public void loadImmunizationsUMA(FhirContext fhirContext, String fhirBase) {
        immunizations.clear();
        IGenericClient client = fhirContext.newRestfulGenericClient(fhirBase);
        Bundle results = client.search(new UriDt(fhirBase + "Immunization?subject=" + me.getId().getIdPart()));
        for (BundleEntry ent: results.getEntries())
        {
            Immunization immunization = (Immunization) ent.getResource();
            if (immunization != null)
                immunizations.add(immunization);
        }        
    }

    private void loadRelatedResources (FhirContext fhirContext, String fhirBase)
    {
        
        loadConditionsAndEncountersAndServiceProviders(fhirContext,fhirBase);
        loadImmunizations(fhirContext, fhirBase);
        loadMedicationPrescriptionsAndMedications(fhirContext,fhirBase);        

    }

    public void reloadHCSEncounters(String sbundle) {
        try {
            XmlParser xmlP = new XmlParser(context);
            StringReader in = new StringReader(sbundle);
            Bundle bundle = xmlP.parseBundle(in);
            encounters.clear();
            List<BundleEntry> lEntries = bundle.getEntries();
            Iterator iter = lEntries.iterator();
            while (iter.hasNext()) {
                BundleEntry entry = (BundleEntry)iter.next();
                Encounter enc = (Encounter)entry.getResource();
                encounters.add(enc);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void reloadHCSConditions(String sbundle) {
        try {
            XmlParser xmlP = new XmlParser(context);
            StringReader in = new StringReader(sbundle);
            Bundle bundle = xmlP.parseBundle(in);
            conditions.clear();
            List<BundleEntry> lEntries = bundle.getEntries();
            Iterator iter = lEntries.iterator();
            while (iter.hasNext()) {
                BundleEntry entry = (BundleEntry)iter.next();
                Condition cond = (Condition)entry.getResource();
                conditions.add(cond);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    public void reloadHCSMedications(String sbundle) {
        try {
            XmlParser xmlP = new XmlParser(context);
            StringReader in = new StringReader(sbundle);
            Bundle bundle = xmlP.parseBundle(in);
            medications.clear();
            List<BundleEntry> lEntries = bundle.getEntries();
            Iterator iter = lEntries.iterator();
            while (iter.hasNext()) {
                BundleEntry entry = (BundleEntry)iter.next();
                Medication meds = (Medication)entry.getResource();
                medications.add(meds);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }        
        
    }
    public void reloadHCSImmunizations(String sbundle) {
        try {
            XmlParser xmlP = new XmlParser(context);
            StringReader in = new StringReader(sbundle);
            Bundle bundle = xmlP.parseBundle(in);
            immunizations.clear();
            List<BundleEntry> lEntries = bundle.getEntries();
            Iterator iter = lEntries.iterator();
            while (iter.hasNext()) {
                BundleEntry entry = (BundleEntry)iter.next();
                Immunization imz = (Immunization)entry.getResource();
                immunizations.add(imz);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    private List<IResource> getAllResources()
    {
        List<IResource> allResources = new ArrayList<IResource>();
        allResources.add(me);
        allResources.addAll(medicationPrescriptions);
        allResources.addAll(medications);
        allResources.addAll(immunizations);
        allResources.addAll(organizations);
        allResources.addAll(conditions);
        allResources.addAll(encounters);

        return allResources;
    }
    
    private void adjustIdsFor(List<IResource> resources, String fhirBase) {


        for (IResource res: resources) {
            res.setId(res.getId().withServerBase(fhirBase,res.getId().getResourceType()).toVersionless());
            List<ResourceReferenceDt> refs = res.getAllPopulatedChildElementsOfType(ResourceReferenceDt.class);
            for (ResourceReferenceDt ref : refs) {
                if (! ref.isEmpty() && !ref.getReference().isEmpty() && ! ref.getReference().isLocal())
                {
                    ref.setReference(ref.getReference().withServerBase(fhirBase, ref.getReference().getResourceType()).toVersionless());
                }
            }
        }
        //return resources;
    }

    private void copyResourcesTo(FhirContext fhirContext, List<IResource> resourcesToCopy, String fhirBase)
    {
        IGenericClient client = fhirContext.newRestfulGenericClient(fhirBase);
        client.setEncoding(EncodingEnum.XML);

        List<IResource> allResources = resourcesToCopy;
        adjustIdsFor(allResources, fhirBase);

        for (IResource res: allResources) {
            try {
                MethodOutcome outcome = client.update(res.getId(), res);

                if (outcome != null && outcome.getCreated() != null)
                    System.out.println("CREATED" + res.getId().getValue());
                else
                    System.out.println("Updated: " + res.getId().getValue());
            }
            catch (ResourceNotFoundException e)
            {
                e.getMessage();
                e.printStackTrace();
            }
        }

    }

    public void copyAllTo(FhirContext fhirContext, String fhirBase)
    {
        copyResourcesTo(fhirContext, getAllResources(), fhirBase);
    }

    public void copyToHIE(FhirContext fhirContext, String fhirBase)
    {
        List<IResource> resourcesToCopy = new ArrayList<IResource>();
        resourcesToCopy.add(me);
        resourcesToCopy.addAll(immunizations);

        copyResourcesTo(fhirContext, resourcesToCopy, fhirBase);
    }    

    public PoFPatient (FhirContext fhirContext, String fhirBase, Patient patient)
    {
        me = patient;
        loadRelatedResources(fhirContext, fhirBase);

    }

    public PoFPatient (FhirContext fhirContext, String fhirBase, String patientId)
    {
        IGenericClient client = fhirContext.newRestfulGenericClient(fhirBase);
        client.setEncoding(EncodingEnum.XML);
        me = (Patient) client.read(Patient.class, new UriDt(fhirBase+"/Patient/" + patientId));
        try {
            loadRelatedResources(fhirContext, fhirBase);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Encounters: "+getEncounters().size());
        System.out.println("Conditions: "+getConditions().size());
        System.out.println("Immunizations: "+getImmunizations().size());
        System.out.println("MedicationPrescriptions: "+getMedicationPrescriptions().size());
        System.out.println("Medications: "+getMedications().size());
        System.out.println("Organizations: "+getOrganizations().size());
        
    }

    public static void main(String[] args) throws IOException {
        FhirContext context = FhirContext.forDstu2();
        PoFPatient poFPatient1 = new PoFPatient(context, "http://tricare.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/", "patient-1032702");
        PoFPatient alice = new PoFPatient(context, "http://tricare.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/", "ONC-VA-POF-P-001");
        System.out.println();
    }
}
