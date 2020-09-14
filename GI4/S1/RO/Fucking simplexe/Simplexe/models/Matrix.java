package models;

import java.text.DecimalFormat;
import java.util.*;

public class Matrix {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Repésente le nombre de ligne de la matrice.
	 */
	private int row;
	
	/**
	 * Représente le nombre de colone de la matrice
	 * 
	 */
	private int column;
	
	/**
	 * Représente le contenu de la matrice.
	 */
	private ArrayList<ArrayList<Element>> table ;
	
	/**
	 * Booléen autorisant l'affichage ou non de la derniére colonne.
	 */
	private boolean displayLastColumn = true ;
	
	/**
	 * Construit une instance vide de la matrice.
	 */
	public Matrix() {
		this(0,0);
	}
	
	/**
	 *<p>Construit une matrice avec row ligne(s) et column colonne(s)
	 *si row ou colonne sont des valeurs négatives il retourne
	 *une matrice nulle.
	 *  
	 * @param row : nombre de ligne.
	 * @param column : nombre de colonne.
	 * @see #Matrix()
	 */
	public Matrix(int row,int column) {
		if(row<1 || column<1){
			this.row = 0;
			this.column = 0;			
		}else{
			this.row = row;
			this.column = column;			
		}
		this.createMatrixContent();
	}
	
	/**
	 * <p>Construit une instance de matrice a partir d'une autre. 
	 * @param matrix : matrice utilisée pour initialiser l'instance
	 * 				   courante.
	 */
	public Matrix(Matrix matrix){
		
		if(matrix==null || matrix.isEmpty()){
			clear();
		}else this.copy(matrix);
	}
	
	/**
	 * 
	 * @return Vrai si la matrice courante est vide, dans le cas contraire faux.
	 */
	public boolean isEmpty() {
		return (this.row==0) || (this.column==0) || (this.table.isEmpty()) ;
	}
	
	/**
	 * <p>Recrée la matrice, avec les dimensions row xcolumn, et affecte à ses élements
	 * la valeur de 0.
	 *  
	 * @param row : Nombre de ligne.
	 * @param column : Nombre de colonne.
	 */
	public void setDimension(int row,int column){
		
		this.row = (row<1)? 0:row;
		
		if((this.row==0)){
			this.column = 0 ;
			if(!table.isEmpty()) table.clear();
		}else{
		
			this.column =  (column<1)? 0:column;
			if(this.column==0){				
				this.row = 0;
				if(!table.isEmpty())table.clear();
			}else{
				this.createMatrixContent();
			}
		}
		
		
	}
	
	/**
	 *<p> Affecte row comme nombre de ligne a la matrice courante.
	 *Pour cela la matrice est réinitialisée à nulle en conservant le 
	 *même nombre de colonne.Si row est négatif le nombre de colonne 
	 *est reinitialisé à zéro de même que le nombre de ligne.
	 *  
	 *<p>  Lorsque vous utilisez cette fonction, veuillez utiliser 
	 *createMatrixContent()pour créer le contenu de la matrice. Si le 
	 *nombre de colonne n'est pas définit, définisse le avant d'appeler 
	 *createMatrixContent(). Ceci afin d'éviter d'événtuel bug.
	 *
	 *<p>   Préférez l'utilisation de setDimension.
	 *
	 * 
	 * @param row : Nombre de ligne.
	 * @see #setDimension(int, int)
	 * @see #setColumnCount(int)
	 * @see #createMatrixContent()
	 * @see #getRowCount()
	 */
	
	public void setRowCount(int row){
		
		this.row = (row<1)? 0:row;
		
		if (this.row==0){
			this.column = 0;
			this.table.clear() ;
		} 
		
		//if(this.column!=0)this.createMatrixContent();		
	}
	
	/**
	 * 
	 * @return Nombre de ligne de la matrice.
	 * @see #setRowCount(int)
	 */
	
	public int getRowCount() { return this.row; }
	
