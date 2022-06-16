/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.LogSessionBeanLocal;

/**
 *
 * @author thamy
 */
@Named
@ViewScoped
public class LogManagedBean implements Serializable{
    @EJB
    private LogSessionBeanLocal logSessionBeanLocal;
    
    private List<Log> allLogs;
    
    private List<String> apis;
    
    private String selectedApi;
    private List<Log> selectedLogs;
    private String selectedEventType;
    
    private String selectedMessageToView;
    
    private String apiFilter;
    private List<Log> filteredLogs;

    private List<String> eventTypeFilters;
    public LogManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Log Managed Bean Post Construct");
        this.allLogs = logSessionBeanLocal.retrieveAllLogs();
        this.filteredLogs = new ArrayList<>(allLogs);
        Set<String> uniqueApis = new HashSet<>();
        for (Log l : this.allLogs) {
            uniqueApis.add(l.getApi());
        }
        apis = new ArrayList<String>(uniqueApis);
        this.eventTypeFilters = new ArrayList<>();
        this.eventTypeFilters.add("Access");
        this.eventTypeFilters.add("Execution");
        this.eventTypeFilters.add("Lambda");
    }
    
    public void applyApiFilter() {
        doApplyApiFilter(this.apiFilter);
    }
    
    public void doApplyApiFilter(String apiFilter) {
        List<Log> newFilteredLogs = new ArrayList<>();
        for (Log l : allLogs) {
            if (l.getApi().equals(apiFilter) && (eventTypeFilters.contains(l.getEventType()))) {
                newFilteredLogs.add(l);
            }
        }
        filteredLogs = newFilteredLogs;
    }
    
    public void removeAllApiFilters() {
        filteredLogs = new ArrayList<>(allLogs);
        apiFilter = null;
        eventTypeFilters = new ArrayList<>();
        eventTypeFilters.add("Access");
        eventTypeFilters.add("Execution");
        eventTypeFilters.add("Lambda");
    }
    
    public void applyEventTypeFilters() {
        List<Log> newFilteredLogs = new ArrayList<>();
        for (Log l : allLogs) {
            if (eventTypeFilters.contains(l.getEventType()) && (apiFilter == null || l.getApi().equals(apiFilter))) {
                newFilteredLogs.add(l);
            }
        }
        filteredLogs = newFilteredLogs;
    }

    /**
     * @return the apis
     */
    public List<String> getApis() {
        return apis;
    }

    /**
     * @param apis the apis to set
     */
    public void setApis(List<String> apis) {
        this.apis = apis;
    }

    /**
     * @return the allLogs
     */
    public List<Log> getAllLogs() {
        return allLogs;
    }

    /**
     * @param allLogs the allLogs to set
     */
    public void setAllLogs(List<Log> allLogs) {
        this.allLogs = allLogs;
    }

    /**
     * @return the selectedApi
     */
    public String getSelectedApi() {
        return selectedApi;
    }

    /**
     * @param selectedApi the selectedApi to set
     */
    public void setSelectedApi(String selectedApi) {
        this.selectedApi = selectedApi;
    }

    /**
     * @return the selectedLogs
     */
    public List<Log> getSelectedLogs() {
        return selectedLogs;
    }

    /**
     * @param selectedLogs the selectedLogs to set
     */
    public void setSelectedLogs(List<Log> selectedLogs) {
        this.selectedLogs = selectedLogs;
    }

    /**
     * @return the selectedEventType
     */
    public String getSelectedEventType() {
        return selectedEventType;
    }

    /**
     * @param selectedEventType the selectedEventType to set
     */
    public void setSelectedEventType(String selectedEventType) {
        this.selectedEventType = selectedEventType;
    }

    /**
     * @return the eventTypeFilters
     */
    public List<String> getEventTypeFilters() {
        return eventTypeFilters;
    }

    /**
     * @param eventTypeFilters the eventTypeFilters to set
     */
    public void setEventTypeFilters(List<String> eventTypeFilters) {
        this.eventTypeFilters = eventTypeFilters;
    }

    /**
     * @return the selectedMessageToView
     */
    public String getSelectedMessageToView() {
        return selectedMessageToView;
    }

    /**
     * @param selectedMessageToView the selectedMessageToView to set
     */
    public void setSelectedMessageToView(String selectedMessageToView) {
        this.selectedMessageToView = selectedMessageToView;
    }

    /**
     * @return the apiFilter
     */
    public String getApiFilter() {
        return apiFilter;
    }

    /**
     * @param apiFilter the apiFilter to set
     */
    public void setApiFilter(String apiFilter) {
        this.apiFilter = apiFilter;
    }

    /**
     * @return the filteredLogs
     */
    public List<Log> getFilteredLogs() {
        return filteredLogs;
    }

    /**
     * @param filteredLogs the filteredLogs to set
     */
    public void setFilteredLogs(List<Log> filteredLogs) {
        this.filteredLogs = filteredLogs;
    }
}
