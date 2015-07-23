/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.validicclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Duane DeCouteau
 */
public class ValidicClient {
    private final String orgId = "54d4fdb88ee42122c2000013";
    private final String orgToken = "e36a224b8828e7d3c47f5442f969a53f7220a7281be5acd0351780f21ac971e9";
    private final String baseORGURL = "https://api.validic.com/v1/organizations";
    private final String pofPatientUserId = "123456789";
    private final String validicId ="5525bf0cf1d1a1165b00004f";
    private final String validicToken = "dbsvmtYYW8e_o6bn2DMs";
    private final String validicMarketPlace="https://app.validic.com/54d4fdb88ee42122c2000013/dbsvmtYYW8e_o6bn2DMs";
    private final String validicBaseAPIURL = "https://api.validic.com/v1";
    private int daysbefore = -6;
    
    public ValidicClient() {
        
    }
    
    public String getRoutineDataFitBit(int daysbefore) {
        this.daysbefore = daysbefore;
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
    
    private String getStartDate() {
        String res = "";
        try {
            Locale locale = Locale.getDefault();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, daysbefore);
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
