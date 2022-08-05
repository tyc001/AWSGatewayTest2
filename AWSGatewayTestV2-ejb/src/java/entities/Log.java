/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thamy
 */
@Entity
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String logId;
    private String eventLogId;
    private String messageType;
    private String owner;
    private String logGroup;
    private String logStream;
    private String eventType;
    private Long times;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Column(length = 7500)
    private String message;
    private String api;
    private String extendedRequestId;

    public Log() {
    }

    public Log(String eventLogId, String messageType, String owner, String logGroup, String logStream, Long times, String message) {
        this.eventLogId = eventLogId;
        this.messageType = messageType;
        this.owner = owner;
        this.logGroup = logGroup;
        if (this.logGroup.contains("Execution")) {
            this.eventType = "Execution";
        } else if (this.logGroup.contains("lambda")) {
            this.eventType = "Lambda";
        } else {
            this.eventType = "Access";
        }
        this.logStream = logStream;
        this.times = times;
        this.dateTime = new Date(this.times);
        this.message = message;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the logId fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Log[ id=" + logId + " ]";
    }

    /**
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the logGroup
     */
    public String getLogGroup() {
        return logGroup;
    }

    /**
     * @param logGroup the logGroup to set
     */
    public void setLogGroup(String logGroup) {
        this.logGroup = logGroup;
/*        if (logGroup.contains("lambda")) {
            this.eventType = "Lambda";
        } else if (logGroup.contains("Execution")) {
            this.eventType = "Execution";
        } else {
            this.eventType = "Access";
        } */
    }

    /**
     * @return the logStream
     */
    public String getLogStream() {
        return logStream;
    }

    /**
     * @param logStream the logStream to set
     */
    public void setLogStream(String logStream) {
        this.logStream = logStream;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the times
     */
    public Long getTimes() {
        return times;
    }

    /**
     * @param times the times to set
     */
    public void setTimes(Long times) {
        this.times = times;
        this.dateTime = new Date(times);
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;

/*        if (message.contains("Host: ")) {
            int startIndex = message.indexOf("Host: ") + "Host: ".length() + 1;
            int endIndex = message.indexOf(",", startIndex) - 1;
            this.api = message.substring(startIndex, endIndex).split("\\.")[0];
        } else if (message.contains("API Key:  API Stage: ")) {
            int startIndex = message.indexOf("API Key:  API Stage: ") + "API Key:  API Stage: ".length();
            int endIndex = message.indexOf("/", startIndex);
            System.out.println("End index:" + endIndex);
            this.api = message.substring(startIndex, endIndex);
        } else if (message.contains("Host=")) {
            int startIndex = message.indexOf("Host=") + "Host=".length();
            int endIndex = message.indexOf(",", startIndex);
            this.api = message.substring(startIndex, endIndex);
        }
        
        if (message.contains("extendedRequestId=")) {
            int startIndex = message.indexOf("extendedRequestId=") + "extendedRequestId=".length();
            int endIndex = message.indexOf(",", startIndex);
            this.extendedRequestId = message.substring(startIndex, endIndex);
        } else if (message.contains("extendedRequestId: ")) {
            int startIndex = message.indexOf("extendedRequestId: ") + "extendedRequestId: ".length() + 1;
            int endIndex = message.indexOf(",", startIndex) - 1;
            this.extendedRequestId = message.substring(startIndex, endIndex);
        } else if (message.contains("Extended Request Id: ")) {
            int startIndex = message.indexOf("Extended Request Id: ") + "Extended Request Id: ".length();
            int endIndex = message.indexOf("=") + 1;
            this.extendedRequestId = message.substring(startIndex, endIndex);            
        } */
    }

    /**
     * @return the eventLogId
     */
    public String getEventLogId() {
        return eventLogId;
    }

    /**
     * @param eventLogId the eventLogId to set
     */
    public void setEventLogId(String eventLogId) {
        this.eventLogId = eventLogId;
    }

    /**
     * @return the api
     */
    public String getApi() {
        return api;
    }

    /**
     * @param api the api to set
     */
    public void setApi(String api) {
        this.api = api;
    }

    /**
     * @return the extendedRequestId
     */
    public String getExtendedRequestId() {
        return extendedRequestId;
    }

    /**
     * @param extendedRequestId the extendedRequestId to set
     */
    public void setExtendedRequestId(String extendedRequestId) {
        this.extendedRequestId = extendedRequestId;
    }

}
