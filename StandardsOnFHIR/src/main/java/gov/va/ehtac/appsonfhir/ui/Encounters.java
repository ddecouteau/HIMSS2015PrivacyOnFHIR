package gov.va.ehtac.appsonfhir.ui;

import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IDatatype;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Condition;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.parser.XmlParser;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.dataresults.ConditionResults;
import gov.va.ehtac.appsonfhir.dataresults.EncounterResults;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.xml.ws.BindingProvider;
import org.hl7.fhir.hcsclient.ApplySecurityLabelsFHIRForResourceReleaseResponse.Return;
import org.hl7.fhir.hcsclient.HCSOrchestrator;
import org.hl7.fhir.hcsclient.HCSOrchestratorService;
import org.hl7.fhir.hcsclient.HcsCategory;
import org.hl7.fhir.hcsclient.HcsTaggingResponse;

@SuppressWarnings("serial")
public class Encounters extends NavigationView {
    private Table encounterTable = new Table();
    SessionAttributes session;    
    Bundle bundle;

    public Encounters() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Patient Encounters - "+session.getPatientNameAgeGenderDisplay());
        final VerticalComponentGroup content = new VerticalComponentGroup();


//        final Label formLabel = new Label("<p><b><font color=\"blue\">Condition Name</font></b></p>"+
//                                          "<p>Status:  Active</p>"+
//                                          "<p>Onset Date: 2014-01-01</p>", ContentMode.HTML);
        createTable();
        
        content.addComponent(encounterTable);


