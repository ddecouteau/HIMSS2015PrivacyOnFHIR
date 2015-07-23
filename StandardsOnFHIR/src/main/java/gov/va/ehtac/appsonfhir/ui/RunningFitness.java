/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.dataresults.FitnessActivity;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Duane DeCouteau
 */
@SuppressWarnings("serial")
public class RunningFitness extends NavigationView {
    private Table runTable;
    SessionAttributes session;
    
    public RunningFitness() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Fitness - Running/Walking Activities");
        final VerticalComponentGroup content = new VerticalComponentGroup();


        createTable();
        
        content.addComponent(runTable);


        setRightComponent(new Label(session.getPatientNameAgeGenderDisplay()));
        setContent(new CssLayout(content));
        
    }
    
    private void createTable() {
        runTable = new Table();
        runTable.setWidth("100%");
        runTable.setHeight("600px");
//        runTable.setHeight("250px");
        runTable.setMultiSelect(false);
        runTable.setSelectable(true);
        runTable.setImmediate(true); // react at once when something is selected
        runTable.setEditable(false);
        runTable.setContainerDataSource(populateRunningActivities());

        runTable.setColumnReorderingAllowed(true);
        runTable.setColumnCollapsingAllowed(false);
        runTable.setVisibleColumns(new Object[] {"activitydate", "distance", "totaltime", "movingtime", "averagepace", "averagehr", "maxhr", "calories"});
        runTable.setColumnHeaders(new String[] {"Date Time", "Distance","Total Time", "Moving Time", "Average Pace", "Avg. HR", "Max HR", "Calories"});        
    }
    
    private IndexedContainer populateRunningActivities() {
        IndexedContainer container = new IndexedContainer();
        List<FitnessActivity> rList = new ArrayList();
        try {
            //populate it later
            container = createIndexedContainerRunning(rList);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return container;
    }
    
    private IndexedContainer createIndexedContainerRunning(Collection<FitnessActivity> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("activitydate", String.class, null);
        container.addContainerProperty("distance", String.class, null);
        container.addContainerProperty("totaltime", String.class, null);
        container.addContainerProperty("movingtime", String.class, null);
        container.addContainerProperty("averagepace", String.class, null);
        container.addContainerProperty("averagehr", String.class, null);
        container.addContainerProperty("maxhr", String.class, null);
        container.addContainerProperty("calories", String.class, null);
        container.addContainerProperty("oRunning", FitnessActivity.class, null);

        int i = 0;
        for (FitnessActivity p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("activitydate").setValue(p.getDateTime());
            item.getItemProperty("distance").setValue(p.getDistance());
            item.getItemProperty("totaltime").setValue(p.getTotalTime());
            item.getItemProperty("movingtime").setValue(p.getMovingTime());
            item.getItemProperty("averagepace").setValue(p.getAvgSpeed());
            item.getItemProperty("averagehr").setValue(p.getAvgHR());
            item.getItemProperty("maxhr").setValue(p.getMaxHR());
            item.getItemProperty("calories").setValue(p.getCalories());
            item.getItemProperty("oRunning").setValue(p);            
        }
        
        
        return container;
    }
}
