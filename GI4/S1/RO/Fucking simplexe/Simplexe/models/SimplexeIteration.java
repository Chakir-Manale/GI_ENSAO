package models;

import java.text.DecimalFormat;

/***
 *<p>Construit une itération de simplexe avec comme tableau initiale matrix.
 * matrix est une matrice ayant la morphologie suivante :
 * <ul>
 * 	<li>La ligne 0, des colonnes 1 à variablesNumber+constraintsNumber: on a les coefficients de la fonction objective.</li>
 * 	<li>La ligne 0, colonne 0 contient la valeur de la fonction objective.</li>
 * 	<li>La colonne 0, des lignes 1 à constraintsNumber, on a le vecteur des membres de droite.</li>
 *  <li>La ligne 0, colonne 2+variablesNumber+constraintsNumber contient la variable de la fonction objective. </li>
 * 	<li>La colonne 2+variablesNumber+constraintsNumber, des lignes 1 à constraintsNumber on a la liste des varaibles de base.</li>
 * 	<li>Les lignes 1 à constraintsNumber et les colonnes 1 à variablesNumber+constraintsNumber : contiennent les coefficients des contraintes.</li>
 * </ul>
 * 
 *<p>Ainsi matrix doit être une matrice de dimension (1+constraintsNumber) x (2+variablesNumber+constraintsNumber). Si ce n'est le cas
 * le tableau du simplexe est initialialisé avec les dimensions ci-dessus et des 
 * valeurs par défaut. 
 */


public class SimplexeIteration {
	
	private static final long serialVersionUID = 1L;
		
	// nombre de variable et nombre de contrainte
	private int variablesNumber=0,constraintsNumber=0;
	
	// pivot, variable de base entrante et variable de base sortante	
	private Element pivot,incomingVariable,comingoutVariable;
	
	//Tableau simplexe initial et Tableau Simlplexe Resultat aprés une Itération.
	private Matrix matrix,result;
	
	//Si un cycle existe la variable cycle sera a true, sinon elle sera à false.
	private boolean cycle= false;
	
	// Represente le numero d'iteration
	private int iterationNumber = 0 ;
	
	/***
	 *<p>Construit une itération de simplexe avec comme tableau initiale matrix.
	 *	  
	 * @param matrix : Tableau simplexe initiale.
	 * @param variablesNumber : Nombre de variables
	 * @param constraintsNumber : Nombre de contraintes.
	 */
	public SimplexeIteration(Matrix matrix,int variablesNumber,int constraintsNumber){
		
		this.setNumbers(variablesNumber, constraintsNumber);		
		if(matrix!=null && matrix.getRowCount()==(1+constraintsNumber) && matrix.getColumnCount()==(2+variablesNumber+constraintsNumber)){
			this.matrix.copy(matrix);			
		}	    
	}

	/**
	 * <p>Créer un tableau simplexe initiale avec zéro comme valeur par défaut. Ce tableau
	 * simplexe aura les dimensions :
	 *  (1+constraintsNumber) x (2+variablesNumber+constraintsNumber)
	 * 
	 * @param variablesNumber : Nombre de variables.
	 * @param constraintsNumber : Nombre de contraintes.
	 * @see #SimplexeIteration(Matrix, int, int)
	 */
	
	public SimplexeIteration(int variablesNumber,int constraintsNumber){
		this(null,variablesNumber,constraintsNumber);
	}
	
	/**
	 * @return Nombre de variables.
	 * @see #setNumbers(int, int)
	 */
	
	public int getVaraiblesNumber(){
		return this.variablesNumber;
	}
	
	/**
	 * @return Nombre de contraintes
	 * @see #setNumbers(int, int)
	 */
	
	public int getConstraintsNumber(){
		return this.constraintsNumber;
	}
	
	
	/**
	 * 
	 * @return Tableau simplexe initiale 
	 */
	
	public Matrix getInitialMatrix() { return this.matrix; }
	
	/**
	 * 
	 * @return Tableau simplexe résultat.
	 */
	
