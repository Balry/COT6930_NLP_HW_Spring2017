package toolsMP;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

/**
 * This class provides the implementation of the OpenNLP Parser.
 * @author "Balry" - Michael Perez
 *
 */
public class ParsingTool {
	
	/**
	 * This function parses constituents in order to perform further syntax analysis.
	 * @param text Text string to be parsed
	 * @return Parse type data structure holding parse constituents of provided text.
	 * @throws IOException If model file not found
	 */
	public static Parse [] parserMP(String text) throws IOException{

		//Loading parser model
		InputStream inputStream = new FileInputStream("OpenNLP_models/en-parser-chunking.bin");
		ParserModel model = new ParserModel(inputStream);
		
		//Creating Parser
		Parser parser = ParserFactory.create(model);
		
		//Parsing text
		Parse [] parsedText = ParserTool.parseLine(text, parser, 1);
		
		return parsedText;
	}
	
	/**
	 * Demo how to use implemented function.
	 * @throws IOException If model file not found
	 */
	public static void main(String[] args) throws IOException {
		String text = "Steve Jobs was immediatelly convinced. I love William Henry Gates III. Mike is cool.";
		
		//Parser demo
		Parse [] parsedText = parserMP(text);
		for (Parse p : parsedText)
			p.show(); 		
	}
}
