import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class MyXMLHandler extends DefaultHandler {
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

		if (qname.equals("disque"))
			count++;
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
	}

	public void characters(char[] data, int start, int end) {
		String str = new String(data, start, end);
		//System.out.println(str);
	}

}
