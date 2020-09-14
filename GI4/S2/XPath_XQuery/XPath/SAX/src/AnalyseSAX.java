
// Parse un document XML en JAVA avec l'Api SAX
//on importe les API necessaires
//pour l'analyse du XML
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


public class AnalyseSAX extends DefaultHandler {
	
	private int count = 0;

	public void startDocument() throws SAXException {
		System.out.println("Debut du parsing");
		System.out.println("----------------");
	}

	public void endDocument() throws SAXException {
		System.out.println("----------------");
		System.out.println("Nombre de disques : " + count);
		System.out.println("----------------");
		System.out.println("Fin du parsing");
	}

	public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) throws SAXException {

		String nomElement = lname;
				if (nomElement.equals("")) nomElement = qname;
				System.out.println("*******");
		System.out.println("startElement : "+ nomElement);
		
				
		
		if (qname.equals("disque"))
			count++;
	}
	
	public void characters(char[] data, int start, int end) {
		String str = new String(data, start, end);
		System.out.println(str);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (localName.equals("")){
			localName = qName;
			} 
		System.out.println("endElement : "+ localName); 
		System.out.println("*******");
	}

	

	
}

