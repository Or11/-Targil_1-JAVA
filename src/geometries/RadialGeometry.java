package geometries;

public abstract class RadialGeometry {
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
    public RadialGeometry(RadialGeometry newRadialGeometry) { _radius = newRadialGeometry.GetRadius(); }

    /**
     * _radius getter
     * @return _radius
     */
    public double GetRadius() { return _radius;}
}
