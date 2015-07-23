package gov.va.ehtac.myappsonfhir.ui;

import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.dstu2.resource.AllergyIntolerance;
import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.parser.XmlParser;
import ca.uhn.fhir.rest.client.IGenericClient;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import gov.va.ehtac.myappsonfhir.HealthElementsForPatientTouchKitUI;
import gov.va.ehtac.myappsonfhir.dataresults.AllergyIntoleranceResults;
import gov.va.ehtac.myappsonfhir.session.SessionAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("serial")
public class AdverseReactions extends NavigationView {
    private Table reactionsTable = new Table();
    SessionAttributes session;    
    Bundle bundle;

    public AdverseReactions() {
        session = ((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Allergy Intolerance - "+session.getPatientNameGenderDisplay());
        final VerticalComponentGroup content = new VerticalComponentGroup();


//        final Label formLabel = new Label("<p><b><font color=\"blue\">Condition Name</font></b></p>"+
//                                          "<p>Status:  Active</p>"+
//                                          "<p>Onset Date: 2014-01-01</p>", ContentMode.HTML);
        createTable();
        
        content.addComponent(reactionsTable);


        setContent(new CssLayout(content));

    }
    
    private Bundle getAdverseReactions() {
        //for time being just do this

        Bundle conditions = null;
        try {
            IGenericClient client = ((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getFhirClient();
            conditions = client.search(new UriDt(getAdverseReactionSearchString()));
            System.out.println("Number of Allergy Intolerances Returned: "+conditions.getEntries().size());
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        bundle = conditions;
        return conditions;
    }
    
    private void createTable() {
        reactionsTable = new Table();
//        reactionsTable.setWidth("100%");
//        reactionsTable.setHeight("250px");
        reactionsTable.setMultiSelect(false);
        reactionsTable.setSelectable(true);
        reactionsTable.setImmediate(true); // react at once when something is selected
        reactionsTable.setEditable(false);
        reactionsTable.setContainerDataSource(populatePatientAdverseReactions());

        reactionsTable.setColumnReorderingAllowed(true);
        reactionsTable.setColumnCollapsingAllowed(false);
        reactionsTable.setVisibleColumns(new Object[] {"selected", "reaction", "severity", "date", "status"});
        reactionsTable.setColumnHeaders(new String[] {"","Reaction", "Severity", "Date", "Status"});
        
        
    }
    
    private IndexedContainer populatePatientAdverseReactions() {
        IndexedContainer container = new IndexedContainer();
        List<AllergyIntoleranceResults> rList = new ArrayList();
        try {
            Bundle conditions = getAdverseReactions();
            List<BundleEntry> lConditions = conditions.getEntries();
            Iterator iter = lConditions.iterator();
            while (iter.hasNext()) {
                try {
                    BundleEntry entry = (BundleEntry)iter.next();
                    AllergyIntolerance allergy = (AllergyIntolerance)entry.getResource();
                    String intoleranceName = allergy.getText().getDiv().getValueAsString();
                    String dateRecorded = allergy.getRecordedDateElement().getValueAsString();
                    String criticality = allergy.getCriticality();
                    String status = allergy.getStatus();
                    AllergyIntoleranceResults obj = new AllergyIntoleranceResults();
                    
                    obj.setAllergyName(new Label(intoleranceName));
                    obj.setCriticality(criticality);
                    obj.setStatus(status);
                    obj.setRecordedDate(dateRecorded);
                    obj.setPayload(allergy);
                    rList.add(obj);
                }
                catch (Exception ex) {
                    System.out.println("****ERROR "+ex.getMessage());
                    ex.printStackTrace();
                }
            }
            
            
        }
        catch (Exception ex) {
            
        }
        container = createIndexedContainerAdverseReactions(rList);
        return container;
    }
    
    private IndexedContainer createIndexedContainerAdverseReactions(Collection<AllergyIntoleranceResults> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("selected", CheckBox.class, null);
        container.addContainerProperty("reaction", Label.class, null);
        container.addContainerProperty("severity", String.class, null);
        container.addContainerProperty("date", String.class, null);
        container.addContainerProperty("status", String.class, null);
        container.addContainerProperty("oAdverseReactions", AllergyIntoleranceResults.class, null);

        int i = 0;
        for (AllergyIntoleranceResults p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("selected").setValue(new CheckBox());
            item.getItemProperty("reaction").setValue(p.getAllergyName());
            item.getItemProperty("severity").setValue(p.getCriticality());
            item.getItemProperty("date").setValue(p.getRecordedDate());
            item.getItemProperty("status").setValue(p.getStatus());
            item.getItemProperty("oAdverseReactions").setValue(p);
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

    private String getAdverseReactionSearchString() {
        String res = session.getBaseURL()+"AllergyIntolerance?subject=Patient/"+session.getPatientId();
        System.out.println(res);
        return res;
    }
}
