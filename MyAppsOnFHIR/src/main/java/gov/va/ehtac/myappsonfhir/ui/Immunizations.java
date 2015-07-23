package gov.va.ehtac.myappsonfhir.ui;

import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Condition;
import ca.uhn.fhir.model.dstu2.resource.Immunization;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.parser.XmlParser;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import gov.va.ehtac.myappsonfhir.HealthElementsForPatientTouchKitUI;
import gov.va.ehtac.myappsonfhir.dataresults.ImmunizationResults;
import gov.va.ehtac.myappsonfhir.session.SessionAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.xml.ws.BindingProvider;


@SuppressWarnings("serial")
public class Immunizations extends NavigationView {
    private Table immunizationTable;
    SessionAttributes session;
    Bundle bundle;
    
    public Immunizations() {
        session = ((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Patient Immunizations - "+session.getPatientNameGenderDisplay());
        final VerticalComponentGroup content = new VerticalComponentGroup();


        createTable();
        
        content.addComponent(immunizationTable);


        final Button submitButton = new Button("Submit");
        submitButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Thanks!");
            }
        });
        setContent(new CssLayout(content, submitButton));
    }
    private Bundle getImmunizations() {
        //for time being just do this

        Bundle immunizations = null;
        try {
            IGenericClient client = ((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getFhirClient();
            immunizations = client.search(new UriDt(getImmunizationSearchString()));
            System.out.println("Number of Immunization Returned: "+immunizations.getEntries().size());
//            List<AtomEntry<? extends Resource>> lConditions = conditions.getEntryList();
//            System.out.println("Number OF Medications: "+lConditions.size());
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        bundle = immunizations;
        return immunizations;
    }
    
    private void createTable() {
        immunizationTable = new Table();
//        immunizationTable.setWidth("100%");
//        immunizationTable.setHeight("250px");
        immunizationTable.setMultiSelect(false);
        immunizationTable.setSelectable(true);
        immunizationTable.setImmediate(true); // react at once when something is selected
        immunizationTable.setEditable(false);
        immunizationTable.setContainerDataSource(populatePatientImmunizations());

        immunizationTable.setColumnReorderingAllowed(true);
        immunizationTable.setColumnCollapsingAllowed(false);
        immunizationTable.setVisibleColumns(new Object[] {"selected",  "displayName", "adminDate", "refused", "refusalReason", "reported", "sls"});
        immunizationTable.setColumnHeaders(new String[] {"", "Vaccine Type", "Date", "Refused", "Refusal Reason", "Reported", ""});
        immunizationTable.setColumnWidth("sls", 80);        
        
        
    }
    
    private void refreshTable() {
        immunizationTable.removeAllItems();
        immunizationTable.setContainerDataSource(populatePatientImmunizations());
        immunizationTable.setColumnReorderingAllowed(true);
        immunizationTable.setColumnCollapsingAllowed(false);
        immunizationTable.setVisibleColumns(new Object[] {"selected",  "displayName", "adminDate", "refused", "refusalReason", "reported", "sls"});
        immunizationTable.setColumnHeaders(new String[] {"", "Vaccine Type", "Date", "Refused", "Refusal Reason", "Reported", ""});    
        immunizationTable.setColumnWidth("sls", 80);        
    }
    
    private IndexedContainer populatePatientImmunizations() {
        IndexedContainer container = new IndexedContainer();
        List<Immunization> iList = session.getPofPatient().getImmunizations();
        List<ImmunizationResults> rList = new ArrayList();
        Iterator iIter = iList.iterator();
        while (iIter.hasNext()) {
            Immunization imz = (Immunization)iIter.next();
            ImmunizationResults obj = new ImmunizationResults();
            obj.setDisplayName(imz.getVaccineType().getCodingFirstRep().getDisplay());
            obj.setAdministerDate(imz.getDate().toString());
            obj.setRefusedIndicator(new Boolean(imz.getWasNotGiven()));
            if (imz.getWasNotGiven().booleanValue()) {
                obj.setRefusalReason(imz.getExplanation().getReasonNotGiven().get(0).getCodingFirstRep().getDisplay());
            }
            else {
                obj.setRefusalReason("Not Applicable");
            }
            obj.setReported(new Boolean(imz.getReported()));
            obj.setPayload(imz);
            String sls = "";
            if (imz.getResourceMetadata() != null && imz.getResourceMetadata().get(ResourceMetadataKeyEnum.SECURITY_LABELS) != null) {
                StringBuffer sb = new StringBuffer();                
                for (CodingDt label : (List<CodingDt>)imz.getResourceMetadata().get(ResourceMetadataKeyEnum.SECURITY_LABELS)) {
                    sb.append(label.getCode());
                    sb.append(" ");
                }
                sls = sb.toString();
            }
            obj.setSls(sls);
            
            rList.add(obj);       
        }
//        try {
//            Bundle immunizations = getImmunizations();
//            List<BundleEntry> lImmunizations = immunizations.getEntries();
//            Iterator iter = lImmunizations.iterator();
//            while (iter.hasNext()) {
//                try {
//                    BundleEntry entry = (BundleEntry)iter.next();
//                    Immunization imz = (Immunization)entry.getResource();
//                    ImmunizationResults obj = new ImmunizationResults();
//                    obj.setDisplayName(imz.getVaccineType().getCoding().get(0).getDisplay());
//                    obj.setAdministerDate(imz.getDate().toString());
//                    obj.setRefusedIndicator(new Boolean(imz.getWasNotGiven()));
//                    if (imz.getWasNotGiven().booleanValue()) {
//                        obj.setRefusalReason(imz.getExplanation().getReason().get(0).getCoding().get(0).getDisplay());
//                    }
//                    else {
//                        obj.setRefusalReason("Not Applicable");
//                    }
//                    obj.setReported(new Boolean(imz.getReported()));
//                    obj.setPayload(imz);
//                    rList.add(obj);
//                }
//                catch (Exception ex) {
//                    System.out.println("****ERROR "+ex.getMessage());
//                }
//            }
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
        container = createIndexedContainerConditions(rList);
        return container;
    }
    
    private IndexedContainer createIndexedContainerConditions(Collection<ImmunizationResults> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("selected", CheckBox.class, null);
        container.addContainerProperty("displayName", Label.class, null);
        container.addContainerProperty("adminDate", String.class, null);
        container.addContainerProperty("refused", Boolean.class, null);
        container.addContainerProperty("refusalReason", String.class, null);
        container.addContainerProperty("reported", Boolean.class, null);
        container.addContainerProperty("sls", String.class, null);
        container.addContainerProperty("oImmunization", ImmunizationResults.class, null);

        int i = 0;
        for (ImmunizationResults p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("selected").setValue(new CheckBox());
            Label l = new Label(p.getDisplayName());
            l.setContentMode(ContentMode.HTML);
            item.getItemProperty("displayName").setValue(l);
            item.getItemProperty("adminDate").setValue(p.getAdministerDate());
            item.getItemProperty("refused").setValue(p.getRefusedIndicator());
            if (p.getRefusedIndicator().booleanValue()) {
                item.getItemProperty("refusalReason").setValue(p.getRefusalReason());
            }
            else {
                item.getItemProperty("refusalReason").setValue("Not Applicable");
            }
            item.getItemProperty("reported").setValue(p.getReported());
            item.getItemProperty("sls").setValue(p.getSls());
            item.getItemProperty("oImmunization").setValue(p);
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
    
    private String getImmunizationSearchString() {
        String res = session.getBaseURL()+"Immunization?subject=Patient/"+session.getPatientId();
        System.out.println(res);
        return res;
    }
    
}
