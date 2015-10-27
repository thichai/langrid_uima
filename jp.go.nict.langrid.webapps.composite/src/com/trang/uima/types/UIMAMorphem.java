

/* First created by JCasGen Fri Apr 03 14:54:33 JST 2015 */
package com.trang.uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Apr 03 15:54:21 JST 2015
 * XML source: C:/Trangmx/Projects/LG_UIMA/jp.go.nict.langrid.webapps.composite/descriptors/typeSystemDescriptor.xml
 * @generated */
public class UIMAMorphem extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(UIMAMorphem.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected UIMAMorphem() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public UIMAMorphem(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public UIMAMorphem(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public UIMAMorphem(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: word

  /** getter for word - gets 
   * @generated
   * @return value of the feature 
   */
  public String getWord() {
    if (UIMAMorphem_Type.featOkTst && ((UIMAMorphem_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "com.trang.uima.types.UIMAMorphem");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UIMAMorphem_Type)jcasType).casFeatCode_word);}
    
  /** setter for word - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setWord(String v) {
    if (UIMAMorphem_Type.featOkTst && ((UIMAMorphem_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "com.trang.uima.types.UIMAMorphem");
    jcasType.ll_cas.ll_setStringValue(addr, ((UIMAMorphem_Type)jcasType).casFeatCode_word, v);}    
   
    
  //*--------------*
  //* Feature: partofspeech

  /** getter for partofspeech - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPartofspeech() {
    if (UIMAMorphem_Type.featOkTst && ((UIMAMorphem_Type)jcasType).casFeat_partofspeech == null)
      jcasType.jcas.throwFeatMissing("partofspeech", "com.trang.uima.types.UIMAMorphem");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UIMAMorphem_Type)jcasType).casFeatCode_partofspeech);}
    
  /** setter for partofspeech - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPartofspeech(String v) {
    if (UIMAMorphem_Type.featOkTst && ((UIMAMorphem_Type)jcasType).casFeat_partofspeech == null)
      jcasType.jcas.throwFeatMissing("partofspeech", "com.trang.uima.types.UIMAMorphem");
    jcasType.ll_cas.ll_setStringValue(addr, ((UIMAMorphem_Type)jcasType).casFeatCode_partofspeech, v);}    
   
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLemma() {
    if (UIMAMorphem_Type.featOkTst && ((UIMAMorphem_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "com.trang.uima.types.UIMAMorphem");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UIMAMorphem_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLemma(String v) {
    if (UIMAMorphem_Type.featOkTst && ((UIMAMorphem_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "com.trang.uima.types.UIMAMorphem");
    jcasType.ll_cas.ll_setStringValue(addr, ((UIMAMorphem_Type)jcasType).casFeatCode_lemma, v);}    
  }

    