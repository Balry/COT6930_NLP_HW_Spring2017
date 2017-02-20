package toolsMP;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;

/**
 * This class provides the implementation of the OpenNLP Part-of-Speech (POS).
 * @author "Balry" - Michael Perez
 *
 */
public class POSTagger {
	
	/**
	 * This function gets the Part-of-Speech tags associated with each of the words in the text string provided.
	 * @param text Text string to be tagged
	 * @return String array with tags of the original text by word
	 * @throws IOException If model file not found
	 */
	public static String [] getPOSTagsArray (String text) throws IOException{
		
		//Loading parts of speech-maxent model
		InputStream inputStream = new FileInputStream("OpenNLP_models/en-pos-maxent.bin");
		POSModel model = new POSModel(inputStream);
		
		//Instantiating POSTaggerME class
		POSTaggerME tagger = new POSTaggerME(model);
		
		//Tokenizing text
		String [] tokenizedText = WSTokenizer.tokenize(text);
		
		//Generating tags
		String [] tags = tagger.tag(tokenizedText);
		
		return tags;
	}
	
	/**
	 * This function finds the Part-of-Speech tags associated with each of the words in the text string provided.
	 * It then combines it with the original text and returns it as a string.
	 * @param text Text string to be tagged
	 * @return String with tagged words in same order as original text
	 * @throws IOException If model file not found
	 */
	public static String getPOSTagsString (String text) throws IOException{
		
		//Generating tags
		String [] tags = getPOSTagsArray(text);
		
		//Tokenizing text
		String [] tokenizedText = WSTokenizer.tokenize(text);
		
		//Initializing the POSSample class to combine tags and text
		POSSample sample = new POSSample(tokenizedText, tags);		
		
		return sample.toString();
	}
	
	/**
	 * This function calculates and returns the probabilities associated with tags of the original tags by word.
	 * @param text Text string to be scanned
	 * @return Returns the probabilities associated with the tags of the original text by word
	 * @throws IOException
	 */
	public static double [] getPOSTagsProb (String text) throws IOException{
		
		//Loading parts-of-speech-maxent model
		InputStream inputStream = new FileInputStream("OpenNLP_models/en-pos-maxent.bin");
		POSModel model = new POSModel(inputStream);
		
		//Instantiating POSTaggerME class
		POSTaggerME tagger = new POSTaggerME(model);
		
		//Tokenizing text
		String [] tokenizedText = WSTokenizer.tokenize(text);
		
		//Generating tags
		String [] tags = tagger.tag(tokenizedText);
		
		//Initializing the POSSample class
		@SuppressWarnings("unused")
		POSSample sample = new POSSample(tokenizedText, tags);		
		
		//Get probabilities for each tag of the last decoded sentence
		double [] probabilities = tagger.probs();
		
		return probabilities;
	}
	
	/**
	 * Demo how to use implemented functions.
	 * @throws IOException If model file not found
	 */
	public static void main(String[] args) throws IOException {
		String text = "Steve Jobs was immediatelly convinced. I love William Henry Gates III. Mike is cool.";

		//POS tagger demo
		String POS = getPOSTagsString(text);
		System.out.println(POS);
		
		System.out.println("");
		
		//Detect POS tag probability demo
		double [] probabilities = getPOSTagsProb(text);
		for(double prob : probabilities){
			System.out.println(prob);
		}
	}
}
