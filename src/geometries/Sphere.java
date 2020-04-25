package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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
        return new Point3D(_center);
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
        Point3D p0 = ray.GetPoint();
        Vector v = ray.GetDirection();
        Vector u;
        try {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException e) {
            return List.of(ray.getPoint(_radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double d = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        ;
        double r = this.getRadius();
        if (d > r) {
            return null;
        }
        double thSquere = alignZero(_radius * _radius - d);

        if (thSquere <= 0) return null;

        double th = alignZero(Math.sqrt(thSquere));

        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        if (t1 <= 0 && t2 <= 0) {
            return null;
        }


        if (t1 > 0 && t2 > 0) return List.of(ray.getPoint(t1), ray.getPoint(t2));

        if (t1 > 0) return List.of(ray.getPoint(t1));
        else return List.of(ray.getPoint(t2));
    }
}
