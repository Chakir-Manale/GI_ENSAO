package MJR;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			File file = new File("disques.xml");
			parser.parse(file, new MyXMLHandler());
			
		}
		catch(DOMException e){
			e.printStackTrace();
		}
		
		catch(TransformerFactoryConfigurationError e){
			e.printStackTrace();
		}
		catch(SAXException e){
			e.printStackTrace();
		}
		catch(ParserConfigurationException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	}