	public Matrix getResultMatrix() { return this.result; }
	
	
	/**
	 * <p>Affecte le nombre de variables et le nombre de contraintes, tout en créant une
	 * matrice de dimension (1+constraintsNumber) x (2+variablesNumber+constraintsNumber).
	 * La matrice ainsi créer aura des valeurs nulles.
	 * 
	 * @param variablesNumber : Nomnbre de variables.
	 * @param constraintsNumber : Nombre de contraintes.
	 * @see #getVaraiblesNumber()
	 * @see #getConstraintsNumber() 
	 */
	public void setNumbers(int variablesNumber,int constraintsNumber){
		
		if(variablesNumber< 1 || constraintsNumber< 1){
			this.variablesNumber = 0;
			this.constraintsNumber = 0;
			this.matrix = new Matrix();			
		}else{
			
			this.variablesNumber = variablesNumber;
			this.constraintsNumber = constraintsNumber;
			this.matrix = new Matrix((1+constraintsNumber),(2+variablesNumber+constraintsNumber));			
		}		
		this.result = new Matrix();
		this.pivot = new Element(-1,-1,"Pivot");
		this.incomingVariable = new Element(-1,-1);
		this.comingoutVariable = new Element(-1,-1);
	}
	
	
	/**
	 * Affecte un numéro d'itération à cette instance.
	 * @param iterationNumber : Numéro d'itération.
	 */
	public void setIterationNumber(int iterationNumber){
		this.iterationNumber = iterationNumber ;
	}
	
	/**
	 * @return Numéro de l'itération de l'instance.
	 */
	public int getIterationNumber(){
		return this.iterationNumber;
	}
	
	/**
	 * @return true si il existe un cycle dans le graphe.
	 */
	
	public boolean isInfiniteLoop() {
		return this.cycle;
	}
	
	/**
	 * @return true si la fonction objective est une fonction illimitée.
	 */
	
	public boolean isUnlimitedFunction() {
		if(pivot==null) return false;
		if(pivot.getColumn()==-1) return false;
		int i; 
		for(i=1;i<this.matrix.getRowCount();i++)
			if(matrix.getValueAt(i, pivot.getColumn())>0) return false;		
		return true;
	}
	
	/**
	 * 	
	 * @param m : Matrice dont on veut connaître l'optimalité.
	 * @return vrai si la matrice m est une solution optimale.
	 */
	private boolean isOptimal(Matrix m) {
		
		if(m==null || m.isEmpty()) return false;		
		int j;
		for(j=1;j<m.getColumnCount();j++)
			if(m.getValueAt(0,j)<0) return false;
		return true;
	}
	
	
	/**
	 * 
	 * @return true si la matrice solution est optimale.
	 */
	public boolean isOptimalSolution(){
		return this.isOptimal(this.result);
	}
	
	/**
	 * 
	 * @return vrai si la matrice initiale est optimale.
	 */
	public boolean isOptimalMatrix() {
		return this.isOptimal(this.matrix);
	}
	
	/**
	 * 
	 * @return Elément pivot.
	 */
	public Element getPivot() { return this.pivot; }
	
		
	/**
	 * 
	 *  Selection de la variable de base Entrante.
	 *     
	 **/
	
	private void  selectIncomingBasisVariable(){
		int j;
		for(j=1;j<this.matrix.getColumnCount();j++){
			if(this.matrix.getValueAt(0,j)<0){
				if(pivot.getColumn()==-1){
					pivot.setColumn(j);
				}else{					
					if(Math.abs(this.matrix.getValueAt(0,j))>Math.abs(this.matrix.getValueAt(0, pivot.getColumn()))){
						pivot.setColumn(j);
					}
				}
			}
		}
	}
	
	
	/**
	 *
	 *  Selection de la variable de base sortante.
	 *   
	 **/
	
	private void selectComingoutBasisVariable() {		
		
		if(pivot.getColumn()!=-1) {
			
			int i,count=0;			
			for(i=1;i<matrix.getRowCount();i++){
			
				if(this.matrix.getValueAt(i, pivot.getColumn())>0){
				
					if(pivot.getRow()==-1) { pivot.setRow(i); count = 0; }
					else{
						
						Matrix r1 = new Matrix(1,matrix.getColumnCount());
						r1.copyRowValues(matrix.getRow(pivot.getRow()), 0);
						r1.multiplyRow(0, (double)1/matrix.getValueAt(pivot.getRow(), pivot.getColumn()));
						
						Matrix r2 = new Matrix(1,matrix.getColumnCount());
						r2.copyRowValues(matrix.getRow(i), 0);
						r2.multiplyRow(0, (double)1/matrix.getValueAt(i, pivot.getColumn()));
						
						int compare = r1.compareRowValues(0, r2.getRow(0));
						
						if(compare==0) count++;
						else if(compare>0) pivot.setRow(i);
					}
				}
			}			
			if(count>0)	{ this.cycle = true; pivot.setRow(-1); pivot.setColumn(-1); }
			else if(pivot.getRow()!=-1) pivot.setValue(matrix.getValueAt(pivot.getRow(), pivot.getColumn()));
		}		
	}
	
	
	/**
	 *Calcul la soluion realisable.
	 **/
	
