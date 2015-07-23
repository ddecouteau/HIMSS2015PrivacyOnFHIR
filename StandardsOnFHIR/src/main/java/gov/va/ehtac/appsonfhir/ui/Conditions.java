package gov.va.ehtac.appsonfhir.ui;

import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IDatatype;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Condition;
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
import gov.va.ehtac.appsonfhir.session.SessionAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.xml.ws.BindingProvider;
import org.hl7.fhir.hcsclient.ApplySecurityLabelsFHIRForResourceReleaseResponse.Return;
import org.hl7.fhir.hcsclient.HCSOrchestrator;
import org.hl7.fhir.hcsclient.HCSOrchestratorService;
import org.hl7.fhir.hcsclient.HcsCategory;
import org.hl7.fhir.hcsclient.HcsTaggingResponse;

@SuppressWarnings("serial")
public class Conditions extends NavigationView {
    private Table conditionTable = new Table();
    SessionAttributes session;    
    Bundle bundle;

    public Conditions() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Patient Conditions - "+session.getPatientNameAgeGenderDisplay());
        final VerticalComponentGroup content = new VerticalComponentGroup();


//        final Label formLabel = new Label("<p><b><font color=\"blue\">Condition Name</font></b></p>"+
//                                          "<p>Status:  Active</p>"+
//                                          "<p>Onset Date: 2014-01-01</p>", ContentMode.HTML);
        createTable();
        
        content.addComponent(conditionTable);


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
                    bundle = getConditions();
                    HCSOrchestratorService service = new HCSOrchestratorService();
                    HCSOrchestrator port = service.getHCSOrchestratorPort();
                    ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:7080/HCSServices/HCSOrchestratorService?wsdl"); 
                    
                    List<HcsCategory> hcs = new ArrayList();
                    Return r = port.applySecurityLabelsFHIRForResourceRelease(composeXMLStringFeed(bundle), "1", hcs, session.getPurposeOfUse());
                    session.getPofPatient().reloadHCSConditions(r.getProcessedAtomFeed());
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
    
    private Bundle getConditions() {
        //for time being just do this

        Bundle conditions = null;
        try {
            IGenericClient client = ((HealthElementsTouchKitUI) UI.getCurrent()).getFhirClient();
            conditions = client.search(new UriDt(getConditionSearchString()));
            System.out.println("Number of Conditions Returned: "+conditions.getEntries().size());
            
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
        conditionTable = new Table();
//        conditionTable.setWidth("100%");
//        conditionTable.setHeight("250px");
        conditionTable.setMultiSelect(false);
        conditionTable.setSelectable(true);
        conditionTable.setImmediate(true); // react at once when something is selected
        conditionTable.setEditable(false);
        conditionTable.setContainerDataSource(populatePatientConditions());

        conditionTable.setColumnReorderingAllowed(true);
        conditionTable.setColumnCollapsingAllowed(false);
        conditionTable.setVisibleColumns(new Object[] {"selected","displayName", "status", "onset", "sls"});
        conditionTable.setColumnHeaders(new String[] {"","Condition Name", "Status", "Date Asserted", ""});        
        conditionTable.setColumnWidth("sls", 80);
    }
    
    private void refreshTable() {
        conditionTable.removeAllItems();
        conditionTable.setContainerDataSource(populatePatientConditions());
        conditionTable.setColumnReorderingAllowed(true);
        conditionTable.setColumnCollapsingAllowed(false);
        conditionTable.setVisibleColumns(new Object[] {"selected","displayName", "status", "onset", "sls"});
        conditionTable.setColumnHeaders(new String[] {"","Condition Name", "Status", "Date Asserted", ""});    
        conditionTable.setColumnWidth("sls", 80);
    }
    
    private IndexedContainer populatePatientConditions() {
        IndexedContainer container = new IndexedContainer();
        List<Condition> cList = session.getPofPatient().getConditions();
        List<ConditionResults> rList = new ArrayList();
        Iterator cIter = cList.iterator();
        while (cIter.hasNext()) {
            Condition cond = (Condition)cIter.next();
            ConditionResults obj = new ConditionResults();
            obj.setDisplayName(cond.getCode().getCoding().get(0).getDisplay());
            obj.setStatus(cond.getClinicalStatus());
            String onset = "";
            try {               
                onset = convertDateToString(cond.getDateAsserted());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            obj.setOnsetDate(onset);
            obj.setPayload(cond);
            String sls = "";
            if (cond.getResourceMetadata() != null && cond.getResourceMetadata().get(ResourceMetadataKeyEnum.SECURITY_LABELS) != null) {
                StringBuffer sb = new StringBuffer();                
                for (CodingDt label : (List<CodingDt>)cond.getResourceMetadata().get(ResourceMetadataKeyEnum.SECURITY_LABELS)) {
                    sb.append(label.getCode());
                    sb.append(" ");
                }
                sls = sb.toString();
            }
            obj.setSls(sls);
            rList.add(obj);            
        }
        
//        try {
//            Bundle conditions = getConditions();
//            List<BundleEntry> lConditions = conditions.getEntries();
//            Iterator iter = lConditions.iterator();
//            while (iter.hasNext()) {
//                try {
//                    BundleEntry entry = (BundleEntry)iter.next();
//                    Condition cond = (Condition)entry.getResource();
//                    ConditionResults obj = new ConditionResults();
//                    obj.setDisplayName(cond.getCode().getCoding().get(0).getDisplay());
//                    obj.setStatus(cond.getStatus());
//                    String onset = "";
//                    try {
//                        IDatatype idt = cond.getOnset();
//                        onset = idt.toString();
//                    }
//                    catch (Exception ex) {
//                        //no data in onset date
//                    }
//
//                    obj.setOnsetDate(onset);
//                    obj.setPayload(cond);
//                    rList.add(obj);
//                }
//                catch (Exception ex) {
//                    System.out.println("****ERROR "+ex.getMessage());
//                    ex.printStackTrace();
//                }
//            }
//            
//            
//        }
//        catch (Exception ex) {
//            
//        }
        container = createIndexedContainerConditions(rList);
        return container;
    }
    
    private IndexedContainer createIndexedContainerConditions(Collection<ConditionResults> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("selected", CheckBox.class, null);
        container.addContainerProperty("displayName", String.class, null);
        container.addContainerProperty("status", String.class, null);
        container.addContainerProperty("onset", String.class, null);
        container.addContainerProperty("sls", String.class, null);
        container.addContainerProperty("oCondition", ConditionResults.class, null);

        int i = 0;
        for (ConditionResults p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("selected").setValue(new CheckBox());
            item.getItemProperty("displayName").setValue(p.getDisplayName());
            item.getItemProperty("status").setValue(p.getStatus());
            item.getItemProperty("onset").setValue(p.getOnsetDate());
            item.getItemProperty("sls").setValue(p.getSls());
            item.getItemProperty("oCondition").setValue(p);
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

    private String getConditionSearchString() {
        String res = session.getBaseURL()+"Condition?subject=Patient/"+session.getPatientId();
        System.out.println(res);
        return res;
    }
    
    private String convertDateToString(Date dt) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String res = sd.format(dt);
        return res;
    }
}
