import engine.MathUtils;

import java.awt.*;

/**
 * a 2D circle
 *
 * @author Florian Becker
 */
public class Circle implements Shape {

    public int radius, x, y;

    // constructor primus
    public Circle() {
        radius = 0;
        x = 0;
        y = 0;
    }

    // constructor secundus
    public Circle(Circle circle) {
        x = circle.x;
        y = circle.y;
        radius = circle.radius;
    }

    // constructor tertius
    public Circle(int radius) {
        this.radius = radius;
    }

    // constructor quārtus
    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // constructor quīntus
    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    // constructor sextus
    public Circle(Point pos, int radius) {
        x = pos.x;
        y = pos.y;
        this.radius = radius;
    }

    /**
     * @param point a given point in the x - y plane
     * @return true when the distance from the centre is smaller or equal the radius
     */
    public boolean contains(Point point) {
        return point.distanceSq(getCentre()) <= radius * radius;
    }

    @Override
    public boolean contains(Shape s) {
        return contains(s.getCentre());
    }

    @Override
    public Point getCentre() {
        return new Point(x, y);
    }

    @Override
    public Point intersection(Line l) {
        final int fx_fx0 = Math.max(Math.abs(l.getStart().y - y), Math.abs(l.getEnd().y - y))
                * MathUtils.sign(Math.min(l.getStart().y - y, l.getEnd().y - y)),
                x_x0 = Math.max(Math.abs(l.getStart().x - x), Math.abs(l.getEnd().x - x))
                        * MathUtils.sign(Math.min(l.getStart().x - x, l.getEnd().x - x));

        final double sin = (fx_fx0 / l.length()), cos = x_x0 / l.length();
        final Point turnedVector = new Point((int) Math.round(radius * cos), (int) Math.round(radius * sin));
        return new Point(x + turnedVector.x, y + turnedVector.y);
    }
}
