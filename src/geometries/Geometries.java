package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {

    private List<Intersectable> intersectables;

    public ArrayList<Point3D> findIntersections(Ray ray) {
        return null;
    }


    public Geometries(List<Intersectable> intersectables) {
        this.intersectables = intersectables;
    }

    public Geometries() {
        this.intersectables = new ArrayList<>();
    }

    public void add(Intersectable geometries){
        this.intersectables.add(geometries);
    }
}
