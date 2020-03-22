package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    Point3D _center;

    /**
     * constructor
     *
     * @param radius
     * @param center
     */
    public Sphere(double radius, Point3D center) {
        super(radius);
        _center = new Point3D(center);
    }

    /**
     * center getter
     *
     * @return _center
     */
    public Point3D getCenterPoint() {
        return _center;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public String toString() {
        return "Sphere: " + super.toString() + " center point = " + _center + "\n";
    }
}
