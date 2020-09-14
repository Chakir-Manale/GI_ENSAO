/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;


import javax.ejb.Remote;
import entity.Photo;
import java.util.List;
/**
 *
 * @author MGB
 */
@Remote
public interface ArchiverRemote {
    void stocker(String nom, byte[] octets) throws java.io.IOException;
    byte[] restituer(String nom) throws java.io.IOException;
    List<Photo> getListe();
    Photo getPhoto(String nom);
    void supprimer(String nom);
}
