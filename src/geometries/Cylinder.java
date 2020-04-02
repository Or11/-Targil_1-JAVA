package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

public class Cylinder extends Tube {
    double _height;

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

    @Override
    public Vector getNormal(Point3D point) {
        Point3D p = getAxisRay().GetPoint();
        Vector v = getAxisRay().GetDirection();
        double t = Util.alignZero(point.subtract(p).dotProduct(v));
        if (t == 0 || Util.isZero(_height - t))
            return v.normalized();
        p = p.add(v.scale(t));
        return point.subtract(p).normalized();

    }

    @Override
    public String toString() {
        return "Cylinder: " + super.toString() + " height = " + _height + "\n";
    }
}
