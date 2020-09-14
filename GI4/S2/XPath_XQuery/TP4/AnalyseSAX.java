 
  import org.xml.sax.*;
  import org.xml.sax.helpers.DefaultHandler;
  import javax.xml.parsers.SAXParserFactory;
  import javax.xml.parsers.ParserConfigurationException;
  import javax.xml.parsers.SAXParser;

  import java.io.*;
  
  public class AnalyseSAX extends DefaultHandler {

 	 public static void main (String args[]) throws IOException {
 		if (args.length != 1) {
 			System.err.println("Erreur, il manque un argument");
 			System.exit(1);
 		}

 		DefaultHandler handler =  new AnalyseSAX();
 		SAXParserFactory factory;
 		factory = SAXParserFactory.newInstance();
 		 try{
 			SAXParser saxParser = factory.newSAXParser();
 			saxParser.parse( new File(args[0]), handler);
 		} catch(Throwable t) {
 			t.printStackTrace();
 			System.exit(1);
 		}
 		System.exit(0);
 	} 
  
 	 public void startDocument() throws SAXException {
 		System.out.println("Début du document");
 	}
  
 	 public void endDocument() throws SAXException {
 		System.out.println("Fin du document");
 	}
  
 	 public void startElement( String namespaceURI, String sName, String qName, Attributes attrs )  
									 throws SAXException {
 		String eName = sName;
 		System.out.println("Balise ouvrante: " + eName);
  	}
  
 	public void endElement(	String namespaceURI, String simpleName,	String qualifiedName )
 								 throws SAXException {
  
 		String nomElement = simpleName;
 		System.out.println("Balise fermante: : " + nomElement);
 	}
  
 	 public void characters(char[] ch, int start, int end) throws SAXException {
 		String chaine =  new String(ch, start, end);
 		chaine = chaine.trim();
 		if (!chaine.equals("")) {
 			System.out.println("données : " + chaine);
 		}
 	}
 }