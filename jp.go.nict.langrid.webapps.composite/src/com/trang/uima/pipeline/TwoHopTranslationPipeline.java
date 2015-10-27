package com.trang.uima.pipeline;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.aae.client.UimaAsBaseCallbackListener;
import org.apache.uima.aae.client.UimaAsynchronousEngine;
import org.apache.uima.adapter.jms.client.BaseUIMAAsynchronousEngine_impl;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.uimafit.util.CasUtil;

import com.trang.uima.types.Target;

/**
 * A sample UIMA application. It creates a UIMA analysis engine, and provides a
 * method for using it to process text.
 * 
 * This sample creates an asynchronous UIMA analysis engine.
 * 
 * @author
 */
public class TwoHopTranslationPipeline {

	/** The pipeline created to process text. */
	private UimaAsynchronousEngine uimaAsEngine = null;

	/**
	 * Creates an asynchronous analysis engine.
	 */
	public TwoHopTranslationPipeline(UimaAsBaseCallbackListener callback,
			String deployFile) throws Exception {
		System.out.println("Running UIMA pipeline with UIMA AS");
		System.out.println("==============================================");

		// creating UIMA analysis engine
		uimaAsEngine = new BaseUIMAAsynchronousEngine_impl();

		// preparing map for use in deploying services
		Map<String, Object> deployCtx = new HashMap<String, Object>();
		deployCtx.put(UimaAsynchronousEngine.DD2SpringXsltFilePath,
				System.getenv("UIMA_HOME") + "/bin/dd2spring.xsl");
		deployCtx.put(UimaAsynchronousEngine.SaxonClasspath,
				"file:" + System.getenv("UIMA_HOME") + "/saxon/saxon8.jar");

		System.out.println("Deploying UIMA services");
		uimaAsEngine.deploy(deployFile, deployCtx);

		// // creating aggregate analysis engine
		// System.out.println("Deploying analysis engine");
		// uimaAsEngine.deploy("./conf/deploy.xml", deployCtx);

		// add callback listener that will be informed when processing completes
		uimaAsEngine.addStatusCallbackListener(callback);

		// preparing map for use in a UIMA client for submitting text to process
		System.out.println("Initialising UIMA client");
		deployCtx
				.put(UimaAsynchronousEngine.ServerUri, "tcp://localhost:61616");
		deployCtx
				.put(UimaAsynchronousEngine.ENDPOINT, "TwoHopTranslationQueue");
		uimaAsEngine.initialize(deployCtx);
	}

	/**
	 * Uses the UIMA analysis engine to process the provided document text.
	 */
	public void twoHopTranslate(String sourceLang, String targetLang,
			String text) throws CASException, Exception {
		CAS cas = uimaAsEngine.getCAS();
		cas.setDocumentText(text);
		cas.setDocumentLanguage(sourceLang + "," + "en" + "," + targetLang);
		uimaAsEngine.sendCAS(cas);
		uimaAsEngine.collectionProcessingComplete();
	}
}