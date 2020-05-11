package geometries;

/**
 * abstract class represent a radial shapes
 *
 * @authors Yossef Matof & Simha Richard
 */
public abstract class RadialGeometry extends Geometry {
    public double _radius;

    /**
     * constructor
     *
     * @param radius
     */
    public RadialGeometry(double radius) {
        _radius = radius;
    }

    /**
     * copy constructor
     *
     * @param newRadialGeometry
     */
    public RadialGeometry(RadialGeometry newRadialGeometry) {
        _radius = newRadialGeometry.getRadius();
    }

    /**
     * _radius getter
     *
     * @return _radius
     */
    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "radius = " + _radius + "\n";
    }
}
