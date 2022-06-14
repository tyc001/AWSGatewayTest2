/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entities.Log;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author thamy
 */
@Local
public interface LogSessionBeanLocal {

    public List<Log> retrieveAllLogs();

    public String createLog(Log log);

    public String updateLog(Log log);

    public void deleteLog(Log log);
    
}
