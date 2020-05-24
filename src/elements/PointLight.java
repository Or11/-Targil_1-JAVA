package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    protected Point3D _position;
    /**
     * _kC :Constant attenuation
     * _kL :Linear attenuation
     * _kQ :Quadratic attenuation
     */
    protected double _kC, _kL, _kQ;
    private int valuesNum = 3;

    /**
     * ctor
     *
     * @param _intensity color intensity
     * @param position   position of light source
     * @param k          3 values kC,kL,kQ not more
     * @throws IllegalArgumentException
     */
    public PointLight(Color _intensity, Point3D position, double... k) throws IllegalArgumentException {
        super(_intensity);
        if (k.length != valuesNum)
            throw new IllegalArgumentException("wrong number of values");
        this._kC = k[0];
        this._kL = k[1];
        this._kQ = k[2];
        this._position = new Point3D(position);
    }

    @Override
    public Color getIntensity(Point3D p) {
        double d = p.distance(_position);
        double dSquared = p.distanceSquared(_position);
        return (_intensity.reduce(_kC + _kL * d + _kQ * dSquared));
    }

    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalized();
    }
}