	/**
	 *<p>Affecte column comme nombre de colonne de la matrice courante.
	 *Cette opération réinitialise la matrice à vide en conservant le même nombre
	 *de ligne. Si le nombre de colonne est négatif, le nombre de ligne est
	 *reinitilisé à zéro, de même que le nombre de colonne.
	 *
	 *<p>Lorsque vous utilisez cette fonction, veuillez utiliser createMatrixContent()
	 *pour créer le contenu de la matrice. Si le nombre de ligne n'est pas
	 *définit, définisse le avant d'appeler createMatrixContent(). Ceci afin 
	 *d'éviter d'événtuel bug.
	 *
	 *<p>Préférez l'utilisation de setDimension.
	 *
	 * 
	 * @param column : Nombre de colonne.
	 * @see #setDimension(int, int)
	 * @see #setRowCount(int)
	 * @see #createMatrixContent()
	 * @see #getColumnCount()
	 */
	
	public void setColumnCount(int column) {
		
		this.column = (column<1)? 0:column;
		
		if (this.column==0){
			this.row = 0;
			this.table.clear();
		}
		
		//if(this.row!=0) this.createMatrixContent();		
	}
	
	/**
	 * 
	 * @return Nombre de colonne de la matrice.
	 * @see #setColumnCount(int)
	 */
	public int getColumnCount() { return this.column; }
	
	/**
	 * <p>Affecte value comme valeur de l'élément se trouvant à la ligne 
	 *row et à la colonne colum.
	 * 
	 * @param row : index de la ligne.
	 * @param column : index de la colonne.
	 * @param value : valeur à affecter.
	 * @see #getValueAt(int, int)
	 */
	public void setValueAt(int row,int column,double value){
		
			this.table.get(row).get(column).setValue(value) ;
	}
		
	
	/**
	 * @param row : Index de la ligne.
	 * @param column : Index de la colonne.
	 * @return Valeur de la cellule se trouvant aux index ci-dessus.
	 * @see #setValueAt(int, int, double)
	 */
	public double getValueAt(int row,int column) {
		return this.table.get(row).get(column).getValue();
	}
	
	
	/**
	 * <p>Initialise les valeurs de l'élément se trouvant à la ligne row et à 
	 *la colonne column, avec les valeurs de l'élément e.
	 * 
	 * @param row : Index de la ligne. 
	 * @param column : Index de la colonne.
	 * @param e : Element dont les valeurs vont être utilisées pour l'initialisation.
	 * @see #getElementAt(int, int)
	 */
	public void setElementAt(int row,int column,Element e){
		this.table.get(row).get(column).setElement(e);	
	}
	
	/**
	 * @param row : Index de la ligne.
	 * @param column : Index de la column.
	 * @return Elément se trouvant à la ligne row et à la colonne column.
	 */
	public Element getElementAt(int row,int column) {
		return this.table.get(row).get(column);
	}
	
	
	/**
	 * Permet ou non la visualisation de la derniére colonne, lorsque l'on utilise la
	 * fonction toString.
	 * @param display : Booléen d'autorisation d'affichage.
	 * @see #toString()
	 */
	public void displayLastColumn(boolean display) {
		this.displayLastColumn = display ;
	}
	
