import java.util.ArrayList;

public class Poisson extends Objet {

    // Constantes
    public static final double PAS = 3;
    public static final double DISTANCE_MIN = 5;
    public static final double DISTANCE_MIN_CARRE = 25;
    public static final double DISTANCE_MAX = 40;
    public static final double DISTANCE_MAX_CARRE = 1600;
    // Attributs
    protected double vitesseX;
    protected double vitesseY;

    // Méthodes
    public Poisson(double _x, double _y, double _dir) {
        posX = _x;
        posY = _y;
        vitesseX = Math.cos(_dir);
        vitesseY = Math.sin(_dir);
    }

    public double getVitesseX() {
        return vitesseX;
    }

    public double getVitesseY() {
        return vitesseY;
    }

    protected void MiseAJourPosition() {
        posX += PAS * vitesseX;
        posY += PAS * vitesseY;
    }

    protected boolean DansAlignement(Poisson p) {
        double distanceCarre = DistanceCarre(p);
        return (distanceCarre < DISTANCE_MAX_CARRE && distanceCarre >
                DISTANCE_MIN_CARRE);
    }

    protected double DistanceAuMur(double murXMin, double murYMin, double murXMax,
                                   double murYMax) {
        double min = Math.min(posX - murXMin, posY - murYMin);
        min = Math.min(min, murXMax - posX);
        min = Math.min(min, murYMax - posY);
        return min;
    }

    protected void Normaliser() {
        double longueur = Math.sqrt(vitesseX * vitesseX + vitesseY * vitesseY);
        vitesseX /= longueur;
        vitesseY /= longueur;
    }

    protected boolean EviterMurs(double murXMin, double murYMin, double murXMax, double
            murYMax) {
        // On s'arrête aux murs
        if (posX < murXMin) {
            posX = murXMin;
        } else if (posY < murYMin) {
            posY = murYMin;
        } else if (posX > murXMax) {
            posX = murXMax;
        } else if (posY > murYMax) {
            posY = murYMax;
        }
        // Changer de direction
        double distance = DistanceAuMur(murXMin, murYMin, murXMax, murYMax);
        if (distance < DISTANCE_MIN) {
            if (distance == (posX - murXMin)) {
                vitesseX += 0.3;
            } else if (distance == (posY - murYMin)) {
                vitesseY += 0.3;
            } else if (distance == (murXMax - posX)) {
                vitesseX -= 0.3;
            } else if (distance == (murYMax - posY)) {
                vitesseY -= 0.3;
            }
            Normaliser();
            return true;
        }
        return false;
    }

    protected boolean EviterObstacles(ArrayList<ZoneAEviter> obstacles) {
        if (!obstacles.isEmpty()) {
            // Recherche de l'obstacle le plus proche
            ZoneAEviter obstacleProche = obstacles.get(0);
            double distanceCarre = DistanceCarre(obstacleProche);
            for (ZoneAEviter o : obstacles) {
                if (DistanceCarre(o) < distanceCarre) {
                    obstacleProche = o;
                    distanceCarre = DistanceCarre(o);
                }
            }
            if (distanceCarre < (obstacleProche.rayon * obstacleProche.rayon)) {
                // Si collision, calcul du vecteur diff
                double distance = Math.sqrt(distanceCarre);
                double diffX = (obstacleProche.posX - posX) / distance;
                double diffY = (obstacleProche.posY - posY) / distance;
                vitesseX = vitesseX - diffX / 2;
                vitesseY = vitesseY - diffY / 2;
                Normaliser();
                return true;
            }
        }
        return false;
    }

    protected boolean EviterPoissons(Poisson[] poissons) {
        // Recherche du poisson le plus proche
        Poisson p;
        if (!poissons[0].equals(this)) {
            p = poissons[0];
        } else {
            p = poissons[1];
        }
        double distanceCarre = DistanceCarre(p);
        for (Poisson poisson : poissons) {
            if (DistanceCarre(poisson) < distanceCarre && !poisson.equals(this)) {
                p = poisson;
                distanceCarre = DistanceCarre(p);
            }
        }
        // Evitement
        if (distanceCarre < DISTANCE_MIN_CARRE) {
            double distance = Math.sqrt(distanceCarre);
            double diffX = (p.posX - posX) / distance;
            double diffY = (p.posY - posY) / distance;
            vitesseX = vitesseX - diffX / 4;
            vitesseY = vitesseY - diffY / 4;
            Normaliser();
            return true;
        }
        return false;
    }

    protected void CalculerDirectionMoyenne(Poisson[] poissons) {
        double vitesseXTotal = 0;
        double vitesseYTotal = 0;
        int nbTotal = 0;
        for (Poisson p : poissons) {
            if (DansAlignement(p)) {
                vitesseXTotal += p.vitesseX;
                vitesseYTotal += p.vitesseY;
                nbTotal++;
            }
        }
        if (nbTotal >= 1) {
            vitesseX = (vitesseXTotal / nbTotal + vitesseX) / 2;
            vitesseY = (vitesseYTotal / nbTotal + vitesseY) / 2;
            Normaliser();
        }
    }

    protected void MiseAJour(Poisson[] poissons, ArrayList<ZoneAEviter> obstacles,
                             double largeur, double hauteur) {
        if (!EviterMurs(0, 0, largeur, hauteur)) {
            if (!EviterObstacles(obstacles)) {
                if (!EviterPoissons(poissons)) {
                    CalculerDirectionMoyenne(poissons);
                }
            }
        }
        MiseAJourPosition();
    }
}
