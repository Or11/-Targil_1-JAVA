package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 * interface for a Geometries composite
 *
 * @authors Yossef Matof & Simha Richard
 */
public interface Geometry extends Intersectable{
    public Vector getNormal(Point3D point);
}
