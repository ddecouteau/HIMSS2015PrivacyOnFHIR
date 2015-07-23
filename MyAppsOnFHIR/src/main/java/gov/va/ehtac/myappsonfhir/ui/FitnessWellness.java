/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;
import com.validic.json.objects.JSONRoutineObject;
import com.validic.json.objects.Routine;
import gov.va.ehtac.myappsonfhir.HealthElementsForPatientTouchKitUI;
import gov.va.ehtac.myappsonfhir.charts.CaloriesChart;
import gov.va.ehtac.myappsonfhir.charts.DistanceChart;
import gov.va.ehtac.myappsonfhir.charts.HeartRateChart;
import gov.va.ehtac.myappsonfhir.charts.StepsChart;
import gov.va.ehtac.myappsonfhir.session.SessionAttributes;
import gov.va.ehtac.validicclient.ValidicClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Duane DeCouteau
 */
@SuppressWarnings("serial")
public class FitnessWellness extends NavigationView {
    private final SessionAttributes session;
    private final CaloriesChart cChart = new CaloriesChart();
    private final DistanceChart dChart = new DistanceChart();
    private final HeartRateChart hChart = new HeartRateChart();
    private final StepsChart sChart = new StepsChart();
    private List<Routine> results = new ArrayList();
    final VerticalComponentGroup content;
    private String dRequest = "chart";
    
    public FitnessWellness() {
        session = ((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("My Health Apps");  
        content = new VerticalComponentGroup();
        this.setWidth("100%");
        this.setHeight("100%");
            
        if (dRequest.equals("chart")) {
            content.addComponent(getChartLayout());
        }
        else if (dRequest.equals("raw")) {
            content.addComponent(getRawData());
        }
        else {
            //this is a problem
            content.addComponent(new Panel());
        }
        
        Button refresh = new Button();
        refresh.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    dRequest = "chart";
                    refresh();
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        });

        refresh.setImmediate(true);
        refresh.setIcon(FontAwesome.REFRESH);
        setRightComponent(refresh);
        
        setContent(new CssLayout(content));   

    }
    
    private void getChartData(int val) {
        ValidicClient client = new ValidicClient();
        String s = client.getRoutineDataFitBit(val);
        JSONRoutineObject obj = new JSONRoutineObject(s);
        results = obj.getlRoutine();
    }
    
    private void refresh() {
        content.removeAllComponents();
        if (dRequest.equals("chart")) {
            content.addComponent(getChartLayout());
        }
        else if (dRequest.equals("raw")) {
            content.addComponent(getRawData());
        }
        else {
            //this is a problem
            content.addComponent(new Panel());
        }
        
        setContent(new CssLayout(content));
    }
    
    private GridLayout getChartLayout() {
        getChartData(-6);
        Button b1 = new Button("See More");
        Button b2 = new Button("See More");
        Button b3 = new Button("See More");
        Button b4 = new Button("See More"); 
        
        b1.setImmediate(true);
        b2.setImmediate(true);
        b3.setImmediate(true);
        b4.setImmediate(true);
        
        b1.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                dRequest = "raw";
                refresh();
            }
        });
        
        b2.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                dRequest = "raw";
                refresh();
            }
        });
        
        b3.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                dRequest = "raw";
                refresh();
            }
        });
        
        b4.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                dRequest = "raw";
                refresh();
            }
        });

        GridLayout grid = new GridLayout(2,2);
        grid.setMargin(true);
        grid.setWidth("100%");
  

        Panel p1 = new Panel("Heart Rate Activity");
        p1.setHeight("100px");
        VerticalComponentGroup v = new VerticalComponentGroup();  
        //v.addComponent(hChart.getChart());
        v.addComponent(hChart.getChart());
        v.addComponent(b1);
        grid.addComponent(v, 0, 0);   
 
        Panel p2 = new Panel("Distance - Miles");
        p2.setHeight("100px");
        VerticalComponentGroup v2 = new VerticalComponentGroup();  
        //v2.addComponent(dChart.getChart());
        v2.addComponent(dChart.getChart(results));
        v2.addComponent(b2);
        grid.addComponent(v2, 1, 0);   
        
        Panel p3 = new Panel("Calories Burned");
        p3.setHeight("100px");
        VerticalComponentGroup v3 = new VerticalComponentGroup(); 
        //v3.addComponent(cChart.getChart());
        v3.addComponent(cChart.getChart(results));
        v3.addComponent(b3);
        grid.addComponent(v3, 0, 1); 

        Panel p4 = new Panel("Steps Taken");
        p4.setHeight("100px");
        VerticalComponentGroup v4 = new VerticalComponentGroup();  
        //v4.addComponent(sChart.getChart());
        v4.addComponent(sChart.getChart(results));
        v4.addComponent(b4);
        grid.addComponent(v4, 1, 1);  
        return grid;
    }
    
    private Table getRawData() {
        getChartData(-30);
        Table fitnessTable = new Table();
        fitnessTable.setWidth("100%");
        fitnessTable.setHeight("450px");
        fitnessTable.setMultiSelect(false);
        fitnessTable.setSelectable(true);
        fitnessTable.setImmediate(true); // react at once when something is selected
        fitnessTable.setEditable(false);
        fitnessTable.setStyleName(Runo.TABLE_SMALL);
        fitnessTable.setContainerDataSource(populateData());

        fitnessTable.setColumnReorderingAllowed(true);
        fitnessTable.setColumnCollapsingAllowed(false);
        fitnessTable.setVisibleColumns(new Object[] {"date","distance", "miles", "steps", "calories"});
        fitnessTable.setColumnHeaders(new String[] {"Date","Distance Feet", "Distance Miles", "Step", "Calories Burned"});   
        return fitnessTable;
    }
    
    private IndexedContainer populateData() {
        IndexedContainer container = null;
        try {
            container = createIndexedContainer(results);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return container;
    }
    
    private IndexedContainer createIndexedContainer(Collection<Routine> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("date", String.class, null);
        container.addContainerProperty("distance", String.class, null);
        container.addContainerProperty("miles", String.class, null);
        container.addContainerProperty("steps", String.class, null);
        container.addContainerProperty("calories", String.class, null);
        container.addContainerProperty("oRoutine", Routine.class, null);

        int i = 0;
        for (Routine p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("date").setValue(p.getTimeStamp());
            item.getItemProperty("distance").setValue(p.getDistance());
            item.getItemProperty("miles").setValue(p.getMiles());
            item.getItemProperty("steps").setValue(p.getSteps());
            item.getItemProperty("calories").setValue(p.getCalories_burned());
            item.getItemProperty("oRoutine").setValue(p);
        }        
        
        return container;
    }

}
