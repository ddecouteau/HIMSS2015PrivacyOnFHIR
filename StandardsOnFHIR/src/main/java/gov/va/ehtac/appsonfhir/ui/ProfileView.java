/**
 * This software is being provided for technology demonstration purposes only.
 * Use of Vaadin Touchkit Add-on API are provided via Affero General Public License
 * (APGL 3.0).  Please refer the APGL 3.0 at www.gnu.org for further details.
 *
 * Items outside of the use of Vaadin Touchkit Add-on API are being provided per
 * FARS 52.227-14 Rights in Data - General.  Any redistribution or request for
 * copyright requires written consent by the Department of Veterans Affairs.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;


/**
 *
 * @author Duane DeCouteau
 */
public class ProfileView extends NavigationView {
    private Label profileAction;
    private ComboBox confCBX;
    private ComboBox pouCBX;
    private SessionAttributes session; 
    
    @Override
    public void attach() {
        super.attach();
        if (profileAction == null) {
            buildView();
        }
    }
    
    private void buildView() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();
        CssLayout content = new CssLayout();
        content.setWidth("100%");
        setCaption("Profile View");
        
        VerticalComponentGroup vGroup = new VerticalComponentGroup();
        profileAction = new Label(
                "<div style='color:#333;'><p>This screen allows the user to modify their <b>Privacy on FHIR</b> "+
                "Purpose of Use, and Security Level for local Access Control Decisions.</d></div>", Label.CONTENT_XHTML);
        
        vGroup.addComponent(profileAction);
        
        //Label identityLBL = new Label("<b>Identity</b>", Label.CONTENT_XHTML);
        TextField providerIdFLD = new TextField("Provider ID/Email Address");
        providerIdFLD.setEnabled(false);
        TextField userIdFLD = new TextField("User ID");
        userIdFLD.setEnabled(false);
        PasswordField userpassFLD = new PasswordField("Password");
        userpassFLD.setEnabled(false);
        
        providerIdFLD.setValue(session.getProviderId());
        userIdFLD.setValue(session.getUserId());
        userpassFLD.setValue("ds4p");
        
        providerIdFLD.setWidth("400px");
        userIdFLD.setWidth("400px");
        userpassFLD.setWidth("400px");
        
        VerticalComponentGroup vGroup2 = new VerticalComponentGroup();
        vGroup2.setCaption("Identity");
        //vGroup.addComponent(identityLBL);
        vGroup2.addComponent(providerIdFLD);
        vGroup2.addComponent(userIdFLD);
        vGroup2.addComponent(userpassFLD);
        
        
        //Label permissionsLBL = new Label("<b>Access Control Setting and Permissions</b>", Label.CONTENT_XHTML);
        pouCBX = new ComboBox("Current Purpose of Use");
        NavigationButton resourceNavBTN = new NavigationButton("Resource Privileges");
        confCBX = new ComboBox("Security Level");
        NavigationButton sensitivityNavBTN = new NavigationButton("Sensitivity Privileges");
        
        pouCBX.setWidth("400px");
        confCBX.setWidth("400px");
        pouCBX.setTextInputAllowed(false);
        confCBX.setTextInputAllowed(false);
        
        populateConfCBX();
        populatePouCBX();
        
        confCBX.setEnabled(true);
        
        VerticalComponentGroup vGroup3 = new VerticalComponentGroup();
        vGroup3.setCaption("Use and Permission Settings");
        //vGroup.addComponent(permissionsLBL);
        vGroup3.addComponent(pouCBX);
        vGroup3.addComponent(resourceNavBTN);
        vGroup3.addComponent(confCBX);
        vGroup3.addComponent(sensitivityNavBTN);
                
        pouCBX.setValue(session.getPurposeOfUse());
        confCBX.setValue(session.getSecurityLevel());

        
        
        //Label organizationLBL = new Label("<b>Organization Info</b>", Label.CONTENT_XHTML);
        TextField organizationFLD = new TextField("Organization");
        TextField organizationUnitFLD = new TextField("Facility");
        TextField organizationIdFLD = new TextField("Home Community");
        
