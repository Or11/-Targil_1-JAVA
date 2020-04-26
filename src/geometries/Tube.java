package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * class represent a tube shape
 */
public class Tube extends RadialGeometry {
    private Ray _axisRay;

    /**
     * constructor
     *
     * @param radius
     * @param axisRay
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        _axisRay = new Ray(axisRay.GetPoint(), axisRay.GetDirection());
    }

    /**
     * axisRay getter
     *
     * @return _axisRay
     */
    public Ray getAxisRay() {
        return new Ray(_axisRay.GetPoint(), _axisRay.GetDirection());
    }


    @Override
    public Vector getNormal(Point3D point) {
        double t = _axisRay.GetDirection().dotProduct(point.subtract(_axisRay.GetPoint()));
        Point3D o = _axisRay.GetPoint().add(_axisRay.GetDirection().scale(t));//TODO refactoring
        return point.subtract(o).normalized();
    }

    @Override
    public String toString() {
        return super.toString() + " axisRay = " + _axisRay + "\n";
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector vTube = _axisRay.GetDirection();
        Vector vectorV0;
        Vector vXvTube;
        Vector rayDirXvTube;
        try {
            vectorV0 = ray.GetPoint().subtract(_axisRay.GetPoint());
        } catch (IllegalArgumentException e) {
            vectorV0 = Vector.zero;
        }
        try {
            rayDirXvTube = vectorV0.crossProduct(vTube);
        } catch (IllegalArgumentException e) {
            rayDirXvTube = Vector.zero;
        }
        try {
            vXvTube = ray.GetDirection().crossProduct(vTube);
        } catch (IllegalArgumentException e) {
            vXvTube = Vector.zero;
        }

        // Cylinder [Ray(Point A,Vector V), r].
        // Point P on infinite cylinder if ((P - A) x (V))^2 = r^2 * V^2
        // P = O + t * V1
        // expand : ((O - A) x (V) + t * (V1 x V))^2 = r^2 * V^2

        double vTube2 = Util.alignZero(vTube.lengthSquared());
        double a = Util.alignZero(vXvTube.lengthSquared());
        double b = Util.alignZero(2 * vXvTube.dotProduct(rayDirXvTube));
        double c = Util.alignZero(rayDirXvTube.lengthSquared() - (_radius * _radius * vTube2));
        double d = Util.alignZero(b * b - 4 * a * c);
        if (d < 0) return null;
        if (a == 0)
            return null;
        double t1 = Util.alignZero((-b - Math.sqrt(d)) / (2 * a));
        double t2 = Util.alignZero((-b + Math.sqrt(d)) / (2 * a));
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0)
            return List.of(ray.getPoint(t1), ray.getPoint(t2));
        if (t1 > 0)
            return List.of(ray.getPoint(t1));
        else
            return List.of(ray.getPoint(t2));
    }
}
