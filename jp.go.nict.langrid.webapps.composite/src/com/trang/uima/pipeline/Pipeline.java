package com.trang.uima.pipeline;

import java.io.File;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.CasPool;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;

import com.trang.exception.NotAvailableException;
import com.trang.exception.NotInitialisedException;
import com.trang.uima.types.Target;

public class Pipeline {
	/** The UIMA analysis engine that runs the pipeline. */
	private AnalysisEngine analysisEngine = null;

	/**
	 * A CAS pool allowing requests to reuse CAS instances without needing to
	 * recreate them.
	 */
	private CasPool casPool = null;

	/**
	 * Time that a new incoming request should wait for a CAS to be available
	 * from the pool.
	 */
	private final static int TIMEOUT = 1000 * 60; // 1 minute in milliseconds

	/**
	 * The number of CAS's to create in the pool, which will define how many
	 * concurrent requests the pipeline can support.
	 */
	private final static int CONCURRENT_REQUESTS = 50;

	private final static String descriptor = "C:/Trangmx/Projects/LG_UIMA/jp.go.nict.langrid.webapps.composite/descriptors/analysisengine/TwoHopTranslationDescriptor.xml";
	
	/** Set to true once the pipeline is successfully initialised. */
	private boolean initialised = false;

	private static class PipelineSingleton {
		private static final Pipeline INSTANCE = new Pipeline();
	}

	/** Gets the singleton instance of the Pipeline. */
	public static Pipeline getInstance() {
		return PipelineSingleton.INSTANCE;
	}

	private Pipeline() {

		try {
			File taeDescriptor = new File(descriptor);
			// File inputFile = new File(source);

			XMLInputSource in = new XMLInputSource(taeDescriptor);
			ResourceSpecifier specifier = UIMAFramework.getXMLParser()
					.parseResourceSpecifier(in);

			analysisEngine = UIMAFramework.produceAnalysisEngine(specifier);

			// create a pool of CAS's that will be used for processing incoming
			// requests
			casPool = new CasPool(CONCURRENT_REQUESTS, analysisEngine);

			initialised = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String translate(String sourceLang, String intermediateLang,
			String targetLang, String source) throws NotInitialisedException,
			NotAvailableException {
		if (initialised == false) {
			throw new NotInitialisedException();
		}

		CAS cas = casPool.getCas(TIMEOUT);

		if (cas == null) {
			throw new NotAvailableException();
		}

		try {
			cas.setDocumentText(source);
			cas.setDocumentLanguage(sourceLang + "," + intermediateLang + ","
					+ targetLang);
			analysisEngine.process(cas);

			String result = getResult(cas.getJCas());
			return result;
		} catch (AnalysisEngineProcessException | CASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			casPool.releaseCas(cas);
		}
	}

	private static String getResult(JCas jcas) {
		FSIterator iter = jcas.getAnnotationIndex(Target.type).iterator();
		String result = "";
		while (iter.isValid()) {
			FeatureStructure fs = iter.get();
			Target transText = (Target) fs;
			result = transText.getContent();
			iter.moveToNext();
		}
		return result;
	}

}
