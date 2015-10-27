package testservice;

import static jp.go.nict.langrid.language.IANALanguageTags.zh_Hans;
import static jp.go.nict.langrid.language.IANALanguageTags.zh_Hant;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ar;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.bg;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.cs;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.da;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.de;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.el;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.en;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.es;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.et;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.fi;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.fr;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.he;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ht;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.hu;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.id;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.it;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ja;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ko;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.lt;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.lv;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.nl;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.no;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.pl;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ro;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.ru;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.sk;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.sl;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.sv;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.th;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.tr;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.uk;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.vi;
import static jp.go.nict.langrid.language.ISO639_1LanguageTags.zh;
import static jp.go.nict.langrid.language.LangridLanguageTags.pt_PT;
import static jp.go.nict.langrid.language.LangridLanguageTags.zh_CN;
import static jp.go.nict.langrid.language.LangridLanguageTags.zh_TW;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import jp.go.nict.langrid.client.ws_1_2.BackTranslationClient;
import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.error.LangridException;
import jp.go.nict.langrid.commons.cs.binding.BindingNode;
import jp.go.nict.langrid.language.Language;
import jp.go.nict.langrid.language.LanguagePair;
import jp.go.nict.langrid.language.util.LanguagePairUtil;
import jp.go.nict.langrid.service_1_2.InvalidParameterException;
import jp.go.nict.langrid.service_1_2.ProcessFailedException;
import jp.go.nict.langrid.service_1_2.backtranslation.BackTranslationResult;
import jp.go.nict.langrid.wrapper.ws_1_2.translation.AbstractTranslationService;

public class BackTranslation extends AbstractTranslationService {

	public BackTranslation() {
		setSupportedLanguagePairs(pairs);
	}
	
	private static Collection<LanguagePair> pairs = new ArrayList<LanguagePair>();
	static {
		LanguagePairUtil.addBidirectionalRoundrobinformedPairs(
				pairs
				, new Language[]{ar, bg, cs, da, nl, en, et, fi
						, fr, de, el, ht, he, hu, id, it, ja, ko
						, lv, lt, no, pl, pt_PT, ro, ru, sk, sl, es
						, sv, th, tr, uk, vi
						, zh, zh_CN, zh_Hans, zh_TW, zh_Hant}
				);
	}
	
	@Override
	protected String doTranslation(Language sourceLang, Language intermediateLang,
			String source)
			throws InvalidParameterException, ProcessFailedException {
		String result = invokeTranslation(sourceLang, intermediateLang, source);
		return result;
	}

	private String invokeTranslation(Language sourceLang,
			Language intermediateLang, String source) {
		BackTranslationClient btran;
		String result = "";
		try {
			btran = ClientFactory
					.createBackTranslationClient(new URL(
							"http://langrid.org/service_manager/invoker/kyoto1.langrid:BackTranslation"));
			btran.getTreeBindings().add(new BindingNode("ForwardTranslationPL", "GoogleTranslate"));
			btran.getTreeBindings().add(new BindingNode("BackwardTranslationPL", "KyotoUJServer"));
			btran.setUserId("ishida.kyoto-u");
			btran.setPassword("tWJaakYm");
			
			BackTranslationResult bresult = btran.backTranslate(sourceLang, intermediateLang, source);
			result = bresult.getTarget();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LangridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

}
