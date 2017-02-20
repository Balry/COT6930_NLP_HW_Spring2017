package toolsMP;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;


/**
 * This class provides the implementation of the OpenNLP named entity recognition.
 * It allows the user to choose which NER model to utilize and provides a formatting functions
 * @author "Balry" - Michael Perez
 *
 */
public class NamedEntityFinder {
	
	/** 
	 * Returns array spans of named entities found according to model type.
	 * @param type char {d, l, m, o, p, t} (defaults to p). d - Dates; l - Locations; m - Money; o - Organizations; p - Persons; t - Time. Not case sensitive.
	 * @param text Text string to be scanned
	 * @return Span with array locations of all found entities of selected type
	 * @throws IOException If model file not found
	 */
	public static Span [] findEntities(char type, String text) throws IOException{
		
		String modelPath="";
		type = Character.toLowerCase(type);
		
		//Select path to model type requested by user. Defaults to person NER.
		switch(type){
			case 'd': 
				modelPath = "OpenNLP_models/en-ner-date.bin";
				break;
			case 'l': 
				modelPath = "OpenNLP_models/en-ner-location.bin";
				break;
			case 'm': 
				modelPath = "OpenNLP_models/en-ner-money.bin";
				break;
			case 'o': 
				modelPath = "OpenNLP_models/en-ner-organization.bin";
				break;
			case 'p': 
				modelPath = "OpenNLP_models/en-ner-person.bin";
				break;
			case 't': 
				modelPath = "OpenNLP_models/en-ner-time.bin";
				break;
			default: 
				modelPath = "OpenNLP_models/en-ner-person.bin";
				break;
		}
		
		//Loading selected entity finder model
		InputStream is = new FileInputStream(modelPath);
		TokenNameFinderModel model = new TokenNameFinderModel(is);

		//Instantiating the NameFinderMe classes according to chosen model type
		NameFinderME nameFinder = new NameFinderME(model);
		
		//Tokenize text
		String [] tokenizedText = SimTokenizer.tokenize(text);
		
		//Find array spans of NER according to model type
		Span [] entities = nameFinder.find(tokenizedText);
		
		return entities;
	}
	
	/**
	 * Returns a formatted string representation of the NER spans and the named entity itself.
	 * @param e Entities Span obtained from @findEntities
	 * @param text Original text string that corresponds to provided span
	 * @return String formatted as follows: "Token number [SPAN#) TYPE] is: ENTITY" 
	 */
	public static String [] entitiesSpanToString (Span [] e, String text){
		
		//If span is empty return "None" since there are no entities of that type in the text
		if (e.length == 0){
			String [] empty = new String [] {"None"};
			return empty;
		}
		
		//Tokenize text
		String tokenizedText [] = SimTokenizer.tokenize(text);
		
		//Format span as follows: "Token number [#) TYPE] is: ENTITY"
		String [] entities = new String [e.length];
		for(int i = 0; i < e.length; i++){
			entities[i] = "Token number " + e[i].toString() + "] is: ";
			for (int j = e[i].getStart(); j < e[i].getEnd(); j++)
				entities[i] += tokenizedText[j] + " ";	
		}
		
		return entities;
	}
	
	/**
	 * Demo how to use included functions.
	 * @throws IOException If model file not found
	 */
	public static void main(String[] args) throws IOException {
		
		String text = "Steve Jobs was immediatelly convinced. I love William Henry Gates III. Mike is cool.";

		//Named entity finder demo
		String [] entities = entitiesSpanToString(findEntities('p', text), text);
		for(String e : entities){
			System.out.println(e);
		}
	}
}
