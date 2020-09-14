import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.*;

public class Main {

	public static void main(String[] args) throws SAXException, IOException {
		// TODO Auto-generated method stub

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File fileXML = new File("disques.xml");
			Document xml = builder.parse(fileXML);

			// Element root = xml.getDocumentElement();
			System.out.println("----------------------------");
			// compteDisques(xml);
			System.out.println("----------------------------");
			// titreDisques1(xml);
			System.out.println("----------------------------");
			// titreDisques2(xml);
			System.out.println("----------------------------");
			// titreDisques3(xml);
			System.out.println("----------------------------");
			// afficheDisques(xml);
			System.out.println("----------------------------");
            afficheNomGroupe(xml);			

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

	public static void compteDisques(Document doc) {
		NodeList list = doc.getElementsByTagName("disque");
		System.out.println("Nombre de disques : " + list.getLength());
	}

	public static void titreDisques1(Document doc) {
		NodeList list = doc.getElementsByTagName("titre");
		for (int i = 0; i < list.getLength(); i++) {
			NodeList texte = list.item(i).getChildNodes();
			System.out.print("Disque : ");
			for (int j = 0; j < texte.getLength(); j++) {
				System.out.print(texte.item(j).getNodeValue());
			}
			System.out.println();
		}
	}

	public static void titreDisques2(Document doc) {
		NodeList list = doc.getElementsByTagName("titre");
		for (int i = 0; i < list.getLength(); i++) {
			list.item(i).normalize();
			System.out.println("Disque : " + list.item(i).getFirstChild().getNodeValue());
		}
	}

	public static void titreDisques3(Document doc) {
		NodeList list = doc.getElementsByTagName("titre");
		for (int i = 0; i < list.getLength(); i++) {
			System.out.println("Disque : " + list.item(i).getTextContent());
		}
	}

	public static void afficheDisques(Document doc) {
		NodeList titres = doc.getElementsByTagName("titre");
		NodeList interpretes = doc.getElementsByTagName("interprète");
		for (int i = 0; i < titres.getLength(); i++) {
			Element titre = (Element) (titres.item(i));
			titre.normalize();
			System.out.print("Disque : " + titre.getFirstChild().getNodeValue());
			Element interprete = (Element) (interpretes.item(i));
			Element groupe = doc.getElementById(interprete.getAttribute("nom"));
			groupe.normalize();
			Node nom = groupe.getFirstChild();
			System.out.println(" interprètè par " + nom.getFirstChild().getNodeValue());
		}
	}

	public static void afficheNomGroupe(Document doc) {
		DocumentTraversal docT = (DocumentTraversal) doc;
		NodeIterator iter = docT.createNodeIterator(doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, new DOMFiltre(),
				true);
		Node node = iter.nextNode();
		while (node != null) {
			Element cible = (Element) node;
			System.out.println(cible.getAttribute("name"));
			node = iter.nextNode();
		}
	}
}
