package testservice;

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
//import jp.ishidalab.uima.aeinvoker.BackwardTranslator;

public class BackwardTranslationService implements TranslationService {

	@Override
	public String translate(String sourceLang, String targetLang, String source)
			throws AccessLimitExceededException, InvalidParameterException,
			LanguagePairNotUniquelyDecidedException,
			NoAccessPermissionException, ProcessFailedException,
			NoValidEndpointsException, ServerBusyException,
			ServiceNotActiveException, ServiceNotFoundException,
			UnsupportedLanguagePairException {
		// TODO Auto-generated method stub
		
////		BackwardTranslator translator = new BackwardTranslator("C:/Trangmx/Programming/UIMA_LANGRID/examples/descriptors/langrid_uima/BackwardTranslationDescriptor.xml");
//		String result = translator.translate(sourceLang, targetLang, source);
//		return result;
		return null;
	}

}
