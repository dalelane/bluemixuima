package com.dalelane.uima.bluemixhelloworld.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;

import com.dalelane.uima.bluemixhelloworld.Pipeline;
import com.dalelane.uima.bluemixhelloworld.exceptions.NotAvailableException;
import com.dalelane.uima.bluemixhelloworld.exceptions.NotInitialisedException;

/**
 * Implements the REST API that is used to submit analysis requests for running
 *  through the UIMA pipeline.  
 * 
 * @author Dale Lane (email@dalelane.co.uk)
 */
@Path("/")
public class UimaResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response processText(String input)
    {
        try {
            return Response
                    .ok(Pipeline.getInstance().process(input))
                    .build();
        } 
        catch (NotInitialisedException e) {
            // UIMA pipeline failed to initialise
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Failed to start pipeline").build();
        } 
        catch (NotAvailableException e) {
            // Unable to get CAS from the pool, suggesting that 
            //  the pipeline is currently running the hard-coded
            //  maximum number of concurrent requests that are 
            //  supported, so we return an error to indicate this
            //  is a temporary error.
            e.printStackTrace();
            return Response.status(Status.SERVICE_UNAVAILABLE).build();
        }
        catch (AnalysisEngineProcessException e) {
            // Processing failed
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } 
        catch (CASRuntimeException e) {
            // Processing failed
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } 
        catch (CASException e) {
            // Processing failed
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } 
    }
}