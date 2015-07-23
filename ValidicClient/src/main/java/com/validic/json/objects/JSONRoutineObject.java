/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validic.json.objects;

import com.validic.json.objects.Routine;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 *
 * @author Duane DeCouteau
 */
public class JSONRoutineObject {

    private List<Routine> lRoutine = new ArrayList();
    private JSONObject jsonSummary;
    private JSONArray jsonRoutine;
    
    public JSONRoutineObject(String jsonString) {
        try {
             JSONTokener tokener = new JSONTokener(jsonString);
            while (tokener.more()) {
                JSONObject jsonObj = (JSONObject)tokener.nextValue();
                if (jsonObj != null) {
                    jsonSummary = (JSONObject)jsonObj.get("summary");
                    jsonRoutine = (JSONArray)jsonObj.get("routine");
                    System.out.println("Array Size "+jsonRoutine.length());
                    
                    int i = 0;
                    for (i = 0; i < jsonRoutine.length(); i++) {
                        Routine r = new Routine();
                        JSONObject rOBJ = (JSONObject)jsonRoutine.getJSONObject(i);
                        r.setCalories_burned(rOBJ.getString("calories_burned"));
                        r.setDistance(rOBJ.getString("distance"));
                        r.setElevation(rOBJ.getString("elevation"));
                        r.setFloors(rOBJ.getString("floors"));
                        r.setId(rOBJ.getString("_id"));
                        r.setLastModified(rOBJ.getString("last_updated"));
                        r.setSource(rOBJ.getString("source"));
                        r.setSourceName(rOBJ.getString("source_name"));
                        r.setSteps(rOBJ.getString("steps"));
                        r.setTimeStamp(rOBJ.getString("timestamp"));
                        r.setDayOfWeek(getDayOfWeek(r.getTimeStamp()));
                        r.setUtcOffset(rOBJ.getString("utc_offset"));
                        BigDecimal miles = new BigDecimal(r.getDistance());
                        BigDecimal fDiv = new BigDecimal("5280");
                        miles = miles.divide(fDiv, 2, RoundingMode.HALF_UP);
                        r.setMiles(miles.toString());
                        System.out.println("TIMESTAMP: "+r.getTimeStamp()+" DayOfWeek: "+r.getDayOfWeek()+" Miles: "+miles.toString()+" Distance: "+r.getDistance()+" Floors: "+r.getFloors()+" Step: "+r.getSteps()+" Calories: "+r.getCalories_burned());
                        lRoutine.add(r);
                        
                    }
                }
                
            }

            System.out.println("Routing Array Size "+lRoutine.size());
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static String getDayOfWeek(String ds) {
        String res = "NA";
        String ds2 = ds.substring(0, 10);
        System.out.println(ds2);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = sd.parse(ds2);
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            res = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH);
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * @return the lRoutine
     */
    public List<Routine> getlRoutine() {
        return lRoutine;
    }

    /**
     * @param lRoutine the lRoutine to set
     */
    public void setlRoutine(List<Routine> lRoutine) {
        this.lRoutine = lRoutine;
    }
}
