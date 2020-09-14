package models;

import java.util.*;

public class Simplexe {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Liste des it�rations de la m�thode du simplexe.
	 */
	private ArrayList<SimplexeIteration> iterations = null;
	
	/**
	 * Repr�sente l'index de l'it�ration courante.
	 */
	private int current = -1 ;
	
	/**
	 * true si une ex�cution � d�j� eut lieue.
	 */
	private boolean executed;
	
	/**
	 * Construit une instance de l'objet simplexe.
	 * 
	 * @param m : Matrix dont sur laquelle on va appliquer le simplexe.
	 * @param variablesNumber : Nombre de varaiables.
	 * @param constraintsNumber : Nombre de contraintes.
	 */	
	public Simplexe(Matrix m,int variablesNumber,int constraintsNumber) {		
		reinit(m,variablesNumber,constraintsNumber);
	}	
	
	/**
	 * Execute l'algorithme du simplexe.
	 */
	public void execute() {
		if(!executed){
			int current=0 ;
			while(!iterations.get(current).isOptimalMatrix()){
				iterations.get(current).setIterationNumber(current+1);
				iterations.get(current).applySimplexe();
				if(!iterations.get(current).isOptimalSolution()){
					SimplexeIteration iteration = new SimplexeIteration(iterations.get(current).getResultMatrix(),iterations.get(current).getVaraiblesNumber(),iterations.get(current).getConstraintsNumber());					
					iterations.add(iteration);					
					current++ ;
				}else break;
			}
			executed = true;
		}
		
	}
	
	/**
	 * R�initialise les donn�es.
	 * 
	 * @param m : Matrix sur laquelle on va appliquer le simplexe.
	 * @param variablesNumber : Nombre de varaiables.
	 * @param constraintsNumber : Nombre de contraintes.
	 */	
	public void reinit(Matrix m,int variablesNumber,int constraintsNumber) {
		
		if(iterations!=null){
			int i;
			for(i=iterations.size()-1;i>=0;i--) iterations.remove(i);
			iterations = null ;
		}
		
		iterations = new ArrayList<SimplexeIteration> ();
		SimplexeIteration iteration = new SimplexeIteration(m,variablesNumber,constraintsNumber);
		current = 0 ;
		iteration.setIterationNumber(current);
		iterations.add(iteration);		
		executed = false ;
	}
	
	/**
	 * 
	 * @return True si l'it�ration courante est la premi�re it�rations.
	 */
	public boolean isFirstIteration() {
		return current==0;
	}
	
	/**
	 * 
	 * @return True si l'it�ration courante est la derni�re it�rations.
	 */
	public boolean isLastIteration() {
		return current==(this.iterations.size()-1);
	}
	
	
	/**
	 * Affecte l'it�ration current comme it�ration courante.
	 * @param current
	 */
	public void setCurrent(int current){
		this.current = current ;
	}	
	
	/**
	 * 
	 * @return It�ration courante.
	 */	
	public SimplexeIteration getCurrentIteration() {
		if(current!=-1) return this.iterations.get(current);
		return null;
	}
	
	/**
	 * 
	 * @return Nombre d'Iteration du Simplexe.
	 */
	public int getIterationCount() { return iterations.size(); }
	
	/**
	 * 
	 * @return Premi�re it�ration de l'application du simplexe, en se positionnat 
	 * 			sur cette derni�re.
	 */
	public SimplexeIteration firstIteration() {
		if(iterations.size()>0) {
			current = 0 ;
			return iterations.get(current);
		}
		current = -1 ;
		return null;
	}
	
	/**
	 * 
	 * @return It�ration suivante l'it�ration courante. Et d�finit l'it�ration
	 * retourn�e comme l'it�ration courante. 
	 */
	public SimplexeIteration nextIteration() {
		if(current!=-1){
			if(current<(iterations.size()-1)) current++;
			return iterations.get(current);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @return It�ration pr�c�dent l'it�ration courante. Et d�finit l'it�ration
	 * retourn�e comme l'it�ration courante. 
	 */
	public SimplexeIteration previousIteration(){		
		if(current!=-1){
			if(current>0) current--;
			return iterations.get(current);
		}
		return null;
	}
	
	/**
	 * 
	 * @return Derni�re it�ration de l'application du Simplexe, en se positionnant
	 *sur cette derni�re.
	 */
	public SimplexeIteration lastIteration() {
		if(iterations.size()>0) {
			current = iterations.size()-1 ;
			return iterations.get(current);
		}
		current = -1 ;
		return null;
	}
	
	/**
	 * 
	 * @param index : Index de l'it�ration dont on veut la trace d'ex�cution.
	 * @return Trace d'ex�cution de l'it�ration 0 � l'it�ration index.
	 */
	
	public String traceExecution(int index){
		return traceExecution(index,index);
	}
	
	/**
	 * 
	 * @param indexB : Index de la premi�re It�ration.
	 * @param indexE : Index de la derni�re It�ration.
	 * @return Trace d'ex�cution des it�rations allant de indexB � indexE.
	 */
	
	public String  traceExecution(int indexB,int indexE){
		String value = "\n                        --- Execution de la m�thode du simplexe ---                        \n\n";
		int i;
		for(i=indexB;i<=indexE;i++){
			value+=iterations.get(i);
		}
		return value;	
	}
	
	
	
	public String toString(){
		return this.traceExecution(0,iterations.size()-1);
	}
	
	public static void main(String arg[]){
		
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
		
		Simplexe s = new Simplexe(m,2,3);		
		s.execute();
		System.out.println(s);
	}
}
