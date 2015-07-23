package org.hl7.fhir.consent;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.codec.binary.Base64;


/**
 * Created by mochaholic on 16/03/2015.
 */
public class JerichoConsentDirective {

    final static String ID_PARAM="_id";
    final static String CDA_START_MARK = "<ns3:data value=\"";
    final static String CDA_END_MARK = "\"";

    final static String XACML_START_MARK = "<value representation=\"B64\" mediaType=\"application/xacml+xml\">";
    final static String XACML_END_MARK = "</value>";

    static CloseableHttpClient httpclient = HttpClients.createDefault();

    String fhirContent ="";

    public String getFhirContent() {
        return fhirContent;
    }

    public String getCdaContent() {
        return cdaContent;
    }

    public String getXacmlContent() {
        return xacmlContent;
    }

    String cdaContent ="";
    String xacmlContent="";


    public static JerichoConsentDirective fetchFromServer (String host, int port, String path, String patientId) throws IOException, URISyntaxException {
        JerichoConsentDirective cd = new JerichoConsentDirective();

        URI uri = new URIBuilder().setScheme("https").setHost(host).setPort(port).setPath(path).setParameter(ID_PARAM , patientId).build();
        HttpGet httpget = new HttpGet(uri);

        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            int status = response.getStatusLine().getStatusCode();
            if (status != 200)
                throw new IOException("Could not fetch the Consent Directive from: "+ uri.toString()+". Server response: " + response.getStatusLine().toString());
            String responseString = EntityUtils.toString(response.getEntity());
            cd.fhirContent = responseString;

            if (! responseString.contains(CDA_START_MARK))
                throw new IOException("Malformed response from: "+ uri.toString()+". Expecting to see: " + CDA_START_MARK);
            String cdaB64String= StringUtils.substringBetween(responseString, CDA_START_MARK, CDA_END_MARK);

            //because sometimes there are  newline characters in the B64 content
            cdaB64String= StringUtils.remove(cdaB64String, '\n');
            cd.cdaContent = new String(Base64.decodeBase64(cdaB64String));
            //cd.cdaContent = new String(Base64.getDecoder().decode(cdaB64String), StandardCharsets.UTF_8);
            //System.out.println(cd.cdaContent);
            if (! cd.cdaContent.contains(XACML_END_MARK))
                throw new IOException("Malformed CDA content from: "+ uri.toString()+". Expecting to see: " + XACML_START_MARK);

            String xacmlStringB64Code = StringUtils.substringBetween(cd.cdaContent, XACML_START_MARK, XACML_END_MARK );

            //because sometimes there are  newline characters in the B64 content
            xacmlStringB64Code = StringUtils.remove(xacmlStringB64Code, '\n');

            cd.xacmlContent = new String(Base64.decodeBase64(xacmlStringB64Code));
            //cd.xacmlContent = new String(Base64.getDecoder().decode(xacmlStringB64Code));
        } finally {
            response.close();

        }

        return cd;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        JerichoConsentDirective cd = JerichoConsentDirective.fetchFromServer("www.consentral.com", 11014, "/FHIRService/search", "NonUmaPatient");
        System.out.println(cd.getXacmlContent());
//        System.out.println(cd.getCdaContent());
//        System.out.println(cd.getFhirContent());

    }


}