	/**
	 * Raméne la matrice courante dans l'état vide, en effaçant ses données.   
	 */
	public void clear() {
		this.row = 0 ;
		this.column = 0 ;
		this.table.clear(); 
	}
		
	
	/**
	 * <p>Copie le contenu de la matrix de la ligne rowB à la ligne rowE,
	 *et de la colonne columnB à la colonne columnE.
	 *La matrice courante est réinitialisé dans les cas suivants :
	 *<ul>
	 *	<li>matrix est une matrice vide.</li>
	 * 	<li> rowB inférieur à 0. </li> 	 
	 * 	<li> rowE inférieur à 0. </li>
	 *  <li> rowE > matrix.getRow()-1 </li> 
	 * 	<li> rowB > rowE </li>
	 * 	<li> columnBinférieur à 0</li>
	 * 	<li> columnE inférieur à 0</li>
	 * 	<li> columnE > matrix.getColumn()-1 </li>
	 * 	<li> columnB > columnE</li> 	
	 *</ul>	 
	 *
	 *<p><strong>NB</strong> : les index vont de zéro à matrix.getRow()-1
	 *(Resp. matrix.getColumn()-1)pour les index de ligne (Resp colonne). </pre>
	 * 
	 * @param matrix : Matrice à copier.
	 * @param rowB : Index de la ligne de début de copie.
	 * @param rowE : Index de la ligne de fin de copie.
	 * @param columnB : Index de la colonne de début de copie. 
	 * @param columnE : Index de la colonne de fin de copie.
	 */
	public void copy(Matrix matrix,int rowB,int rowE,int columnB,int columnE){
		boolean reinit = (matrix==null || matrix.isEmpty()) || (rowB<0 || rowE<0 || rowB>rowE) || (columnB<0 || columnE<0 || columnB>columnE);
		reinit = reinit || rowE > matrix.getRowCount()-1 || columnE > matrix.getColumnCount()-1 ;
		
		this.clear();
		if(!reinit) {
			int i,j;
			
			this.row = matrix.getRowCount();
			this.column = matrix.getColumnCount();
			
			this.createMatrixContent();		
			
			for(i=rowB;i<=rowE;i++)for(j=columnB;j<=columnE;j++)//{
			  //this.setValueAt(i, j, matrix.getValueAt(i, j));
				this.setElementAt(i, j, matrix.getElementAt(i, j));
			//}
			
		}
	}
	
	
	/**
	 * <p> Copie matrix de la ligne(Resp colonne) 0 à ( matrix.getRow()-1 ) [ Resp (matrix.getColumn()-1) ] dans la matrice courante.
	 * 
	 * @param matrix : Matrice à copier. 
	 * @see #copy(Matrix, int, int, int, int)
	 */
	public void copy(Matrix matrix){
		if(matrix==null) this.clear();
		else this.copy(matrix, 0, matrix.getRowCount()-1, 0, matrix.getColumnCount()-1);
	}
	
	/**
	 * <p>Copie les valeurs de la ligne row à la ligne d'index indexRow de la matrice.
	 * Le vecteur row à copier doit avoir le même nombre de colonne que la matrice.
	 * @param row : Ligne à copier.
	 * @param indexRow : Index de la ligne où les informations seront copiées.
	 */
	
	public void copyRowValues(ArrayList<Element> row,int indexRow) {
		
		if(row!=null && row.size()== this.column){
			int j;		
			for(j=0;j<this.column;j++){
				this.setValueAt(indexRow,j,row.get(j).getValue());
			}
		}
		
	}
	
	/**
	 * <p>Copie les valeurs de la colonne column à la colonne d'index indexColumn de la 
	 * matrice. Le vecteur column à copier doit avoir le même nombre de ligne que la 
	 * matrice.
	 * @param column : Colonne à copier.
	 * @param indexColumn : Index de la colonne où les informations seront copiées.
	 */
	
	public void copyColumnValues(ArrayList<Element> column,int indexColumn) {
		
		if(column!=null && column.size()== this.row){
			int i;		
			for(i=0;i<this.row;i++){
				this.setValueAt(i,indexColumn,column.get(i).getValue());
			}
		}
		
	}
	
	/**
	 * Créer le contenu de la matrice. Les valeurs sont affectées à zéros.
	 */
	
	public void createMatrixContent() {		
		int i,j;
		this.table = new ArrayList<ArrayList<Element>>();		
		for(i=0;i<this.row;i++){
			ArrayList<Element> ligne = new ArrayList<Element>(this.column);
			for(j=0;j<this.column;j++){
				ligne.add(new Element(i,j));
			}
			this.table.add(ligne);
		}		
	}
	
	
	
