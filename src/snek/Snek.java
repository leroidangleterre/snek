package snek;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author arthu
 */
class Snek {

    // List of (line,col) coordinates of all elements of the snek;
    private ArrayList<Point> coordinates;

    private CardinalPoint currentHeading;

    public Snek(int line, int col) {
        coordinates = new ArrayList<>();
        coordinates.add(new Point(col, line));
        currentHeading = CardinalPoint.EAST;
    }

    public void paint(Graphics g, double x0, double y0, double zoom, double size) {

        int appSize = (int) (size * zoom);

        // Paint all the elements of the snek
        Point p;

        int xApp = 0, yApp = 0;

        for (int rank = coordinates.size() - 1; rank >= 0; rank--) {
            p = coordinates.get(rank);
            xApp = (int) (x0 + (size * (p.x + 0.5)) * zoom);
            yApp = (int) (y0 + (size * (p.y + 0.5)) * zoom);
            g.setColor(Color.green);
            g.fillOval(xApp - appSize / 2, yApp - appSize / 2, (int) (size * zoom), (int) (size * zoom));
        }

        // Add a mouth to the head
        switch (currentHeading) {
        case EAST:
            xApp += size * zoom / 4;
            break;
        case NORTH:
            yApp -= size * zoom / 4;
            break;
        case WEST:
            xApp -= size * zoom / 4;
            break;
        case SOUTH:
            yApp += size * zoom / 4;
            break;
        }
        g.setColor(Color.blue);
        appSize = appSize / 5;
        g.fillOval(xApp - appSize / 2, yApp - appSize / 2, appSize, appSize);
    }

    void step() {
        // Add a new point, which is the new position of the head
        Point currentHead = coordinates.get(0);
        int newCol = currentHead.x;
        int newLine = currentHead.y;

        switch (currentHeading) {
        case NORTH:
            newLine--;
            break;
        case EAST:
            newCol++;
            break;
        case SOUTH:
            newLine++;
            break;
        case WEST:
            newCol--;
            break;
        default:
            break;
        }

        Point newHead = new Point(newCol, newLine);
        coordinates.add(0, newHead);

    }

    void turnLeft() {
        switch (currentHeading) {
        case NORTH:
            currentHeading = CardinalPoint.WEST;
            break;
        case EAST:
            currentHeading = CardinalPoint.NORTH;
            break;
        case SOUTH:
            currentHeading = CardinalPoint.EAST;
            break;
        case WEST:
            currentHeading = CardinalPoint.SOUTH;
            break;
        }
    }

    void turnRight() {
        switch (currentHeading) {
        case NORTH:
            currentHeading = CardinalPoint.EAST;
            break;
        case EAST:
            currentHeading = CardinalPoint.SOUTH;
            break;
        case SOUTH:
            currentHeading = CardinalPoint.WEST;
            break;
        case WEST:
            currentHeading = CardinalPoint.NORTH;
            break;
        }
    }
}
