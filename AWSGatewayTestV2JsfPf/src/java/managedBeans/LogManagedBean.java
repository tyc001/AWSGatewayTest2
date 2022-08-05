/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Log;
import helpers.LogListHelper;
import helpers.RequestParamsHelper;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.LogSessionBeanLocal;

/**
 *
 * @author thamy
 */
@Named
@ViewScoped
public class LogManagedBean implements Serializable {

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
    private List<Log> filteredLogs3;

    private List<String> eventTypeFilters;

    private String user;
    private String token;
    private RequestParamsHelper requestParams;

    public LogManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Log Managed Bean Post Construct");
        this.allLogs = logSessionBeanLocal.retrieveAllLogs();
//        this.filteredLogs = new ArrayList<>();
/*        for (Log l : allLogs) {
            this.filteredLogs.add(l);
        } */
//        this.filteredLogs = new ArrayList<>(allLogs);
//        this.filteredLogs2 = new ArrayList<>();
        Set<String> uniqueApis = new HashSet<>();
        for (Log l : this.allLogs) {
            uniqueApis.add(l.getApi());
        }
        apis = new ArrayList<String>(uniqueApis);
        this.eventTypeFilters = new ArrayList<>();
        /*        this.eventTypeFilters.add("Access");
        this.eventTypeFilters.add("Execution");
        this.eventTypeFilters.add("Lambda"); */
    }

    /*    public void applyApiFilter() {
        doApplyApiFilter(this.apiFilter);
    }

    public void doApplyApiFilter(String apiFilter) {
        if (!usedFilter) {
            System.out.println("APPLY API FILTER REGULAR START");
            List<Log> newFilteredLogs = new ArrayList<>();
            for (Log l : allLogs) {
                if (l.getApi().equals(apiFilter) && (eventTypeFilters.contains(l.getEventType()))) {
                    newFilteredLogs.add(l);
                }
            }
            filteredLogs = newFilteredLogs;
//        filteredLogs2.add(filteredLogs.get(0));
            System.out.println("APPLY API FILTER REGULAR END");
        } else {
            System.out.println("APPLY API FILTER FILTERED START");
            List<Log> newFilteredLogs = new ArrayList<>();
            for (Log l : allLogs) {
                if (l.getApi().equals(apiFilter) && (eventTypeFilters.contains(l.getEventType())) && (filterString == null || l.getExtendedRequestId().contains(filterString))) {
                    newFilteredLogs.add(l);
                }
            }
            filteredLogs = newFilteredLogs;
//        filteredLogs2.add(filteredLogs.get(0));
            System.out.println("APPLY API FILTER FILTERED END");
        }
    } */
    public void refresh() throws IOException {
        System.out.println("REFRESHING PAGE");
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        ////      ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        //      ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        /*        if (!usedFilter) {
            filteredLogs = new ArrayList<>(allLogs);
            apiFilter = null;
            eventTypeFilters = new ArrayList<>();
            eventTypeFilters.add("Access");
            eventTypeFilters.add("Execution");
            eventTypeFilters.add("Lambda");
        } else {
            filteredLogs2 = new ArrayList<>(allLogs);
            apiFilter = null;
            eventTypeFilters = new ArrayList<>();
            eventTypeFilters.add("Access");
            eventTypeFilters.add("Execution");
            eventTypeFilters.add("Lambda");
        } */
    }

    /*    public void applyEventTypeFilters() {
        if (!usedFilter) {
            System.out.println("APPLY EVENT TYPE FILTERS START");
            List<Log> newFilteredLogs = new ArrayList<>();
            for (Log l : allLogs) {
                if (eventTypeFilters.contains(l.getEventType()) && (apiFilter == null || l.getApi().equals(apiFilter))) {
                    newFilteredLogs.add(l);
                }
            }
            filteredLogs = newFilteredLogs;
            System.out.println("IS FILTEREDLOGS ONE EMPTY?" + (filteredLogs.isEmpty() ? "YES" : "NO: LENGTH:" + filteredLogs.size()));
            System.out.println("IS FILTEREDLOGS TWO EMPTY?" + (filteredLogs2.isEmpty() ? "YES" : "NO: LENGTH:" + filteredLogs2.size()));
            System.out.println("APPLY EVENT TYPE FILTERS END");
        } else {
            System.out.println("APPLY FILTERED EVENT TYPE FILTERS START");
            List<Log> newFilteredLogs = new ArrayList<>();
            for (Log l : allLogs) {
                if (eventTypeFilters.contains(l.getEventType()) && (apiFilter == null || l.getApi().equals(apiFilter)) && (filterString == null || l.getExtendedRequestId().contains(filterString))) {
                    newFilteredLogs.add(l);
                }
            }
            filteredLogs = newFilteredLogs;
            System.out.println("IS FILTEREDLOGS ONE EMPTY?" + (filteredLogs.isEmpty() ? "YES" : "NO: LENGTH:" + filteredLogs.size()));
            System.out.println("IS FILTEREDLOGS TWO EMPTY?" + (filteredLogs2.isEmpty() ? "YES" : "NO: LENGTH:" + filteredLogs2.size()));
            System.out.println("APPLY FILTERED EVENT TYPE FILTERS END");
        }
    } */
    public boolean filterByEventType(Object value, Object filter, Locale locale) {
        String eventType = (String) value;
        if (eventTypeFilters.isEmpty() || eventTypeFilters.contains(eventType)) {
            System.out.println("FILTER BY EVENT TYPE: TRUE");
            return true;
        }
        System.out.println("FILTER BY EVENT TYPE: FALSE");
        return false;
    }

    /*    public boolean filterExtendedRequestId(Object value, Object filter, Locale locale) {
        System.out.println("FILTER EXTENDED REQUEST ID START");
        if (!usedFilter) {
            usedFilter = true;
        }
        String filterText = filter == null ? null : filter.toString().trim();
        this.filterString = filterText;
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        Log trueValue = (Log) value;
        String currentExtendedRequestId = trueValue.getExtendedRequestId();
//        List<Log> filteredLogList = new ArrayList<>();
        System.out.println("FILTER EXTENDED REQUEST ID END" + ++methodCallCount);

        if (currentExtendedRequestId.contains(filterText)) {
            return true;
        }
        return false;
    } */
    public void viewAPILogs(ActionEvent event) throws IOException {
        String apiToView = (String) event.getComponent().getAttributes().get("api");
        LogListHelper logListHelper = new LogListHelper(allLogs);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("apiToView", apiToView);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("logListHelper", logListHelper);
        if (!(this.user == null)) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", this.user);
        } else if (!(this.token == null)) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("token", this.token);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewSingleAPILogs.xhtml");

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

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
        if (!(allLogs == null) || !allLogs.isEmpty()) {
            List<Log> newLogList = new ArrayList<>();
            List<Log> nonLambdaLogs = new ArrayList<>();
            Set<String> extendedRequestIds = new HashSet<>();
            Set<String> apis = new HashSet<>();
            for (Log l : allLogs) {
                if (l.getEventType().equals("Lambda")) {
                    String message = l.getMessage();
                    int accountIdIndex = message.indexOf("accountId: ", message.indexOf("identity"));
                    System.out.println(message.substring(accountIdIndex + "accountId: ".length() + 1, message.indexOf(",", accountIdIndex) - 1));
                    if (message.substring(accountIdIndex + "accountId: ".length() + 1, message.indexOf(",", accountIdIndex) - 1).equals(this.user)) {
                        extendedRequestIds.add(l.getExtendedRequestId());
                        newLogList.add(l);
                        apis.add(l.getApi());
                    }
                } else {
                    nonLambdaLogs.add(l);
                }
            }
            for (Log l : nonLambdaLogs) {
                if (extendedRequestIds.contains(l.getExtendedRequestId())) {
                    newLogList.add(l);
                    apis.add(l.getApi());
                }
            }
            allLogs = new ArrayList<>(newLogList);
            this.apis = new ArrayList<>(apis);
        }
    }

    /**
     * @return the filteredLogs3
     */
    public List<Log> getFilteredLogs3() {
        return filteredLogs3;
    }

    /**
     * @param filteredLogs3 the filteredLogs3 to set
     */
    public void setFilteredLogs3(List<Log> filteredLogs3) {
        this.filteredLogs3 = filteredLogs3;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
        if (!(allLogs == null) || !allLogs.isEmpty()) {
            List<Log> newLogList = new ArrayList<>();
            List<Log> nonLambdaLogs = new ArrayList<>();
            Set<String> extendedRequestIds = new HashSet<>();
            Set<String> apis = new HashSet<>();
            for (Log l : allLogs) {
                if (l.getEventType().equals("Lambda")) {
                    String message = l.getMessage();
                    int authorizationTokenIndex = message.indexOf("authorizationToken: ");
                    System.out.println(message.substring(authorizationTokenIndex + "authorizationToken: ".length() + 1, message.indexOf(",", authorizationTokenIndex) - 1));
                    if (message.substring(authorizationTokenIndex + "authorizationToken: ".length() + 1, message.indexOf(",", authorizationTokenIndex) - 1).equals(this.token)) {
                        extendedRequestIds.add(l.getExtendedRequestId());
                        newLogList.add(l);
                        apis.add(l.getApi());
                    }
                } else {
                    nonLambdaLogs.add(l);
                }
            }
            for (Log l : nonLambdaLogs) {
                if (extendedRequestIds.contains(l.getExtendedRequestId())) {
                    newLogList.add(l);
                    apis.add(l.getApi());
                }
            }
            allLogs = new ArrayList<>(newLogList);
            this.apis = new ArrayList<>(apis);
        }
    }

    /**
     * @return the requestParams
     */
    public RequestParamsHelper getRequestParams() {
        return requestParams;
    }

    /**
     * @param requestParams the requestParams to set
     */
    public void setRequestParams(RequestParamsHelper requestParams) {
        this.requestParams = requestParams;
    }
}
