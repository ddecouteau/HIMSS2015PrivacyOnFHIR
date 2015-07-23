package gov.va.ehtac.appsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickEvent;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickListener;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;

@SuppressWarnings("serial")
public class MenuView extends NavigationView {
    
    SessionAttributes session;

    public MenuView() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();
        setCaption("FHIR Resource Provider View");        
        final VerticalComponentGroup content = new VerticalComponentGroup();
        NavigationButton btnEncounters = new NavigationButton("Encounters");
        btnEncounters.addClickListener(new NavigationButtonClickListener() {
           @Override
           public void buttonClick(NavigationButtonClickEvent event) {
               getNavigationManager().navigateTo(new Encounters());
           }
        });
        content.addComponent(btnEncounters);
        NavigationButton btnAdverseReactions = new NavigationButton("Allergy Intolerance");
        btnAdverseReactions.addClickListener(new NavigationButtonClickListener() {
           @Override
           public void buttonClick(NavigationButtonClickEvent event) {
               getNavigationManager().navigateTo(new AdverseReactions());
           }
        });        
        content.addComponent(btnAdverseReactions);
        NavigationButton btnConditions = new NavigationButton("Conditions");
        btnConditions.addClickListener(new NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButtonClickEvent event) {
                getNavigationManager().navigateTo(new Conditions());
            }
        });
        content.addComponent(btnConditions);
        NavigationButton btnMedications = new NavigationButton("Medications");
        btnMedications.addClickListener(new NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButtonClickEvent event) {
                getNavigationManager().navigateTo(new Medications());
            }
        });
        content.addComponent(btnMedications);
        NavigationButton btnLabs = new NavigationButton("Observations");
        btnLabs.addClickListener(new NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButtonClickEvent event) {
                getNavigationManager().navigateTo(new Vitals());
            }
        });
        content.addComponent(btnLabs);
        NavigationButton btnImmunizations = new NavigationButton("Immunizations");
        btnImmunizations.addClickListener(new NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButtonClickEvent event) {
                getNavigationManager().navigateTo(new Immunizations());
            }
        });
        content.addComponent(btnImmunizations);
        
        //setRightComponent(new Label(session.getPatientNameAgeGenderDisplay()));
        
        setContent(content);
    };
}
