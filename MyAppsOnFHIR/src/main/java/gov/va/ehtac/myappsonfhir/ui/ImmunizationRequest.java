/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.ui;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Immunization;
import com.edmondsci.fhir.uma.SimpleUMAEnabledFhirClient;
import com.edmondsci.oauth.OAuth2Client;
import com.edmondsci.pof.UMAEnabledPoFPatient;
import com.edmondsci.uma.SimpleUMAUser;
import com.edmondsci.uma.UMAConfig;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import gov.va.ehtac.myappsonfhir.HealthElementsForPatientTouchKitUI;
import gov.va.ehtac.myappsonfhir.dataresults.ImmunizationResults;
import gov.va.ehtac.myappsonfhir.session.SessionAttributes;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Duane DeCouteau
 */
public class ImmunizationRequest extends NavigationView {
    private ProgressBar progress = new ProgressBar();
    private SessionAttributes session;
    private Button beginButton = new Button("Begin Application Request");
    private Popover popover;
    private UMAEnabledPoFPatient alice;
    private Table immunizationTable;
    
    
    public ImmunizationRequest() {
        session = ((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Protected Resource Request - UMA Flow");  
        final VerticalComponentGroup content = new VerticalComponentGroup();
        this.setWidth("100%");
        this.setHeight("100%");
        
        beginButton.setImmediate(true);
        beginButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                //call uma pofpatient and request immunizations

                //session.getPofPatient().loadImmunizationsUMA(session.getContext(), "http://va.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/");
                try {
                    String res = performUMARequest();
                    
                    System.out.println(res);
                    Popover popover = getPopoverResults(res);
                    popover.showRelativeTo(getNavigationBar());
                    

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        

        content.addComponent(beginButton);
        
        setContent(new CssLayout(content));            
    }
    
    private String performUMARequest() throws Exception, URISyntaxException {
        FhirContext context = FhirContext.forDstu2();

        String umaConfigUri ="https://healthauth.org/.well-known/uma-configuration";
        UMAConfig umaConfig = UMAConfig.loadFrom(umaConfigUri).setRequesting_party_claims_endpoint("https://healthauth.org/rqp_claims");
        String appClientId=  "39e7263d-bc88-401d-a75b-1797189e655b";
        String appClientSecret = "aNIIsy4Ix0tPX8ZkRGn25w3JIYBw1zJVBTsZyN_k2eYIFu0mTAX7C9f1hCH41i5JWtF92ccmRSMN1IyAfTCFRg";
        String appClientRedirectUri = "http://mhs.edmondsci.com:8080/appsonfhir";

        OAuth2Client appClient = new OAuth2Client();
        appClient.setClientId(appClientId);
        appClient.setClientSecret(appClientSecret);
        appClient.setClientRedirectUri(appClientRedirectUri);

        String adminUsername = "admin";
        String adminPassword = "password";

        String appUsersUsername="drduane";
        String appUsersPassword="password";
        String appUsersEmail = "drduane@openid.test.sitenv.org";

        SimpleUMAUser appUser = new SimpleUMAUser()
                                    .setUsername(appUsersUsername)
                                    .setPassword(appUsersPassword)
                                    .setEmail(appUsersEmail);


        FhirContext fhirContext = FhirContext.forDstu2();
        SimpleUMAEnabledFhirClient umaEnabledFhirClient = new SimpleUMAEnabledFhirClient(fhirContext, umaConfig, appClient, appUser, adminUsername, adminPassword);


        alice = new UMAEnabledPoFPatient(context,
                "http://va.edmondsci.com:8080/uma-hapi-fhir/baseDstu2", 
                umaEnabledFhirClient, 
                "ONC-VA-POF-P-001");

//        UMAEnabledPoFPatient alice = new UMAEnabledPoFPatient(context, 
//                "http://va.edmondsci.com:8080/uma-hapi-fhir/baseDstu2", 
//                new MagicUMAEnabledFhirClient(fhirContext, "http://va.edmondsci.com:8080/uma-hapi-fhir/baseDstu2", "THE_MAGIC_TOKEN" ),
//                "ONC-VA-POF-P-001");


        String res = alice.getAccessControlErrorsWhileLoading();
        System.out.println(res);

        return res;
        
    }
    
    private Popover getPopoverResults(String umaResults) {
        popover = new Popover();
        popover.setModal(true);
        popover.setClosable(true);
        popover.setWidth("500px");
        popover.setHeight("350px");
        CssLayout popLayout = new CssLayout();
        popLayout.setSizeFull();
        
        NavigationView navView = new NavigationView(popLayout);
        navView.setCaption("Request Completed");
        Label lbl = new Label(umaResults);

        CssLayout layout2 = new CssLayout();
        layout2.addComponent(lbl);
        setTable();
        layout2.addComponent(immunizationTable);
        popLayout.addComponent(layout2);
        
        Button close = new Button(null, new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                popover.removeFromParent();
            }
        });
        close.setIcon(new ThemeResource("../runo/icons/64/cancel.png"));
        navView.setRightComponent(close);
        popover.setContent(navView);
        
        return popover;
    }
    
    private void setTable() {
        immunizationTable = new Table();
        immunizationTable.setCaption("Immunizations Allowed To Transferred");
        immunizationTable.setWidth("100%");
        immunizationTable.setHeight("250px");
        immunizationTable.setMultiSelect(false);
        immunizationTable.setSelectable(true);
        immunizationTable.setImmediate(true); // react at once when something is selected
        immunizationTable.setEditable(false);
        immunizationTable.setContainerDataSource(populateImmunizationReturned());

        immunizationTable.setColumnReorderingAllowed(true);
        immunizationTable.setColumnCollapsingAllowed(false);
        immunizationTable.setVisibleColumns(new Object[] {"displayName"});
        immunizationTable.setColumnHeaders(new String[] {"Vaccine Type"});
    }
    
    private IndexedContainer populateImmunizationReturned() {
        IndexedContainer res = new IndexedContainer();
        List<Immunization> l = alice.getImmunizations();
        res = createIndexedContainer(l);
        return res;
    }
    
    private IndexedContainer createIndexedContainer(Collection<Immunization> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("displayName", String.class, null);
        container.addContainerProperty("oImmunization", Immunization.class, null);

        int i = 0;
        for (Immunization p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("displayName").setValue(p.getVaccineType().getCodingFirstRep().getDisplay());
            item.getItemProperty("oImmunization").setValue(p);
        }
        return container;
        
    }

}