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

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;

/**
 *
 * @author Duane DeCouteau
 */
public class LogoutView extends NavigationView {
    private Label logoutAction;
    
    @Override
    public void attach() {
        super.attach();
        if (logoutAction == null) {
            buildView();
        }
    }
    
    private void buildView() {
        CssLayout content = new CssLayout();
        content.setWidth("100%");
        setCaption("Logout / End Session");
        
        VerticalComponentGroup vGroup = new VerticalComponentGroup();
        logoutAction = new Label(
                "<div style='color:#333;'><p>You have requested to end your session, if true continue by clicking the \"Ok\" button.</p></div>", Label.CONTENT_XHTML);
        Button okBTN = new Button("Ok");
        okBTN.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                ((HealthElementsTouchKitUI) UI.getCurrent()).getSession().close();
            }
        });
        vGroup.addComponent(logoutAction);
        vGroup.addComponent(okBTN);
        content.addComponent(vGroup);
        setContent(content);
        
    }
    
}
