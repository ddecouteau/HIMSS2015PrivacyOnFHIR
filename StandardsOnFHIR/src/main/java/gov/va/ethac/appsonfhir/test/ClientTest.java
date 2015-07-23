/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ethac.appsonfhir.test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.IGenericClient;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Duane DeCouteau
 */
public class ClientTest {
    private static String baseURL="http://mhs.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2";
    private static String patientId = "1134281";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ClientTest();
        getMedications();
    }
    
    private static void getMedications() {
        try {
            FhirContext ctx = FhirContext.forDstu2();
            IGenericClient client = ctx.newRestfulGenericClient(baseURL);
            
            Bundle bundle = client.search().forResource(Patient.class).prettyPrint().encodedXml().execute();
            System.out.println("Bundle: "+bundle.toString());
//            FHIRSimpleClient client = new FHIRSimpleClient();
//            Map<String, String> parameters = new HashMap<String, String>();
//            client.setPreferredFeedFormat(FeedFormat.FEED_XML);            
//            parameters.put("name", "Thomas");
//            client.initialize(baseURL);
//            Bundle b = client.search(Patient.class, parameters);
//            List<BundleEntryComponent> be = b.getEntry();
//            Iterator iter = be.iterator();
//            while (iter.hasNext()) {
//                BundleEntryComponent bec = (BundleEntryComponent)iter.next();
//                Resource r = bec.getResource();
//            }
            
            
            
        }
        catch (Exception ex) {
            System.out.println("No CONDITIONS FOR PATIENT: "+patientId);
            ex.printStackTrace();
        }       
        
    }
//    private static String composeXMLStringFeed(Bundle feed) {
//        String res = "";
//        try {
//            XmlParser xml = new XmlParser();
//            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//            xml.compose(bo, feed, true);
//            res = new String(bo.toByteArray());
//            System.out.println("XML FEED: "+res);
//            bo.close();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return res;
//    }    
}
