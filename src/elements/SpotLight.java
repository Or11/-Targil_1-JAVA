package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

import javax.swing.*;

public class SpotLight extends PointLight {
    protected Vector _direction;
    protected double _concentration = 1; // how much the light is concentrate


    /**
     * @param _intensity color
     * @param vector     direction
     * @param position   position
     * @param kC         :Constant attenuation
     * @param kL         :Linear attenuation
     * @param kQ         :Quadratic attenuation
     * @throws IllegalArgumentException
     */
    public SpotLight(Color _intensity, Point3D position, Vector vector, double kC, double kL, double kQ) throws IllegalArgumentException {
        super(_intensity, position, kC, kL, kQ);
        _direction = vector.normalized();
    }

    /**
     * constructor with a concentration parameter
     *
     * @param _intensity    color
     * @param vector        direction
     * @param position      position
     * @param concentration how much the light is concentrate values >=1
     * @param kC            :Constant attenuation
     * @param kL            :Linear attenuation
     * @param kQ            :Quadratic attenuation
     * @throws IllegalArgumentException
     */
    public SpotLight(Color _intensity, Point3D position, Vector vector, double kC, double kL, double kQ, double concentration) throws IllegalArgumentException {
        super(_intensity, position, kC, kL, kQ);
        if (concentration < 0)
            throw new IllegalArgumentException("illegal argument");
        _concentration = concentration;
        _direction = vector.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));
        if (Util.isZero(projection))
            return Color.BLACK;
        double max = Math.max(0, projection);
        Color color = super.getIntensity(p);
        if (_concentration != 1) {
            max = Math.pow(max, _concentration); //define how concentrate the light
        }
        return color.scale(max);
    }

}