	private void computeRealizableBasisSolution() {
		
		if(pivot.getRow()!=-1 && pivot.getColumn()!=-1){
			int i ;
			
			/*Initialise la matrice résultat*/
			result.setDimension(matrix.getRowCount(),matrix.getColumnCount());
			result.copy(matrix);			
			
			/*Nouvelle Ligne pivot*/
			result.multiplyRow(pivot.getRow(),(double) 1/pivot.getValue());
			
			/*Calcul des autres lignes*/
			for(i=0;i<result.getRowCount();i++){				
				
				if(i==pivot.getRow()) continue;
				
				Matrix row = new Matrix(1,result.getColumnCount());
				row.copyRowValues(result.getRow(pivot.getRow()),0);				
				row.multiplyRow(0,(-1)*result.getValueAt(i,pivot.getColumn()));
				result.addRow(i, row.getRow(0));
			}
			
			/* Initialisation des variables de base entrante et sortante. */
		    incomingVariable.setElement(result.getElementAt(0, pivot.getColumn()));
		    comingoutVariable.setElement(result.getElementAt(pivot.getRow(), result.getColumnCount()-1));
		    result.getElementAt(pivot.getRow(), result.getColumnCount()-1).setName(result.getElementAt(0,pivot.getColumn()).getName());
		    
		}
	}	
	
	/**
	 * Applique l'algorithme du simplexe.
	 */
	public void applySimplexe() {
		
		pivot.setElement(null);
		pivot.setName("Pivot");		
		incomingVariable.setElement(null);
		comingoutVariable.setElement(null);		
		this.cycle = false ;
		result.clear();
		
		if(!this.isOptimalMatrix()){
			this.selectIncomingBasisVariable();
			if(!this.isUnlimitedFunction()){
				this.selectComingoutBasisVariable();
				if(!this.isInfiniteLoop()){
					this.computeRealizableBasisSolution();
				}
			}
		}
	}
		
	public String toString() {
		String valeur = "\nIteration "+this.getIterationNumber()+"\n\n";
		int i,j;
		DecimalFormat nombre = new DecimalFormat("0.00");
		valeur+="-- Probléme de Programmation Linéaire à résoudre\n\n";		
		if(!this.matrix.isEmpty()){
			matrix.displayLastColumn(false);			
			valeur+= "   Max "+matrix.getElementAt(0,matrix.getColumnCount()-1).getName()+" = ";
			for(j=1;j<matrix.getColumnCount()-1;j++){
				
				if(matrix.getValueAt(0,j)==0) continue;
				if(j!=1) valeur+=" + " ;
				valeur+= "("+nombre.format((-1)*matrix.getValueAt(0,j))+")"+matrix.getElementAt(0,j).getName();				
				
			}
			valeur+=" \n" ;
			valeur+="   Sujet à : \n";
			for(i=1;i<matrix.getRowCount();i++){
				
				valeur+="      ";
				for(j=1;j<matrix.getColumnCount()-1;j++){
					
					if(matrix.getValueAt(0,j)==0) continue;					
					if(j!=1) valeur+=" + " ;
					valeur+= "("+nombre.format(matrix.getValueAt(i,j))+")"+matrix.getElementAt(0,j).getName();					
					
				}
				valeur+=" <= "+matrix.getValueAt(i,0)+" \n" ;
			}
			
			valeur+="         ";
			for(j=1;j<=this.variablesNumber;j++){
				valeur+=matrix.getElementAt(0,j).getName();
				if(j!=this.variablesNumber) valeur+=", ";
				else valeur+=" >= 0 \n\n";
			}
			
			valeur+="-- Tableau simplex Initiale.\n\n" ;
			valeur+= matrix+"\n\n";
			
		}else valeur+="  [Tableau  Simplexe Initial Vide]\n\n";
		
		
		valeur+="-- Tableau Simplexe Solution.\n\n";
		if(!this.result.isEmpty()){		
			result.displayLastColumn(false);
			valeur+=result+"\n\n";
			valeur+="-- Résumé Itération "+this.iterationNumber+".\n\n";
			valeur+="   Solution de base réalisable : " ;
			valeur+="\n";
			for(i=1;i<result.getRowCount();i++){
				valeur+="      "+result.getElementAt(i, result.getColumnCount()-1)+"\n";
				//if(i!=result.getRowCount()-1) valeur+=",";
				 
			}
			for(j=1;j<result.getColumnCount()-1;j++){
				if(result.getValueAt(0,j)==0) continue ;
				valeur+="      "+result.getElementAt(0,j).getName()+" = 0\n";
				//valeur+=", "+result.getElementAt(0,j).getName()+" = 0\n";
			}
			valeur+="\n";
			valeur+="   Valeur Fonction Objective : "+result.getElementAt(0, result.getColumnCount()-1)+"\n";
			
			if(pivot.getRow()!=-1&& pivot.getColumn()!=-1){
				valeur+="   Ligne Pivot = "+(pivot.getRow()+1)+", Colonne Pivot = "+(pivot.getColumn()+1)+", "+pivot+"\n";
			}
			
			if(this.incomingVariable.getRow()!=-1 && this.incomingVariable.getColumn()!=-1){
				valeur+="   Variable de base Entrante : "+incomingVariable.getName()+"\n";
			}
			
			if(this.comingoutVariable.getRow()!=-1 && this.comingoutVariable.getColumn()!=-1){
				valeur+="   Variable de base Sortante : "+comingoutVariable.getName()+"\n";
			}
			
			if(this.isOptimalSolution()) valeur+="   Solution de base Optimale\n";
			else valeur+="   Solution de base non Optimale\n";
			
			if(this.isInfiniteLoop()) valeur+="   Une boucle infinie a été détecté.\n";
			if(this.isUnlimitedFunction()) valeur+="   La Fonction Objective est une fonction illimitée.\n";
			
		}else valeur+="  [Tableau  Simplexe Résultat Vide]\n";
		return valeur+="\n";
	}
	
