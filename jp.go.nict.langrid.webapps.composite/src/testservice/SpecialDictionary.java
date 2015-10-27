package testservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import jp.go.nict.langrid.client.ws_1_2.BilingualDictionaryWithLongestMatchSearchClient;
import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.language.Language;
import jp.go.nict.langrid.service_1_2.InvalidParameterException;
import jp.go.nict.langrid.service_1_2.ProcessFailedException;
import jp.go.nict.langrid.service_1_2.bilingualdictionary.Translation;
import jp.go.nict.langrid.service_1_2.typed.MatchingMethod;
import jp.go.nict.langrid.wrapper.ws_1_2.bilingualdictionary.AbstractBilingualDictionaryService;

public class SpecialDictionary extends AbstractBilingualDictionaryService {

	@Override
	protected Collection<Translation> doSearch(Language headLang,
			Language targetLang, String headWord, MatchingMethod machingMethod)
			throws InvalidParameterException, ProcessFailedException {
		// TODO Auto-generated method stub
		try {
			BilingualDictionaryWithLongestMatchSearchClient cl = ClientFactory
					.createBilingualDictionaryWithLongestMatchSearchClient(new URL(
							"http://langrid.org/service_manager/invoker/kyoto1.langrid:KyotoSpecialtyDictionary"));
			cl.setUserId("ishida.kyoto-u");
			cl.setPassword("tWJaakYm");
			return Arrays.asList(cl.search(headLang, targetLang, headWord, machingMethod));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
