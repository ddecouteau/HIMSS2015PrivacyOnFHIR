package com.esc.hapidevtest;

import ca.uhn.fhir.api.ExtendedResourceMetadataKeyEnum;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.parser.XmlParser;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mochaholic on 22/01/2015.
 */
public class UpdatedParserTest {


    public static void main(String[] args) {
        FhirContext ctx = FhirContext.forDstu2();
        IParser xmlParer = new XmlParser(ctx).setPrettyPrint(true);


        //create a resource
        Patient patient = new Patient();
        patient.addName().addFamily("DiMaggio").addGiven("Joe");

        //create security labels
        CodingDt securityLabel1 = new CodingDt("http://hl7.org/fhir/v3/ActCode", "CEL").setDisplay("Celebrity").setVersion("3.0");
        CodingDt securityLabel2 = new CodingDt("http://hl7.org/fhir/v3/Confidentiality", "R").setDisplay("Restricted");

        //create security labels list
        List<CodingDt> securityLabels = new ArrayList<CodingDt>();
        securityLabels.add(securityLabel1);
        securityLabels.add(securityLabel2);
        ResourceMetadataMap metadataMap = new ResourceMetadataMap();
        metadataMap.put(ResourceMetadataKeyEnum.SECURITY_LABELS, securityLabels);

        //add some other metadata
        metadataMap.put(ResourceMetadataKeyEnum.UPDATED, new InstantDt("2015-01-21T16:19:11.000-05:00"));

        //add all metadata to resource
        patient.setResourceMetadata(metadataMap);

        //encode the resource
        String resourceString = xmlParer.encodeResourceToString(patient);
        //print the XML-encoded resource
        System.out.println(resourceString);

        //now try to parse the resource back from the XML
        IResource resource = xmlParer.parseResource(resourceString);

        //make sure the security labels are parsed and are there
        System.out.println();
        for (CodingDt label : (List<CodingDt>)resource.getResourceMetadata().get(ResourceMetadataKeyEnum.SECURITY_LABELS))
            System.out.println("Labels: " + label.getSystem() + ":" + label.getCode() +" (" + label.getDisplay() + ") ");
        System.out.println();

        //re-encode the resource. this should create an identical XML
        assert (xmlParer.encodeResourceToString(resource).equals(resourceString));
        System.out.println(xmlParer.encodeResourceToString(resource));

    }
}
