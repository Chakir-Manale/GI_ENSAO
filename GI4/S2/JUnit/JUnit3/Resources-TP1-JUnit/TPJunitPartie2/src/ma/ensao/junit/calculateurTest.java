package ma.ensao.junit;

import junit.framework.TestCase;
import ma.ensao.calculateur.Calculatrice;

public class calculateurTest extends TestCase{

	Calculatrice C;
	protected void setUp() {
		C=new Calculatrice();
	}
	protected void tearDown() {
		C=null;
	}
	
	/* 
	 * test de la method inisialize 
	 */
	public void testinitialize() {
		assertEquals("", C.getEcran());
		assertEquals("0", C.getOperantA());
		assertEquals("0", C.getOperantB());
		assertEquals('.', C.getOperantion());
	}
	
	/* scenarios: 
	 * 1 - Multiply
	 * 2 - Add
	 * 3 - soustraction
	 * 4 - divide
	 * 6 - divide by 0 
	 */
	
	public void testScenario1() {
		C.initialize();
		C.taperTouche('2');
		C.taperTouche('+');
		C.taperTouche('2');
		C.taperTouche('=');
		assertEquals("3", C.getContenuEcran());
		
	}
	public void testScenario2() {
	
		C.initialize();
		C.taperTouche('6');
		C.taperTouche('*');
		C.taperTouche('2');
		C.taperTouche('=');
		assertEquals("12", C.getContenuEcran());
		
	}
	public void testScenario3() {
		C.initialize();
		C.taperTouche('2');
		C.taperTouche('-');
		C.taperTouche('2');
		C.taperTouche('=');
		assertEquals("3", C.getContenuEcran());
		
	}
	public void testScenario4() {
		C.initialize();
		C.taperTouche('2');
		C.taperTouche('/');
		C.taperTouche('2');
		C.taperTouche('=');
		assertEquals("1", C.getContenuEcran());
		
	}
	public void testScenario5() {
	
		C.initialize();
		C.taperTouche('2');
		C.taperTouche('/');
		C.taperTouche('0');
		C.taperTouche('=');
		assertEquals("Erreur: operation impossible !!", C.getContenuEcran());
		
	}
	
}