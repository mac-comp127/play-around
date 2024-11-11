package transform;

import edu.macalester.graphics.Point;

public class Warble implements PointTransform {
    private double xFactor;
    private double yFactor;

    public Warble(double xFactor, double yFactor) {
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }

    @Override
    public Point apply(Point p) {
        return new Point(
            Math.cos(xFactor * (p.getX() * p.getX() + p.getY() * p.getY())),
            Math.sin(p.getX() * p.getY() * yFactor)
        );
    }
}
