/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.ui;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.dataresults.PatientSearchResults;
import gov.va.ehtac.appsonfhir.dataresults.PoFPatient;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author Duane DeCouteau
 */
@SuppressWarnings("serial")
public class PatientSearch extends NavigationView {
    
    private Table searchTable = new Table();
    private String lastName;
    Popover popover;
    //fix below with filter
    SessionAttributes session;
    private Label patientDisplayName;
    private String patientSearchString;
    
    public PatientSearch() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Patient Search");
        patientDisplayName = new Label(session.getPatientNameAgeGenderDisplay());
        patientDisplayName.setImmediate(true);
        setRightComponent(patientDisplayName);
        final VerticalComponentGroup content = new VerticalComponentGroup();  
        
        final TextField nameField = new TextField("Enter all or part of Last Name");
        nameField.setImmediate(true);
        content.addComponent(nameField);
        
        final Button submitButton = new Button("Search");
        submitButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                lastName = nameField.getValue();
                Popover popover = getPopoverResults();
                popover.showRelativeTo(getNavigationBar());
                
            }
        });
        submitButton.setImmediate(true);
        
        setContent(new CssLayout(content, submitButton));
    
    }
    
    private void createTable() {
        searchTable = new Table();
        searchTable.setWidth("100%");
        //searchTable.setHeight("60%");
        searchTable.setHeight("300px");
        searchTable.setMultiSelect(false);
        searchTable.setSelectable(true);
        searchTable.setImmediate(true); // react at once when something is selected
        searchTable.setEditable(false);
        searchTable.setContainerDataSource(populatePatientList());

        searchTable.setColumnReorderingAllowed(true);
        searchTable.setColumnCollapsingAllowed(false);
        searchTable.setVisibleColumns(new Object[] {"lastname", "firstname", "gender", "dob"});
        searchTable.setColumnHeaders(new String[] {"Last Name", "First Name", "Gender", "DoB"});
        
//        searchTable.addListener(new ItemClickEvent.ItemClickListener() {
//            @Override
//            public void itemClick(ItemClickEvent event) {
//                if (event.isDoubleClick()) {
//                    try {
//                        Object rowId = searchTable.getValue();
//                        if (rowId != null) {
//                            String id = (String)searchTable.getContainerProperty(rowId, "patientid").getValue();
//                            String last = (String)searchTable.getContainerProperty(rowId, "lastname").getValue();
//                            String first = (String)searchTable.getContainerProperty(rowId, "firstname").getValue();
//                            String gender = (String)searchTable.getContainerProperty(rowId, "gender").getValue();
//                            String dob = (String)searchTable.getContainerProperty(rowId, "dob").getValue();
//                            session.setPatientId(id);
//                            session.setPatientNameAgeGenderDisplay(last+", "+first+" "+gender+" "+dob);
//                            patientDisplayName.setValue(session.getPatientNameAgeGenderDisplay());
//                            popover.close();
//                        }
//                    }
//                    catch (Exception ex) {
//                        ex.printStackTrace();
//                    }   
//                }
//            }
//        });
        
    }
    
    private IndexedContainer populatePatientList() {
        IndexedContainer container = new IndexedContainer();
        List<PatientSearchResults> rList = new ArrayList();
        try {
            Bundle patients = getPatients();
            List<BundleEntry> lpatients = patients.getEntries();
            System.out.println("NUMBER OF Patients: "+lpatients.size());
            Iterator iter = lpatients.iterator();
            while (iter.hasNext()) {
                try {
                    BundleEntry entry = (BundleEntry)iter.next();
                    String lastname = "";
                    String firstname = "";
                    String gender = "";
                    String dob = "";
                    String patientid = "";
                    IResource iPatient = entry.getResource();

                    Patient patient = (Patient)iPatient;
                    patientid = iPatient.getId().getIdPart();                    
                    try {
                        lastname = patient.getName().get(0).getFamily().get(0).getValue();
                        firstname = patient.getName().get(0).getGiven().get(0).getValue();
                        try { 
                            gender = patient.getGender(); 
                        } catch (Exception gx) {}
                        if (gender == null) {
                            gender = "";
                        }
                        try {dob = patient.getBirthDateElement().getValueAsString(); } catch (Exception dx) {}

//                        Iterator iterID = patient.getIdentifier().iterator();
//                        while (iterID.hasNext()) {
//                            IdentifierDt id = (IdentifierDt)iterID.next();
//                            System.out.println("Identifier Use: "+id.getUse());
//                            String officialUse = id.getUse();
//                            System.out.println(lastname+", "+firstname+" "+id.getSystem()+" "+id.getValue());
//                            if (officialUse.equals("official") || officialUse.equals("usual")) {
//                                patientid = id.getValue();
//                            }
//                        }

                    }
                    catch(Exception ex) {
                        ex.printStackTrace();
                    }
                    PatientSearchResults pRes = new PatientSearchResults();
                    pRes.setBirthDate(dob);
                    pRes.setFirstName(firstname);
                    pRes.setGender(gender);
                    pRes.setLastName(lastname);
                    pRes.setPatientId(patientid);
                    pRes.setPayload(patient);
                    rList.add(pRes);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
            container = createIndexedContainerPatients(rList);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return container;
    }
    
    private IndexedContainer createIndexedContainerPatients(Collection<PatientSearchResults> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("lastname", String.class, null);
        container.addContainerProperty("firstname", String.class, null);
        container.addContainerProperty("dob", String.class, null);
        container.addContainerProperty("gender", String.class, null);
        container.addContainerProperty("patientid", String.class, null);
        container.addContainerProperty("oResults", PatientSearchResults.class, null);

        int i = 0;
        for (PatientSearchResults p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("lastname").setValue(p.getLastName());
            item.getItemProperty("firstname").setValue(p.getFirstName());
            item.getItemProperty("dob").setValue(p.getBirthDate());
            item.getItemProperty("gender").setValue(p.getGender());
            item.getItemProperty("patientid").setValue(p.getPatientId());
            item.getItemProperty("oResults").setValue(p);
        }
        return container;
    }
    
    
    private Bundle getPatients() {
        //for time being just do this
        Bundle patients = null;
        try {
            IGenericClient client = ((HealthElementsTouchKitUI) UI.getCurrent()).getFhirClient();
            patients = client.search(new UriDt(getPatientSearchString()));            
            //System.out.println("Number of Patients Returned: "+patients.getEntries().size());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return patients;
        
    }
    
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
    
    private Popover getPopoverResults() {
        popover = new Popover();
        popover.setModal(true);
        popover.setClosable(true);
        popover.setWidth("500px");
        popover.setHeight("350px");
        CssLayout popLayout = new CssLayout();
        popLayout.setSizeFull();
        
        NavigationView navView = new NavigationView(popLayout);
        navView.setCaption("Search Results");

        CssLayout layout2 = new CssLayout();
        createTable();
        layout2.addComponent(searchTable);
        
        Button selectPatient = new Button("Select", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    Object rowId = searchTable.getValue();
                    if (rowId != null) {
                        String id = (String)searchTable.getContainerProperty(rowId, "patientid").getValue();
                        String last = (String)searchTable.getContainerProperty(rowId, "lastname").getValue();
                        String first = (String)searchTable.getContainerProperty(rowId, "firstname").getValue();
                        String gender = (String)searchTable.getContainerProperty(rowId, "gender").getValue();
                        String dob = (String)searchTable.getContainerProperty(rowId, "dob").getValue();
                        PatientSearchResults results = (PatientSearchResults)searchTable.getContainerProperty(rowId, "oResults").getValue();
                        session.setPatientId(id);
                        session.setPatientNameAgeGenderDisplay(last+", "+first+" "+gender+" "+dob);
                        patientDisplayName.setValue(session.getPatientNameAgeGenderDisplay());
                        System.out.println("PATIENT NameAgeGender: "+session.getPatientNameAgeGenderDisplay()+" PATIENT ID: "+session.getPatientId());
                        System.out.println(results.getPayload());
                        popover.close();
                        grabAllPatientData();
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        layout2.addComponent(selectPatient);
        
        popLayout.addComponent(layout2);
        
        Button close = new Button(null, new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                popover.removeFromParent();
            }
        });
        close.setIcon(new ThemeResource("../runo/icons/64/cancel.png"));
        navView.setLeftComponent(close);
        navView.setRightComponent(selectPatient);
        
        popover.setContent(navView);
        
        return popover;
    }
    
    private String getPatientSearchString() {
        String res = session.getBaseURL()+"Patient?family="+lastName;
        System.out.println(res);
        return res;
    }
    
    private void grabAllPatientData() {
        FhirContext context = session.getContext();
        String baseURL = session.getBaseURL();
        String patientId = session.getPatientId();
        PoFPatient p = new PoFPatient(context, baseURL, patientId);
        session.setPofPatient(p);
    }
    
}
