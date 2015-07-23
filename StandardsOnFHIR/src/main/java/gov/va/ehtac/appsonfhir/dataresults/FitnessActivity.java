/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.dataresults;

/**
 *
 * @author Duane DeCouteau
 */
public class FitnessActivity {
    private String dateTime;
    private String fitnessType;  //Running, Biking, or Swimming
    private String startTime;
    private String movingTime;
    private String totalTime;
    private String distance;
    private String avgSpeed;
    private String maxSpeed;
    private String avgHR;
    private String maxHR;
    private String calories;
    //swimming only
    private String totalStrokes;
    // run and bike
    private String avgCadence;
    // bike only
    private String avgPWR;

    /**
     * @return the dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the fitnessType
     */
    public String getFitnessType() {
        return fitnessType;
    }

    /**
     * @param fitnessType the fitnessType to set
     */
    public void setFitnessType(String fitnessType) {
        this.fitnessType = fitnessType;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the movingTime
     */
    public String getMovingTime() {
        return movingTime;
    }

    /**
     * @param movingTime the movingTime to set
     */
    public void setMovingTime(String movingTime) {
        this.movingTime = movingTime;
    }

    /**
     * @return the totalTime
     */
    public String getTotalTime() {
        return totalTime;
    }

    /**
     * @param totalTime the totalTime to set
     */
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * @return the avgSpeed
     */
    public String getAvgSpeed() {
        return avgSpeed;
    }

    /**
     * @param avgSpeed the avgSpeed to set
     */
    public void setAvgSpeed(String avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    /**
     * @return the maxSpeed
     */
    public String getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed the maxSpeed to set
     */
    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * @return the avgHR
     */
    public String getAvgHR() {
        return avgHR;
    }

    /**
     * @param avgHR the avgHR to set
     */
    public void setAvgHR(String avgHR) {
        this.avgHR = avgHR;
    }

    /**
     * @return the maxHR
     */
    public String getMaxHR() {
        return maxHR;
    }

    /**
     * @param maxHR the maxHR to set
     */
    public void setMaxHR(String maxHR) {
        this.maxHR = maxHR;
    }

    /**
     * @return the calories
     */
    public String getCalories() {
        return calories;
    }

    /**
     * @param calories the calories to set
     */
    public void setCalories(String calories) {
        this.calories = calories;
    }

    /**
     * @return the totalStrokes
     */
    public String getTotalStrokes() {
        return totalStrokes;
    }

    /**
     * @param totalStrokes the totalStrokes to set
     */
    public void setTotalStrokes(String totalStrokes) {
        this.totalStrokes = totalStrokes;
    }

    /**
     * @return the avgCadence
     */
    public String getAvgCadence() {
        return avgCadence;
    }

    /**
     * @param avgCadence the avgCadence to set
     */
    public void setAvgCadence(String avgCadence) {
        this.avgCadence = avgCadence;
    }

    /**
     * @return the avgPWR
     */
    public String getAvgPWR() {
        return avgPWR;
    }

    /**
     * @param avgPWR the avgPWR to set
     */
    public void setAvgPWR(String avgPWR) {
        this.avgPWR = avgPWR;
    }
    
}
