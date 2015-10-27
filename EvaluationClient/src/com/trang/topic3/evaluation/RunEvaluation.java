package com.trang.topic3.evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.go.nict.langrid.client.ws_1_2.ClientFactory;
import jp.go.nict.langrid.client.ws_1_2.TranslationClient;
import jp.go.nict.langrid.language.Language;

/**
 * 
 * @author Trang
 *
 */
public class RunEvaluation {

	

	private static String datafile = "./data/topic3/document_ja(100).txt";

	public static void main(String[] args) throws Exception {
		
		String seviceName = "UIMAASTwoHopTranslation";
		
		TranslationClient client = ClientFactory
				.createTranslationClient(new URL(
						"http://localhost:8080/jp.go.nict.langrid.webapps.composite/services/" + seviceName));
		
		
		String document = readFile(datafile);
		
		Language sourceLang = new Language("ja");
		Language targetLang = new Language("vi");
		
		System.out.println("Translating the document...");
		
		long start = System.currentTimeMillis();
		String result = client.translate(sourceLang, targetLang, document);
		long finish = System.currentTimeMillis();
		
		System.out.println("Result: " + result);
		
		System.out.println("Finish translation in: " + (finish - start));

	}
	
	private static String readFile(String fileName) {
		String result = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			
			String line = br.readLine();
			
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			result = sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
}