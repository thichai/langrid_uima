package jp.ishdalab.langrid.service.test;

import java.net.URL;

import jp.go.nict.langrid.client.impl.protobuf.PbClientFactory;
import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;

import org.junit.Test;

public class TestBackwardTranslation {
	@Test
	public void test() throws Exception{
		TranslationService service = new PbClientFactory().create(TranslationService.class
				, new URL("http://localhost:8080/jp.go.nict.langrid.webapps.wikimedia/pbServices/BingTranslation")
				);
		System.out.println(service.translate("en", "ja", "This is a simple example."));
	}
}
