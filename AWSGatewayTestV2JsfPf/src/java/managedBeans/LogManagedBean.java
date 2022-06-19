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
    private String filterString;
    private List<Log> filteredLogs;
    private List<Log> filteredLogs2;

    private List<String> eventTypeFilters;
    private static int methodCallCount;
    public LogManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Log Managed Bean Post Construct");
        this.allLogs = logSessionBeanLocal.retrieveAllLogs();
        this.filteredLogs = new ArrayList<>(allLogs);
        this.filteredLogs2 = new ArrayList<>();
        Set<String> uniqueApis = new HashSet<>();
        for (Log l : this.allLogs) {
            uniqueApis.add(l.getApi());
        }
        apis = new ArrayList<String>(uniqueApis);
        this.eventTypeFilters = new ArrayList<>();
        this.eventTypeFilters.add("Access");
        this.eventTypeFilters.add("Execution");
        this.eventTypeFilters.add("Lambda");
        methodCallCount = 0;
    }
    
    public void applyApiFilter() {
        doApplyApiFilter(this.apiFilter);
    }
    
    public void doApplyApiFilter(String apiFilter) {
        System.out.println("APPLY API FILTER START");
        List<Log> newFilteredLogs = new ArrayList<>();
        for (Log l : allLogs) {
            if (l.getApi().equals(apiFilter) && (eventTypeFilters.contains(l.getEventType()))) {
                newFilteredLogs.add(l);
            }
        }
        filteredLogs = newFilteredLogs;
//        filteredLogs2.add(filteredLogs.get(0));
        System.out.println("APPLY API FILTER END");
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
        System.out.println("APPLY EVENT TYPE FILTERS START");
        List<Log> newFilteredLogs = new ArrayList<>();
        for (Log l : allLogs) {
            if (eventTypeFilters.contains(l.getEventType()) && (apiFilter == null || l.getApi().equals(apiFilter))) {
                newFilteredLogs.add(l);
            }
        }
        filteredLogs = newFilteredLogs;
        System.out.println("APPLY EVENT TYPE FILTERS END");
    }
    
    public boolean filterExtendedRequestId(Object value, Object filter, Locale locale) {
        System.out.println("FILTER EXTENDED REQUEST ID START");
        String filterText = filter == null? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        String currentExtendedRequestId = value.toString();
        List<Log> filteredLogList = new ArrayList<>();
        System.out.println("FILTER EXTENDED REQUEST ID END" + ++methodCallCount);
        
        if (currentExtendedRequestId.contains(filterText)) {
            return true;
        }
        return false;
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

    /**
     * @return the filterString
     */
    public String getFilterString() {
        return filterString;
    }

    /**
     * @param filterString the filterString to set
     */
    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }

    /**
     * @return the filteredLogs2
     */
    public List<Log> getFilteredLogs2() {
        return filteredLogs2;
    }

    /**
     * @param filteredLogs2 the filteredLogs2 to set
     */
    public void setFilteredLogs2(List<Log> filteredLogs2) {
        this.filteredLogs2 = filteredLogs2;
    }
}
