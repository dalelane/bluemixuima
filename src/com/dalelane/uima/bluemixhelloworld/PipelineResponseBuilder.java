package com.dalelane.uima.bluemixhelloworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class to create a POJO to store annotations copied from an analysis CAS. 
 *  The POJO is implemented as a map of annotation types to annotation details. 
 *  The annotation details are a list of key/value pairs. 
 *  
 * For example:
 *  {
 *     "email" : [ 
 *                   { "user" : "email", "host" : "dalelane.co.uk", "address" : "email@dalelane.co.uk", "positions" : { "start" : 123, "end" : 132 } },
 *                   { "user" : "test", "host" : "dalelane.co.uk", "address" : "test@dalelane.co.uk", "positions" : { "start" : 223, "end" : 233 } }
 *               ],
 *     "longword" : [
 *                   { "word" : "extraordinary", "length" : 13, "positions" : { "start" : 100, "end" : 113 } }
 *                  ]
 *  }   
 *  
 * The aim of this class is to manage the Map to simplify the code in {@link Pipeline}. 
 *  It also adds the start/end position indexes to all of the annotations, as this is
 *  common for all annotations.
 * 
 * @author Dale Lane (email@dalelane.co.uk)
 */
public class PipelineResponseBuilder {

    private Map<String, List<Map<String, Object>>> responses = new HashMap<String, List<Map<String, Object>>>();

    /** Returns the response created with the annotations that were created. */
    public Map<String, List<Map<String, Object>>> getResponses(){
        return responses;
    }
    
    /** 
     * Adds the attributes from an annotation to the response being built up by this class. 
     * 
     * @param type - the name of the type of annotation being added  
     * @param start - the offset into the input text where the annotation starts
     * @param end - the offset into the input text where the annotation ends
     * @param responseAttrs - a set of key/value pairs with the attributes copied from the annotation
     */
    public void addResponse(String type, int start, int end, Map<String, Object> responseAttrs){
        // add the start/end offsets to the details being saved
        Map<String, Integer> positions = new HashMap<String, Integer>();
        positions.put("start", start);
        positions.put("end", end);
        responseAttrs.put("positions", positions);
        // add the annotation attributes to the list for this type of annotation
        getResponse(type).add(responseAttrs);
    }
    
    /** Internal helper function to get a list from the map, creating it if it doesn't exist. */
    private List<Map<String, Object>> getResponse(String key){
        List<Map<String, Object>> typedResponses = null;
        if (responses.containsKey(key)){
            typedResponses = responses.get(key);
        }
        else {
            typedResponses = new ArrayList<Map<String, Object>>();
            responses.put(key, typedResponses);
        }
        return typedResponses;
    }
}
