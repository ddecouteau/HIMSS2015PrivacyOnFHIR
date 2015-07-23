/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.hl7.hcs.providers;

import gov.va.ehtac.hl7.hcs.policy.reference.ProblemListSensitivityRules;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Socraticgrid Staff
 */
public class ProblemListProvider {
    

    public ProblemListSensitivityRules createProblemListSensitivityRulesObjectFromXML(String ruleXML) {
        ProblemListSensitivityRules obj = null;
        try {
            JAXBContext context = JAXBContext.newInstance(ProblemListSensitivityRules.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(ruleXML);

            Object o = unmarshaller.unmarshal(sr);
            obj = (ProblemListSensitivityRules)o;

        }
        catch (Exception e) {
            //log.warn("",e);
            e.printStackTrace();
        }        
        return obj;
    }
    
    public String createProblemListSensitivityRulesXMLFromObject(ProblemListSensitivityRules obj) {
        String res = "";
        
        try {
            JAXBContext context = JAXBContext.newInstance(ProblemListSensitivityRules.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(obj, sw);

            res = sw.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return res;
    }
    
}
