package jp.ishdalab.langrid.service.test;

import java.net.URL;

import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ja;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.en;
import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.TranslationClient;

public class JServerTranslationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		TranslationClient cl = ClientFactory
//				.createTranslationClient(new URL(
//						"http://localhost:8080/langrid-2.0/invoker/trang:KyotoUJServer"));
		TranslationClient cl = ClientFactory
		.createTranslationClient(new URL(
				"http://localhost/langrid-2.0/invoker/trang:GoogleTranslate"));
		
		cl.setUserId("langrid");
		cl.setPassword("langrids");
		System.out.println("Result: " + cl.translate(en, ja, "Hello."));

	}

}
