package com.trang.service;

import com.trang.exception.NotAvailableException;
import com.trang.exception.NotInitialisedException;
import com.trang.uima.pipeline.MorphologicalAnalysisPipeline;
import com.trang.uima.pipeline.Pipeline;

import jp.go.nict.langrid.service_1_2.AccessLimitExceededException;
import jp.go.nict.langrid.service_1_2.InvalidParameterException;
import jp.go.nict.langrid.service_1_2.LanguageNotUniquelyDecidedException;
import jp.go.nict.langrid.service_1_2.LanguagePairNotUniquelyDecidedException;
import jp.go.nict.langrid.service_1_2.NoAccessPermissionException;
import jp.go.nict.langrid.service_1_2.NoValidEndpointsException;
import jp.go.nict.langrid.service_1_2.ProcessFailedException;
import jp.go.nict.langrid.service_1_2.ServerBusyException;
import jp.go.nict.langrid.service_1_2.ServiceNotActiveException;
import jp.go.nict.langrid.service_1_2.ServiceNotFoundException;
import jp.go.nict.langrid.service_1_2.UnsupportedLanguageException;
import jp.go.nict.langrid.service_1_2.UnsupportedLanguagePairException;
import jp.go.nict.langrid.service_1_2.morphologicalanalysis.Morpheme;
import jp.go.nict.langrid.service_1_2.morphologicalanalysis.MorphologicalAnalysisService;
import jp.go.nict.langrid.service_1_2.translation.TranslationService;

public class UIMACASPoolMorphologicalAnalysisService implements
		MorphologicalAnalysisService {

	@Override
	public Morpheme[] analyze(String sourceLang, String source)
			throws AccessLimitExceededException, InvalidParameterException,
			LanguageNotUniquelyDecidedException, NoAccessPermissionException,
			NoValidEndpointsException, ProcessFailedException,
			ServerBusyException, ServiceNotActiveException,
			ServiceNotFoundException, UnsupportedLanguageException {
		// TODO Auto-generated method stub
		Morpheme[] result = null;

		try {
			result = MorphologicalAnalysisPipeline.getInstance().analyse(
					sourceLang, source);
		} catch (NotInitialisedException | NotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}

}
