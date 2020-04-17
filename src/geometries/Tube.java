package geometries;

import primitives.Point3D;
import primitives.Ray;
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
        return _axisRay;
    }


    @Override
    public Vector getNormal(Point3D point) {
        double t = _axisRay.GetDirection().dotProduct(point.subtract(_axisRay.GetPoint()));
        Point3D o = _axisRay.GetPoint().add(_axisRay.GetDirection().scale(t));
        return point.subtract(o).normalized();
    }

    @Override
    public String toString() {
        return super.toString() + " axisRay = " + _axisRay + "\n";
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
