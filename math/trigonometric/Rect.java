import java.awt.*;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * an extension to {@link Rectangle}
 *
 * @author Florian Becker
 */
public final class Rect extends Rectangle implements Shape {

    // boa constructor
    public Rect() {
        super();
    }

    // constructus maximus
    public Rect(Dimension d) {
        super(d);
    }

    // constructo patronum
    public Rect(int width, int height) {
        super(width, height);
    }

    // constructo ergo sum
    public Rect(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    // darth constructus the wise
    public Rect(Point p) {
        super(p);
    }

    // palpatine the construction machine
    public Rect(Point p, Dimension d) {
        super(p, d);
    }

    // professor C
    public Rect(Rectangle r) {
        super(r);
    }

    // plain constructor
    public Rect(Rect r) {
        super(r.getLocation(), r.getSize());
    }

    @Override
    public boolean contains(Shape s) {
        return contains(s.getCentre());
    }

    @Override
    public Point getCentre() {
        return new Point((int) getCenterX(), (int) getCenterY());
    }

    /**
     * @return an array of length four with the sides represented as lines in the order:
     * <ol>
     * <li>upper-left to upper-right</li>
     * <li>upper-right to lower-light</li>
     * <li>lower-right to lower-left</li>
     * <li>lower-left to upper-left</li>
     * </ol>
     */
    public Line[] getSides() {
        final Point[] vertecies = getVertices();
        final Line[] sides = new Line[4];
        sides[0] = new Line(vertecies[0], vertecies[1]);
        sides[1] = new Line(vertecies[1], vertecies[2]);
        sides[2] = new Line(vertecies[2], vertecies[3]);
        sides[3] = new Line(vertecies[3], vertecies[0]);
        return sides;
    }

    /**
     * @return an array of length four with the vertices represented as Points in the order:
     * <ol>
     * <li>upper-left</li>
     * <li>upper-right</li>
     * <li>lower-right</li>
     * <li>lower-left</li>
     * </ol>
     */
    public Point[] getVertices() {
        final Point pos = getLocation();
        final Point[] corners = new Point[4];
        corners[0] = pos;
        corners[1] = new Point(pos.x + width, pos.y);
        corners[2] = new Point(pos.x + width, pos.y + height);
        corners[3] = new Point(pos.x, pos.y + height);
        return corners;
    }

    @Override
    public Point intersection(Line l) {
        final LinkedList<Point> intersections = new LinkedList<Point>();
        for (Line line : getSides()) {
            final Point intersection = line.intersection(l);
            if (intersection != null)
                intersections.add(intersection);
        }
        intersections.sort(new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                return java.lang.Double.compare(o1.distanceSq(l.getEnd()), o2.distanceSq(l.getEnd()));
            }
        });
        return intersections.pollFirst();
    }
}
