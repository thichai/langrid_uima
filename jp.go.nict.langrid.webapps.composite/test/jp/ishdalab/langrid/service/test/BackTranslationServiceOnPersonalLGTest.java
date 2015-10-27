package jp.ishdalab.langrid.service.test;

import java.net.MalformedURLException;
import java.net.URL;

import jp.go.nict.langrid.client.ws_1_2.BackTranslationClient;
import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.commons.cs.binding.BindingNode;
import jp.go.nict.langrid.service_1_2.backtranslation.BackTranslationResult;
import jp.go.nict.langrid.service_1_2.backtranslation.BackTranslationService;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.en;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ja;

public class BackTranslationServiceOnPersonalLGTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		BackTranslationClient client = ClientFactory
		.createBackTranslationClient(new URL(
				"http://localhost:8181/personal_langrid/invoker/kyotou.langrid:BackTranslation"));
		client.getTreeBindings()
				.add(new BindingNode(
						"ForwardTranslationPL",
						"http://localhost:8181/personal_langrid/invoker/kyotou.langrid:BingTranslate"));
		client.getTreeBindings()
				.add(new BindingNode(
						"BackwardTranslationPL",
						"http://localhost:8181/personal_langrid/invoker/kyotou.langrid:BingTranslate"));

		BackTranslationResult result = client.backTranslate(en, ja, "This a simple example.");
		System.out.println("Intermediate result: " + result.getIntermediate());
		System.out.println("Target result: " + result.getTarget());

	}

}
