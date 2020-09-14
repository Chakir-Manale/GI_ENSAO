package ma.ensao.calculateur;

/**
 * 
 * @author M. ETTIFOURI El Hassane
 * 
 */
public class Calculatrice {

	private String ecran;
	private String operantA;
	private String operantB;
	private char operantion;
	private char touch;
	public Calculatrice()
	{
		initialize();
	}
	
	public void initialize()
	{
		this.ecran = "";
		this.operantA = "0";
		this.operantB = "0";
		this.operantion = '.';
	}

	public void taperTouche(char touche)
	{
		if(isOperateur(touche))
		{
			this.operantion = touche;
			this.ecran = ""+touche;
		}
		else if(touche == '=')
		{
			calculateResult();
		}
		else if(isChiffre(touche))
		{
			if(this.operantion == '.')
			{
				this.operantA += touche;
				
			}
			else
			{
				if(this.operantB.equals("0"))
				{
					this.ecran = "";
				}
				this.operantB += touche;
			}
			this.ecran += touche;
		}
		else 
		{
			this.touch=touche;
			this.ecran = "Erreur: Caracrtere '"+touche+"' non supporte !!";
		}
	}
	
	private boolean isChiffre(char touche) {
		
		return '0' <= touche && touche <= '9';
	}

	private void calculateResult() {
		
		if(this.ecran.equals("Erreur: Caracrtere '"+touch+"' non supporte !!")) {
			this.ecran="Erreur: Caracrtere '"+touch+"' non supporte !!";
		}
		else
		{
		int a = Integer.parseInt(this.operantA);
		int b = Integer.parseInt(this.operantB);
		int resultat = 0;
		switch (this.operantion)
		{
		   case '+': 
			   resultat = a+b;
			   this.ecran = ""+resultat;
			   break;
		   case '-':
			      resultat = a-b;
			      this.ecran = ""+resultat;
				break;
		   case '*':
			      resultat = a*b;
			      this.ecran = ""+resultat;
				break;
		   case '/':
				
			     if(b==0)
			     {
			    	 this.ecran = "Erreur: operation impossible !!";
			     }
			     else
			     {
			    	  resultat = a/b;
				      this.ecran = ""+resultat;
			     }
				break;
		   default:
			    this.ecran = "Erreur !!";
			  break;
		}
		}
	}

	private boolean isOperateur(char touche) {
		
		return (touche == '+') || (touche == '-') || (touche == '*') || (touche == '/') ;
	}

	public String getContenuEcran()
	
	{
		return this.ecran;
	}
	
}
