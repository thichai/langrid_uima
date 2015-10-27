package jp.ishdalab.langrid.service.test;

import static jp.go.nict.langrid.language.ISO639_1LanguageTags.en;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ja;

import java.net.MalformedURLException;
import java.net.URL;

import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.MorphologicalAnalysisClient;
import jp.go.nict.langrid.client.ws_1_2.TranslationClient;
import jp.go.nict.langrid.service_1_2.morphologicalanalysis.typed.Morpheme;

public class TestService {

	/**
	 * @param args
	 * @throws Exceptions 
	 */
	public static void main(String[] args) throws Exception {
		String serviceURL = "http://localhost:8081/personal_langrid/invoker/kyoto0.langrid_standford_pos_tagger";
		MorphologicalAnalysisClient cl = ClientFactory.createMorphologicalAnalysisClient(new URL(serviceURL));
		cl.setUserId("langrid");
		cl.setPassword("svhuiG6s");
		Morpheme[] result = cl.analyze(en, "Are \"flipped\" classroom the future of MOOCs? One blended");
		Morpheme[] result2 = cl.analyze(en, "Economists Don’t See Much Difference Between Yellen Fed and Third Bernanke Term");
		System.out.println(result[0].getWord());
		System.out.println(result2[0].getWord());

	}

}
