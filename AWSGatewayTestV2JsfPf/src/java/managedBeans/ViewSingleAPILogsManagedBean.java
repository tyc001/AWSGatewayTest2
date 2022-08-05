/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Log;
import helpers.LogListHelper;
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
public class ViewSingleAPILogsManagedBean implements Serializable {

    @EJB
    private LogSessionBeanLocal logSessionBeanLocal;

    private String apiToView;
    private List<Log> allLogs;
    private List<Log> logsToView;
    private List<Log> filteredLogs;
    private List<String> apis;

    private List<String> eventTypeFilters;

    private String currentUser;
    private String currentToken;

    private String fullMessage;

    public ViewSingleAPILogsManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        this.setLogsToView(new ArrayList<>());
        this.eventTypeFilters = new ArrayList<>();
        this.apiToView = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("apiToView");
        this.currentUser = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("user");
        this.currentToken = (String)FacesContext.getCurrentInstance().getExternalContext().getFlash().get("token");
        allLogs = ((LogListHelper) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("logListHelper")).getLogList();
        this.logsToView = new ArrayList<>();
        /*        List<Log> allLogs = logSessionBeanLocal.retrieveAllLogs(); */
        Set<String> uniqueApis = new HashSet<>();
        for (Log l : allLogs) {
            uniqueApis.add(l.getApi());
            if (l.getApi().equals(this.apiToView)) {
                this.getLogsToView().add(l);
            }
        }
        this.apis = new ArrayList<>(uniqueApis);
        System.out.println("CURRENT USER: " + currentUser);
    }

    public boolean filterByEventType(Object value, Object filter, Locale locale) {
        String eventType = (String) value;
        if (getEventTypeFilters().isEmpty() || getEventTypeFilters().contains(eventType)) {
            System.out.println("FILTER BY EVENT TYPE: TRUE");
            return true;
        }
        System.out.println("FILTER BY EVENT TYPE: FALSE");
        return false;
    }

    public void returnToIndex() throws IOException {
        if (currentUser != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?user=" + currentUser);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }

    public void viewAPILogs(ActionEvent event) throws IOException {
        String apiToView = (String) event.getComponent().getAttributes().get("api");

        LogListHelper logListHelper = new LogListHelper(allLogs);
        if (!(this.currentUser == null)) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", this.currentUser);
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("apiToView", apiToView);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("logListHelper", logListHelper);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewSingleAPILogs.xhtml");

    }

    /**
     * @return the apiToView
     */
    public String getApiToView() {
        return apiToView;
    }

    /**
     * @param apiToView the apiToView to set
     */
    public void setApiToView(String apiToView) {
        this.apiToView = apiToView;
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
     * @return the logsToView
     */
    public List<Log> getLogsToView() {
        return logsToView;
    }

    /**
     * @param logsToView the logsToView to set
     */
    public void setLogsToView(List<Log> logsToView) {
        this.logsToView = logsToView;
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
     * @return the currentUser
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the fullMessage
     */
    public String getFullMessage() {
        return fullMessage;
    }

    /**
     * @param fullMessage the fullMessage to set
     */
    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
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
     * @return the currentToken
     */
    public String getCurrentToken() {
        return currentToken;
    }

    /**
     * @param currentToken the currentToken to set
     */
    public void setCurrentToken(String currentToken) {
        this.currentToken = currentToken;
    }

}