	/**
	 * <p>Initialise le contenu de la matrice,allant de la ligne rowB
	 * (resp colonne columnB) à la ligne rowE(resp colonne columnE) avec value comme
	 * valeur des éléments.
	 *  
	 * <p><Strong> Attention a vos index !!! </Strong>
	 *  
	 * @param value : Valeur à affecter à la plage d'éléments.
	 * @param rowB  : Index de la ligne de début.
	 * @param rowE  : Index de la ligne de fin. 
	 * @param columnB : Index de la colonne de début.
	 * @param columnE : Index de la colenne de fin.
	 */
	public void initWith(double value,int rowB,int rowE,int columnB,int columnE){
	
		/*boolean init = !this.isEmpty() && rowB >=0 && rowE< this.row && rowB<=rowE  ;
		 init = init && columnB >= 0 && columnE< this.column && columnB<=columnE ;
		 
		 if(init){*/
			 int i,j;
			 for(i=rowB;i<=rowE;i++)for(j=columnB;j<=columnE;j++)
				  this.setValueAt(i, j, value);
		 //}
	}
	
	
	/**
	 * <p>Initialise le contenu de la matrice,allant de la ligne rowB
	 * (resp colonne columnB) à la ligne rowE(resp colonne columnE) avec les valeurs
	 *  de e comme valeurs des éléments.	 
	 *
	 * <p><strong>Attention a vos index !!! </strong>
	 *  
	 * @param e     : Eléments initialiseur.
	 * @param rowB  : Index de la ligne de début.
	 * @param rowE  : Index de la ligne de fin. 
	 * @param columnB : Index de la colonne de début.
	 * @param columnE : Index de la colenne de fin.
	 */
	public void initWith(Element e,int rowB,int rowE,int columnB,int columnE){
		
		/*boolean init = !this.isEmpty() && rowB >=0 && rowE< this.row && rowB<=rowE  ;
		 init = init && columnB >= 0 && columnE< this.column && columnB<=columnE ;
		 
		 if(init){*/
			 int i,j;
			 for(i=rowB;i<=rowE;i++)for(j=columnB;j<=columnE;j++)
				  this.setElementAt(i, j,e);
		 //}		
	}
	
	
	
	
	
	/**
	 * <p> Affecte les valeurs de la ligne row à la ligne d'index indexRow.
	 * La ligne doit avoir le même nombre de colonne que celle de la matrice.
	 * 
	 * <p> <strong>NB:</strong> seul la valeur des éléments est utilisée, les autres valeurs ne sont pas
	 * affectées.
	 * 
	 * @param row : Vecteurs contenant les valeurs à affecter.
	 * @param indexRow : Index de la ligne à substituer.
	 */
	public void setRowValues(ArrayList<Element> row,int indexRow){
		
		if(row!=null && row.size()== this.column){
			int j ;			
			for(j=0;j<this.column;j++) 
				setValueAt(indexRow, j, row.get(j).getValue());
		}
		
	}
	
	/**
	 * @param indexRow : Index de la ligne à retourner.
	 * @return : Ligne d'index indexRow.
	 */
	public ArrayList <Element> getRow(int indexRow) {
		if(indexRow<this.row && indexRow>=0) return table.get(indexRow);
		else return null;
	}
	
	/**
	 * <p>Affecte les valeurs de la colonne column à la colonne d'index indexColumn.
	 * La colonne column doit avoir le même nombre de ligne que la 
	 * matrice courante.
	 * 
	 * <p><strong>NB:</strong> seul la valeur des éléments est utilisée, les autres valeurs ne sont pas
	 * affectées.
	 * 
	 * @param column : Nouvelle colonne.
	 * @param indexColumn : Index de la colonne à substituer.
	 */
	public void setColumnValues(ArrayList<Element> column,int indexColumn){
		
		if(column!=null && column.size()== this.row){
			int i ;			
			for(i=0;i<this.row;i++) 
				setValueAt(i, indexColumn, column.get(i).getValue());
		}		
	}
	
	/**
	 * @param indexColumn : Index de la colonne à retourner.
	 * @return : Colonne d'index indexColumn.
	 */
	
	public ArrayList<Element> getColumn(int indexColumn) {
		if(indexColumn<this.column && indexColumn>=0){
			ArrayList<Element> colonne = new ArrayList<Element>();
			int i ;
			for(i=0;i<this.row;i++)
				colonne.add(this.getElementAt(i, indexColumn));
			return colonne;
		}else return null;
	}
	
	/**
	 * Incrémente de value les éléments de la ligne d'index indexRow.
	 * 
	 * @param indexRow : Index de la ligne à additionner.
	 * @param value : Valeur de l'incrément.
	 */
	
	public void addRow(int indexRow,double value){
	
		int j;
		for(j=0;j<this.column;j++)
			setValueAt(indexRow, j,getValueAt(indexRow, j)+value);		
			
	}
	
	/**
	 * Ajoute aux éléments de la ligne d'index indexRow, la valeur de l'élément e. 
	 * 
	 * @param indexRow : Index d la ligne à additionner.
	 * @param e : Elément contenant la valeur de l'incrément.
	 */
	public void addRow(int indexRow,Element e){
		
		if(e!=null){
			int j;
			for(j=0;j<this.column;j++)
				setValueAt(indexRow, j,getValueAt(indexRow, j)+e.getValue());
		}
	}
	
