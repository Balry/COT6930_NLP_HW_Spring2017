package toolsMP;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;

/**
 * This class provides the implementation of the OpenNLP chunker.
 * @author "Balry" - Michael Perez
 *
 */
public class Chunker {
	
	/**
	 * Chunking consists of dividing a text in syntactically correlated parts of words, like noun groups, 
	 * verb groups, but does not specify their internal structure, nor their role in the main sentence.
	 * @param text Text string to be chunked
	 * @return String array of the chunked text.
	 * @throws IOException If model file not found
	 */
	public static String [] getChunks (String text) throws IOException{
		
		//Generating Tags from text
		String [] tags = POSTagger.getPOSTagsArray(text);
		
		//Tokenizing text
		String [] tokenizedText = WSTokenizer.tokenize(text);
		
		//Loading the chunker model
		InputStream inputStream = new FileInputStream("OpenNLP_models/en-chunker.bin");
		ChunkerModel model = new ChunkerModel(inputStream);
		
		//Instantiating the ChunkerMe class
		ChunkerME chunkerME = new ChunkerME(model);
		
		//Generating Chunks
		String [] chunks = chunkerME.chunk(tokenizedText, tags);
		
		return chunks;
	}
	
	/**
	 * Demo how to use included function.
 	 * @throws IOException If model file not found
	 */
	public static void main(String[] args) throws IOException{
		String text = "Steve Jobs was immediatelly convinced. I love William Henry Gates III. Mike is cool.";
		
		//Chunker demo
		String [] chunks = getChunks(text);
		for(String chunk : chunks){
			System.out.println(chunk);
		}
	}
}
