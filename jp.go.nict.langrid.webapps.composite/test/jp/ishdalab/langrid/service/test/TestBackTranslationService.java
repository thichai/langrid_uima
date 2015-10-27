package jp.ishdalab.langrid.service.test;

import java.net.URL;

import jp.go.nict.langrid.client.RequestAttributes;
import jp.go.nict.langrid.client.impl.protobuf.PbClientFactory;
import jp.go.nict.langrid.commons.cs.binding.BindingNode;
import jp.go.nict.langrid.service_1_2.backtranslation.BackTranslationResult;
import jp.go.nict.langrid.service_1_2.backtranslation.BackTranslationService;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;
import junit.framework.Assert;

import org.junit.Test;

public class TestBackTranslationService {
	@Test
	public void test() throws Exception{
		
		BackTranslationService service = new PbClientFactory().create(BackTranslationService.class
				, new URL("http://localhost:8080/jp.go.nict.langrid.webapps.wikimedia/invoker/kyotou.langrid:BackTranslation")
				);
		RequestAttributes req = (RequestAttributes)service;
//		req.setUserId("langrid");
//		req.setPassword("12345");
		req.getTreeBindings().add(new BindingNode("ForwardTranslationPL"
				, "BingTranslate"));
		req.getTreeBindings().add(new BindingNode("BackwardTranslationPL"
				, "BingTranslate"));
		
		BackTranslationResult backTransResult = service.backTranslate("en", "ja", "How are you today.");
		System.out.println("Intermediate Result: " + backTransResult.getIntermediate());
		System.out.println("Target Result: " + backTransResult.getTarget());
	}
}
