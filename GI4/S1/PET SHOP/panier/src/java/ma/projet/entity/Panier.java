
package ma.projet.entity;

/**
 *
 * @author lachgar
 */
public class Panier {
    private Produit produit;
    private int qte;

    public Panier() {
    }

    public Panier(Produit produit, int qte) {
        this.produit = produit;
        this.qte = qte;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
    
    
}
