
/* First created by JCasGen Sat Apr 12 23:31:53 BST 2014 */
package com.dalelane.uima.bluemixhelloworld.annotations;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** An annotation applied to strings that look like valid email addresses.
 * Updated by JCasGen Sat Apr 12 23:31:53 BST 2014
 * @generated */
public class Email_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Email_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Email_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Email(addr, Email_Type.this);
  			   Email_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Email(addr, Email_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Email.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.dalelane.uima.bluemixhelloworld.annotations.Email");
 
  /** @generated */
  final Feature casFeat_host;
  /** @generated */
  final int     casFeatCode_host;
  /** @generated */ 
  public String getHost(int addr) {
        if (featOkTst && casFeat_host == null)
      jcas.throwFeatMissing("host", "com.dalelane.uima.bluemixhelloworld.annotations.Email");
    return ll_cas.ll_getStringValue(addr, casFeatCode_host);
  }
  /** @generated */    
  public void setHost(int addr, String v) {
        if (featOkTst && casFeat_host == null)
      jcas.throwFeatMissing("host", "com.dalelane.uima.bluemixhelloworld.annotations.Email");
    ll_cas.ll_setStringValue(addr, casFeatCode_host, v);}
    
  
 
  /** @generated */
  final Feature casFeat_user;
  /** @generated */
  final int     casFeatCode_user;
  /** @generated */ 
  public String getUser(int addr) {
        if (featOkTst && casFeat_user == null)
      jcas.throwFeatMissing("user", "com.dalelane.uima.bluemixhelloworld.annotations.Email");
    return ll_cas.ll_getStringValue(addr, casFeatCode_user);
  }
  /** @generated */    
  public void setUser(int addr, String v) {
        if (featOkTst && casFeat_user == null)
      jcas.throwFeatMissing("user", "com.dalelane.uima.bluemixhelloworld.annotations.Email");
    ll_cas.ll_setStringValue(addr, casFeatCode_user, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Email_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_host = jcas.getRequiredFeatureDE(casType, "host", "uima.cas.String", featOkTst);
    casFeatCode_host  = (null == casFeat_host) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_host).getCode();

 
    casFeat_user = jcas.getRequiredFeatureDE(casType, "user", "uima.cas.String", featOkTst);
    casFeatCode_user  = (null == casFeat_user) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_user).getCode();

  }
}



    