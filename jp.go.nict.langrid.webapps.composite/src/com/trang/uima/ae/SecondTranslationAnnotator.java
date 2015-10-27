package com.trang.uima.ae;

import java.net.MalformedURLException;
import java.net.URL;

import jp.go.nict.langrid.client.impl.protobuf.PbClientFactory;
import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.TranslationClient;
import jp.go.nict.langrid.client.ws_1_2.error.LangridException;
import jp.go.nict.langrid.language.InvalidLanguageTagException;
import jp.go.nict.langrid.language.Language;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;

import com.trang.uima.types.IntermediateResult;
import com.trang.uima.types.Target;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

public class SecondTranslationAnnotator extends JCasAnnotator_ImplBase {

	private static final Logger LOGGER = Logger
			.getLogger(SecondTranslationAnnotator.class);

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		// org.apache.log4j.BasicConfigurator.configure();
		// LOGGER.info("[BackwardTranslation Annotator] initializing BackwardTranslation Annotator ...");
	}

	public void process(JCas aJCas) {
		// LOGGER.info("[BackwardTranslation Annotator] Processing BackwardTranslation Annotator ...");

		try {

			/*String seviceName = "MockTranslationB";
			String serviceURL = "http://localhost:8082/jp.go.trang.langrid.webapps.echo/services/";
			
			TranslationClient client = ClientFactory
					.createTranslationClient(new URL(serviceURL + seviceName));*/
			
			 //Google Translation Service 
			TranslationClient client = ClientFactory
					.createTranslationClient(new URL(
							"http://langrid.org/service_manager/invoker/kyoto1.langrid:GoogleTranslate"));
			client.setUserId("ishida.kyoto-u");
			client.setPassword("tWJaakYm");

			FSIterator translationIterator = aJCas.getAnnotationIndex(
					IntermediateResult.type).iterator();
			FeatureStructure fs = translationIterator.get();
			IntermediateResult translationText = (IntermediateResult) fs;
			String sourceL = translationText.getFromLang();
			String targetL = translationText.getToLang();
			Language sourceLang = new Language(sourceL);
			Language targetLang = new Language(targetL);

			String docText = translationText.getContent();

			int dotIndex = docText.indexOf(".");
			String sentenceID = docText.substring(0, dotIndex);
			String source = docText.substring(dotIndex + 1);

			long before = System.currentTimeMillis();
			System.out.println("	The second translation of partition " + sentenceID + " starts at: " + before);
			
			String result = client.translate(sourceLang, targetLang, source);
			String finalResult = sentenceID + "." + result;

			Target target = new Target(aJCas);
			target.setBegin(0);
			target.setEnd(result.length());
			target.setFromLang(sourceL);
			target.setToLang(targetL);
			target.setContent(finalResult);
			target.addToIndexes();
			
			long after = System.currentTimeMillis();
			System.out.println("	The second translation of partition " + sentenceID + " finishes in: " + (after-before) + " ms");

		} catch (MalformedURLException | LangridException
				| InvalidLanguageTagException e) {
			LOGGER.error("SecondTranslation Error: " + e.getMessage());
		}

	}
}
