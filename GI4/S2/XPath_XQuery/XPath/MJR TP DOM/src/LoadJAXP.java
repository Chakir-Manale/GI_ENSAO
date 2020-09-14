import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.IOException; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
public class LoadJAXP {
  public static void main(String[] args) {
    if(args.length<1 || args.length>2) {
      System.err.println("usage: LoadJAXP [-valid] xmlfile");
      System.exit(1);
    }
    boolean validation=false;
    String xmlFile=args[0];     
    if(args.length==2) {
      if(!args[0].equals("-valid")) {
	System.err.println("usage: LoadJAXP [-valid] xmlfile");
	System.exit(1);
      } else {
	validation=true;
	xmlFile=args[1];
      }
    }
    // cr�ation de l'usine
    DocumentBuilderFactory usine=null;
    try {
      usine=DocumentBuilderFactory.newInstance();
    } catch(FactoryConfigurationError e) {
      System.err.println("Impossible de creer l'usine : "+e);
      System.exit(1);
    }
    // configuration
    // prise en compte des espaces de noms
    usine.setNamespaceAware(true);
    // activation de la validation (suivant l'option -valid)
    usine.setValidating(validation);
    // suppresion des blancs ignorables
    usine.setIgnoringElementContentWhitespace(true);
    DocumentBuilder analyseur=null;
    try {
      analyseur=usine.newDocumentBuilder();
    } catch(ParserConfigurationException e) {
      System.err.println("Impossible de creer l'analyseur : "+e);
      System.exit(1);
    }
    // gestionnaire d'erreur personnalis�
    analyseur.setErrorHandler(new PrintError());
    // on peut enfin demander une analyse 
    Document doc=null;
    try {
      doc=analyseur.parse(xmlFile);
    } catch(SAXException e) {
      System.err.println("Erreur d'analyse syntaxique : "+e);
      System.exit(1);
    } catch(IOException e) {
      System.err.println("Erreur de chargement du document : "+e);
      System.exit(1);
    }
    // on peut enfin travailler sur doc ...
  }
}
