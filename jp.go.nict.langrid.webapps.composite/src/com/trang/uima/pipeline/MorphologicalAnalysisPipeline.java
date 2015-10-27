package com.trang.uima.pipeline;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.go.nict.langrid.service_1_2.morphologicalanalysis.Morpheme;
import jp.go.nict.langrid.service_1_2.typed.PartOfSpeech;

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
import com.trang.uima.types.UIMAMorphem;

public class MorphologicalAnalysisPipeline {
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
	private final static int CONCURRENT_REQUESTS = 1;

	private final static String descriptor = "C:/Trangmx/UIMA/UIMA_LANGRID/jp.go.nict.langrid.webapps.composite/descriptors/analysisengine/MeCabDescriptor.xml";
	
	/** Set to true once the pipeline is successfully initialised. */
	private boolean initialised = false;

	private static class PipelineSingleton {
		private static final MorphologicalAnalysisPipeline INSTANCE = new MorphologicalAnalysisPipeline();
	}

	/** Gets the singleton instance of the Pipeline. */
	public static MorphologicalAnalysisPipeline getInstance() {
		return PipelineSingleton.INSTANCE;
	}

	private MorphologicalAnalysisPipeline() {

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
	
	public Morpheme[] analyse(String sourceLang, String source) throws NotInitialisedException, NotAvailableException {
		if (initialised == false) {
			throw new NotInitialisedException();
		}

		CAS cas = casPool.getCas(TIMEOUT);

		if (cas == null) {
			throw new NotAvailableException();
		}
		
		try {
			cas.setDocumentText(source);
			cas.setDocumentLanguage(sourceLang);
			analysisEngine.process(cas);

			Morpheme[] result = getResult(cas.getJCas());
			return result;
		} catch (AnalysisEngineProcessException | CASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			casPool.releaseCas(cas);
		}
	}

	private Morpheme[] getResult(JCas jCas) {
		// TODO Auto-generated method stub
		FSIterator iter = jCas.getAnnotationIndex(UIMAMorphem.type).iterator();
		List<Morpheme> morList = new ArrayList<Morpheme>();
		while (iter.isValid()) {
			FeatureStructure fs = iter.get();
			UIMAMorphem uimamor = (UIMAMorphem) fs;
			Morpheme mor = new Morpheme(uimamor.getWord(), uimamor.getLemma(), uimamor.getPartofspeech());
			morList.add(mor);
			iter.moveToNext();
		}
		Morpheme[] result = morList.toArray(new Morpheme[morList.size()]);
		return result;
	}
}
