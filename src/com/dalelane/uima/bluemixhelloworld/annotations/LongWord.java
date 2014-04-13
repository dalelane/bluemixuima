

/* First created by JCasGen Sat Apr 12 23:31:53 BST 2014 */
package com.dalelane.uima.bluemixhelloworld.annotations;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** An annotation applied to long words found in the provided text, where any word over seven letters long is considered long.
 * Updated by JCasGen Sat Apr 12 23:31:53 BST 2014
 * XML source: /Users/dalelane/Documents/dev/bluemixuima/com.dalelane.uima.bluemixhelloworld/typeSystemDescriptor.xml
 * @generated */
public class LongWord extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(LongWord.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected LongWord() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public LongWord(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public LongWord(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public LongWord(JCas jcas, int begin, int end) {
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
     
 
    
  //*--------------*
  //* Feature: length

  /** getter for length - gets The number of characters in the word
   * @generated */
  public int getLength() {
    if (LongWord_Type.featOkTst && ((LongWord_Type)jcasType).casFeat_length == null)
      jcasType.jcas.throwFeatMissing("length", "com.dalelane.uima.bluemixhelloworld.annotations.LongWord");
    return jcasType.ll_cas.ll_getIntValue(addr, ((LongWord_Type)jcasType).casFeatCode_length);}
    
  /** setter for length - sets The number of characters in the word 
   * @generated */
  public void setLength(int v) {
    if (LongWord_Type.featOkTst && ((LongWord_Type)jcasType).casFeat_length == null)
      jcasType.jcas.throwFeatMissing("length", "com.dalelane.uima.bluemixhelloworld.annotations.LongWord");
    jcasType.ll_cas.ll_setIntValue(addr, ((LongWord_Type)jcasType).casFeatCode_length, v);}    
  }

    