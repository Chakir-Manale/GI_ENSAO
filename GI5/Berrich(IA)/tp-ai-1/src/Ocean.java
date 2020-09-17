
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Ocean extends Observable {
    // Attributs
    protected Poisson[] poissons;
    protected ArrayList<ZoneAEviter> obstacles;
    protected Random generateur;
    protected double largeur;
    protected double hauteur;

    // Méthodes
    public Ocean(int _nbPoissons, double _largeur, double _hauteur) {
        largeur = _largeur;
        hauteur = _hauteur;
        generateur = new Random();
        obstacles = new ArrayList();
        poissons = new Poisson[_nbPoissons];
        for (int i = 0; i < _nbPoissons; i++) {
            poissons[i] = new Poisson(generateur.nextDouble() * largeur,
                    generateur.nextDouble() * hauteur,
                    generateur.nextDouble() * 2 * Math.PI);
        }
    }

    public void AjouterObstacle(double _posX, double _posY, double rayon) {
        obstacles.add(new ZoneAEviter(_posX, _posY, rayon));
    }

    protected void MiseAJourObstacles() {
        for (ZoneAEviter obstacle : obstacles) {
            obstacle.MiseAJour();
        }
        obstacles.removeIf(o -> o.estMort());
    }

    protected void MiseAJourPoissons() {
        for (Poisson p : poissons) {
            p.MiseAJour(poissons, obstacles, largeur, hauteur);
        }
    }

    public void MiseAJourOcean() {
        MiseAJourObstacles();
        MiseAJourPoissons();
        setChanged();
        notifyObservers();
    }
}
