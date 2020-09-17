public class ZoneAEviter extends Objet {

    protected double rayon;
    protected int tempsRestant = 10000;

    public ZoneAEviter(double _x, double _y, double _rayon) {
        posX = _x;
        posY = _y;
        rayon = _rayon;
    }

    public double getRayon() {
        return rayon;
    }

    public void MiseAJour() {
        tempsRestant--;
    }

    public boolean estMort() {
        return tempsRestant <= 0;
    }
}
