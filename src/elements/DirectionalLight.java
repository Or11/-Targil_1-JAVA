package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {
    protected Vector _direction;

    /**
     * ctor
     *
     * @param _intensity color intensity of light source
     * @param vector     direction of light
     * @throws IllegalArgumentException in case of Zero Vector
     */
    public DirectionalLight(Color _intensity, Vector vector) throws IllegalArgumentException {
        super(_intensity);
        this._direction = vector.normalized();

    }

    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction.normalized();
    }
}
