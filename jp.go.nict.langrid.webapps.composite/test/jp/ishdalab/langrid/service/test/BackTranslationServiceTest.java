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

public class BackTranslationServiceTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
//		BackTranslationClient client = ClientFactory
//		.createBackTranslationClient(new URL(
//				"http://localhost:8080/langrid-2.0/invoker/trang:BackTranslation"));
//      //Call the service from Apache Tomcat
//		BackTranslationClient client = ClientFactory
//		.createBackTranslationClient(new URL(
//				"http://localhost:8080/langrid-2.0/invoker/trang:UIMABackTranslation"));
		
		//Call the service from apache server (Using Apache-Tomcat connector module)
		BackTranslationClient client = ClientFactory
		.createBackTranslationClient(new URL(
				"http://localhost/langrid-2.0/invoker/trang:UIMABackTranslation"));
		
		client.setUserId("langrid");
		client.setPassword("langrid");
		client.getTreeBindings()
				.add(new BindingNode(
						"ForwardTranslationPL",
						"trang:KyotoUJServer"));
		client.getTreeBindings()
				.add(new BindingNode(
						"BackwardTranslationPL",
						"trang:GoogleTranslate"));

		BackTranslationResult result = client.backTranslate(en, ja, "This is example of running Aggregate Analysis Engine in the Language Grid. The AAE is doing backtranslation from Enlish to Japanese. Biding services are J-Server and GoogleTranslation");
		System.out.println(result);

	}

}
