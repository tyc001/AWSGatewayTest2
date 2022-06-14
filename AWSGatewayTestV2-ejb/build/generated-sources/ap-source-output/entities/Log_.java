package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-06-10T15:45:03")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, String> owner;
    public static volatile SingularAttribute<Log, Date> dateTime;
    public static volatile SingularAttribute<Log, Long> times;
    public static volatile SingularAttribute<Log, String> messageType;
    public static volatile SingularAttribute<Log, String> logGroup;
    public static volatile SingularAttribute<Log, String> logStream;
    public static volatile SingularAttribute<Log, String> logId;
    public static volatile SingularAttribute<Log, String> eventType;
    public static volatile SingularAttribute<Log, String> eventLogId;
    public static volatile SingularAttribute<Log, String> api;
    public static volatile SingularAttribute<Log, String> message;
    public static volatile SingularAttribute<Log, String> extendedRequestId;

}