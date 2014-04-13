
/* First created by JCasGen Sat Apr 12 23:31:53 BST 2014 */
package com.dalelane.uima.bluemixhelloworld.annotations;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;

/** An annotation applied to strings that are made up of a capital letter followed by one or more lower-case letters.
 * Updated by JCasGen Sat Apr 12 23:31:53 BST 2014
 * @generated */
public class CapitalisedWord_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CapitalisedWord_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CapitalisedWord_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CapitalisedWord(addr, CapitalisedWord_Type.this);
  			   CapitalisedWord_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CapitalisedWord(addr, CapitalisedWord_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = CapitalisedWord.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.dalelane.uima.bluemixhelloworld.annotations.CapitalisedWord");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CapitalisedWord_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    