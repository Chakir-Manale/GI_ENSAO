package ma.ensao.junit.calculateur;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ClientTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(ClientTests.class.getName());
		
		suite.addTestSuite(CalculatriceTest.class);
	
		return suite;
	}
	

	public static void main(String args[]) { 
	    junit.textui.TestRunner.run(suite());
	}
}