	/**
	 *<p>Ajoute élément par élément, les valeurs des éléments de la ligne row, aux
	 * éléments de la la ligne d'index indexRow 
	 * 
	 *<p><strong> NB:</strong>
	 * <p>   - row doit avoir le même nombre de colonne que la matrice.
	 * <p>   - Ce sont les valeurs des éléments qui sont utilisées.
	 * 
	 * @param indexRow : Index de la ligne à additionner.
	 * @param row : Vecteur d'additon.
	 */
	public void addRow(int indexRow,ArrayList<Element> row) {
		
		if(row!=null && row.size()== this.column){
			int j;
			for(j=0;j<this.column;j++)
				this.setValueAt(indexRow, j, getValueAt(indexRow, j)+row.get(j).getValue());
		}
	}
	
	
	/**
	 * Ajoute aux éléments de la colonne d'index indexColumn, la valeur value.
	 * 
	 * @param indexColumn : Index de la colonne à additionner.
	 * @param value : Valeur de l'incrémment.
	 */
	public void addColumn(int indexColumn,double value){
	
		int i;
		for(i=0;i<this.row;i++)
			setValueAt(i,indexColumn ,getValueAt(i,indexColumn)+value);
	}	
	
	/**
	 * Ajoute aux éléments de la colonne d'index indexColumn, la valeur de l'élément
	 * e. 
	 * 
	 * @param indexColumn : Colonne à additionner.
	 * @param e : Elément contenant la valeur de l'incrément.
	 */
	public void addColumn(int indexColumn,Element e){
		
		if(e!=null){
			int i;
			for(i=0;i<this.row;i++)
				setValueAt(i,indexColumn ,getValueAt(i,indexColumn)+e.getValue());
		}
		
	}
	
	
	/**
	 * <p>Additionne élément par élément, la colonne d'index indexColumn avec la colonne
	 * column.
	 * 
	 * <p><strong>NB:</strong>
	 * <ul>
	 *  <li> column doit avoir le même nombre de ligne que la matrice. </li>
	 * 	<li> ce sont les valeurs des éléments qui sont utilisées. </li>
	 * </ul>
	 * 
	 * @param indexColumn : Index de la colonne à additionner
	 * @param column : Vecteur utilisée dans l'addition.
	 */
	public void addColumn(int indexColumn,ArrayList<Element> column) {
		
		if(column!=null && column.size()== this.row){
			int i;
			for(i=0;i<this.row;i++)
				this.setValueAt(i,indexColumn, getValueAt(i, indexColumn)+column.get(i).getValue());
		}
		
	}
	
	/**
	 * Multiplie les éléments de la ligne d'index indexRow par la valeur value.
	 *  
	 * @param indexRow : Index de la ligne à multiplier.
	 * @param value : Facteur de la multiplication.
	 */
	
	public void multiplyRow(int indexRow,double value){
		
		int j;
		for(j=0;j<this.column;j++)
			setValueAt(indexRow,j ,getValueAt(indexRow,j)*value);
		
	}
	
	/**
	 * Multiplie les éléments de la ligne d'index indexRow par la valeur de l'élément
	 * e.
	 * 
	 * @param indexRow : Index de la ligne.
	 * @param e : Elément dont la valeur sera utilisée pour la multiplication.
	 */
	
	public void multiplyRow(int indexRow,Element e){
		
		if(e!=null){
			int j;
			for(j=0;j<this.column;j++)
				setValueAt(indexRow,j ,getValueAt(indexRow,j)*e.getValue());
		}
		
	}
	
