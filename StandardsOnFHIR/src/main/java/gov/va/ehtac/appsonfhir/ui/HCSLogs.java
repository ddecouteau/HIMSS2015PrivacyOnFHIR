/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.appsonfhir.ui;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.addon.touchkit.ui.HorizontalButtonGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;
import gov.va.ehtac.appsonfhir.HealthElementsTouchKitUI;
import gov.va.ehtac.appsonfhir.session.SessionAttributes;
import gov.va.ehtac.fhir.hcs.client.HCSLogger;
import gov.va.ehtac.fhir.hcs.client.HCSLogger_Service;
import gov.va.ehtac.fhir.hcs.client.Hcslogs;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import org.w3c.dom.Element;
import ca.uhn.fhir.util.PrettyPrintWriterWrapper;
import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Label;
import java.io.ByteArrayOutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Duane DeCouteau
 */
@SuppressWarnings("serial")
public class HCSLogs extends NavigationView {
    SessionAttributes session;
    private Table logTable;
    Popover popover;
    
    public HCSLogs() {
        session = ((HealthElementsTouchKitUI) UI.getCurrent()).getSessionAttributes();           
        setCaption("Standards Log Output");
        final VerticalComponentGroup content = new VerticalComponentGroup();

        createTable();
        
        content.addComponent(logTable);

        Button refresh = new Button();
        refresh.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    logTable.removeAllItems();
                    logTable.setContainerDataSource(populateHCSLogs());
                    logTable.setColumnReorderingAllowed(true);
                    logTable.setColumnCollapsingAllowed(false);
                    logTable.setVisibleColumns(new Object[] {"date", "purposeofuse", "resourceType", "id"});
                    logTable.setColumnHeaders(new String[] {"Date", "POU","Resource Requested", "Resource ID"});          

                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        });

        refresh.setImmediate(true);
        refresh.setIcon(FontAwesome.REFRESH);
        setRightComponent(refresh);
        
        HorizontalButtonGroup hGroup = new HorizontalButtonGroup();
        hGroup.setCaption("Click Button View Std Area");
        hGroup.setWidth("100%");
        
        Button resourceBtn = new Button("FHIR Resource");
        Button slsRulesExecBtn = new Button("SLS Rules");
        Button slsOutputBtn = new Button("SLS Actions");
        Button ppsRulesExecBtn = new Button("PPS Rules");
        Button ppsOutputBtn = new Button("PPS Actions");
        Button consentBtn = new Button("Consent");
        Button labeledResourceBtn = new Button("HCS Results");
        
        resourceBtn.setImmediate(true);
        slsRulesExecBtn.setImmediate(true);
        slsOutputBtn.setImmediate(true);
        ppsRulesExecBtn.setImmediate(true);
        ppsOutputBtn.setImmediate(true);
        consentBtn.setImmediate(true);
        labeledResourceBtn.setImmediate(true);
        
        resourceBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    Object rowId = logTable.getValue();
                    if (rowId != null) {
                        String obj = (String)logTable.getContainerProperty(rowId, "resource").getValue();
                        Popover popover = getPopoverTextArea(obj, "FHIR Resource");
                        popover.showRelativeTo(getNavigationBar());
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        slsRulesExecBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    Object rowId = logTable.getValue();
                    if (rowId != null) {
                        String obj = (String)logTable.getContainerProperty(rowId, "slsrules").getValue();
                        Popover popover = getPopoverTextArea(obj, "SLS Rules");
                        popover.showRelativeTo(getNavigationBar());
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        slsOutputBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    Object rowId = logTable.getValue();
                    if (rowId != null) {
                        String obj = (String)logTable.getContainerProperty(rowId, "slsresults").getValue();
                        Popover popover = getPopoverTextArea(obj, "SLS Actions");
                        popover.showRelativeTo(getNavigationBar());
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
                
        ppsRulesExecBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    Object rowId = logTable.getValue();
                    if (rowId != null) {
                        String obj = (String)logTable.getContainerProperty(rowId, "ppsrules").getValue();
                        Popover popover = getPopoverTextArea(obj, "PPS Rules");
                        popover.showRelativeTo(getNavigationBar());
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
        
        ppsOutputBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    Object rowId = logTable.getValue();
                    if (rowId != null) {
                        String obj = (String)logTable.getContainerProperty(rowId, "ppsresults").getValue();
                        Popover popover = getPopoverTextArea(obj, "PPS Actions");
                        popover.showRelativeTo(getNavigationBar());
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        consentBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    Object rowId = logTable.getValue();
                    if (rowId != null) {
                        String obj = (String)logTable.getContainerProperty(rowId, "consent").getValue();
                        Popover popover = getPopoverTextArea(obj, "Consent");
                        popover.showRelativeTo(getNavigationBar());
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        labeledResourceBtn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    Object rowId = logTable.getValue();
                    if (rowId != null) {
                        String obj = (String)logTable.getContainerProperty(rowId, "labeledresource").getValue();
                        Popover popover = getPopoverTextArea(obj, "HCS Results");
                        popover.showRelativeTo(getNavigationBar());
                    }
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });        

        
        
        
                
        
        hGroup.addComponent(resourceBtn);
        hGroup.addComponent(slsRulesExecBtn);
        hGroup.addComponent(slsOutputBtn);
        hGroup.addComponent(consentBtn);        
        hGroup.addComponent(ppsRulesExecBtn);
        hGroup.addComponent(ppsOutputBtn);
        hGroup.addComponent(labeledResourceBtn);
        content.addComponent(hGroup);


        setContent(new CssLayout(content));
    }
    
    private void createTable() {
        logTable = new Table();
        logTable.setWidth("100%");
        logTable.setHeight("350px");
        logTable.setMultiSelect(false);
        logTable.setSelectable(true);
        logTable.setImmediate(true); // react at once when something is selected
        logTable.setEditable(false);
        logTable.setStyleName(Runo.TABLE_SMALL);
        logTable.setContainerDataSource(populateHCSLogs());

        logTable.setColumnReorderingAllowed(true);
        logTable.setColumnCollapsingAllowed(false);
        logTable.setVisibleColumns(new Object[] {"date","purposeofuse", "resourceType", "id"});
        logTable.setColumnHeaders(new String[] {"Date","POU", "Resource Requested", "Resource ID"});
        
        
        
    }
    
    private IndexedContainer populateHCSLogs() {
        IndexedContainer container = new IndexedContainer();
        List<Hcslogs> rList = new ArrayList();
        try {
            rList = getLogs();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        container = createIndexedContainerLogs(rList);
        return container;
        
    }
    
    private IndexedContainer createIndexedContainerLogs(Collection<Hcslogs> collection) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("date", String.class, null);
        container.addContainerProperty("id", String.class, null);
        container.addContainerProperty("resourceType", String.class, null);
        container.addContainerProperty("resource", String.class, null);
        container.addContainerProperty("slsrules", String.class, null);
        container.addContainerProperty("slsresults", String.class, null);
        container.addContainerProperty("ppsrules", String.class, null);
        container.addContainerProperty("ppsresults", String.class, null);
        container.addContainerProperty("consent", String.class, null);
        container.addContainerProperty("labeledresource", String.class, null);
        container.addContainerProperty("purposeofuse", String.class, null);
        container.addContainerProperty("oLogs", Hcslogs.class, null);

        int i = 0;
        for (Hcslogs p : collection) {
            i++;
            Integer id = new Integer(i);
            Item item = container.addItem(id);
            item.getItemProperty("date").setValue(convertXMLGregorianCalendar(p.getMsgdate()));
            item.getItemProperty("id").setValue(p.getMsgid());
            item.getItemProperty("resourceType").setValue(getInfo(p.getFhirbundle()));
            item.getItemProperty("resource").setValue(p.getFhirbundle());
            item.getItemProperty("slsrules").setValue(p.getSlsexecrules());
            item.getItemProperty("slsresults").setValue(p.getSlsoutput());
            item.getItemProperty("ppsrules").setValue(p.getPpsexecrules());
            item.getItemProperty("ppsresults").setValue(p.getPpsoutput());
            item.getItemProperty("consent").setValue(p.getObligations());
            item.getItemProperty("labeledresource").setValue(p.getLabeledresource());
            item.getItemProperty("purposeofuse").setValue(p.getPurposeofuse());
            item.getItemProperty("oLogs").setValue(p);
        }
        return container;
    }
    
    
    private List<Hcslogs> getLogs() {
        List<Hcslogs> res = new ArrayList();
        try {
            HCSLogger_Service service = new HCSLogger_Service();
            HCSLogger port = service.getHCSLoggerPort();
            ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:7080/HCSServices/HCSLogger?wsdl");
            res = port.getAllHCSLogEvents();

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
       return res; 
    }
    
    private String convertXMLGregorianCalendar(XMLGregorianCalendar xc) {
        String res = "";
        try {
            Date dt = xc.toGregorianCalendar().getTime();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            res = sd.format(dt);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    private String getInfo(String val) {
        String res = "";
        if (val.contains("Condition")) {
            res = "Condition Bundle";
        }
        else if (val.contains("Observation")) {
            res = "Observation Bundle";
        }
        else if (val.contains("Medication")) {
            res = "Medication Bundle";
        }
        else if (val.contains("Allergy")) {
            res = "Allergy Intolerence Bundle";
        }
        else if (val.contains("Immunization")) {
            res = "Immunization Bundle";
        }
        else if (val.contains("Encounter")) {
            res = "Encounter Bundle";
        }
        else {
            res = "Unknown Resource Bundle";
        }
        return res;
    }
    private Button getButtonWithIcon(String val) {
        Button rButton = new Button();
        rButton.setStyleName(Runo.LABEL_SMALL);
        Label lbl = new Label();
        rButton.setData(val);
        if (val.contains("Condition")) {
            rButton.setCaption("Condition Bundle");            
        }
        else if (val.contains("Observation")) {
            rButton.setCaption("Observation Bundle");
        }
        else if (val.contains("MedicationPrescription")) {
            rButton.setCaption("Medication Prescription Bundle");
        }
        else if (val.contains("Allergy")) {
            rButton.setCaption("Allergy Intolerence Bundle");
        }
        else if (val.contains("Immunization")) {
            rButton.setCaption("Immunization Bundle");
        }
        else if (val.contains("Encounter")) {
            rButton.setCaption("Encounter Bundle");
        }
        else {
            rButton.setCaption("Unknown Resource Bundle");
        }
        rButton.setIcon(new ThemeResource("../runo/icons/16/document-web.png"));
        rButton.setData(val);
        
//        rButton.addListener(new MouseEvents.ClickListener() {
//
//            @Override
//            public void click(MouseEvents.ClickEvent event) {
//                String data = (String)event.getButton().getData();
//                String title = (String)event.getButton().getCaption();
//                Popover p = getPopoverTextArea(data, title);
//                p.showRelativeTo(getNavigationBar());
//            }
//        });
        
        return rButton;
    }
    
    private Popover getPopoverTextArea(String val, String title) {
        popover = new Popover();
        popover.setModal(true);
        popover.setClosable(true);
        popover.setWidth("700px");
        popover.setHeight("550px");

        CssLayout popLayout = new CssLayout();
        popLayout.setSizeFull();
        
        NavigationView navView = new NavigationView(popLayout);
        navView.setCaption(title);

        CssLayout layout2 = new CssLayout();
        TextArea textArea = new TextArea();
        textArea.setWidth("100%");
        textArea.setHeight("490px");
        textArea.setValue(val);
//        textArea.setReadOnly(true);
//        textArea.setStyleName(Runo.LABEL_SMALL);
        layout2.addComponent(textArea);
        
        popLayout.addComponent(layout2);
        
        Button close = new Button(null, new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                popover.removeFromParent();
            }
        });
        close.setIcon(new ThemeResource("../runo/icons/64/cancel.png"));
        navView.setRightComponent(close);
        
        popover.setContent(navView);
        
        return popover;
    }
    
    private String prettyUpXML(String sourceXML) {
        String res = sourceXML;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter xStream = factory.createXMLStreamWriter(bos);
            PrettyPrintWriterWrapper pretty = new PrettyPrintWriterWrapper(xStream);
            pretty.writeCharacters(sourceXML);
            res = bos.toString();
            xStream.flush();
            xStream.close();
            bos.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
}
