/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.dataresults;

import ca.uhn.fhir.context.FhirContext;
import com.edmondsci.fhir.uma.MagicUMAEnabledFhirClient;
import com.edmondsci.fhir.uma.SimpleUMAEnabledFhirClient;
import com.edmondsci.oauth.OAuth2Client;
import com.edmondsci.pof.UMAEnabledPoFPatient;
import com.edmondsci.uma.SimpleUMAUser;
import com.edmondsci.uma.UMAConfig;
import java.net.URISyntaxException;

/**
 *
 * @author Duane DeCouteau
 */
public class PofUMAPatient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception, URISyntaxException {
        FhirContext context = FhirContext.forDstu2();

        String umaConfigUri ="https://healthauth.org/.well-known/uma-configuration";
        UMAConfig umaConfig = UMAConfig.loadFrom(umaConfigUri).setRequesting_party_claims_endpoint("https://healthauth.org/rqp_claims");
        String appClientId=  "39e7263d-bc88-401d-a75b-1797189e655b";
        String appClientSecret = "aNIIsy4Ix0tPX8ZkRGn25w3JIYBw1zJVBTsZyN_k2eYIFu0mTAX7C9f1hCH41i5JWtF92ccmRSMN1IyAfTCFRg";
        String appClientRedirectUri = "http://mhs.edmondsci.com:8080/appsonfhir";

        OAuth2Client appClient = new OAuth2Client();
        appClient.setClientId(appClientId);
        appClient.setClientSecret(appClientSecret);
        appClient.setClientRedirectUri(appClientRedirectUri);

        String adminUsername = "admin";
        String adminPassword = "password";

        String appUsersUsername="drduane";
        String appUsersPassword="password";
        String appUsersEmail = "drduane@openid.test.sitenv.org";

        SimpleUMAUser appUser = new SimpleUMAUser()
                                    .setUsername(appUsersUsername)
                                    .setPassword(appUsersPassword)
                                    .setEmail(appUsersEmail);


        FhirContext fhirContext = FhirContext.forDstu2();
        SimpleUMAEnabledFhirClient umaEnabledFhirClient = new SimpleUMAEnabledFhirClient(fhirContext, umaConfig, appClient, appUser, adminUsername, adminPassword);


        UMAEnabledPoFPatient alice = new UMAEnabledPoFPatient(context,
                "http://va.edmondsci.com:8080/uma-hapi-fhir/baseDstu2", 
                umaEnabledFhirClient, 
                "ONC-VA-POF-P-001");

//        UMAEnabledPoFPatient alice = new UMAEnabledPoFPatient(context, 
//                "http://va.edmondsci.com:8080/uma-hapi-fhir/baseDstu2", 
//                new MagicUMAEnabledFhirClient(fhirContext, "http://va.edmondsci.com:8080/uma-hapi-fhir/baseDstu2", "THE_MAGIC_TOKEN" ),
//                "ONC-VA-POF-P-001");


        System.out.println(alice.getAccessControlErrorsWhileLoading());

        System.out.println();
    }
    
}
