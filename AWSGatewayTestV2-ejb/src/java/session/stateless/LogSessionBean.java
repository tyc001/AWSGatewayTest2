/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entities.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author thamy
 */
@Stateless
public class LogSessionBean implements LogSessionBeanLocal {

    @PersistenceContext(unitName = "AWSGatewayTestV2-ejbPU")
    private EntityManager em;

    @Override
    public List<Log> retrieveAllLogs() {
        Query query = em.createQuery("SELECT l FROM Log l");
        
        return query.getResultList();
    }
    
    @Override
    public String createLog(Log log) {
        em.persist(log);
        
        return log.getLogId();
    }
    
    @Override
    public String updateLog(Log log) {
        if (log.getLogId() == null) {
            return null;
        }
        em.merge(log);
        
        return log.getLogId();
    }
    
    @Override
    public void deleteLog(Log log) {
        em.remove(log);
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
