import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
public class PrintError implements ErrorHandler {
  public void error(SAXParseException exception) throws SAXException {
    System.err.println("error: "+exception);
  }
  public void fatalError(SAXParseException exception) 
    throws SAXException {
    System.err.println("fatal error: "+exception);
  }
  public void warning(SAXParseException exception) 
    throws SAXException {
    System.err.println("warning: "+exception);
  }
}
