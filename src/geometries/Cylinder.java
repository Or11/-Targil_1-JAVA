package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * class represent a Cylinder
 *
 * @authors Yossef Matof & Simha Richard
 */
public class Cylinder extends Tube {
    private double _height;

    /**
     * constructor
     *
     * @param radius
     * @param height
     * @param axisRay
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        _height = height;
    }

    /**
     * height getter
     *
     * @return _height
     */
    public double get_height() {
        return _height;
    }

    private double getRayMultT(Point3D point) {
        Point3D p = getAxisRay().GetPoint();
        Vector v = getAxisRay().GetDirection();
        return Util.alignZero(point.subtract(p).dotProduct(v));
    }

    @Override
    public Vector getNormal(Point3D point) {
        Point3D p = getAxisRay().GetPoint();
        Vector v = getAxisRay().GetDirection();
        double t = getRayMultT(point);
        if (t == 0 || Util.isZero(_height - t))
            return v.normalized();
        p = p.add(v.scale(t));
        return point.subtract(p).normalized();

    }

    @Override
    public String toString() {
        return "Cylinder: " + super.toString() + " height = " + _height + "\n";
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Ray axisRay = getAxisRay();
        Point3D A = axisRay.GetPoint();
        Point3D B = axisRay.getPoint(_height);
        Plane p1 = new Plane(axisRay.GetPoint(), axisRay.GetDirection());
        Plane p2 = new Plane(axisRay.getPoint(_height), axisRay.GetDirection());
        List<GeoPoint> intersections = super.findIntersections(ray);
        if (intersections == null) {
            if (ray.GetDirection().equals(axisRay.GetDirection())) {
                if (baseInter(A, ray))
                    return List.of(
                            new GeoPoint(this, p1.findIntersections(ray).get(0).getPoint()),
                            new GeoPoint(this, p2.findIntersections(ray).get(0).getPoint()));
                else if (baseInter(B, ray))
                    return List.of(new GeoPoint(this, p2.findIntersections(ray).get(0).getPoint()));

            } else if (ray.GetDirection().equals(axisRay.GetDirection().scale(-1))) {
                if (baseInter(B, ray))
                    return List.of(
                            new GeoPoint(this, p2.findIntersections(ray).get(0).getPoint()),
                            new GeoPoint(this, p1.findIntersections(ray).get(0).getPoint()));
                else if (baseInter(A, ray))
                    return List.of(new GeoPoint(this, p1.findIntersections(ray).get(0).getPoint()));
            }

        } else if (intersections.size() == 1) {
            double t = getRayMultT(intersections.get(0).getPoint());
            if (t > 0 && t < _height) {
                if (baseInter(A, ray))
                    return List.of(new GeoPoint(this, p1.findIntersections(ray).get(0).getPoint()));
                else if (baseInter(B, ray))
                    return List.of(new GeoPoint(this, p2.findIntersections(ray).get(0).getPoint()),
                            new GeoPoint(this, ray.getPoint(t)));
                return intersections;
            } else {
                if (baseInter(A, ray))
                    return List.of(p1.findIntersections(ray).get(0));
                else if (baseInter(B, ray))
                    return List.of(p2.findIntersections(ray).get(0));
                return null;
            }
        } else {
            double t1 = getRayMultT(intersections.get(0).getPoint());
            double t2 = getRayMultT(intersections.get(1).getPoint());
            if (t1 > 0 && t1 < _height && t2 > 0 && t2 < _height)
                return intersections;
            else if (t1 > 0 && t1 < _height) {
                if (baseInter(B, ray))
                    return List.of(new GeoPoint(this, ray.getPoint(t1)),
                            new GeoPoint(this, p1.findIntersections(ray).get(0).getPoint()));
                else
                    return List.of(new GeoPoint(this, ray.getPoint(t1)),
                            new GeoPoint(this, p2.findIntersections(ray).get(0).getPoint()));
            } else {
                if (baseInter(A, ray)) {
                    return List.of(new GeoPoint(this, p1.findIntersections(ray).get(0).getPoint()),
                            new GeoPoint(this, ray.getPoint(t2)));
                } else {
                    return List.of(new GeoPoint(this, p2.findIntersections(ray).get(0).getPoint()),
                            new GeoPoint(this, ray.getPoint(t2)));
                }
            }
        }
        return null;
    }

    private boolean baseInter(Point3D point, Ray ray) {
        Sphere sphere = new Sphere(_radius, point);
        Plane plane = new Plane(point, getAxisRay().GetDirection());
        return sphere.findIntersections(ray) != null && plane.findIntersections(ray) != null;
    }
}