        final Button submitButton = new Button("Submit");
        submitButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Thanks!");
            }
        });
        Button b = new Button("HCS");
        b.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    bundle = getEncounters();
                    HCSOrchestratorService service = new HCSOrchestratorService();
                    HCSOrchestrator port = service.getHCSOrchestratorPort();
                    ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:7080/HCSServices/HCSOrchestratorService?wsdl"); 
                    
                    List<HcsCategory> hcs = new ArrayList();
                    Return r = port.applySecurityLabelsFHIRForResourceRelease(composeXMLStringFeed(bundle), "1", hcs, session.getPurposeOfUse());
                    session.getPofPatient().reloadHCSEncounters(r.getProcessedAtomFeed());
                    refreshTable();
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        });

        b.setIcon(FontAwesome.SHARE);
        setRightComponent(b);
        setContent(new CssLayout(content));

    }
    
    private Bundle getEncounters() {
        //for time being just do this

        Bundle conditions = null;
        try {
            IGenericClient client = ((HealthElementsTouchKitUI) UI.getCurrent()).getFhirClient();
            conditions = client.search(new UriDt(getEncounterSearchString()));
            System.out.println("Number of Encounters Returned: "+conditions.getEntries().size());
            
//            Dstu 1 below...            
//            FHIRSimpleClient client = new FHIRSimpleClient();
//            Map<String, String> parameters = new HashMap<String, String>();
//            client.setPreferredFeedFormat(FeedFormat.FEED_XML);            
//            parameters.put("subject", "Patient/smart-:P:"+session.getPatientId());
//            client.initialize(session.getBaseURL());
//            conditions = client.search(Condition.class, parameters);
//            composeXMLStringFeed(conditions);            
//            List<AtomEntry<? extends Resource>> lConditions = conditions.getEntryList();
//            System.out.println("Number OF Medications: "+lConditions.size());
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        bundle = conditions;
        return conditions;
    }
    
    private void createTable() {
        encounterTable = new Table();
//        encounterTable.setWidth("100%");
//        encounterTable.setHeight("250px");
        encounterTable.setMultiSelect(false);
        encounterTable.setSelectable(true);
        encounterTable.setImmediate(true); // react at once when something is selected
        encounterTable.setEditable(false);
        encounterTable.setContainerDataSource(populatePatientEncounters());

        encounterTable.setColumnReorderingAllowed(true);
        encounterTable.setColumnCollapsingAllowed(false);
        encounterTable.setVisibleColumns(new Object[] {"selected", "displayname", "startdate", "enddate", "encountertype", "status", "sls"});
        encounterTable.setColumnHeaders(new String[] {"", "Chief Complaint", "Start Date", "End Date", "Type", "Status", ""}); 
    }
    
    private void refreshTable() {
        encounterTable.removeAllItems();
        encounterTable.setContainerDataSource(populatePatientEncounters());
        encounterTable.setColumnReorderingAllowed(true);
        encounterTable.setColumnCollapsingAllowed(false);
        encounterTable.setVisibleColumns(new Object[] {"selected", "displayname", "startdate", "enddate", "encountertype", "status", "sls"});
        encounterTable.setColumnHeaders(new String[] {"", "Chief Complaint", "Start Date", "End Date", "Type", "Status", ""}); 
    }
    
    private IndexedContainer populatePatientEncounters() {
        IndexedContainer container = new IndexedContainer();
        List<Encounter> eList = session.getPofPatient().getEncounters();
        List<EncounterResults> rList = new ArrayList();
        Iterator eIter = eList.iterator();
        while (eIter.hasNext()) {
            Encounter e = (Encounter)eIter.next();
            EncounterResults obj = new EncounterResults();
            obj.setDisplayName(e.getReason().get(0).getCodingFirstRep().getDisplay());
            obj.setStatus(e.getStatus());
            obj.setStartDate("N/A");
            obj.setEndDate("N/A");
            try {
                if (e.getPeriod() != null) {
                    obj.setEndDate(e.getPeriod().getEndElement().getValueAsString());
                    obj.setStartDate(e.getPeriod().getStartElement().getValueAsString());
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            obj.setEncounterType(e.getClassElement().toString());
            obj.setPayload(e);
            String sls = "";
            if (e.getResourceMetadata() != null && e.getResourceMetadata().get(ResourceMetadataKeyEnum.SECURITY_LABELS) != null) {
                StringBuffer sb = new StringBuffer();                
                for (CodingDt label : (List<CodingDt>)e.getResourceMetadata().get(ResourceMetadataKeyEnum.SECURITY_LABELS)) {
                    sb.append(label.getCode());
                    sb.append(" ");
                }
                sls = sb.toString();
            }
            obj.setSls(sls);
            rList.add(obj);
        }
//        try {
//            Bundle conditions = getEncounters();
//            List<BundleEntry> lConditions = conditions.getEntries();
//            Iterator iter = lConditions.iterator();
//            while (iter.hasNext()) {
//                try {
//                    BundleEntry entry = (BundleEntry)iter.next();
//                    Encounter cond = (Encounter)entry.getResource();
//                    EncounterResults obj = new EncounterResults();
//                    obj.setDisplayName(cond.getText().getDiv().getValueAsString());
//                    obj.setStatus(cond.getStatus());
//                    obj.setEndDate(cond.getPeriod().getEndElement().getValueAsString());
//                    obj.setStartDate(cond.getPeriod().getStartElement().getValueAsString());
//                    obj.setEncounterType(cond.getClassElement().toString());
//                    obj.setPayload(cond);
//                    rList.add(obj);
//                }
//                catch (Exception ex) {
//                    System.out.println("****ERROR "+ex.getMessage());
//                    ex.printStackTrace();
//                }
//            }          
//        }
//        catch (Exception ex) {           
//        }
        container = createIndexedContainerEncounters(rList);
        return container;
    }
    
    private IndexedContainer createIndexedContainerEncounters(Collection<EncounterResults> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("selected", CheckBox.class, null);
        container.addContainerProperty("startdate", String.class, null);
        container.addContainerProperty("enddate", String.class, null);
        container.addContainerProperty("encountertype", String.class, null);
        container.addContainerProperty("status", String.class, null);
        container.addContainerProperty("displayname", String.class, null);
        container.addContainerProperty("sls", String.class, null);
        container.addContainerProperty("oEncounters", EncounterResults.class, null);

        int i = 0;
        for (EncounterResults p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("selected").setValue(new CheckBox());
            item.getItemProperty("startdate").setValue(p.getStartDate());
            item.getItemProperty("enddate").setValue(p.getEndDate());
            item.getItemProperty("encountertype").setValue(p.getEncounterType());
            item.getItemProperty("status").setValue(p.getStatus());
            item.getItemProperty("displayname").setValue(p.getDisplayName());
            item.getItemProperty("sls").setValue(p.getSls());
            item.getItemProperty("oEncounters").setValue(p);
        }
        return container;
    }
    
    
    private String composeXMLStringFeed(Bundle feed) {
        String res = "";
        try {
            XmlParser xmlP = new XmlParser(((HealthElementsTouchKitUI) UI.getCurrent()).getFhirContext());
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

    private String getEncounterSearchString() {
        String res = session.getBaseURL()+"Encounter?patient=Patient/"+session.getPatientId();
        System.out.println(res);
        return res;
    }
}
