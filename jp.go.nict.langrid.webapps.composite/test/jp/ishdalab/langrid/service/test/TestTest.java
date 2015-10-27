package jp.ishdalab.langrid.service.test;

import static jp.go.nict.langrid.language.ISO639_1LanguageTags.en;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ja;

import java.net.MalformedURLException;
import java.net.URL;

import testservice.Test;
import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.TranslationClient;

public class TestTest {

	/**
	 * @param args
	 * @throws Exceptions 
	 */
	public static void main(String[] args) throws Exception {
		Test test = new Test();
		String[] result = test.decodeTuple("(en ja vi fr)");
		System.out.println(result.length);

	}

}
