package snek;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author arthu
 */
public class World {

    private enum TILE_TYPE {
        EMPTY,
        APPLE,
        WALL
    };

    private Snek snek;

    private int initLine, initCol;

    private TILE_TYPE[][] terrain;
    private double gridStep = 1;

    public World() {
        int nbLines = 11;
        int nbCols = 15;
        terrain = new TILE_TYPE[nbLines][];
        for (int line = 0; line < nbLines; line++) {
            terrain[line] = new TILE_TYPE[nbCols];
            for (int col = 0; col < terrain[0].length; col++) {
                terrain[line][col] = TILE_TYPE.EMPTY;
            }
        }
        initSnek();
    }

    public void paint(Graphics g, double x0, double y0, double zoom) {

        int appSize = (int) (gridStep * zoom);
        for (int line = 0; line < terrain.length; line++) {
            int yApp = (int) (y0 + (gridStep * (line + 0.5)) * zoom);
            for (int col = 0; col < terrain[0].length; col++) {
                int xApp = (int) (x0 + (gridStep * (col + 0.5)) * zoom);
                switch (terrain[line][col]) {
                case EMPTY:
                    g.setColor(Color.gray);
                    g.fillRect(xApp - appSize / 2, yApp - appSize / 2, appSize, appSize);
                    g.setColor(Color.black);
                    g.drawRect(xApp - appSize / 2, yApp - appSize / 2, appSize, appSize);

                    g.setColor(Color.blue);
                    g.fillRect(xApp, yApp, 1, 1);
                    break;
                default:
                    break;
                }
            }
        }
        snek.paint(g, x0, y0, zoom, gridStep);
    }

    private void initSnek() {
        initLine = 8; // terrain.length / 2;
        initCol = 3; // terrain[0].length / 2;
        snek = new Snek(initLine, initCol);
    }

    void mousePressed(double xWorld, double yWorld) {
    }

    void mouseReleased() {
    }

    void mouseDragged(double xWorld, double yWorld) {
    }

    void step() {
        snek.step();
    }

    void restart() {
    }

    void turnLeft() {
        snek.turnLeft();
    }

    void turnRight() {
        snek.turnRight();
    }

}
