/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.charts;


import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;
import com.validic.json.objects.Routine;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Duane DeCouteau
 */
public class StepsChart {
    //setup value categories 
    private String cat1 = "Sun";
    private String cat2 = "Mon";
    private String cat3 = "Tue";
    private String cat4 = "Wed";
    private String cat5 = "Thu";
    private String cat6 = "Fri";
    private String cat7 = "Sat";
    
    // setup values
    private Number val1 = 0;
    private Number val2 = 0;
    private Number val3 = 0;
    private Number val4 = 0;
    private Number val5 = 0;
    private Number val6 = 0;
    private Number val7 = 0;
    
    public StepsChart() {
        
    }
    
    public Component getChart(List<Routine> rList) {
        for (int i = 0; i < rList.size(); i++) {
            Routine r = rList.get(i);
            if (i == 0) {
                val7 = new BigDecimal(r.getSteps());
                cat7 = r.getDayOfWeek();
            }
            else if(i == 1) {
                val6 = new BigDecimal(r.getSteps());
                cat6 = r.getDayOfWeek();
            }
            else if(i == 2) {
                val5 = new BigDecimal(r.getSteps());
                cat5 = r.getDayOfWeek();
            }
            else if (i == 3) {
                val4 = new BigDecimal(r.getSteps());
                cat4 = r.getDayOfWeek();

            }
            else if (i == 4) {
                val3 = new BigDecimal(r.getSteps());
                cat3 = r.getDayOfWeek();
                
            }
            else if (i == 5) {
                val2 = new BigDecimal(r.getSteps());
                cat2 = r.getDayOfWeek();
         
            }
            else if (i == 6) {
                val1 = new BigDecimal(r.getSteps());
                cat1 = r.getDayOfWeek();
                
            }
            else {
              //done   
            }
        }
        return getChart();
    }
    
    
    public Component getChart() {
        Chart chart = new Chart(ChartType.LINE);
        chart.setWidth("400px");

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Steps Taken - Weekly");
        conf.setSubTitle("Source: FitBit.com");

        XAxis x = new XAxis();
        x.setCategories(cat1, cat2, cat3, cat4, cat5, cat6, cat7);
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        y.setTitle("Step Count");
        conf.addyAxis(y);

        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor("#FFFFFF");
        legend.setHorizontalAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(100);
        legend.setY(70);
        legend.setFloating(true);
        legend.setShadow(true);
        conf.setLegend(legend);


        PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(0.2);
        plot.setBorderWidth(0);

        conf.addSeries(new ListSeries("Steps", val1, val2, val3, val4, val5, val6, val7));

        chart.drawChart(conf);
        
        return chart;    }
}
