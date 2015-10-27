package com.trang.service;


import com.trang.exception.NotAvailableException;
import com.trang.exception.NotInitialisedException;
import com.trang.uima.pipeline.Pipeline;

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

public class UIMACASPoolTwoHopTranslationService implements TranslationService {

	@Override
	public String translate(String sourceLang, String targetLang, String source)
			throws AccessLimitExceededException, InvalidParameterException,
			LanguagePairNotUniquelyDecidedException,
			NoAccessPermissionException, ProcessFailedException,
			NoValidEndpointsException, ServerBusyException,
			ServiceNotActiveException, ServiceNotFoundException,
			UnsupportedLanguagePairException {
		// TODO Auto-generated method stub
		
		String result = "";
		try {
			result = Pipeline.getInstance().translate(sourceLang, "en", targetLang, source);
		} catch (NotInitialisedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
