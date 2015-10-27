package com.trang.uima.ae;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.MorphologicalAnalysisClient;
import jp.go.nict.langrid.client.ws_1_2.TranslationClient;
import jp.go.nict.langrid.client.ws_1_2.error.LangridException;
import jp.go.nict.langrid.language.InvalidLanguageTagException;
import jp.go.nict.langrid.language.Language;
import jp.go.nict.langrid.service_1_2.morphologicalanalysis.typed.Morpheme;

import com.trang.uima.types.IntermediateResult;
import com.trang.uima.types.UIMAMorphem;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

public class MophologicalAnalysisAnnotator extends JCasAnnotator_ImplBase {

	private static final Logger LOGGER = Logger
			.getLogger(MophologicalAnalysisAnnotator.class);

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		// org.apache.log4j.BasicConfigurator.configure();
		// LOGGER.info("[ForwardTranslation Annotator] initializing ForwardTranslation Annotator ...");
	}

	public void process(JCas aJCas) {
		// LOGGER.info("[ForwardTranslation Annotator] processing ForwardTranslation Annotator ...");

		/*
		 * //List of morphological analysis services
		 * http://langrid.org/service_manager
		 * /invoker/kyoto1.langrid:Juman_service
		 * http://langrid.org/service_manager
		 * /invoker/kyoto1.langrid:ChasenService
		 * http://langrid.org/service_manager/invoker/kyoto1.langrid:KyTea
		 * http://langrid.org/service_manager/invoker/kyoto1.langrid:Yahoomorph
		 * http://langrid.org/service_manager/invoker/kyoto1.langrid:Mecab
		 */
		String morphoService = "http://langrid.org/service_manager/invoker/kyoto1.langrid:Mecab";

		try {

			MorphologicalAnalysisClient cl = ClientFactory
					.createMorphologicalAnalysisClient(new URL(morphoService));
			cl.setUserId("ishida.kyoto-u");
			cl.setPassword("tWJaakYm");

			String docText = aJCas.getDocumentText();

			String lang = aJCas.getDocumentLanguage();
			Language sourceLang = new Language(lang);

			Morpheme[] result = cl.analyze(sourceLang, docText);

			for (Morpheme morph : result) {
				UIMAMorphem uimaMorph = new UIMAMorphem(aJCas);
				uimaMorph.setWord(morph.getWord());
				uimaMorph.setPartofspeech(morph.getPartOfSpeech()
						.getExpression());
				uimaMorph.setLemma(morph.getLemma());
				uimaMorph.addToIndexes();
			}
			Thread.sleep(500);

		} catch (MalformedURLException | LangridException
				| InvalidLanguageTagException | InterruptedException e) {
			LOGGER.error("FirstTranslation Error: " + e.getMessage());

		}
	}
}
