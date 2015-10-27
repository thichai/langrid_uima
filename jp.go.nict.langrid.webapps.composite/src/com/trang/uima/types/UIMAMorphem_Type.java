
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
public class UIMAMorphem_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (UIMAMorphem_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = UIMAMorphem_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new UIMAMorphem(addr, UIMAMorphem_Type.this);
  			   UIMAMorphem_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new UIMAMorphem(addr, UIMAMorphem_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = UIMAMorphem.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("com.trang.uima.types.UIMAMorphem");
 
  /** @generated */
  final Feature casFeat_word;
  /** @generated */
  final int     casFeatCode_word;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getWord(int addr) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "com.trang.uima.types.UIMAMorphem");
    return ll_cas.ll_getStringValue(addr, casFeatCode_word);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setWord(int addr, String v) {
        if (featOkTst && casFeat_word == null)
      jcas.throwFeatMissing("word", "com.trang.uima.types.UIMAMorphem");
    ll_cas.ll_setStringValue(addr, casFeatCode_word, v);}
    
  
 
  /** @generated */
  final Feature casFeat_partofspeech;
  /** @generated */
  final int     casFeatCode_partofspeech;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPartofspeech(int addr) {
        if (featOkTst && casFeat_partofspeech == null)
      jcas.throwFeatMissing("partofspeech", "com.trang.uima.types.UIMAMorphem");
    return ll_cas.ll_getStringValue(addr, casFeatCode_partofspeech);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPartofspeech(int addr, String v) {
        if (featOkTst && casFeat_partofspeech == null)
      jcas.throwFeatMissing("partofspeech", "com.trang.uima.types.UIMAMorphem");
    ll_cas.ll_setStringValue(addr, casFeatCode_partofspeech, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lemma;
  /** @generated */
  final int     casFeatCode_lemma;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getLemma(int addr) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "com.trang.uima.types.UIMAMorphem");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lemma);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLemma(int addr, String v) {
        if (featOkTst && casFeat_lemma == null)
      jcas.throwFeatMissing("lemma", "com.trang.uima.types.UIMAMorphem");
    ll_cas.ll_setStringValue(addr, casFeatCode_lemma, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public UIMAMorphem_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_word = jcas.getRequiredFeatureDE(casType, "word", "uima.cas.String", featOkTst);
    casFeatCode_word  = (null == casFeat_word) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_word).getCode();

 
    casFeat_partofspeech = jcas.getRequiredFeatureDE(casType, "partofspeech", "uima.cas.String", featOkTst);
    casFeatCode_partofspeech  = (null == casFeat_partofspeech) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_partofspeech).getCode();

 
    casFeat_lemma = jcas.getRequiredFeatureDE(casType, "lemma", "uima.cas.String", featOkTst);
    casFeatCode_lemma  = (null == casFeat_lemma) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemma).getCode();

  }
}



    