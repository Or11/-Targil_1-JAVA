package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry{
    Point3D _p;
    Vector _normal;

    /**
     * constructor
     * @param p
     * @param normal
     */
    public Plane(Point3D p, Vector normal) {
        _p = new Point3D(p);
        _normal = new Vector(normal);
    }

    /**
     * constructor
     * @param p
     * @param normal
     */
    public Plane(Point3D a, Point3D b, Point3D c) {


    }

    /**
     * point getter
     * @return _p
     */
    public Point3D getPoint() { return _p;}

    /**
     * normal getter
     * @return _normal
     */
    public Vector getNormal() { return _normal;}

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public String toString() {
        return "Plane: " +
                "point = " + _p +
                ", normal = " + _normal +
                '}';
    }
}
