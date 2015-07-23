/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.ui;

import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IDatatype;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.parser.XmlParser;
import ca.uhn.fhir.rest.client.IGenericClient;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import gov.va.ehtac.myappsonfhir.HealthElementsForPatientTouchKitUI;
import gov.va.ehtac.myappsonfhir.dataresults.ObservationResults;
import gov.va.ehtac.myappsonfhir.session.SessionAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Duane DeCouteau
 */

public class Vitals extends NavigationView {
    private Table vitalsTable;
    SessionAttributes session;
    Bundle bundle;
    
    public Vitals() {
        session = ((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Observations - "+session.getPatientNameGenderDisplay());
        final VerticalComponentGroup content = new VerticalComponentGroup();


        createTable();
        
        content.addComponent(vitalsTable);


        final Button submitButton = new Button("Submit");
        submitButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("Thanks!");
            }
        });

        setContent(new CssLayout(content));
        
    }
    
    private void createTable() {
        vitalsTable = new Table();
//        vitalsTable.setWidth("100%");
//        vitalsTable.setHeight("250px");
        vitalsTable.setMultiSelect(false);
        vitalsTable.setSelectable(true);
        vitalsTable.setImmediate(true); // react at once when something is selected
        vitalsTable.setEditable(false);
        vitalsTable.setContainerDataSource(populatePatientVitals());

        vitalsTable.setColumnReorderingAllowed(true);
        vitalsTable.setColumnCollapsingAllowed(false);
        vitalsTable.setVisibleColumns(new Object[] {"selected", "displayName", "value", "unitofmeasure", "dateobserved", "reflow", "refhigh", "status"});
        vitalsTable.setColumnHeaders(new String[] {"", "Observation", "Value", "UnitOfMeasure", "Date", "Ref. Low", "Ref. High", "Status"});
         
    }

    private IndexedContainer populatePatientVitals() {
        IndexedContainer container = new IndexedContainer();
        List<ObservationResults> rList = new ArrayList();
        try {
            Bundle vitals = getVitals();
            List<BundleEntry> lvitals = vitals.getEntries();
            System.out.println("NUMBER OF Observations: "+lvitals.size());
            Iterator iter = lvitals.iterator();
            while (iter.hasNext()) {
                try {
                    BundleEntry entry = (BundleEntry)iter.next();
                    Observation obs = (Observation)entry.getResource();
                    ObservationResults obj = new ObservationResults();

                    IDatatype dt = obs.getApplies();
                    CodeableConceptDt c = obs.getCode();
                    QuantityDt q = (QuantityDt)obs.getValue();
                    //units is missing
                    
                    //get reference range if applicable
                    String refLow = "";
                    String refHigh = "";
                    String status = "";
                    String displayname = "";
                    String codesystem = "";
                    String code = "";
                    String value = "";
                    String units = "";
                    String dateobserved = "";
                    
                    try {
                        if (obs.getReferenceRange().size() > 0) {
                            refLow = obs.getReferenceRange().get(0).getLow().getValue().toString();
                            refHigh = obs.getReferenceRange().get(0).getHigh().getValue().toString();
                        }
                        status = obs.getStatus();
                        displayname = c.getCodingFirstRep().getDisplay();
                        codesystem = c.getCodingFirstRep().getSystem();
                        code = c.getCodingFirstRep().getCode();
                        value = q.getValue().toEngineeringString();
                        units = q.getUnits();
                        DateTimeDt dts = (DateTimeDt)dt;
                        dateobserved = dts.getValueAsString();
                    } 
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    
                    obj.setDisplayName(displayname);
                    obj.setValue(value);
                    obj.setUnitOfMeasure(units);
                    obj.setDateObserved(dateobserved);
                    obj.setCodeSystem(codesystem);
                    obj.setCode(code);
                    obj.setObservationType("");
                    obj.setStatus(status);
                    obj.setRefHigh(refHigh);
                    obj.setRefLow(refLow);
                    obj.setPayload(obs);
                    rList.add(obj);
                }
                catch (Exception ex) {
                    System.out.println("****ERROR "+ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        container = createIndexedContainerVitals(rList);
        return container;
    }
    
    private Bundle getVitals() {
        //for time being just do this
        Bundle vitals = null;
        try {
            IGenericClient client = ((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getFhirClient();
             vitals = client.search(new UriDt(getObservationsSearchString()));
            System.out.println("Number of Observations Returned: "+vitals.getEntries().size());
//            List<AtomEntry<? extends Resource>> lConditions = conditions.getEntryList();
//            System.out.println("Number OF Medications: "+lConditions.size());
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        bundle = vitals;
        return vitals;
        
    }
    
    
    private IndexedContainer createIndexedContainerVitals(Collection<ObservationResults> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("selected", CheckBox.class, null);
        container.addContainerProperty("displayName", String.class, null);
        container.addContainerProperty("value", String.class, null);
        container.addContainerProperty("unitofmeasure", String.class, null);
        container.addContainerProperty("dateobserved", String.class, null);
        container.addContainerProperty("codesystem", String.class, null);
        container.addContainerProperty("code", String.class, null);
        container.addContainerProperty("status", String.class, null);
        container.addContainerProperty("reflow", String.class, null);
        container.addContainerProperty("refhigh", String.class, null);
        container.addContainerProperty("oObservation", ObservationResults.class, null);

        int i = 0;
        for (ObservationResults p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("selected").setValue(new CheckBox());
            item.getItemProperty("displayName").setValue(p.getDisplayName());
            item.getItemProperty("value").setValue(p.getValue());
            item.getItemProperty("unitofmeasure").setValue(p.getUnitOfMeasure());
            item.getItemProperty("dateobserved").setValue(p.getDateObserved());
            item.getItemProperty("codesystem").setValue(p.getCodeSystem());
            item.getItemProperty("code").setValue(p.getCode());
            item.getItemProperty("status").setValue(p.getStatus());
            item.getItemProperty("reflow").setValue(p.getRefLow());
            item.getItemProperty("refhigh").setValue(p.getRefHigh());
            item.getItemProperty("oObservation").setValue(p);
        }
        return container;
    }
    
    
    private String composeXMLStringFeed(Bundle feed) {
        String res = "";
        try {
            XmlParser xmlP = new XmlParser(((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getFhirContext());
            res = xmlP.encodeBundleToString(feed);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }    
//    
//    private String composeXMLString(Resource obj) {
//        String res = "";
//        try {
//            XmlComposer xml = new XmlComposer();
//            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//            xml.compose(bo, obj, true);
//            res = new String(bo.toByteArray());
//            bo.close();
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return res;
//    }
    private String getObservationsSearchString() {
        String res = session.getBaseURL()+"Observation?subject=Patient/"+session.getPatientId();
        System.out.println(res);
        return res;
    }
    
}