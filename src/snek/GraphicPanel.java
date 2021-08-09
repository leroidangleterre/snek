package snek;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author arthu
 */
public class GraphicPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private World world;

    private double x0, y0, zoom;
    private int xMouse, yMouse;

    private boolean isPanning;

    private Timer timer;
    private int delay, period; // milliseconds
    private int stepNumber;

    public GraphicPanel(World newWorld) {
        super();
        world = newWorld;
        x0 = 170.9;
        y0 = 222.0;
        zoom = 35.9;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addKeyListener(new SnekKeyListener(this));

        isPanning = false;

        timer = null;
        delay = 0;
        period = 500;
        stepNumber = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        world.paint(g, x0, y0, zoom);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            isPanning = false;
            double xWorld = (e.getX() - x0) / zoom;
            double yWorld = (this.getHeight() - e.getY() - y0) / zoom;
            world.mousePressed(xWorld, yWorld);
        } else if (e.getButton() == 2) {
            // Mousewheel click, must pan the view
            isPanning = true;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            world.mouseReleased();
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - xMouse;
        int dy = e.getY() - yMouse;
        xMouse = e.getX();
        yMouse = e.getY();

        if (isPanning) {
            x0 += dx;
            y0 += dy;
        } else {
            double xWorld = (e.getX() - x0) / zoom;
            double yWorld = (this.getHeight() - e.getY() - y0) / zoom;
            world.mouseDragged(xWorld, yWorld);
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        double zoomFact = 1.0;

        switch (e.getWheelRotation()) {
        case -1:
            // Zoom in
            zoomFact = 1.1;
            break;
        case 1:
            // Zoom out
            zoomFact = 1 / 1.1;
            break;
        default:
            break;
        }

        int h = this.getHeight();

        x0 = zoomFact * (x0 - e.getX()) + e.getX();
        y0 = zoomFact * (y0 - e.getY()) + e.getY();
        zoom = zoom * zoomFact;
        repaint();
    }

    void step() {
        world.step();
        repaint();
    }

    /**
     * Toggle between play and pause
     *
     * @return true if the sim is now playing (i.e. it was started), false
     * otherwise
     */
    boolean playPause() {
        if (timer == null) {
            play();
            return true;
        } else {
            pause();
            return false;
        }
    }

    private void play() {
        // Start the simulation
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                world.step();
                repaint();
                System.out.println("Step " + stepNumber);
                stepNumber++;
            }
        }, delay, period);
        System.out.println("Timer started");
    }

    private void pause() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        System.out.println("Timer stopped");
    }

    void restart() {

        pause();
        world.restart();
        repaint();
    }

    void turnSnek() {
        world.turnLeft();
        repaint();
    }

    void turnSnekLeft() {
        world.turnLeft();
        repaint();
    }

    void turnSnekRight() {
        world.turnRight();
        repaint();
    }

}
