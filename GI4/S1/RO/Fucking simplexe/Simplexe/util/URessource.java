package util;

import java.awt.*;
import java.net.*;
import java.text.DecimalFormat;

import javax.swing.*;

/**
 * Cette Classe définit l'ensemble des fonctions utiles
 **/

public class URessource {
	
								/* Les Constantes. */
	
	/* Les différents modes de l'application.*/
	public static final int CONFIGURATION_MODE  = 1 ;
	public static final int EDITION_MODE        = 2 ;
	public static final int SIMULATION_MODE     = 3 ;
	
	/*	Les messages de la status barre.	*/
	public static String CONFIGURATION_MESSAGE  = "Mode Paramétrage Tableau Simplexe";
	public static String EDITION_MESSAGE        = "Mode Edition Tableau Simplexe";
	public static String SIMULATION_MESSAGE     = "Mode Simulation de l'algorithme du Simplexe";
	
	/*	Autres Messages	*/
	
	public static final String RIGHT_MEMBER  = "Membre de droite";
	public static final String BASIS_VARIABLES = "Variable de base";
	
	/*	Divers Constantes	*/
	public static Color BLUE_COLOR = new Color(0,128,177);
	public static Color WHITE_COLOR = new Color(0,0,0);
	
								/*		LEs Fonctions 	*/
	/**
	 * 
	 * @param packageName : nom du paquétage dont on désire avoir l'url
	 * @return URL du paquétage de nom packageName.
	 */
	
	public static URL urlPackage(String packageName){
      URL url = URessource.class.getResource("URessource.class");
      try{
       String path = url.getFile();
       int index = path.lastIndexOf("util/URessource.class");
       String pathPackage = path.substring(0,index)+packageName+"/";
       return new URL(url.getProtocol(), url.getHost(), url.getPort(), pathPackage);     
      }catch(MalformedURLException e){} 
      return null;        
    }
	
	public static ImageIcon createIcon(String pathIcon, String namePackage){

	   try{  
	    URL url = urlPackage(namePackage);
	    String path= url.getFile()+"UImages/"+pathIcon;    
	    //System.out.println("--> Chemin de pathIcon: "+path);
	    return new ImageIcon(new URL(url.getProtocol(), url.getHost(), url.getPort(), path));	  
	   }catch(MalformedURLException e){
	       System.out.println("url mal formé");
	   } 
	   return null;
	}

	/**
	 * <p> Arrondi number sous l'ordre order. Si order est negatif le nombre est 
	 * retournee sans etre arrondi. Si par contre l'ordre est o on utilse MAth.round pour
	 * arrondir le nombre.</p>
	 * @param number Nombre a arrondir.
	 * @param order  Nombre representant l'ordre de l'arrondi.
	 * @return L'arrondi d'ordre 2 du parametre.
	 */
	public static final double round(double number,int order) {	
		double rounded ;		
		
		if(order<0) return number ;
		if(order==0) Math.round(number);
		
		String decimalFormat ="0.";			
		for(int i=1;i<=order;i++) decimalFormat+="0";		
		DecimalFormat format  = new DecimalFormat(decimalFormat);
		rounded = Double.parseDouble(format.format(number).replace(',', '.'));
		return rounded ;
	}
}
