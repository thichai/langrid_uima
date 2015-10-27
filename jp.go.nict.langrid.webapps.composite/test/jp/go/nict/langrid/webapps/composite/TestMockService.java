package jp.go.nict.langrid.webapps.composite;

import java.net.URL;

import jp.go.nict.langrid.client.impl.protobuf.PbClientFactory;
import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.service_1_2.morphologicalanalysis.Morpheme;
import jp.go.nict.langrid.service_1_2.morphologicalanalysis.MorphologicalAnalysisService;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;

import org.junit.Test;

public class TestMockService {
	@Test
	public void test() throws Exception{
		MorphologicalAnalysisService service = new PbClientFactory().create(MorphologicalAnalysisService.class
				, new URL("http://localhost:8080/jp.ac.kyoto_u.i.soc.ai.langrid.webapps.standfordpostagger/pbServices/StandfordPosTagger")
				);
		Morpheme[] result = service.analyze("zh", "【四个男人的抢“狗”戏】一年多来，在争夺搜狗这出大戏里，周鸿祎、张朝阳、王小川和马化腾各自扮演了什么角色？王小川的谋略、周鸿祎为何得而复失、张朝阳面临的选择题、360为何出局？真正的赢家又是谁？详见全文：");
		System.out.println(result.length);
	}
}
