/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Photo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MGB
 */
@Stateless
public class Archiver implements ArchiverRemote{
    private final String répertoire = "D:/Archivage/";
    @PersistenceContext
    EntityManager persistance;
    
    @Override
    public void stocker(String nom, byte[] octets) throws IOException  {
        File fichier = new File(répertoire+nom);
        if (fichier.exists()) return;
        FileOutputStream fluxphoto = new FileOutputStream(fichier);
        fluxphoto.write(octets);
        fluxphoto.close();
        enregistrer(nom);
    }

    @Asynchronous
    private void enregistrer(String nom) throws IOException {
        File fichier = new File(répertoire+nom);
        BufferedImage image = ImageIO.read(fichier);
        Photo photo = new Photo(nom, fichier.length());
        photo.setDimensions(image);
        persistance.persist(photo);
    }
    
    public byte[] restituer(String nom) throws IOException {
        File fichier = new File(répertoire+nom);
        if (!fichier.exists()) return null;
        FileInputStream fluxphoto = new FileInputStream(fichier);
        byte[] octets = new byte[(int)fichier.length()];
        fluxphoto.read(octets);
        fluxphoto.close();
        return octets;
    }

    @Override
    public List<Photo> getListe() { return persistance.createNamedQuery("toutes").getResultList(); }

    @Override
    public Photo getPhoto(String nom) {  return persistance.find(Photo.class, nom);   }

    @Override
    public void supprimer(String nom) {
        new File(répertoire+nom).delete();
        Photo photo = getPhoto(nom);
        persistance.remove(photo);
    }
}
