/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hl7.fhir.hcsservices.ws;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hl7.fhir.hcsservices.jpa.Hcslogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Duane DeCouteau
 */
@WebService(serviceName = "HCSLogger")
public class HCSLogger {

    private Logger logger = LoggerFactory.getLogger(HCSLogger.class);
       
    //for jpa stuff
    EntityManagerFactory emf = null;

    private EntityManagerFactory getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("org.hl7.fhir_HCSServices_war_2.0PU");
        }
        return emf;
    }
    protected void persist(Object object) {
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(object);
            t.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
            //throw new RuntimeException(e);
        }
    }

    protected void update(Object object) {
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.merge(object);
            t.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());            
            e.printStackTrace();

        }
    }

    protected void delete(Object object) {
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.remove(object);
            t.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());            
            e.printStackTrace();

        }
    }

    protected void flush() {
        try {
            EntityManager em = getEntityManager().createEntityManager();
            em.setFlushMode(FlushModeType.COMMIT);
            em.flush();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            //java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, "exception caught", e);
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllHCSLogEvents")
    public List<Hcslogs> getAllHCSLogEvents() {
        List<Hcslogs> res = new ArrayList();
        try {
            EntityManager em = getEntityManager().createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            Query q = em.createNamedQuery("Hcslogs.findAll");
            res = q.getResultList();
            t.commit();
        }
        catch (Exception ex) {
           logger.error(ex.getMessage());
        }
        return res;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "saveAuthorizationEvent")
    public Boolean saveHCSEvent(@WebParam(name = "hcsobj")
    Hcslogs hcsobj) {
        Boolean res = new Boolean(true);
        try {
            persist(hcsobj);
        }
        catch (Exception ex) {
            res = new Boolean(false);
            ex.printStackTrace();
        }
        return res;
    }
    
    
    
}
