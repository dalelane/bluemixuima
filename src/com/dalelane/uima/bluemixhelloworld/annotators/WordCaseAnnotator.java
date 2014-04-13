package com.dalelane.uima.bluemixhelloworld.annotators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.dalelane.uima.bluemixhelloworld.annotations.CapitalisedWord;

/**
 * Implements a UIMA annotator. Searches through the text in CAS's that the annotator
 *  processes, looking for strings that look like capitalised words. 
 *  A {@link CapitalisedWord} annotation is added to the CAS for each one found.  
 * 
 * @author Dale Lane (email@dalelane.co.uk)
 */
public class WordCaseAnnotator extends JCasAnnotator_ImplBase {

    /** The regular expression used to find capitalised words. */
    private Pattern p = null;

    /** Prepares the annotator by pre-compiling the regex that the annotator uses. */
    @Override
    public void initialize(UimaContext aContext) throws ResourceInitializationException {
        super.initialize(aContext);
        
        p = Pattern.compile("(?U)\\b\\p{Lu}\\p{L}*\\b");
    }
    
   /**
     * Adds {@link CapitalisedWord} annotations to the provided CAS for each 
     *  string found in the CAS document text that matches a regular
     *  expression written to find capitalised words. 
     */
    @Override
    public void process(JCas jcas) throws AnalysisEngineProcessException 
    {
        Matcher matcher = p.matcher(jcas.getDocumentText());
        while (matcher.find()){
            jcas.addFsToIndexes(new CapitalisedWord(jcas, matcher.start(), matcher.end()));
        }            
    }
}
