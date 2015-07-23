/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir.hcsservices.ws;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hl7.fhir.hcsservices.jpa.Clinicallabels;
import org.hl7.fhir.hcsservices.jpa.Ppsactions;


/**
 *
 * @author Socraticgrid Staff
 */
@WebService(name = "HCSAdaptiveRulesInterface", targetNamespace = "ws.hcs.hl7.socraticgrid.org")
public class HCSAdaptiveRulesInterface {
       private Logger logger = LoggerFactory.getLogger(HCSAdaptiveRulesInterface.class);
       
    //for jpa stuff
    EntityManagerFactory emf = null;

    private EntityManagerFactory getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("org.hl7.fhir_HCSServices_war_2.0PU");
        }
        return emf;
    }
//    protected void persist(Object object) {
//        try {
//            EntityManager em = getEntityManager().createEntityManager();
//            EntityTransaction t = em.getTransaction();
//            t.begin();
//            em.persist(object);
//            t.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
//            //throw new RuntimeException(e);
//        }
//    }
//
//    protected void update(Object object) {
//        try {
//            EntityManager em = getEntityManager().createEntityManager();
//            EntityTransaction t = em.getTransaction();
//            t.begin();
//            em.merge(object);
//            t.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
//            //throw new RuntimeException(e);
//        }
//    }
//
//    protected void delete(Object object) {
//        try {
//            EntityManager em = getEntityManager().createEntityManager();
//            EntityTransaction t = em.getTransaction();
//            t.begin();
//            em.remove(object);
//            t.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
//            //throw new RuntimeException(e);
//        }
//    }
//
//    protected void flush() {
//        try {
//            EntityManager em = getEntityManager().createEntityManager();
//            em.setFlushMode(FlushModeType.COMMIT);
//            em.flush();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
//        }
//    }
       
       
       /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllClinicalRules")
    public List<Clinicallabels> getAllClinicalRules() {

        List<Clinicallabels> res = new ArrayList();
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            Query q = em.createNamedQuery("Clinicallabels.findAll");
            res = q.getResultList();
            t.commit();
            em.close();
        }
        catch (Exception ex) {
            System.err.println("HCSAdaptiveRulesInterface:getAllClinicalLabels "+ex.getMessage());
            ex.printStackTrace();
        }
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllPPSActionsByPurposeOfUse")
    public List<Ppsactions> getAllPPSActionsByPurposeOfUse(@WebParam(name = "purposeOfUse") String purposeOfUse) {
        List<Ppsactions> res = new ArrayList();
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            Query q = em.createNamedQuery("Ppsactions.findByPurposeOfUse");
            q.setParameter("purposeOfUse", purposeOfUse);
            res = q.getResultList();
            t.commit();
            em.close();
        }
        catch (Exception ex) {
            System.err.println("HCSAdaptiveRulesInterface:getAllPPSActionRules "+ex.getMessage());
            ex.printStackTrace();
        }
        
        return res;
    }
    


}
