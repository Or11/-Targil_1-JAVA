package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * class represent a plane in 3D Cartesian coordinate
 *
 * @authors Yossef Matof & Simha Richard
 */
public class Plane extends Geometry {
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
     * constructor with Color and 3 Points
     *
     * @param color
     * @param a
     * @param b
     * @param c
     */
    public Plane(Color color, Point3D a, Point3D b, Point3D c) {
        this(a, b, c);
        this._emission = color;
    }

    /**
     * constructor with color, Point and normal
     *
     * @param color
     * @param p
     * @param normal
     */
    public Plane(Color color, Point3D p, Vector normal) {
        this(p, normal);
        this._emission = color;
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
    public List<GeoPoint> findIntersections(Ray ray) {

        Point3D p0 = ray.GetPoint();
        if (p0.equals(_p)) return null;
        Vector v = ray.GetDirection();
        double denominator = alignZero(_normal.dotProduct(v));
        double numerator = alignZero(_normal.dotProduct(_p.subtract(p0)));
        if (denominator == 0 || numerator == 0) return null;
        double t = numerator / denominator;
        if (t > 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t)));
        }
        return null;
    }
}
