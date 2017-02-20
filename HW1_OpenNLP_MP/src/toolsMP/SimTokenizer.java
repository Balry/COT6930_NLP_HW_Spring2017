package toolsMP;
import opennlp.tools.tokenize.SimpleTokenizer;

/**
 * This class provides the implementation of the OpenNLP Simple Tokenizer.
 * @author "Balry" - Michael Perez
 *
 */
public class SimTokenizer {

	/**
	 * Returns string array with tokenized text.
	 * @param text Text string to be scanned
	 * @return String array with tokenized text
	 */
	public static String [] tokenize(String text){
		
		//Instantiating SimpleTokenizer class
		SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
		
		//Tokenizing text
		String [] tokens = simpleTokenizer.tokenize(text);
		
		return tokens;
	}
	
	/**
	 * Demo how to use implemented function.
	 */
	public static void main(String[] args) {
		String text = "Jobs was immediatelly convinced. I love Bill Gates.";
		
		//Simple tokenizer demo
		String [] tokenizedText = tokenize(text);
		for(String token : tokenizedText){
			System.out.println(token);
		}
	}
}