/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ethac.appsonfhir.test;

import java.util.Date;

/**
 *
 * @author Duane DeCouteau
 */
public class TestTime {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new TestTime();
        calculateTime();
    }
    
    private static void calculateTime() {
        Long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime);
        long totalTime = (endTime - startTime);
        System.out.println(totalTime);
    }
    
}
