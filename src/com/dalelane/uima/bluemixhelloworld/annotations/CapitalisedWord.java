

/* First created by JCasGen Sat Apr 12 23:31:53 BST 2014 */
package com.dalelane.uima.bluemixhelloworld.annotations;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** An annotation applied to strings that are made up of a capital letter followed by one or more lower-case letters.
 * Updated by JCasGen Sat Apr 12 23:31:53 BST 2014
 * XML source: /Users/dalelane/Documents/dev/bluemixuima/com.dalelane.uima.bluemixhelloworld/typeSystemDescriptor.xml
 * @generated */
public class CapitalisedWord extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(CapitalisedWord.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected CapitalisedWord() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CapitalisedWord(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CapitalisedWord(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CapitalisedWord(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
}

    