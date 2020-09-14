package models;

import java.text.DecimalFormat;

import util.URessource;

/**
 * 
 * <p>Cette classe repr&eacute;sente un donn�e.
 * Cette donn�e est caract�ris�e par : <br/> 
 * <ul>
 * 	<li>Une abscisse.</li>
 * 	<li>Une ordonn�e.</li>  
 *  <li>Une valeur r&eacuteelle.</li>
 *  <li>Un nom.</li>
 * </ul>
 *
 */

public class Element {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 *  Index de la ligne occup�e par l'�l�ment.
	 **/
	
	private int row ;
		
	/**
	 *  Index de la ligne occup�e par l'�l�ment.   
	 **/
	
	private int column ;
		
	/**
	 * Valeur de l' �l�ment.
	 */
	private double value;
	
	/**
	 * Nom de l'�l�ment. 
	 */
	private String name ;
	
	/**
	 * Compteur d'objets de classe Element.
	 */
	private static int count = -1 ;
	
	/**
	 * Construit une instance d'objet de classe Element.
	 *
	 * @param row : index de la ligne de la matrice, o� l'on trouve 
	 * 				l'instance.
	 * @param column : index de la colonne de la matrice, o� l'on trouve 
	 * 				   l'instance. 
	 * @param value : valeur de l'instance.
	 * @param name : nom de l'instance. 
	 */
	
	public Element(int row,int column,double value,String name){
	
		this.row = row ;
		this.column = column ;
		this.value = URessource.round(value,2) ;
		this.name = name ;
		Element.count++;
	}
	
	/**
	 * Construit une instance avec les valeurs suivantes :
	 * <ul>
	 * 	<li> row = -1 </li>
	 *  <li> colulmn = -1</li>
	 *  <li> value = 0</li>
	 *  <li> name = "".</li>
	 * </ul>
	 * 
	 * @see #Element(int, int, double, String)
	 */
	public Element() {
		this(-1,-1,0,null);
	}
	
	/**
	 * Construit une instance avec une valeur par d�faut de z�ro et un nom par d�faut.
	 *
	 * @param row : Index de la ligne de la matrice o� se trouve	
	 * 				l'�l�ment. 
	 * @param column : Index de la colonne de la matrice o� se trouve 
	 * 				   l'�l�ment.
	 * 
	 * @see #Element(int, int, double, String) 
	 */
	
	public Element(int row,int column) {
		this(row,column,0,null);
	}
	
	/**
	 * Construit une instance d'Element avec comme nom par d�faut "".
	 *   
	 * @param row : index ligne.
	 * @param column : index colonne.
	 * @param value : valeur de l'instance.
	 * 
	 * @see #Element(int, int, double, String)
	 */
	
	public Element(int row,int column,double value) {
		this(row,column,value,null);
	}
	
	
	/**
	 * Construit une instance d'Element avec value = 0 .
	 * 
	 * @param row : index ligne.
	 * @param column : index colonne.
	 * @param name : nom de l'instance.
	 * 
	 * @see #Element(int, int, double, String)
	 */
	
	public Element(int row,int column,String name) {
		this(row,column,0,name);
	}
	
	
	/**
	 * R�initialise le compte des instances � z�ro.
	 */
	public static void reinitCount() { count=-1;}
	
	/**
	 * 
	 * @return Index de la ligne de l'instance courante.
	 * @see #setRow(int)
	 */
	
	public int getRow() { return this.row ; }
	
	/**
	 * 
	 * @return Index de la colonne de  l'instance courante.
	 * @see #setColumn(int)
	 */
	
	public int getColumn() { return this.column ; }
	
	/**
	 * 
	 * @return Valeur de l'instance courante.
	 * @see #setValue(double)
	 */
	
	public double getValue() { return this.value ; }
	
	/**
	 * 
	 * @return Nom de l'instance courante.
	 * @see #setName(String)  
	 */
	public String getName() { return this.name ; }
	
	
	/**
	 * Affecte name comme nom de l'instance courante
	 * @param name 
	 * @see #getName()
	 */
	public void setName(String name) { this.name = name ; }
	
	/**
	 * Affecte value comme valeur de l'instance courante.
	 * @param value
	 * @see #getValue()
	 */
	
	public void setValue(double value) { this.value = URessource.round(value,2) ; }
	
	/**
	 * Affecte column comme index de la colonne de l'instance courante.
	 * @param column
	 * @see #setColumn(int)
	 */
	public void setColumn(int column) { this.column = column ; }
	
	/**
	 * Affecte row comme index de la ligne de l'instance courante.
	 * @param row
	 * @see #setRow(int)
	 */
	public void setRow(int row) { this.row = row ;}

	
	/**
	 * Affecte les valeurs de l'�l�ment e � l'�l�ment courant.
	 * Si e est null, des valeurs par d�faut sont affect�es, comme suit :
	 * 
	 * <ul>
	 * 	<li>row = -1</li>
	 *  <li>column = -1</li>
	 *  <li>value = 0 </li>
	 *  <li>name = ""</li>
	 * </ul>
	 * 
	 * @param e : Element dont les valeurs seront utilis�es pour l'initilisation
	 */
	public void setElement(Element e){
		if(e==null){
			this.row = -1 ;
			this.column = -1;
			this.value = 0;
			this.name = null;
		}else{			
			this.row = e.getRow() ;
			this.column = e.getColumn();
			this.value = URessource.round(e.getValue(),2);
			this.name = e.getName();
		}
	}
	
	public String toString(){
		DecimalFormat nombre = new DecimalFormat("0.00");
		if(name!=null)return this.name+" = "+nombre.format(this.value);
		if(this.row!=-1 && this.column!=-1) return "("+this.row+","+this.column+") = "+ nombre.format(this.value) ;
		return ""+nombre.format(this.value);
	}
	
}
