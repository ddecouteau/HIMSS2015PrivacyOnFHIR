/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;

/**
 *
 * @author Duane DeCouteau
 */
public class FHIRChart extends NavigationView {
        SessionAttributes session;
    public FHIRChart() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();          
        setCaption("TriCare Portal");
        //final VerticalComponentGroup vert = new VerticalComponentGroup();
        GridLayout layout = new GridLayout(3,2);
        layout.setMargin(true);
        layout.setWidth("80%");
        layout.setHeight("100%");
        
        Panel problemListPanel = getProblemList();
        Panel medicationPanel = getMedications();
        Panel encountersPanel = getEncounters();
        Panel allergiesPanel = getAllergies();
        Panel testsPanel = getTests();
        Panel carePanel = getCarePlan();
        
        layout.addComponent(problemListPanel, 0,0);
        layout.setComponentAlignment(problemListPanel, Alignment.TOP_LEFT);
        layout.addComponent(medicationPanel, 1, 0);
        layout.setComponentAlignment(medicationPanel, Alignment.TOP_LEFT);        
        layout.addComponent(encountersPanel, 2, 0);
        layout.setComponentAlignment(encountersPanel, Alignment.TOP_LEFT);        
        layout.addComponent(allergiesPanel, 0, 1);
        layout.setComponentAlignment(allergiesPanel, Alignment.TOP_LEFT);        
        layout.addComponent(testsPanel, 1, 1);
        layout.setComponentAlignment(testsPanel, Alignment.TOP_LEFT);        
        layout.addComponent(carePanel, 2,1);
        layout.setComponentAlignment(carePanel, Alignment.TOP_LEFT);        
        
        //vert.addComponent(layout);

        setContent(new CssLayout(layout));
        
    }
    
    private Panel getProblemList() {
        Panel panel = new Panel();
        panel.setCaption("Problem List");
        panel.setWidth("80%");
        panel.setHeight("100px");
        
        return panel;
    }
    
    private Panel getMedications() {
        Panel panel = new Panel();
        panel.setStyleName(Runo.LAYOUT_DARKER);
        panel.setCaption("Medications");
        panel.setWidth("80%");
        panel.setHeight("100px");
        
        return panel;
    }
    
    private Panel getEncounters() {
        Panel panel = new Panel();
        panel.setCaption("Encounters");
        panel.setWidth("80%");
        panel.setHeight("100px");
        
        return panel;
    }
    
    private Panel getAllergies() {
        Panel panel = new Panel();
        panel.setCaption("Recent Test Results");
        panel.setWidth("80%");
        panel.setHeight("100px");
        
        return panel;
    }
    
    private Panel getTests() {
        Panel panel = new Panel();
        panel.setCaption("Recent Results");
        panel.setWidth("100%");
        panel.setHeight("100px");        
        
        return panel;
    }
    
    private Panel getCarePlan() {
        Panel panel = new Panel();
        panel.setCaption("Care Plan");
        panel.setWidth("100%");
        panel.setHeight("100px");

        return panel;
    }
}
