package unittests;

import geometries.Cylinder;
import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class CylinderTests {

    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //Test simple cylinder
        Ray r = new Ray(new Point3D(0, 1, 0), new Vector(0, 1, 0));
        Cylinder c1 = new Cylinder(4, r,5);

        assertEquals("not good normal",
                new Vector(1, 0, 0),
                c1.getNormal(new Point3D(4, 3, 0)));

        //another Test
        Ray r1 = new Ray(Point3D.ZERO, new Vector(1, 1, 0));
        Cylinder c2 = new Cylinder(2, r1,4);
        assertEquals("", new Vector(0, 0, 1), c2.getNormal(new Point3D(2, 2, 2)));

        //Test point on the base
        assertEquals(c2.getAxisRay().GetDirection(),c2.getNormal(new Point3D(1,1,0)));

        // =============== Boundary Values Tests ==================

        //Test point not on the Tube
        try {
            Vector v = c2.getNormal(new Point3D(0, 0, 3));
            fail("not thrown Exception.");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        //Test point null
        try {
            c2.getNormal(null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }
}