package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

import javax.swing.*;

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
        _direction = vector.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));
        if (Util.isZero(projection))
            return Color.BLACK;
        double max = Math.max(0, projection);
        Color color = super.getIntensity(p);
       /* if (_concentration != 1) {
            factor = Math.pow(factor, _concentration);
        }*/
        return color.scale(max);
    }

}
