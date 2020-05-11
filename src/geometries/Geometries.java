package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * class represent a Geometries composite
 *
 * @authors Yossef Matof & Simha Richard
 */
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
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = null;

        for (Intersectable geo : intersectables) {
            List<GeoPoint> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<GeoPoint>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;

    }


    public void add(Intersectable geometries) {
        this.intersectables.add(geometries);
    }
}
