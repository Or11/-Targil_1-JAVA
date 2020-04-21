package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {

    private List<Intersectable> intersectables;

    /**
     * default constructor
     */
    public Geometries() {
        this.intersectables = new ArrayList<>();
    }

    /**
     * constructor with List
     *
     * @param intersectables List of intersectables
     */
    public Geometries(List<Intersectable> intersectables) {
        this.intersectables = intersectables;
    }

    /**
     * all the intersection points of the ray with the geometries in the collection
     *
     * @param ray
     * @return ArrayList of all the points
     */
    public ArrayList<Point3D> findIntersections(Ray ray) {
        return null;
    }


    public void add(Intersectable geometries) {
        this.intersectables.add(geometries);
    }
}
