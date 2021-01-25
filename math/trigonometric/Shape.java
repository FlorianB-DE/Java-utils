import engine.Collider;

import java.awt.*;

/**
 * a template for a shape used mainly by {@link Collider}s
 *
 * @author Florian Becker
 */
public interface Shape {
    /**
     * contain this s?
     *
     * @param s the other Shape
     * @return
     */
    boolean contains(Shape s);

    /**
     * @return centre of the shape, preferably the centre of mass
     */
    Point getCentre();

    /**
     * @param l a {@link Line}
     * @return the intersection between the shape and the line
     */
    Point intersection(Line l);
}
