/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.validicclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.validic.json.objects.JSONRoutineObject;
import com.validic.json.objects.Routine;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Duane DeCouteau
 */
public class ValidicTestClient {
    private static final String orgId = "54d4fdb88ee42122c2000013";
    private static final String orgToken = "e36a224b8828e7d3c47f5442f969a53f7220a7281be5acd0351780f21ac971e9";
    private static String baseORGURL = "https://api.validic.com/v1/organizations";
    private static String pofPatientUserId = "123456789";
    private static String validicId ="5525bf0cf1d1a1165b00004f";
    private static String validicToken = "dbsvmtYYW8e_o6bn2DMs";
    private static String validicMarketPlace="https://app.validic.com/54d4fdb88ee42122c2000013/dbsvmtYYW8e_o6bn2DMs";
    private static String validicBaseAPIURL = "https://api.validic.com/v1";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //doOrganizationCheck();
        //doUserProvisioning();
        //getUserData();
        //getUserBiometrics();
        //getLatestOrganization();
        String res = getRoutineDataFitBit();
        JSONRoutineObject r = new JSONRoutineObject(res);
        //getRoutineDataGarmin();
    }
    
    private static void doOrganizationCheck() {
        String jsonString = orgId+".json?access_token="+orgToken;
        Client client = Client.create();
        try {
            String urlParameters = URLEncoder.encode(jsonString, "UTF-8");            
            WebResource webResource = client.resource(baseORGURL+"/"+jsonString);

            String res = webResource.accept(MediaType.APPLICATION_JSON).get(String.class);
            System.out.println("Response : "+ res);
                   
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            client.destroy();
        }
        
    }
    
    private static void doUserProvisioning() {
        String jsonString = orgId+"/users.json";
        String input = "{"
                + "\"user\": {"
                + "\"uid\": \""+pofPatientUserId+"\""
                + "}, "
                + "\"access_token\": \""+orgToken+"\""
                + "}";
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(baseORGURL+"/"+jsonString);
            ClientResponse res = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input);
            System.out.println("Response : "+ res.getEntity(String.class));
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void getUserData() {
        String jsonString = "fitness.json?authentication_token="+validicToken;
        Client client = Client.create();
        try {
            String urlParameters = URLEncoder.encode(jsonString, "UTF-8");            
            WebResource webResource = client.resource(validicBaseAPIURL+"/"+jsonString);

            String res = webResource.accept(MediaType.APPLICATION_JSON).get(String.class);
            System.out.println("Response : "+ res);
                   
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            client.destroy();
        }
        
    }
    
    private static void getUserBiometrics() {
        String jsonString = orgId+"/users/"+validicId+"/biometrics.json?access_token="+orgToken;
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(baseORGURL+"/"+jsonString);
            String res = webResource.accept(MediaType.APPLICATION_JSON).get(String.class);
            System.out.println("Response : "+ res);
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void getLatestOrganization() {
        String jsonString = orgId+"/fitness.json?access_token="+orgToken;
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(baseORGURL+"/"+jsonString);
            String res = webResource.accept(MediaType.APPLICATION_JSON).get(String.class);
            System.out.println("Response : "+ res);
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    private static String getRoutineDataFitBit() {
        String querystartdate = getStartDate();
        String resourceString = "https://api.validic.com/v1/organizations/54d4fdb88ee42122c2000013/users/5525bf0cf1d1a1165b00004f/routine.json?access_token=e36a224b8828e7d3c47f5442f969a53f7220a7281be5acd0351780f21ac971e9&start_date="+querystartdate+"&source=fitbit";
        String res = null;
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(resourceString);
            res = webResource.accept(MediaType.APPLICATION_JSON).get(String.class);
            System.out.println(res);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
    private static void getRoutineDataGarmin() {
        String querystartdate = getStartDate();
        String resourceString = "https://api.validic.com/v1/organizations/54d4fdb88ee42122c2000013/users/5525bf0cf1d1a1165b00004f/fitness.json?access_token=e36a224b8828e7d3c47f5442f969a53f7220a7281be5acd0351780f21ac971e9&start_date="+querystartdate+"&source=garmin";
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(resourceString);
            String res = webResource.accept(MediaType.APPLICATION_JSON).get(String.class);
            System.out.println(res);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    private static String getStartDate() {
        String res = "";
        try {
            Locale locale = Locale.getDefault();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -6);
            Date d = c.getTime();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            res = sd.format(d);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return res;
    }
    
    

}
