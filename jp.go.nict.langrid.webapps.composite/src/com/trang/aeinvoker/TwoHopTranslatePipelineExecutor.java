package com.trang.aeinvoker;

import java.io.File;

import org.apache.uima.aae.client.UimaAsBaseCallbackListener;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.util.FileUtils;

import com.trang.uima.pipeline.TwoHopTranslationPipeline;
import com.trang.uima.types.Target;

/**
 * A test application - runs some sample text through a UIMA pipeline, timing 
 *  how long it takes for the five annotators to run. 
 *  
 * NOTE: Before running this, you have to start the ActiveMQ broker. This 
 *  can be done by running $UIMA_HOME/bin/startBroker.sh
 *  
 * @author 
 */
public class TwoHopTranslatePipelineExecutor {

	public static final String SAMPLE_DOCUMENT_TEXT = "C:/Trangmx/Projects/LG_UIMA/jp.go.nict.langrid.webapps.composite/testdata/example.txt";
	
	/** Stores the time that the pipeline was started. */
	private static long before = -1;
	
	
	public static void main(String[] args) throws CASException, Exception 
	{
		// prepares a listener for when the analysis engine is complete
		UimaAsBaseCallbackListener asyncListener = new UimaAsBaseCallbackListener() {
			/**
			 * This will be called once the text is processed.
			 */
			@Override
			public void entityProcessComplete(CAS output, EntityProcessStatus aStatus) {
				// record the time that this was complete
				long after = System.currentTimeMillis();

				
				
				FSIterator iter;
				try {
					iter = output.getJCas().getAnnotationIndex(Target.type).iterator();
					String result = "";
					while (iter.isValid()) {
						FeatureStructure fs = iter.get();
						Target transText = (Target) fs;
						result = transText.getContent();
						iter.moveToNext();
					}
					System.out.println("Result: " + result);
					// display the time spent processing the text
					System.out.println("Time spent in pipeline: " + (after - before));
				} catch (CASRuntimeException | CASException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		// constructs a class to create and run a UIMA pipeline
		TwoHopTranslationPipeline uimaPipeline = new TwoHopTranslationPipeline(asyncListener, "C:/Trangmx/Projects/LG_UIMA/jp.go.nict.langrid.webapps.composite/descriptors/analysisengine/Deploy_TwoHopTranslationAE.xml");

		// run the sample document through the pipeline
		System.out.println("Processing document...");
		before = System.currentTimeMillis();
		
		String document = FileUtils.file2String(new File(SAMPLE_DOCUMENT_TEXT));
	    document = document.trim();
		uimaPipeline.twoHopTranslate("vi", "ja", document);
	}
}