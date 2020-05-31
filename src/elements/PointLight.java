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
     * @param kC         :Constant attenuation
     * @param kL         :Linear attenuation
     * @param kQ         :Quadratic attenuation
     * @throws IllegalArgumentException
     */
    public PointLight(Color _intensity, Point3D position, double kC, double kL, double kQ) throws IllegalArgumentException {
        super(_intensity);
        if (kC < 0 || kL < 0 || kQ < 0)
            throw new IllegalArgumentException("Illegal Arguments");
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
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
