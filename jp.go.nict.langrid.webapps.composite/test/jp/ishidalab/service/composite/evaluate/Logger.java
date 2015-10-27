package jp.ishidalab.service.composite.evaluate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
	
	public final static void mylog(String fileName, String message) {
		File file = new File("C:/Trangmx/PhD Research/UIMA and U-Compare/Evaluation/Evaluate data" + fileName);
		// ArrayList<String> messages = new ArrayList<String>();
		try {
			BufferedWriter buff = new BufferedWriter(new FileWriter(file, true));
			buff.append(message);
			buff.append("\r\n");
			buff.flush();
			buff.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}