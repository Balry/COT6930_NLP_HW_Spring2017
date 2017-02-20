package toolsMP;
import opennlp.tools.tokenize.WhitespaceTokenizer;

/**
 * This class provides the implementation of the OpenNLP Whitespace Tokenizer.
 * @author "Balry" - Michael Perez
 *
 */
public class WSTokenizer {

	/**
	 * Returns string array with tokenized text.
	 * @param text Text string to be scanned
	 * @return String array with tokenized text
	 */
	public static String [] tokenize(String text){
		
		//Instantiating WhitespaceTokenizer class
		WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
				
		//Tokenizing text
		String [] tokens = whitespaceTokenizer.tokenize(text);
		
		return tokens;
	}
	
	/**
	 * Demo how to use implemented function.
	 */
	public static void main(String[] args) {
		String text = "Jobs was immediatelly convinced. I love Bill Gates.";
		
		//Whitespace tokenizer demo
		String [] tokenizedText = tokenize(text);
		for(String token : tokenizedText){
			System.out.println(token);
		}
	}
}