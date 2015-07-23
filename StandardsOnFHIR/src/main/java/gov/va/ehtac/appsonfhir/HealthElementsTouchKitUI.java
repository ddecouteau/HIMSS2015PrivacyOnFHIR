package gov.va.ehtac.appsonfhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.rest.client.IGenericClient;
import gov.va.ehtac.appsonfhir.ui.*;
import gov.va.ehtac.appsonfhir.gwt.client.*;

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
import gov.va.ehtac.appsonfhir.session.SessionAttributes;

/**
 * The UI's "main" class
 */
@SuppressWarnings("serial")
@Widgetset("gov.va.ehtac.appsonfhir.gwt.HealthElementsWidgetSet")
@Theme("touchkit")
// Cache static application files so as the application can be started
// and run even when the network is down.
@CacheManifestEnabled
// Switch to the OfflineMode client UI when the server is unreachable
@OfflineModeEnabled
// Make the server retain UI state whenever the browser reloads the app
@PreserveOnRefresh
public class HealthElementsTouchKitUI extends UI {
    private SessionAttributes sessionAttributes;

    private FhirContext fhirContext = FhirContext.forDstu2();
    private IGenericClient fhirClient;
    private TabBarView tabBarView;

    
    private final HealthElementsPersistToServerRpc serverRpc = new HealthElementsPersistToServerRpc() {
        @Override
        public void persistToServer() {
            // TODO this method is called from client side to store offline data
        }
    };

    @Override
    protected void init(VaadinRequest request) {
        sessionAttributes = new SessionAttributes();
        initFHIRClient();
        setTabBarView(new TabBarView());
        final NavigationManager navigationManager = new NavigationManager();
        navigationManager.setCaption("Resources");
        navigationManager.setCurrentComponent(new MenuView());
        Tab tab;
        tab = getTabBarView().addTab(navigationManager);
        tab.setIcon(FontAwesome.FIRE);
        tab = getTabBarView().addTab(new PatientSearch(), "Patient Search");
        tab.setIcon(FontAwesome.SEARCH);
        tab = getTabBarView().addTab(new DestinationControl(), "Transfer");
        tab.setIcon(FontAwesome.UPLOAD);
        tab = getTabBarView().addTab(new ProfileView(), "Provider Profile");
        tab.setIcon(FontAwesome.COFFEE);
        tab = getTabBarView().addTab(new Settings(), sessionAttributes.getGetConnectedServer());
        tab.setIcon(FontAwesome.DATABASE);
        tab = getTabBarView().addTab(new HCSLogs(), "Standards Output");
        tab.setIcon(FontAwesome.TABLE);
        tab = getTabBarView().addTab(new LogoutView(), "Logout");
        tab.setIcon(FontAwesome.POWER_OFF);
        
        getTabBarView().setImmediate(true);

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

    /**
     * @return the sessionUI
     */
    public SessionAttributes getSessionAttributes() {
        return sessionAttributes;
    }
    
    private void initFHIRClient() {
        setFhirClient(getFhirContext().newRestfulGenericClient(getSessionAttributes().getBaseURL()));
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
     * @return the fhirContext
     */
    public FhirContext getFhirContext() {
        return fhirContext;
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

    /**
     * @param fhirContext the fhirContext to set
     */
    public void setFhirContext(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

}

