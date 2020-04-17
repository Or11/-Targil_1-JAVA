package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere extends RadialGeometry {
    private Point3D _center;

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
        if (point == null) throw new NullPointerException("point can not be a null.");
        return point.subtract(_center).normalized();
    }

    @Override
    public String toString() {
        return "Sphere: " + super.toString() + " center point = " + _center + "\n";
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
