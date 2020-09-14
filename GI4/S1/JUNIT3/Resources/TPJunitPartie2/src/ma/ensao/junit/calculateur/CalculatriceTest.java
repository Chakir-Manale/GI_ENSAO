package ma.ensao.junit.calculateur;

import junit.framework.TestCase;
import ma.ensao.calculateur.Calculatrice;

public class CalculatriceTest extends TestCase {

	private Calculatrice calcul;
	
	
	protected void setUp() throws Exception {
		calcul= new Calculatrice();
	}

	protected void tearDown() throws Exception {
		calcul=null;
	}

	public void testInitialize()
	{
		String resultat;
		calcul.taperTouche('5');
		calcul.taperTouche('6');
		resultat=calcul.getContenuEcran();
		assertEquals("les touches ne marches pas ", "56", resultat);
		calcul.initialize();
		resultat=calcul.getContenuEcran();
		assertEquals("la methode initialize ne marche pas ", "", resultat);
	}
	public void testSenario1() {
		
		String resultat;
		calcul.initialize();
		calcul.taperTouche('5');
		calcul.taperTouche('6');
		calcul.taperTouche('+');
		calcul.taperTouche('2');
		calcul.taperTouche('0');
		calcul.taperTouche('1');
		calcul.taperTouche('1');
		calcul.taperTouche('=');
		resultat=calcul.getContenuEcran();
		
		assertEquals("la somme de '56+2011' ne retourne pas la bonne valeur! ", "2067", resultat);
	}
	
public void testSenario2() {
		
		String resultat;
		calcul.initialize();
		calcul.taperTouche('3');
		calcul.taperTouche('0');
		calcul.taperTouche('4');
		calcul.taperTouche('0');
		calcul.taperTouche('-');
		calcul.taperTouche('1');
		calcul.taperTouche('0');
		calcul.taperTouche('2');
		calcul.taperTouche('0');
		calcul.taperTouche('=');
		resultat=calcul.getContenuEcran();
		
		assertEquals("la somme de '3040-1020' ne retourne pas la bonne valeur! ", "2020", resultat);
	}

public void testSenario3() {
	
	String resultat;
	calcul.initialize();
	calcul.taperTouche('9');
	calcul.taperTouche('0');
	calcul.taperTouche('*');
	calcul.taperTouche('1');
	calcul.taperTouche('1');
	calcul.taperTouche('=');
	resultat=calcul.getContenuEcran();
	
	assertEquals("la somme de '90*11' ne retourne pas la bonne valeur! ", "990", resultat);
}

public void testSenario4() {
	
	String resultat;
	calcul.initialize();
	calcul.taperTouche('1');
	calcul.taperTouche('4');
	calcul.taperTouche('4');
	calcul.taperTouche('/');
	calcul.taperTouche('3');
	calcul.taperTouche('=');
	resultat=calcul.getContenuEcran();
	
	assertEquals("la somme de '144/3' ne retourne pas la bonne valeur! ", "48", resultat);
}

public void testSenario5() {
	
	String resultat;
	calcul.initialize();
	calcul.taperTouche('1');
	calcul.taperTouche('4');
	calcul.taperTouche('4');
	calcul.taperTouche('/');
	calcul.taperTouche('0');
	calcul.taperTouche('=');
	resultat=calcul.getContenuEcran();
	
	assertEquals("Nous ne pouvons pas diviser pas zéro ! ", "Erreur: operation impossible !!", resultat);
}

public void testSenario6() {
	
	String resultat;
	calcul.initialize();
	calcul.taperTouche('1');
	calcul.taperTouche('0');
	calcul.taperTouche('0');
	calcul.taperTouche('+');
	calcul.taperTouche('2');
	calcul.taperTouche('0');
	calcul.taperTouche('F');
	calcul.taperTouche('=');
	resultat=calcul.getContenuEcran();
	
	assertEquals("Erreur: Caracrtere 'F' non supporte !! ", "Erreur: Caracrtere 'F' non supporte !!", resultat);

}
	
}
