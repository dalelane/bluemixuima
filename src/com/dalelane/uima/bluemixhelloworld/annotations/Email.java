

/* First created by JCasGen Sat Apr 12 23:31:53 BST 2014 */
package com.dalelane.uima.bluemixhelloworld.annotations;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** An annotation applied to strings that look like valid email addresses.
 * Updated by JCasGen Sat Apr 12 23:31:53 BST 2014
 * XML source: /Users/dalelane/Documents/dev/bluemixuima/com.dalelane.uima.bluemixhelloworld/typeSystemDescriptor.xml
 * @generated */
public class Email extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Email.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Email() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Email(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Email(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Email(JCas jcas, int begin, int end) {
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
  //* Feature: host

  /** getter for host - gets The hostname for the email address
   * @generated */
  public String getHost() {
    if (Email_Type.featOkTst && ((Email_Type)jcasType).casFeat_host == null)
      jcasType.jcas.throwFeatMissing("host", "com.dalelane.uima.bluemixhelloworld.annotations.Email");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Email_Type)jcasType).casFeatCode_host);}
    
  /** setter for host - sets The hostname for the email address 
   * @generated */
  public void setHost(String v) {
    if (Email_Type.featOkTst && ((Email_Type)jcasType).casFeat_host == null)
      jcasType.jcas.throwFeatMissing("host", "com.dalelane.uima.bluemixhelloworld.annotations.Email");
    jcasType.ll_cas.ll_setStringValue(addr, ((Email_Type)jcasType).casFeatCode_host, v);}    
   
    
  //*--------------*
  //* Feature: user

  /** getter for user - gets The username for the email address
   * @generated */
  public String getUser() {
    if (Email_Type.featOkTst && ((Email_Type)jcasType).casFeat_user == null)
      jcasType.jcas.throwFeatMissing("user", "com.dalelane.uima.bluemixhelloworld.annotations.Email");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Email_Type)jcasType).casFeatCode_user);}
    
  /** setter for user - sets The username for the email address 
   * @generated */
  public void setUser(String v) {
    if (Email_Type.featOkTst && ((Email_Type)jcasType).casFeat_user == null)
      jcasType.jcas.throwFeatMissing("user", "com.dalelane.uima.bluemixhelloworld.annotations.Email");
    jcasType.ll_cas.ll_setStringValue(addr, ((Email_Type)jcasType).casFeatCode_user, v);}    
  }

    