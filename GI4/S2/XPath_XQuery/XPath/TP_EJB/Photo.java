/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MGB
 */
@Entity
@NamedQuery(name="toutes", query="SELECT photo FROM Photo AS photo")
public class Photo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date instant;
    private int largeur;
    private int hauteur;
    private long poids;
    
    public Photo() { }

    public Photo(String nom, long poids) {
        id = nom;
        instant = new Date();
        this.poids = poids;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public long getPoids() {
        return poids;
    }

    public void setPoids(long poids) {
        this.poids = poids;
    }
    
    
    public void setDimensions(BufferedImage image) {
        largeur = image.getWidth();
        hauteur = image.getHeight();
    }
    
    @Override
    public String toString() {
        return id+" ("+largeur+", "+hauteur+")";
    }
    
}
