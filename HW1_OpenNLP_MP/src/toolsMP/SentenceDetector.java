package toolsMP;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * This class provides the implementation of the OpenNLP Sentence Detector and Sentence Probability Detector.
 * @author "Balry" - Michael Perez
 *
 */
public class SentenceDetector {
	
	/**
	 * Returns detected sentences in a string array.
	 * @param text Text string to be scanned
	 * @return String array with detected sentences
	 * @throws IOException If model file not found
	 */
	public static String [] detectSentences (String text) throws IOException{
		//Loading sentence detector model
		InputStream inputStream = new FileInputStream("OpenNLP_models/en-sent.bin");
		SentenceModel model = new SentenceModel(inputStream);
		
		//Instantiating the SentenceDetectorME class
		SentenceDetectorME detector = new SentenceDetectorME(model);
		
		//Detecting sentences in text
		String [] sentences = detector.sentDetect(text);
		
		return sentences;
	}
	
	/**
	 * This function calculates and returns the probabilities associated with the most recent detected sentences.
	 * @param text Text string to be scanned
	 * @return Returns the probabilities associated with the most recent calls to sentDetect()
	 * @throws IOException
	 */
	public static double [] detectSentenceProbability (String text) throws IOException{
		
		//Loading sentence detector model
		InputStream inputStream = new FileInputStream("OpenNLP_models/en-sent.bin");
		SentenceModel model = new SentenceModel(inputStream);
				
		//Instantiating the SentenceDetectorME class
		SentenceDetectorME detector = new SentenceDetectorME(model);

		//Detecting sentences in text
		@SuppressWarnings("unused")
		String [] sentences = detector.sentDetect(text);
		
		//Get probabilities of the last decoded sentence
		double [] probabilities = detector.getSentenceProbabilities();
		
		return probabilities;
	}
	
	/**
	 * Demo how to use implemented functions.
	 * @throws IOException If model file not found
	 */
	public static void main(String[] args) throws IOException {
		String text = "Jobs was immediatelly convinced. I love Bill Gates.";
		
		//Sentence detector demo
		String [] sentences = detectSentences(text);
		for(String sentence : sentences){
			System.out.println(sentence);
		}
		
		System.out.println("");
		
		//Detect sentence probability demo
		double [] probabilities = detectSentenceProbability(text);
		for(double prob : probabilities){
			System.out.println(prob);
		}
	}
}
