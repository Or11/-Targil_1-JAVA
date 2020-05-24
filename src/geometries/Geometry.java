package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * interface for a Geometries composite
 *
 * @authors Yossef Matof & Simha Richard
 */
public abstract class Geometry implements Intersectable {

    /**
     * default ctor
     */
    public Geometry() {
        this._emission = Color.BLACK;
    }

    /**
     * ctor
     * @param emission
     */
    public Geometry(Color emission) {
        this._emission = emission;
    }

    /**
     * emission getter
     * @return Color emission
     */
    public Color getEmission() {
        return _emission;
    }

    protected Color _emission;
    public abstract Vector getNormal(Point3D point);
}
