package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Interface represent a light sources.
 *
 * @authors Yossef Matof & Simha Richard
 */
public interface LightSource {
    /**
     * get the color intensity effect of the light on a specified point
     *
     * @param p the point
     * @return the color intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * return the vector direction of the light relative to the point p
     *
     * @param p the point
     * @return Vector
     */
    public Vector getL(Point3D p);

}
