package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface for a Geometries composite
 *
 * @authors Yossef Matof & Simha Richard
 */
public abstract class Geometry implements Intersectable {

    protected Color _emission;
    protected Material _material;

    /**
     * default ctor
     * Material init with 0,0,0 values
     * Color init with BLACK
     */
    public Geometry() {
        this._emission = Color.BLACK;
        this._material = new Material(0, 0, 0);
    }

    /**
     * ctor with Color parameter
     * Material init with 0,0,0 values
     *
     * @param emission
     */
    public Geometry(Color emission) {
        this._emission = emission;
        this._material = new Material(0, 0, 0);
    }

    /**
     * ctor with Color and Material parameters
     *
     * @param _emission
     * @param _material
     */
    public Geometry(Color _emission, Material _material) {
        this._emission = _emission;
        this._material = _material;
    }

    /**
     * emission getter
     *
     * @return Color emission
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * material getter
     *
     * @return Material
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * get normal to geometry in given point
     *
     * @param point point on geometry
     * @return normalized vector
     */
    public abstract Vector getNormal(Point3D point);
}
