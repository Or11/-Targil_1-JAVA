package geometries;

import primitives.*;

import java.util.List;
/**
 * class represent a Geometries that can be intersect by ray
 *
 * @authors Yossef Matof & Simha Richard
 */
public interface Intersectable {
    List<Point3D> findIntersections(Ray ray);
}
