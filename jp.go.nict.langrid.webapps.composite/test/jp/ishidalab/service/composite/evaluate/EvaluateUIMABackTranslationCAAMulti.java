package jp.ishidalab.service.composite.evaluate;

import java.net.URL;

import jp.go.nict.langrid.client.RequestAttributes;
import jp.go.nict.langrid.client.impl.protobuf.PbClientFactory;
import jp.go.nict.langrid.commons.cs.binding.BindingNode;
import jp.go.nict.langrid.service_1_2.backtranslation.BackTranslationResult;
import jp.go.nict.langrid.service_1_2.backtranslation.BackTranslationService;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;
import junit.framework.Assert;

import org.junit.Test;

public class EvaluateUIMABackTranslationCAAMulti {
	@Test
	public void test() throws Exception {

		 String fileName1 = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/7 paragraphs.txt";
		 String fileName2 = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/17 paragraphs.txt";
		 String fileName3 = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/23 paragraphs.txt";
		 String fileName4 = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/40 paragraphs.txt";
		 String fileName5 = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/60 paragraphs.txt";
		 String[] fileCollection = new String[]{fileName1, fileName2, fileName3, fileName4, fileName5};
		 
		 for (int i = 0; i < 2; i++) {
			testData(fileCollection[i]);
		}

		
	}

	private void testData(String fileName) throws Exception{
		System.out.println("Process with file " + fileName);
		long sumtime = 0;
		for (int i = 1; i <= 10; i++) {
			long runtime = runBackTranslation(fileName);
			System.out.println("Process time " + i + " : " + runtime);
			sumtime +=runtime;
		}
		
		System.out.println("Total time: " + sumtime);
		System.out.println("Average time: " + sumtime/10);
	}
	
	private long runBackTranslation(String fileName) throws Exception {
		BackTranslationService service = new PbClientFactory().create(
				BackTranslationService.class, new URL(
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/"
								+ "UIMABackTranslationCASMulti"));
		RequestAttributes req = (RequestAttributes) service;
		// req.setUserId("ishida.kyoto-u");
		// req.setPassword("tWJaakYm");
		req.getTreeBindings()
				.add(new BindingNode(
						"ForwardTranslationPL",
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/ForwardTranslation"));
		req.getTreeBindings()
				.add(new BindingNode(
						"BackwardTranslationPL",
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/BackwardTranslation"));

		long before = System.currentTimeMillis();
		BackTranslationResult backTransResult = service.backTranslate("en",
				"ja", fileName);
		long after = System.currentTimeMillis();

		return after - before;
	}
}
