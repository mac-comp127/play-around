package transform;

import edu.macalester.graphics.Point;

public class Squinch implements PointTransform {
    private double zoominess;
    private double rotation;

    public Squinch(double zoominess, double rotation) {
        this.zoominess = zoominess;
        this.rotation = rotation;
    }

    @Override
    public Point apply(Point p) {
        double r = 1 / (Math.hypot(p.getX(), p.getY()) + zoominess);
        double theta = Math.atan2(p.getY(), p.getX()) + rotation;
        return new Point(
            r * Math.cos(theta),
            r * Math.sin(theta)
        );
    }
    
}
