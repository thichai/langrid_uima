package jp.ishdalab.langrid.service.test;

import java.io.File;
import java.net.URL;

import jp.go.nict.langrid.client.impl.protobuf.PbClientFactory;
import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;

import org.apache.uima.util.FileUtils;
import org.junit.Test;

public class TestForwardTranslation {
	@Test
	public void test() throws Exception{
		TranslationService service = new PbClientFactory().create(TranslationService.class
				, new URL("http://localhost:8080/jp.go.nict.langrid.webapps.composite/pbServices/ForwardTranslation")
				);
		String fileName = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/7 paragraphs.txt";
//		String fileName = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/17 paragraphs.txt";
//		String fileName = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/23 paragraphs.txt";
//		String fileName = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/40 paragraphs.txt";
//		String fileName = "C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Test data/60 paragraphs.txt";
		String docs = FileUtils.file2String(new File(fileName));
		long before = System.currentTimeMillis();
		String result = service.translate("en", "ja", docs);
		long after = System.currentTimeMillis();
		System.out.println(result);
		System.out.println("Processing time: " + (after - before));
	}
}
