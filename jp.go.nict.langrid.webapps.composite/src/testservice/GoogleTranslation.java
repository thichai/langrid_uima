package testservice;

import java.net.MalformedURLException;
import java.net.URL;

import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.TranslationClient;
import jp.go.nict.langrid.client.ws_1_2.error.LangridException;
import jp.go.nict.langrid.language.InvalidLanguageTagException;
import jp.go.nict.langrid.language.Language;
import jp.go.nict.langrid.service_1_2.AccessLimitExceededException;
import jp.go.nict.langrid.service_1_2.InvalidParameterException;
import jp.go.nict.langrid.service_1_2.LanguagePairNotUniquelyDecidedException;
import jp.go.nict.langrid.service_1_2.NoAccessPermissionException;
import jp.go.nict.langrid.service_1_2.NoValidEndpointsException;
import jp.go.nict.langrid.service_1_2.ProcessFailedException;
import jp.go.nict.langrid.service_1_2.ServerBusyException;
import jp.go.nict.langrid.service_1_2.ServiceNotActiveException;
import jp.go.nict.langrid.service_1_2.ServiceNotFoundException;
import jp.go.nict.langrid.service_1_2.UnsupportedLanguagePairException;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;

public class GoogleTranslation implements TranslationService{

	@Override
	public String translate(String sourceLang, String targetLang, String source)
			throws AccessLimitExceededException, InvalidParameterException,
			LanguagePairNotUniquelyDecidedException,
			NoAccessPermissionException, ProcessFailedException,
			NoValidEndpointsException, ServerBusyException,
			ServiceNotActiveException, ServiceNotFoundException,
			UnsupportedLanguagePairException {
		// TODO Auto-generated method stub
		
		try {
			TranslationClient cl = ClientFactory.createTranslationClient(new URL("http://langrid.org/service_manager/invoker/kyoto1.langrid:GoogleTranslate"));
			cl.setUserId("ishida.kyoto-u");
			cl.setPassword("tWJaakYm");
			Language sourceL = new Language(sourceLang);
			Language tarL = new Language(targetLang);
			return cl.translate(sourceL, tarL, source);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidLanguageTagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LangridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
