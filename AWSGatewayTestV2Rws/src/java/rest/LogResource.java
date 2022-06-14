/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.Log;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import session.stateless.LogSessionBeanLocal;

/**
 * REST Web Service
 *
 * @author thamy
 */
@Path("Log")
public class LogResource {

    LogSessionBeanLocal logSessionBeanLocal = lookupLogSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LogResource
     */
    public LogResource() {
    }

    @Path("retrieveAllLogs")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllLogs() {
        try {
            List<Log> logs = logSessionBeanLocal.retrieveAllLogs();

            GenericEntity<List<Log>> genericEntity = new GenericEntity<List<Log>>(logs) {
            };

            return Response.status(Response.Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLog(Log log) {
        if (log != null) {
            try {
                String logId = logSessionBeanLocal.createLog(log);

                return Response.status(Response.Status.OK).entity(logId).build();
            } catch (Exception ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Log entity sent!").build();
        }
    }

    private LogSessionBeanLocal lookupLogSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (LogSessionBeanLocal) c.lookup("java:global/AWSGatewayTestV2/AWSGatewayTestV2-ejb/LogSessionBean!session.stateless.LogSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
