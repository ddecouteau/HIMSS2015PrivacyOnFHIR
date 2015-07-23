/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.myappsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;
import gov.va.ehtac.myappsonfhir.HealthElementsForPatientTouchKitUI;
import gov.va.ehtac.myappsonfhir.session.SessionAttributes;

/**
 *
 * @author Duane DeCouteau
 */
@SuppressWarnings("serial")
public class ValidicMarketPlace extends NavigationView {
    private SessionAttributes session;
    
    public ValidicMarketPlace() {
        session = ((HealthElementsForPatientTouchKitUI) UI.getCurrent()).getSessionAttributes();        
        setCaption("Health App Marketplace");
        final VerticalComponentGroup content = new VerticalComponentGroup();
        BrowserFrame browser = new BrowserFrame("", new ExternalResource("https://app.validic.com/54d4fdb88ee42122c2000013/dbsvmtYYW8e_o6bn2DMs"));
        browser.setWidth("100%");
        browser.setHeight("500px");
        content.addComponent(browser);              
        setContent(new CssLayout(content));        
    }
}
