import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {

   public static void main(String[] args) {

      try {
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser parser = factory.newSAXParser();
         
         parser.parse(new File("disques.xml"), new AnalyseSAX());

      } catch (Exception e) { e.printStackTrace(); }
   }
}