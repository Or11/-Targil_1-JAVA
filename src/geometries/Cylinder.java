package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
    double _height;

    /**
     * constructor
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
     * @return _height
     */
    public double get_height() {
        return _height;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder: " + super.toString() + " height = " + _height + "\n";
    }
}
