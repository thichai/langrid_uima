package testservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.MorphologicalAnalysisClient;
import jp.go.nict.langrid.language.Language;
import jp.go.nict.langrid.service_1_2.InvalidParameterException;
import jp.go.nict.langrid.service_1_2.ProcessFailedException;
import jp.go.nict.langrid.service_1_2.morphologicalanalysis.typed.Morpheme;
import jp.go.nict.langrid.wrapper.ws_1_2.morphologicalanalysis.AbstractMorphologicalAnalysisService;

public class MeCab extends AbstractMorphologicalAnalysisService {

	@Override
	protected Collection<Morpheme> doAnalyze(Language lang, String source)
			throws InvalidParameterException, ProcessFailedException {
		// TODO Auto-generated method stub
		try {
			MorphologicalAnalysisClient cl = ClientFactory.createMorphologicalAnalysisClient(new URL("http://langrid.org/service_manager/invoker/kyoto1.langrid:Mecab"));
			cl.setUserId("ishida.kyoto-u");
			cl.setPassword("tWJaakYm");
			return Arrays.asList(cl.analyze(lang, source));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
