/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import entities.Log;
import java.util.List;

/**
 *
 * @author thamy
 */
public class LogListHelper {
    private List<Log> logList;

    public LogListHelper(List<Log> logList) {
        this.logList = logList;
    }

    public LogListHelper() {
    }

    /**
     * @return the logList
     */
    public List<Log> getLogList() {
        return logList;
    }

    /**
     * @param logList the logList to set
     */
    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }
}
