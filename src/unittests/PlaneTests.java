package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class PlaneTests {

    @Test
    public void getNormal() {
        Plane p1 = new Plane(new Point3D(1,2,3),new Vector(1,2,3));
        // ============ Equivalence Partitions Tests ==============
        // =============== Boundary Values Tests ==================
    }
}