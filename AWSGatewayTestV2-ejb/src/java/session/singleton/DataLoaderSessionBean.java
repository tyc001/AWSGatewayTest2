/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.singleton;

import entities.Log;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import session.stateless.LogSessionBeanLocal;

/**
 *
 * @author thamy
 */
@Singleton
@LocalBean
@Startup
public class DataLoaderSessionBean {

    @EJB
    private LogSessionBeanLocal logSessionBeanLocal;
    public DataLoaderSessionBean() {
    }
    @PostConstruct
    public void postConstruct() {
    /*    logSessionBeanLocal.createLog(new Log("LogOne", "DATA_MESSAGE", "620755372077", "AccessLogGroup", "Stream1", System.currentTimeMillis(), "AccessMessage"));
        logSessionBeanLocal.createLog(new Log("LogTwo", "DATA_MESSAGE", "620755372077", "ExecutionLogGroup", "Stream1", System.currentTimeMillis(), "ExecutionMessage"));
        logSessionBeanLocal.createLog(new Log("LogThree", "DATA_MESSAGE", "620755372077", "LambdaLogGroup", "Stream1", System.currentTimeMillis(), "LambdaMessage")); */

    }
}
