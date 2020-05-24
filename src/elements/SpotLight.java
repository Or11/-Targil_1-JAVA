package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {
    protected Vector _direction;


    /**
     * @param _intensity color
     * @param vector     direction
     * @param position   position
     * @param k          3 values kC,kL,kQ not more
     * @throws IllegalArgumentException
     */
    public SpotLight(Color _intensity, Vector vector, Point3D position, double... k) throws IllegalArgumentException {
        super(_intensity, position, k);
        _direction = new Vector(vector);
    }

}
