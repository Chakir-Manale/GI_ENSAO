/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package photos;

import static com.sun.tools.xjc.reader.Ring.add;
import entity.Photo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import session.ArchiverRemote;

/**
 *
 * @author MGB
 */
public class Client extends JFrame {
    private JTabbedPane onglets = new JTabbedPane();
    private JPanel panneauLocal = new JPanel(new BorderLayout());
    private JPanel panneauServeur = new JPanel(new BorderLayout());
    private JToolBar outilsLocal = new JToolBar();
    private JToolBar outilsDistant = new JToolBar();
    private JLabel photoLocale = new JLabel();
    private JLabel photoDistante = new JLabel();
    private JLabel description = new JLabel();
    private JComboBox listePhotos = new JComboBox();
    private JFileChooser sélecteur = new JFileChooser();
    private Photo photo;
    private File fichier;
    private byte[] octets;
    private boolean effacer = true;
    @EJB
    private static ArchiverRemote archivage;

    public Client() {
        super("Envoyer des photos");
        add(onglets);
        onglets.add("Photos en local", panneauLocal);
        onglets.add("Photos distantes", panneauServeur);

        panneauLocal.add(outilsLocal, BorderLayout.NORTH);
        panneauLocal.add(new JScrollPane(photoLocale));
        outilsLocal.add(new AbstractAction("Sélectionner") {
            public void actionPerformed(ActionEvent e) {
                sélecteur.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (sélecteur.showOpenDialog(Client.this)==JFileChooser.APPROVE_OPTION) {
                    fichier = sélecteur.getSelectedFile();
                    photoLocale.setIcon(new ImageIcon(fichier.getPath()));
                }
            }
        });
        outilsLocal.add(new AbstractAction("Envoyer") {
            public void actionPerformed(ActionEvent e) {
                if (fichier!=null)
                    try {
                        byte[] octets = new byte[(int) fichier.length()];
                        FileInputStream lecture = new FileInputStream(fichier);
                        lecture.read(octets);
                        lecture.close();
                        archivage.stocker(fichier.getName(), octets);
                        listingPhotos();
                    }
                    catch (Exception ex) {
                        setTitle("Impossible d'envoyer le fichier");
                    }
              }
        });

        panneauServeur.add(outilsDistant, BorderLayout.NORTH);
        panneauServeur.add(new JScrollPane(photoDistante));
        panneauServeur.add(description, BorderLayout.SOUTH);

        outilsDistant.add(new AbstractAction("Restituer") {
            public void actionPerformed(ActionEvent e) {
                sélecteur.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (sélecteur.showSaveDialog(Client.this)==JFileChooser.APPROVE_OPTION) {
                    try {
                        fichier = new File(sélecteur.getSelectedFile() + "/" +photo.getId());
                        FileOutputStream fluxImage = new FileOutputStream(fichier);
                        fluxImage.write(octets);
                        fluxImage.close();
                    } 
                    catch (Exception ex) {  setTitle("Problème pour restituer la photo en local"); }                  
                }
            }
        });
        outilsDistant.add(new AbstractAction("Supprimer") {
            public void actionPerformed(ActionEvent e) {
                archivage.supprimer(photo.getId());
                listingPhotos();
            }
        });
        outilsDistant.add(listePhotos);
        listePhotos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!effacer)
                    try {
                        photo = (Photo) listePhotos.getSelectedItem();
                        octets = archivage.restituer(photo.getId());
                        ByteArrayInputStream fluxImage = new ByteArrayInputStream(octets);
                        photoDistante.setIcon(new ImageIcon(ImageIO.read(fluxImage)));
                    }
                    catch (Exception ex) {  setTitle("Problème pour récupérer l'image du serveur");  }
                }
        });
        listingPhotos();
        setSize(500, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void listingPhotos() {
        effacer = true;
        listePhotos.removeAllItems();
        for (Photo photo : archivage.getListe()) listePhotos.addItem(photo);
        effacer = false;
        if (listePhotos.getItemCount()>0) listePhotos.setSelectedIndex(0);
    }
    
    public static void main(String[] args) { new Client(); }
    
}
