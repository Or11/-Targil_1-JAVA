package unittests;

import geometries.Geometries;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

public class GeometriesTest {
    @Test
    public void findIntsersections() {
        Geometries geometries = new Geometries();
        Ray ray = new Ray(new Point3D(1,0,0),new Vector(1,0,0));
        ArrayList a = geometries.findIntersections(ray);

    }

    @Test
    public void add() {
    }
}