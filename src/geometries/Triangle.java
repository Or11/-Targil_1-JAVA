package geometries;

import primitives.Point3D;

/**
 * class that represent a Triangle shape
 */
public class Triangle extends Polygon {
    /**
     * constructor with 3 vertices
     *
     * @param verticeA first vertice
     * @param verticeB second vertice
     * @param verticeC third vertice
     */
    public Triangle(Point3D verticeA, Point3D verticeB, Point3D verticeC) {
        super(verticeA, verticeB, verticeC);
    }
}
