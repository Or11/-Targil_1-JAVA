package geometries;

import primitives.Color;

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
     * constructor with Color
     *
     * @param emission Color
     * @param radius
     */
    public RadialGeometry(Color emission, double radius) {
        super(emission);
        this._radius = radius;
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
