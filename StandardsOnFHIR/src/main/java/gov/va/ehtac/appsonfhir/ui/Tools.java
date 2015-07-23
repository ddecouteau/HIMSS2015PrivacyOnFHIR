/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;

/**
 *
 * @author Duane DeCouteau
 */
public class Tools extends NavigationView {
    
    public Tools() {
        setCaption("Patient Tools");
        final HorizontalButtonGroup hGroup = new HorizontalButtonGroup();
        Button weightBTN = new Button("Weight Scale");
        weightBTN.setIcon(FontAwesome.DELICIOUS);
        weightBTN.setWidth("310px");
        Button heartRateBTN = new Button("Heart Rate");
        heartRateBTN.setIcon(FontAwesome.HEART);
        heartRateBTN.setWidth("310px");
        Button bloodPressureBTN = new Button("Blood Pressure");
        bloodPressureBTN.setIcon(FontAwesome.HEART_O);
        bloodPressureBTN.setWidth("310px");
        Button glucoseBTN = new Button("Diabetes Management");
        glucoseBTN.setIcon(FontAwesome.FILE_CODE_O);
        glucoseBTN.setWidth("310px");
        hGroup.addComponent(weightBTN);
        hGroup.addComponent(heartRateBTN);
        hGroup.addComponent(bloodPressureBTN);
        hGroup.addComponent(glucoseBTN);
        
        setContent(new CssLayout(hGroup));        
    }
}
