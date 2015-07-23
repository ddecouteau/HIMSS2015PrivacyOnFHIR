package gov.va.ehtac.myappsonfhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.IGenericClient;
import gov.va.ehtac.myappsonfhir.ui.*;
import gov.va.ehtac.myappsonfhir.gwt.client.*;

import com.vaadin.addon.touchkit.annotations.CacheManifestEnabled;
import com.vaadin.addon.touchkit.annotations.OfflineModeEnabled;
import com.vaadin.addon.touchkit.extensions.OfflineMode;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import gov.va.ehtac.myappsonfhir.dataresults.PoFPatient;
import gov.va.ehtac.myappsonfhir.session.SessionAttributes;

/**
 * The UI's "main" class
 */
@SuppressWarnings("serial")
@Widgetset("gov.va.ehtac.myappsonfhir.gwt.HealthElementsForPatientWidgetSet")
@Theme("touchkit")
// Cache static application files so as the application can be started
// and run even when the network is down.
@CacheManifestEnabled
// Switch to the OfflineMode client UI when the server is unreachable
@OfflineModeEnabled
// Make the server retain UI state whenever the browser reloads the app
@PreserveOnRefresh
public class HealthElementsForPatientTouchKitUI extends UI {
    private SessionAttributes sessionAttributes;

    private FhirContext fhirContext = FhirContext.forDstu2();
    private IGenericClient fhirClient;
    private TabBarView tabBarView;

    private final HealthElementsForPatientPersistToServerRpc serverRpc = new HealthElementsForPatientPersistToServerRpc() {
        @Override
        public void persistToServer() {
            // TODO this method is called from client side to store offline data
        }
    };

    @Override
    protected void init(VaadinRequest request) {
        tabBarView = new TabBarView();
        sessionAttributes = new SessionAttributes();
        grabAllPatientData();
        final NavigationManager navigationManager = new NavigationManager();
        setTabBarView(new TabBarView());
        navigationManager.setCaption("My Personal FHIR");
        navigationManager.setCurrentComponent(new MenuView());
        Tab tab;
        tab = getTabBarView().addTab(navigationManager);
        tab.setIcon(FontAwesome.FIRE);
        tab = getTabBarView().addTab(new FitnessWellness(), "Fitness & Wellness");
        tab.setIcon(FontAwesome.STETHOSCOPE);
        tab = getTabBarView().addTab(new ValidicMarketPlace(), "Validic MarketPlace");
        tab.setIcon(FontAwesome.HOME); 
        tab = getTabBarView().addTab(new ImmunizationRequest(), "Protected Resource Request - UMA Flow");
        tab.setIcon(FontAwesome.REORDER);
        tab = getTabBarView().addTab(new LogoutView(), "Logout");
        tab.setIcon(FontAwesome.POWER_OFF);
        
        tabBarView.setImmediate(true);

        setContent(getTabBarView());

        // Use of the OfflineMode connector is optional.
        OfflineMode offlineMode = new OfflineMode();
        offlineMode.extend(this);
        // Maintain the session when the browser app closes.
        offlineMode.setPersistentSessionCookie(true);
        // Define the timeout in secs to wait when a server request is sent
        // before falling back to offline mode.
        offlineMode.setOfflineModeTimeout(15);
    }

    private void initFHIRClient() {
        setFhirClient(getFhirContext().newRestfulGenericClient(getSessionAttributes().getBaseURL()));
    }
    
    /**
     * @return the sessionAttributes
     */
    public SessionAttributes getSessionAttributes() {
        return sessionAttributes;
    }

    /**
     * @param sessionAttributes the sessionAttributes to set
     */
    public void setSessionAttributes(SessionAttributes sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    /**
     * @return the fhirContext
     */
    public FhirContext getFhirContext() {
        return fhirContext;
    }

    /**
     * @param fhirContext the fhirContext to set
     */
    public void setFhirContext(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    /**
     * @return the fhirClient
     */
    public IGenericClient getFhirClient() {
        return fhirClient;
    }

    /**
     * @param fhirClient the fhirClient to set
     */
    public void setFhirClient(IGenericClient fhirClient) {
        this.fhirClient = fhirClient;
    }

    /**
     * @return the tabBarView
     */
    public TabBarView getTabBarView() {
        return tabBarView;
    }

    /**
     * @param tabBarView the tabBarView to set
     */
    public void setTabBarView(TabBarView tabBarView) {
        this.tabBarView = tabBarView;
    }
    
    private void grabAllPatientData() {
        FhirContext context = sessionAttributes.getContext();
        String baseURL = sessionAttributes.getBaseURL();
        String patientId = sessionAttributes.getPatientId();
        PoFPatient p = new PoFPatient(context, baseURL, patientId);
        sessionAttributes.setPofPatient(p);
    }
    
}

