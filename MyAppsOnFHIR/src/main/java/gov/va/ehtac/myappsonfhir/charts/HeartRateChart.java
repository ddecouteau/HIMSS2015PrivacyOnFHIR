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

/**
 *
 * @author Duane DeCouteau
 */
public class HeartRateChart {
    
    public HeartRateChart() {
        
    }
    
    public Component getChart() {
        Chart chart = new Chart(ChartType.LINE);
        chart.setWidth("400px");

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Heart Rate - Weekly");
        conf.setSubTitle("Source: FitBit.com");

        XAxis x = new XAxis();
        x.setCategories("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        y.setTitle("BPM - API Pending");
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

        conf.addSeries(new ListSeries("Max BPM", 78, 130, 147, 83, 128, 100, 131));
        conf.addSeries(new ListSeries("At Rest BPM", 55, 70, 60, 69, 53, 71, 50));
        chart.drawChart(conf);
        
        return chart;    
    }
}
