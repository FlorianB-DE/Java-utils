import engine.Camera;

import java.awt.*;

/**
 * literally a line
 *
 * @author Florian Becker
 */
public class Line {

    /**
     * the coordinates
     */
    public int x1, x2, y1, y2;

    // constructor #1
    public Line() {
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
    }

    // constructor #2
    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    // constructor #3
    public Line(Line line) {
        x1 = line.x1;
        x2 = line.x2;
        y1 = line.y1;
        y2 = line.y2;
    }

    // constructor #3
    public Line(Point a, Point b) {
        x1 = a.x;
        x2 = b.x;
        y1 = a.y;
        y2 = b.y;
    }

    public void draw(Graphics g, Camera c) {
        final Color prev = g.getColor();
        g.setColor(Color.yellow);
        final Point start = Camera.fromWorldToCamera(c, getStart()), end = Camera.fromWorldToCamera(c, getEnd());
        g.drawLine(start.x, start.y, end.x, end.y);
        g.setColor(prev);
    }

    public void drawCollision(Point p, Graphics g, Camera c) {
        draw(g, c);
        final Color prev = g.getColor();
        g.setColor(Color.red);
        final Point loc = Camera.fromWorldToCamera(c, p);
        g.fillOval(loc.x, loc.y, 10, 10);
        g.setColor(prev);
    }

    public Point getEnd() {
        return new Point(x2, y2);
    }

    public Point getStart() {
        return new Point(x1, y1);
    }

    /**
     * you could not live with your own
     *
     * @param l . And where did that bring you? <b> Back to me.</b>
     * @return an approximation to the actual point of intersection
     * @see for Line-Line intersection:</br>
     * https://en.wikipedia.org/wiki/Line-line_intersection
     */
    public Point intersection(Line l) {
        final double u = (((x1 - l.x1) * (l.y1 - l.y2)) - ((y1 - l.y1) * (l.x1 - l.x2)))
                / (double) (((x1 - x2) * (l.y1 - l.y2)) - ((y1 - y2) * (l.x1 - l.x2)));
        final double t = -(((x1 - x2) * (y1 - l.y1)) - ((y1 - y2) * (x1 - l.x1)))
                / (double) (((x1 - x2) * (l.y1 - l.y2)) - ((y1 - y2) * (l.x1 - l.x2)));
        if (u > 1 || t > 1 || u < 0 || t < 0)
            return null;
        return new Point((int) Math.round(l.x1 + (t * (l.x2 - l.x1))), (int) Math.round(l.y1 + (t * (l.y2 - l.y1))));
    }

    /**
     * @param l the other line
     * @return true on intersection, false otherwise
     */
    public boolean intersects(Line l) {
        return !(intersection(l) == null);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return Math.sqrt((x2 - x1) * (x2 - x1) + ((y2 - y1) * (y2 - y1)));
    }
}
