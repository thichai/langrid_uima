package com.trang.aeinvoker;

import java.io.File;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;

import com.trang.uima.types.IntermediateResult;
import com.trang.uima.types.Target;

public class TwoHopTranslator {

	private String descriptor;
	public TwoHopTranslator(String descriptor) {
		this.descriptor = descriptor;
	}

	public String translate(String sourceLang, String intermediateLang, String targetLang, String source) {
		try {
			File taeDescriptor = new File(descriptor);
//			File inputFile = new File(source);

			XMLInputSource in = new XMLInputSource(taeDescriptor);
			ResourceSpecifier specifier = UIMAFramework.getXMLParser()
					.parseResourceSpecifier(in);

			AnalysisEngine tae = UIMAFramework.produceAnalysisEngine(specifier);

//			String document = FileUtils.file2String(inputFile, "UTF-8");
			JCas jcas = tae.newJCas();
			jcas.setDocumentText(source);
			jcas.setDocumentLanguage(sourceLang + "," + intermediateLang + "," + targetLang);

			tae.process(jcas);

			String result = getResult(jcas, sourceLang, targetLang);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getResult(JCas jcas, String sourceLang,
			String interLang) {
		FSIterator iter = jcas.getAnnotationIndex(Target.type)
				.iterator();
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