        organizationFLD.setValue("Military Health Systems");
        organizationUnitFLD.setValue("Navy Medical Center San Diego - NMCSD");
        organizationIdFLD.setValue("2.16.840.1.113883.4.349");
        
        organizationFLD.setEnabled(false);
        organizationUnitFLD.setEnabled(false);
        organizationIdFLD.setEnabled(false);
        
        organizationFLD.setWidth("400px");
        organizationUnitFLD.setWidth("400px");
        organizationIdFLD.setWidth("400px");
        
        VerticalComponentGroup vGroup4 = new VerticalComponentGroup();
        vGroup4.setCaption("Organization Info");
        //vGroup.addComponent(organizationLBL);
        vGroup4.addComponent(organizationFLD);
        vGroup4.addComponent(organizationUnitFLD);
        vGroup4.addComponent(organizationIdFLD);
        
        //Label locationLBL = new Label("<b>Location</b>", Label.CONTENT_XHTML);
        TextField cityFLD = new TextField("City");
        TextField stateFLD = new TextField("State");
        TextField zipFLD = new TextField("Zip Code");
        TextField countryFLD = new TextField("Country");
        
        //set defaults and disable
        cityFLD.setValue("San Diego");
        stateFLD.setValue("CA");
        zipFLD.setValue("92104");
        countryFLD.setValue("U.S.A");
        
        cityFLD.setEnabled(false);
        stateFLD.setEnabled(false);
        zipFLD.setEnabled(false);
        countryFLD.setEnabled(false);
        
        cityFLD.setWidth("400px");
        stateFLD.setWidth("400px");
        zipFLD.setWidth("400px");
        countryFLD.setWidth("400px");
        VerticalComponentGroup vGroup5 = new VerticalComponentGroup();
        vGroup5.setCaption("Location Info");
        //vGroup.addComponent(locationLBL);
        vGroup5.addComponent(cityFLD);
        vGroup5.addComponent(stateFLD);
        vGroup5.addComponent(zipFLD);
        vGroup5.addComponent(countryFLD);

        
        pouCBX.addListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                String val = (String)pouCBX.getValue();
                session.setPurposeOfUse(val);
            }
        });
        
        confCBX.addListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                String val = (String)confCBX.getValue();
                session.setSecurityLevel(val);
            }
        });
        
        pouCBX.setImmediate(true);
        confCBX.setImmediate(true);
        
        content.addComponent(vGroup);
        content.addComponent(vGroup2);
        content.addComponent(vGroup3);
        content.addComponent(vGroup4);
        content.addComponent(vGroup5);
        setContent(content);
        
    }
    
    private void populateConfCBX() {
        confCBX.addItem("V");
        confCBX.setItemCaption("V", "V (very restricted)");
        confCBX.addItem("R");
        confCBX.setItemCaption("R", "R (restricted)");
        confCBX.addItem("N");
        confCBX.setItemCaption("N", "N (normal)");
    }
    
    private void populatePouCBX() {
        pouCBX.addItem("TREAT");
        pouCBX.setItemCaption("TREAT", "Treatment");
        pouCBX.addItem("ETREAT");
        pouCBX.setItemCaption("ETREAT", "Emergency");
        pouCBX.addItem("HOPERAT");
        pouCBX.setItemCaption("HOPERAT", "Healthcare Operations");
        pouCBX.addItem("COVERAGE");
        pouCBX.setItemCaption("COVERAGE", "Coverage");
        pouCBX.addItem("HPAYMENT");
        pouCBX.setItemCaption("HPAYMENT", "Payment");
        pouCBX.addItem("PUBHLTH");
        pouCBX.setItemCaption("PUBHLTH", "Public Health");
        pouCBX.addItem("PATRQT");
        pouCBX.setItemCaption("PATRQT", "Patient Request");
        pouCBX.addItem("HRESCH");
        pouCBX.setItemCaption("HRESCH", "Research");
        pouCBX.addItem("HMARKT");
        pouCBX.setItemCaption("HMARKT", "Marketing");
    }
}
