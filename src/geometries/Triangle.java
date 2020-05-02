package geometries;

import primitives.Point3D;

/**
 * class that represent a 2D Triangle shape in 3D space
 *
 * @authors Yossef Matof & Simha Richard
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
