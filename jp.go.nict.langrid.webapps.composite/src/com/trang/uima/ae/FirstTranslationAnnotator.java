package com.trang.uima.ae;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.TranslationClient;
import jp.go.nict.langrid.client.ws_1_2.error.LangridException;
import jp.go.nict.langrid.language.InvalidLanguageTagException;
import jp.go.nict.langrid.language.Language;

import com.trang.uima.types.IntermediateResult;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

public class FirstTranslationAnnotator extends JCasAnnotator_ImplBase {

	private static final Logger LOGGER = Logger
			.getLogger(FirstTranslationAnnotator.class);

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		// org.apache.log4j.BasicConfigurator.configure();
		// LOGGER.info("[ForwardTranslation Annotator] initializing ForwardTranslation Annotator ...");
	}

	public void process(JCas aJCas) {
		// LOGGER.info("[ForwardTranslation Annotator] processing ForwardTranslation Annotator ...");

		try {

			/*String seviceName = "MockTranslationA";
			String serviceURL = "http://localhost:8081/jp.go.trang.langrid.webapps.echo/services/";
			
			
			
			TranslationClient client = ClientFactory
					.createTranslationClient(new URL(serviceURL + seviceName));*/
			
			

			// J-Server translation service
			TranslationClient client = ClientFactory
					.createTranslationClient(new URL(
							"http://langrid.org/service_manager/invoker/kyoto1.langrid:KyotoUJServer"));
			client.setUserId("ishida.kyoto-u");
			client.setPassword("tWJaakYm");
			
			
			String langlistStr = aJCas.getDocumentLanguage();
			List<String> langList = Arrays.asList(langlistStr
					.split("\\s*,\\s*"));

			String sourceL = langList.get(0);
			String intermediateL = langList.get(1);
			String targetL = langList.get(2);

			Language sourceLang = new Language(sourceL);
			Language intermediateLang = new Language(intermediateL);
			Language targetLang = new Language(targetL);

			String docText = aJCas.getDocumentText();

			int dotIndex = docText.indexOf(".");
			String sentenceID = docText.substring(0, dotIndex);
			String source = docText.substring(dotIndex + 1);

			long start = System.currentTimeMillis();
			System.out.println("	The first translation of partition " + sentenceID
					+ " starts at:" + start);

			String result = client.translate(sourceLang, intermediateLang,
					source);
			
			String finalResult = sentenceID + "." + result; 

			IntermediateResult transText = new IntermediateResult(aJCas);
			transText.setBegin(0);
			transText.setEnd(result.length());
			transText.setFromLang(intermediateL);
			transText.setToLang(targetL);
			transText.setContent(finalResult);
			transText.addToIndexes();

			long finish = System.currentTimeMillis();

			System.out.println("	The first translation of partition "
					+ sentenceID + " finishes in: " + (finish - start) + " ms");

		} catch (MalformedURLException | LangridException
				| InvalidLanguageTagException e) {
			LOGGER.error("FirstTranslation Error: " + e.getMessage());

		}
	}
}
