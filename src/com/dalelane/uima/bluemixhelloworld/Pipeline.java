package com.dalelane.uima.bluemixhelloworld;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.CasPool;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

import com.dalelane.uima.bluemixhelloworld.annotations.CapitalisedWord;
import com.dalelane.uima.bluemixhelloworld.annotations.Email;
import com.dalelane.uima.bluemixhelloworld.annotations.LongWord;
import com.dalelane.uima.bluemixhelloworld.exceptions.NotAvailableException;
import com.dalelane.uima.bluemixhelloworld.exceptions.NotInitialisedException;


/**
 * Implements a sample UIMA pipeline which performs the analysis on text received in requests. 
 * 
 * @author Dale Lane (email@dalelane.co.uk)
 */
public class Pipeline {

    /** The UIMA analysis engine that runs the pipeline. */
    private AnalysisEngine analysisEngine = null; 
    
    /** A CAS pool allowing requests to reuse CAS instances without needing to recreate them. */
    private CasPool casPool = null;

    /** Time that a new incoming request should wait for a CAS to be available from the pool. */
    private final static int TIMEOUT = 1000 * 60; // 1 minute in milliseconds
    
    /** The number of CAS's to create in the pool, which will define how many concurrent requests the pipeline can support. */
    private final static int CONCURRENT_REQUESTS = 25;
    
    /** Set to true once the pipeline is successfully initialised. */
    private boolean initialised = false;

    /** Support for lazy initialising of a singleton instance of the Pipeline class. */
    private static class PipelineSingleton {
        private static final Pipeline INSTANCE = new Pipeline();
    }
    
    /** Gets the singleton instance of the Pipeline. */
    public static Pipeline getInstance() {
        return PipelineSingleton.INSTANCE;
    }
    
    /** Creates the singleton instance of the pipeline. */
    private Pipeline() 
    {
        try {
            // the root location for files once the war file is deployed
            File blueMixAppFsRoot = new File("./apps/myapp/");
    
            // the location of the pipeline's analysis engine descriptor file
            File analysisEngineDescriptorFile = new File(blueMixAppFsRoot, "com.dalelane.uima.bluemixhelloworld/aggregateAnalysisEngineDescriptor.xml");
            
            // parse the descriptor file
            XMLInputSource aeDescriptorSource = new XMLInputSource(analysisEngineDescriptorFile);
            ResourceSpecifier aeSpecifier = UIMAFramework.getXMLParser().parseResourceSpecifier(aeDescriptorSource);
            
            // create the UIMA pipeline from the parsed descriptor
            analysisEngine = UIMAFramework.produceAnalysisEngine(aeSpecifier);
            
            // create a pool of CAS's that will be used for processing incoming requests
            casPool = new CasPool(CONCURRENT_REQUESTS, analysisEngine);
            
            // init complete
            initialised = true;
        } 
        catch (IOException e) { 
            e.printStackTrace();
        } 
        catch (InvalidXMLException e) {
            e.printStackTrace();
        } 
        catch (ResourceInitializationException e) {
            e.printStackTrace();
        } 
    }
    
    
    /**
     * Runs the provided text through the pipeline, and returns a map of the annotations
     *  that are generated. 
     *  
     * @param inputtext - text to analyse
     * @throws NotInitialisedException - if the pipeline failed to initialise
     * @throws NotAvailableException - if the analysis engine is already processing as many concurrent requests as it can support
     * @throws AnalysisEngineProcessException - if the analysis of the text fails
     * @throws CASRuntimeException - if the attempt to add the input text to the CAS fails
     * @throws CASException - if the attempt to get a JCas from the CAS fails
     */
    public Map<String, List<Map<String, Object>>> process(String inputtext) 
            throws NotInitialisedException, NotAvailableException, 
                   AnalysisEngineProcessException, CASRuntimeException, CASException 
    {
        
        // check that the pipeline was successfully initialised before trying to use it
        if (initialised == false){
            throw new NotInitialisedException();
        }
        
        // try and get a CAS from the pool - if all of the CAS's are currently in use
        //  (all CONCURRENT_REQUESTS of them) we wait for TIMEOUT milliseconds for one
        //  to be available
        CAS cas = casPool.getCas(TIMEOUT);
        if (cas == null){
            // all of the CAS's are in use, even after waiting for a minute
            //  so give up and report a temporary not-available error
            throw new NotAvailableException();
        }

        try {
            // add the provided input text to the CAS analysis structure
            cas.setDocumentText(inputtext);

            // run the CAS through the pipeline
            analysisEngine.process(cas);

            // prepare a pojo container to copy the CAS annotations 
            //  to, so the CAS can be returned to the pool 
            PipelineResponseBuilder responseBldr = new PipelineResponseBuilder();

            // iterate through annotations of interest using the superclass for all 
            //  annotations defined in /com.dalelane.uima.bluemixhelloworld/typeSystemDescriptor.xml
            FSIterator<Annotation> annotationsIterator = cas.getJCas().getAnnotationIndex(com.dalelane.uima.bluemixhelloworld.annotations.Annotation.type).iterator();
            while (annotationsIterator.hasNext()){
                Map<String, Object> responseAttrs = new HashMap<String, Object>();

                Annotation annotation = annotationsIterator.next();
                
                // each annotation has it's own attribute types, so we use instanceof 
                //  to work out which attributes to get out of the annotation
                                
                if (annotation instanceof Email){
                    Email emailAnnotation = (Email) annotation;
                    responseAttrs.put("address", emailAnnotation.getCoveredText());
                    responseAttrs.put("host", emailAnnotation.getHost());
                    responseAttrs.put("user", emailAnnotation.getUser());
                }
                else if (annotation instanceof LongWord){
                    LongWord longAnnotation = (LongWord) annotation;
                    responseAttrs.put("word", longAnnotation.getCoveredText());
                    responseAttrs.put("length", longAnnotation.getLength());
                }
                else if (annotation instanceof CapitalisedWord){
                    CapitalisedWord caseAnnotation = (CapitalisedWord) annotation;
                    responseAttrs.put("word", caseAnnotation.getCoveredText());
                }
                
                // add the annotation details to the POJO
                
                responseBldr.addResponse(annotation.getType().getName(), 
                                         annotation.getBegin(), 
                                         annotation.getEnd(), 
                                         responseAttrs);
            }
            
            // return the map with the annotations copied out of the CAS
            return responseBldr.getResponses();
        } 
        finally {
            // return the CAS to the pool (even if the processing failed) 
            //  so that it can be reused by subsequent analysis requests
            casPool.releaseCas(cas);
        }
    }
}
