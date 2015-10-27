
/* First created by JCasGen Fri Apr 03 14:54:33 JST 2015 */
package com.trang.uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Apr 03 15:54:21 JST 2015
 * @generated */
public class IntermediateResult_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (IntermediateResult_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = IntermediateResult_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new IntermediateResult(addr, IntermediateResult_Type.this);
  			   IntermediateResult_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new IntermediateResult(addr, IntermediateResult_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = IntermediateResult.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.trang.uima.types.IntermediateResult");
 
  /** @generated */
  final Feature casFeat_FromLang;
  /** @generated */
  final int     casFeatCode_FromLang;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getFromLang(int addr) {
        if (featOkTst && casFeat_FromLang == null)
      jcas.throwFeatMissing("FromLang", "com.trang.uima.types.IntermediateResult");
    return ll_cas.ll_getStringValue(addr, casFeatCode_FromLang);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFromLang(int addr, String v) {
        if (featOkTst && casFeat_FromLang == null)
      jcas.throwFeatMissing("FromLang", "com.trang.uima.types.IntermediateResult");
    ll_cas.ll_setStringValue(addr, casFeatCode_FromLang, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ToLang;
  /** @generated */
  final int     casFeatCode_ToLang;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getToLang(int addr) {
        if (featOkTst && casFeat_ToLang == null)
      jcas.throwFeatMissing("ToLang", "com.trang.uima.types.IntermediateResult");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ToLang);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setToLang(int addr, String v) {
        if (featOkTst && casFeat_ToLang == null)
      jcas.throwFeatMissing("ToLang", "com.trang.uima.types.IntermediateResult");
    ll_cas.ll_setStringValue(addr, casFeatCode_ToLang, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Content;
  /** @generated */
  final int     casFeatCode_Content;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getContent(int addr) {
        if (featOkTst && casFeat_Content == null)
      jcas.throwFeatMissing("Content", "com.trang.uima.types.IntermediateResult");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Content);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContent(int addr, String v) {
        if (featOkTst && casFeat_Content == null)
      jcas.throwFeatMissing("Content", "com.trang.uima.types.IntermediateResult");
    ll_cas.ll_setStringValue(addr, casFeatCode_Content, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public IntermediateResult_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_FromLang = jcas.getRequiredFeatureDE(casType, "FromLang", "uima.cas.String", featOkTst);
    casFeatCode_FromLang  = (null == casFeat_FromLang) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_FromLang).getCode();

 
    casFeat_ToLang = jcas.getRequiredFeatureDE(casType, "ToLang", "uima.cas.String", featOkTst);
    casFeatCode_ToLang  = (null == casFeat_ToLang) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ToLang).getCode();

 
    casFeat_Content = jcas.getRequiredFeatureDE(casType, "Content", "uima.cas.String", featOkTst);
    casFeatCode_Content  = (null == casFeat_Content) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Content).getCode();

  }
}



    