	/**
	 * Multiplie élément par élément, la ligne l d'index indexRow avec la ligne row.
	 *
	 *<p><strong> NB:</strong>
	 *<ul>
	 *  <li>row doit avoir le même nombre de colonne que la matrice.</li>
	 * 	<li> ce sont les valeurs des éléments qui sont utilisées.</li>
	 *</ul>   
	 * @param indexRow : Index de la ligne à multiplier
	 * @param row : Ligne contenant les facteurs dans ces éléments.
	 */
	
	
	public void multiplyRow(int indexRow,ArrayList<Element> row) {
		
		if(row!=null && row.size()== this.column){
			int j;
			for(j=0;j<this.column;j++)
				this.setValueAt(indexRow,j, getValueAt(indexRow,j)*row.get(j).getValue());
		}
	}
	
	
	/**
	 * Multiplie les valeurs de la colonne d'index indexColumn avec la valeur value.
	 * 
	 * @param indexColumn : index de la colonne.
	 * @param value : valeur utiliser pour la multiplication.
	 */
	
	public void multiplyColumn(int indexColumn,double value){
		
		int i;
		for(i=0;i<this.row;i++)
			setValueAt(i,indexColumn ,getValueAt(i,indexColumn)*value);
		
	}
	
	/**
	 * <p>Multiplie les valeurs de la colonne d'index indexColumn avec celle
	 * de l'élément e.
	 * 
	 * @param indexColumn : Index de la colonne.
	 * @param e : Elément contenant la valeur à utiliser.
	 */
	
	public void multiplyColumn(int indexColumn,Element e){
		
		if(e!=null){
			int i;
			for(i=0;i<this.row;i++)
				setValueAt(i,indexColumn ,getValueAt(i,indexColumn)*e.getValue());
		}
	}
	
	/**
	 * <p>Multiplie éléments par éléments la colonne c d'index indexColumn par la 
	 * colonne column.
	 *  
	 * <p><strong>NB:</strong>
	 * <ul>
	 *  <li>column doit avoir le même nombre de ligne que la matrice.</li>
	 * 	<li>Ce sont les valeurs des éléments qui sont utilisées.</li>
	 * </ul>
	 *  
	 * @param indexColumn :Index de la colonne
	 * @param column : Colonne donc les valeurs seront utilisées pour la multiplication
	 */
	
	public void multiplyColumn(int indexColumn,ArrayList<Element> column) {
		
		if(column!=null && column.size()== this.row){
			int i;
			for(i=0;i<this.row;i++)
				this.setValueAt(i,indexColumn, getValueAt(i, indexColumn)*column.get(i).getValue());
		}
	}
	
	
	/**
	 *<p>Compare la ligne r1 d'index indexRow, à la ligne row, selon le principe
	 * du minimunm lexicographique.
	 * 
	 *<p>si row < r1, alors le premier élément non null  de row - r1 est négatif 
	 * si au contraire le premier élément non null  de row - r1 est positif alors 
	 * row > r1. si tous les éléments de row-r1 sont égaux à zéro alors 
	 * r1 = row.
	 *  
	 *<p>Si row n'a pas le même nombre de colonne que la matrice la valeur -2 
	 * est retournée. 
	 * 
	 * @param indexRow : Index de la ligne.
	 * @param row : Ligne avec laquelle on effectue la comparaison. 
	 * @return -1 si r1 < row, 0 si r1 = row et 1 si r1 > row.
	 */
	
	public int compareRowValues(int indexRow,ArrayList<Element> row) {
		if(row==null || row.size()!=this.column) return -2;
		else {			
			int j;
			double val;
			for(j=0;j<this.column;j++){				
				if( (val=row.get(j).getValue()- getValueAt(indexRow,j))!=0){
					 return (val < 0) ? 1:-1 ;
				}
			}			
			return 0;
		}
	}
	
	/**
	 *<p>Compare la colonne c1 d'index indexColumn, à la colonne column, selon le principe
	 * du minimunm lexicographique.
	 * 
	 *<p> si c1 < column, alors le premier élément non null  de column - c1 est négatif 
	 * si au contraire le premier élément non null  de column - c1 est positif alors 
	 * c1 > column. si tous les éléments de column-c1 sont égaux à zéro alors 
	 * c1 = column.
	 * 
	 * <p>Si column n'a pas le même nombre de ligne que la matrice la valeur -2 
	 * est retournée.
	 * 
	 * @param indexColumn : Index de la colonne.
	 * @param column : Colonne avec laquelle on effectue la comparaison. 
	 * @return -1 si c1 < column, 0 si c1 = column et 1 si c1 > column.
	 */
	
