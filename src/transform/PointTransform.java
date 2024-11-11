package transform;

import edu.macalester.graphics.Point;

/**
 * A transformation of the 2D plane.
 */
public interface PointTransform {
    Point apply(Point p);
}