	public static void main(String arg[]){
		
		/* Un tableau simplexe initial*/
		
		Matrix m = new Matrix(4,7);
		m.setElementAt(0,0,new Element(0,0,0,"S"));		
		m.setElementAt(0,1,new Element(0,1,-3,"x1"));
		m.setElementAt(0,2,new Element(0,2,-5,"x2"));
		m.setElementAt(0,3,new Element(0,3,0,"x3"));
		m.setElementAt(0,4,new Element(0,4,0,"x4"));
		m.setElementAt(0,5,new Element(0,5,0,"x5"));
		m.setElementAt(0, 6, new Element(0,6,0,"Z"));
		m.setElementAt(1,0,new Element(1,0,4,"b1"));
		m.setElementAt(1,1,new Element(1,1,1,"a1"));
		m.setElementAt(1,2,new Element(1,2,0,"a2"));
		m.setElementAt(1,3,new Element(1,3,1,"a3"));
		m.setElementAt(1,4,new Element(1,4,0,"a4"));
		m.setElementAt(1,5,new Element(1,5,0,"a5"));
		m.setElementAt(1, 6, new Element(1,6,4,"x3"));
		m.setElementAt(2,0,new Element(2,0,12,"b2"));
		m.setElementAt(2,1,new Element(2,1,0,"a6"));
		m.setElementAt(2,2,new Element(2,2,2,"a7"));
		m.setElementAt(2,3,new Element(2,3,0,"a8"));
		m.setElementAt(2,4,new Element(2,4,1,"a9"));
		m.setElementAt(2,5,new Element(2,5,0,"a10"));
		m.setElementAt(2, 6, new Element(2,6,12,"x4"));
		m.setElementAt(3,0,new Element(3,0,18,"b3"));
		m.setElementAt(3,1,new Element(3,1,3,"a11"));
		m.setElementAt(3,2,new Element(3,2,2,"a12"));
		m.setElementAt(3,3,new Element(3,3,0,"a13"));
		m.setElementAt(3,4,new Element(3,4,0,"a14"));
		m.setElementAt(3,5,new Element(3,5,1,"a15"));
		m.setElementAt(3, 6, new Element(3,6,18,"x5"));
		
		SimplexeIteration s = new SimplexeIteration(m,2,3);
				
		s.setIterationNumber(1);
		
		//System.out.println("-- SimplexeIteration --");
		System.out.println(s);
		
		s.applySimplexe();
		System.out.println(s);
	}
}
