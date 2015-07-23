/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;

/**
 *
 * @author Duane DeCouteau
 */
public class DestinationControl extends NavigationView {
    SessionAttributes session;
    
    public DestinationControl() {
        setCaption("Push Patient Resources to other Entities");
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();
        
        final VerticalComponentGroup content = new VerticalComponentGroup();  
        
        final ComboBox baseURLCBX = new ComboBox("Destination");
        baseURLCBX.addItem("42CFRPart2");    
        baseURLCBX.addItem("Military Health Systems");        
        baseURLCBX.addItem("Tricare");
        baseURLCBX.addItem("Dept. of Veterans Affairs");
        baseURLCBX.addItem("HHS ONC - Health Infomation Exchange(HIE)");
        baseURLCBX.addItem("FHIR/VA - Health Information Exchange (HIE)");
        
        baseURLCBX.setTextInputAllowed(false);
        
        baseURLCBX.addValueChangeListener(new ComboBox.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                
                String caption = (String)baseURLCBX.getValue();
                String newURL = getEndpoint(caption);                
                session.setTransmitURL(newURL);
                setTabDisplayName(caption);
                System.out.println("BASEURL: "+newURL);
            }
        });
        
        baseURLCBX.setImmediate(true);
        content.addComponent(baseURLCBX);
        
        final Button submitButton = new Button("Transmit Immunizations");
        submitButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (session.getTransmitURL().equals(session.getBaseURL())) {
                    Notification.show("Source and Destination Cannot Be Same", Notification.Type.ERROR_MESSAGE);
                }
                else {
                    session.getPofPatient().copyToHIE(session.getContext(), session.getTransmitURL());
                }
            }
        });
        submitButton.setImmediate(true);
        
        
        setContent(new CssLayout(content, submitButton));        
        
    }
    private void setTabDisplayName(String caption) {
        ((HealthElementsTouchKitUI) UI.getCurrent()).getTabBarView().getSelelectedTab().setCaption(caption);
    }
    
    private String getEndpoint(String orgName) {
        String res = "";
        if (orgName.equals("42CFRPart2")) res = "http://42CFRPart2.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/";
        if (orgName.equals("Dept. of Veterans Affairs")) res = "http://va.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/";
        if (orgName.equals("Military Health Systems")) res = "http://mhs.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/";
        if (orgName.equals("HHS ONC - Health Infomation Exchange(HIE)")) res = "http://localhost:8080";
        if (orgName.equals("Tricare")) res = "http://tricare.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/";      
        if (orgName.equals("FHIR/VA - Health Information Exchange (HIE)")) res = "http://hie.edmondsci.com:8080/hapi-fhir-jpaserver/baseDstu2/";        
        return res;
    }
}
