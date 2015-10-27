package jp.go.nict.langrid.webapps.composite;

import java.net.URL;

import jp.go.nict.langrid.client.RequestAttributes;
import jp.go.nict.langrid.client.impl.protobuf.PbClientFactory;
import jp.go.nict.langrid.commons.cs.binding.BindingNode;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;

import org.junit.Test;

public class ThreeHopTranslationJaEnTest {
	@Test
	public void test() throws Exception{
		TranslationService service = new PbClientFactory().create(TranslationService.class
				, new URL("http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/" +
						"ThreeHopTranslationJaEn")
				);
		RequestAttributes req = (RequestAttributes)service;
		//req.setUserId("ishida.kyoto-u");
		//req.setPassword("tWJaakYm");
		req.getTreeBindings().add(new BindingNode("FirstTranslationPL"
				, "http://localhost:8080/jp.go.nict.langrid.webapps.blank/pbServices/TranslationService"));
		req.getTreeBindings().add(new BindingNode("SecondTranslationPL"
				, "http://localhost:8080/jp.go.nict.langrid.webapps.blank/pbServices/TranslationService"));
		req.getTreeBindings().add(new BindingNode("ThirdTranslationPL"
				, "http://localhost:8080/jp.go.nict.langrid.webapps.blank/pbServices/TranslationService"));
		System.out.println(service.translate("en", "ja", "Hello"));
	}
}
