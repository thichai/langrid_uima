package jp.go.nict.langrid.webapps.composite;

import java.net.URL;

import jp.go.nict.langrid.client.RequestAttributes;
import jp.go.nict.langrid.client.impl.protobuf.PbClientFactory;
import jp.go.nict.langrid.commons.cs.binding.BindingNode;
import jp.go.nict.langrid.composite.translation.TranslationCombinedWithBilingualDictionary;
import jp.go.nict.langrid.service_1_2.multihoptranslation.MultihopTranslationResult;
import jp.go.nict.langrid.service_1_2.multihoptranslation.MultihopTranslationService;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;
import jp.go.nict.langrid.service_1_2.translation.TranslationWithInternalDictionaryService;
import jp.go.nict.langrid.service_1_2.translation.TranslationWithTemporalDictionaryService;

import org.junit.Test;

public class TranslationCombineWithDicTest {
	@Test
	public void test() throws Exception {
		
		TranslationWithInternalDictionaryService service = new PbClientFactory().create(TranslationWithInternalDictionaryService.class, new URL(
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/"
								+ "TranslationCombinedWithBilingualDictionary"));
		RequestAttributes req = (RequestAttributes) service;
		// req.setUserId("ishida.kyoto-u");
		// req.setPassword("tWJaakYm");
		req.getTreeBindings()
				.add(new BindingNode(
						"BilingualDictionaryPL",
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/SpecialDictionary"));
		req.getTreeBindings()
				.add(new BindingNode(
						"MorphologicalAnalysisPL",
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/MeCab"));
		req.getTreeBindings()
		.add(new BindingNode(
				"TranslationPL",
				"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/UIMACASPoolTwoHopTranslation"));
		String result = service.translate("ja", "de", "1.ほとんどの場合は薬剤を使って消毒をしますが、お湯で消毒をすることもできます。その場合は、60℃のお湯に5分ぐらいつけます。");
		System.out.println(result);
	}
}
