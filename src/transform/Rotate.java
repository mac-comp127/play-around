package transform;

import edu.macalester.graphics.Point;

public class Rotate implements PointTransform {
    private final double theta;

    public Rotate(double theta) {
        this.theta = theta;
    }

    @Override
    public Point apply(Point p) {
        return p.rotate(theta);
    }
    
}
