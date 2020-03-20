package geometries;

public abstract class RadialGeometry implements Geometry{
    public double _radius;

    /**
     * constructor
     * @param radius
     */
    public RadialGeometry(double radius) { _radius = radius; }

    /**
     * copy constructor
     * @param newRadialGeometry
     */
    public RadialGeometry(RadialGeometry newRadialGeometry) { _radius = newRadialGeometry.getRadius(); }

    /**
     * _radius getter
     * @return _radius
     */
    public double getRadius() { return _radius;}

    @Override
    public String toString() {
        return "radius = " + _radius + "\n";
    }
}
