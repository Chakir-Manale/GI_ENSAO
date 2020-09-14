
package ma.projet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author lachgar
 */
@Entity
public class Produit {
    @Id
    @GeneratedValue
    private int id;
    private String libelle;
    private double prix;
    private String photo;

    public Produit() {
    }

    public Produit(int id, String libelle, double prix, String photo) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
        this.photo = photo;
    }

    public Produit(String libelle, double prix, String photo) {
        this.libelle = libelle;
        this.prix = prix;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

  
    
    
    
}
