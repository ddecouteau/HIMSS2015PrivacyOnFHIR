/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.dataresults;

import com.validic.json.objects.Routine;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Duane DeCouteau
 */
public class JSONRoutineObject {

    private List<Routine> lRoutine = new ArrayList();
    private JSONObject jsonRoutine;
    
    public JSONRoutineObject(String jsonString) {
        try {
            JSONTokener tokener = new JSONTokener(jsonString);
            while (tokener.more()) {
                JSONObject jsonObj = (JSONObject)tokener.nextValue();
                if (jsonObj != null) {
                    jsonRoutine = jsonObj.getJSONObject("routine"); 
                    Routine r = new Routine();
                    r.setCalories_burned(jsonRoutine.getString("calories_burned"));
                    r.setDistance(jsonRoutine.getString("distance"));
                    r.setElevation(jsonRoutine.getString("elevation"));
                    r.setFloors(jsonRoutine.getString("floors"));
                    r.setId(jsonRoutine.getString("_id"));
                    r.setLastModified(jsonRoutine.getString("last_update"));
                    r.setSource(jsonRoutine.getString("source"));
                    r.setSourceName(jsonRoutine.getString("source_name"));
                    r.setSteps(jsonRoutine.getString("steps"));
                    r.setTimeStamp(jsonRoutine.getString("timestamp"));
                    r.setUtcOffset(jsonRoutine.getString("utc_offset"));
                    lRoutine.add(r);
                }
                
            }
            System.out.println("Routing Array Size "+lRoutine.size());
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
