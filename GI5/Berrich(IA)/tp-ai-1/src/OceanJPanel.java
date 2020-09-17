import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class OceanJPanel extends JPanel implements Observer, MouseListener {
    protected Ocean ocean;
    protected Timer timer;

    public OceanJPanel() {
        this.setBackground(new Color(132, 221, 1));
        this.addMouseListener(this);
    }

    public void Lancer() {
        ocean = new Ocean(10000, this.getWidth(), getHeight());
        ocean.addObserver(this);
        TimerTask tache = new TimerTask() {
            @Override
            public void run() {
                ocean.MiseAJourOcean();
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(tache, 0, 15);
    }

    protected void DessinerPoisson(Poisson p, Graphics g) {
        g.drawLine((int) p.posX, (int) p.posY, (int) (p.posX - 10 * p.vitesseX), (int) (p.posY - 10 * p.vitesseY));
    }

    protected void DessinerObstacle(ZoneAEviter o, Graphics g) {
        g.drawOval((int) (o.posX - o.rayon), (int) (o.posY - o.rayon), (int) o.rayon * 2, (int) o.rayon * 2);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Poisson p : ocean.poissons) {
            DessinerPoisson(p, g);
        }
        for (ZoneAEviter o : ocean.obstacles) {
            DessinerObstacle(o, g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ocean.AjouterObstacle(e.getX(), e.getY(), 10);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
