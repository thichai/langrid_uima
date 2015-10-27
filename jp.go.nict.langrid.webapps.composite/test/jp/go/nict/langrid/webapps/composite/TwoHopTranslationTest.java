package jp.go.nict.langrid.webapps.composite;

import java.net.URL;

import jp.go.nict.langrid.client.RequestAttributes;
import jp.go.nict.langrid.client.impl.protobuf.PbClientFactory;
import jp.go.nict.langrid.commons.cs.binding.BindingNode;
import jp.go.nict.langrid.service_1_2.multihoptranslation.MultihopTranslationResult;
import jp.go.nict.langrid.service_1_2.multihoptranslation.MultihopTranslationService;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;

import org.junit.Test;

public class TwoHopTranslationTest {
	@Test
	public void test() throws Exception {
		MultihopTranslationService service = new PbClientFactory().create(
				MultihopTranslationService.class, new URL(
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/"
								+ "TwoHopTranslation"));
		RequestAttributes req = (RequestAttributes) service;
		// req.setUserId("ishida.kyoto-u");
		// req.setPassword("tWJaakYm");
		req.getTreeBindings()
				.add(new BindingNode(
						"FirstTranslationPL",
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/GoogleTranslation"));
		req.getTreeBindings()
				.add(new BindingNode(
						"SecondTranslationPL",
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/JServerTranslation"));
		MultihopTranslationResult result = service.multihopTranslate("vi", new String[] {"en"}, "ja", "Xin chào!");
		System.out.println(result.getTarget());
	}
}
