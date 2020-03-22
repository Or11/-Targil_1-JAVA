package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class represent a tube shape
 */
public class Tube extends RadialGeometry {
    Ray _axisRay;

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
        return _axisRay;
    }


    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + " axisRay = " + _axisRay + "\n";
    }

}
