package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

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

    public double getRayMultT(Point3D point) {
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
    public List<Point3D> findIntersections(Ray ray) {
        Ray axisRay = getAxisRay();
        Sphere sp1 = new Sphere(_radius, axisRay.GetPoint());
        Sphere sp2 = new Sphere(_radius, axisRay.getPoint(_height));
        Plane p1 = new Plane(axisRay.GetPoint(), axisRay.GetDirection());
        Plane p2 = new Plane(axisRay.getPoint(_height), axisRay.GetDirection());
        if (super.findIntersections(ray) == null) {
            if (ray.GetDirection().equals(axisRay.GetDirection())) {
                if (sp1.findIntersections(ray) != null)
                    if (p1.findIntersections(ray) != null)
                        return List.of(
                                p1.findIntersections(ray).get(0),
                                p2.findIntersections(ray).get(0));
                    else
                        return p2.findIntersections(ray);
                else if (sp2.findIntersections(ray) != null)
                    return p2.findIntersections(ray);

            } else if (ray.GetDirection().equals(axisRay.GetDirection().scale(-1))) {
                if (sp2.findIntersections(ray) != null)
                    if (p2.findIntersections(ray) != null)
                        return List.of(
                                p2.findIntersections(ray).get(0),
                                p1.findIntersections(ray).get(0));
                    else
                        return p1.findIntersections(ray);
            }

        } else if (super.findIntersections(ray).size() == 1) {
            double t = getRayMultT(super.findIntersections(ray).get(0));
            if (t > 0 && t < _height) {
                if (sp1.findIntersections(ray) == null && sp2.findIntersections(ray) == null)
                    return super.findIntersections(ray);
                else if (sp1.findIntersections(ray) != null && p1.findIntersections(ray) != null)
                    return List.of(p1.findIntersections(ray).get(0),ray.getPoint(t));
                else if (p2.findIntersections(ray) != null)
                    return List.of(p2.findIntersections(ray).get(0),ray.getPoint(t));
            } else {
                if (sp1.findIntersections(ray) == null && sp2.findIntersections(ray) == null)
                    return null;
                else if (sp1.findIntersections(ray) != null && p1.findIntersections(ray) != null)
                    return List.of(p1.findIntersections(ray).get(0));
                else if (p2.findIntersections(ray) != null)
                    return List.of(p2.findIntersections(ray).get(0));
            }
        } else {
            double t1 = getRayMultT(super.findIntersections(ray).get(0));
            double t2 = getRayMultT(super.findIntersections(ray).get(1));
            if (t1 > 0 && t1 < _height && t2 > 0 && t2 < _height)
                return super.findIntersections(ray);
            else if (t1 > 0 && t1 < _height) {
                if (sp1.findIntersections(ray) != null)
                    return List.of(ray.getPoint(t1),
                            p1.findIntersections(ray).get(0));
                else
                    return List.of(ray.getPoint(t1),
                            p2.findIntersections(ray).get(0));
            } else {
                if (sp1.findIntersections(ray) != null) {
                    return List.of(p1.findIntersections(ray).get(0),
                            ray.getPoint(t2));
                } else {
                    return List.of(p2.findIntersections(ray).get(0),
                            ray.getPoint(t2));
                }
            }
        }
        return null;
    }
}
