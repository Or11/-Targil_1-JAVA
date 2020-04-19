package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Plane implements Geometry {
    private Point3D _p;
    private Vector _normal;

    /**
     * constructor
     *
     * @param p
     * @param normal
     */
    public Plane(Point3D p, Vector normal) {
        _p = new Point3D(p);
        _normal = new Vector(normal);
    }

    /**
     * constructor
     *
     * @param a
     * @param b
     * @param c
     */
    public Plane(Point3D a, Point3D b, Point3D c) {
        _p = new Point3D(a);
        Vector v1 = b.subtract(a);
        Vector v2 = c.subtract(a);
        _normal = v1.crossProduct(v2).normalized();
    }

    /**
     * plane reference point getter
     *
     * @return Point3D point of reference
     */
    public Point3D getPoint() {
        return _p;
    }

    /**
     * normal getter
     *
     * @return _normal
     */
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        if (point == null) throw new NullPointerException("point can not be a null.");
        return _normal.normalized();
    }

    @Override
    public String toString() {
        return "Plane: " +
                "point = " + _p +
                ", normal = " + _normal +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