	public int compareColumnValues(int indexColumn,ArrayList<Element> column) {
		if(column==null || column.size()!=this.row) return -2;
		else {			
			int i;
			double val;
			for(i=0;i<this.row;i++){				
				if( (val=column.get(i).getValue()- getValueAt(i,indexColumn))!=0){
					 return (val < 0) ? 1:-1 ;
				}
			}			
			return 0;
		}
	}
	
	public String toString(){
			
		if(this.isEmpty()) return  "[ Matrix vide ]";
		
		int i,j,count;		
		DecimalFormat nombre = new DecimalFormat("0.00");
		String valeur = "" ;		
		count = (this.displayLastColumn) ? this.column : (this.column-1);		
		for(i=0;i<this.row;i++){
			for(j=0;j<count;j++) valeur+= " "+nombre.format(this.getValueAt(i, j))+" ";
			valeur+="\n";
		}
		
		return valeur;
	}
	
	public static void main(String[] args){
	
		Matrix m = new Matrix(4,6);
		m.setValueAt(0,0,0);
		m.setValueAt(0,1,-3);
		m.setValueAt(0,2,-5);
		m.setValueAt(0,3,0);
		m.setValueAt(0,4,0);
		m.setValueAt(0,5,0);
		m.setValueAt(1,0,4);
		m.setValueAt(1,1,1);
		m.setValueAt(1,2,0);
		m.setValueAt(1,3,1);
		m.setValueAt(1,4,0);
		m.setValueAt(1,5,0);
		m.setValueAt(2,0,12);
		m.setValueAt(2,1,0);
		m.setValueAt(2,2,2);
		m.setValueAt(2,3,0);
		m.setValueAt(2,4,1);
		m.setValueAt(2,5,0);
		m.setValueAt(3,0,12);
		m.setValueAt(3,1,3);
		m.setValueAt(3,2,2);
		m.setValueAt(3,3,0);
		m.setValueAt(3,4,0);
		m.setValueAt(3,5,1);
		System.out.println("     -- Matrice m --     ");
		System.out.println(m.toString());
		System.out.println("nbre ligne = "+m.getRowCount()+"; nbre colonne ="+m.getColumnCount());
		
		Matrix a = new Matrix(1,6);
		a.copyRowValues(m.getRow(2), 0);
		System.out.println("   -- Matrice a --   ");
		System.out.println(a.toString());
		
		Matrix b = new Matrix(1,6);
		b.copyRowValues(m.getRow(3), 0);
		System.out.println("   -- Matrice b --   ");
		System.out.println(b.toString());
		
		a.multiplyRow(0,(double)1/a.getValueAt(0, 2));
		b.multiplyRow(0, (double)1/b.getValueAt(0, 2));
		System.out.println("   -- Matrice a et b --   ");
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		int compare = a.compareRowValues(0, b.getRow(0));
		
		if(compare<0) System.out.println("   -- a < b --   ");
		else if(compare==0) System.out.println("   -- a == b --   ");
			else System.out.println("   -- a > b --   ");
		/*System.out.println("   -- Matrice a init avec m --   ");
		a.copy(m);
		System.out.println(a.toString());
		
		System.out.println("   -- Matrice b --   ");
		Matrix b = new Matrix();
		b.setCountRow(4);
		b.setCountColumn(6);
		b.createMatrixContent();
		b.initWith(3,0,3,0,5);
		System.out.println(b.toString());
		
		System.out.println("   -- Ligne zéro de a + ligne zéro de b --   ");
		//b.multiplyRow(0,(double)1/3);
		//a.multiplyRow(0, b.getRow(0));
		a.addRow(0, b.getRow(0));
		System.out.println(a.toString());
		int result = a.compareRowValues(0, b.getRow(0));
		System.out.println("   -- resultat comparaison ligne 0 de a et 0 de b = "+result+" --   ");
		
		
		System.out.println("   -- Matrice b --   ");
		b.setRowValues(a.getRow(3), 1);
		b.setColumnValues(a.getColumn(2), 5);
		System.out.println(b.toString());
		
		b.clear();
		System.out.println("   -- b apres effacement --   \n"+b.toString());		
		System.out.println(" infos sur de l'elt (0,0) de a est "+a.getElementAt(0,0)+" celui de m en(2,0) "+m.getElementAt(2,0));*/
		
	}
	
}
