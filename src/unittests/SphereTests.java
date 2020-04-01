package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class SphereTests {

    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point3D point = new Point3D(3, 1, 2);

        //Test point on the sphere
        Sphere sp1 = new Sphere(3, point);
        assertEquals("not a good normal",
                new Vector(1, 2, 2).normalized(),
                sp1.getNormal(new Point3D(4, 3, 4)));
        //Test radius < 1
        Sphere sp2 = new Sphere(0.5, point);
        assertEquals("not a good normal",
                new Vector(0, 1, 0),
                sp2.getNormal(new Point3D(3, 1.5, 2)));
        // =============== Boundary Values Tests ==================

        //Test point in the sphere
        try{
            sp1.getNormal(new Point3D(3,0,0));
            fail("point in the sphere");
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }

        //Test point is outside of the sphere
        try{
            sp1.getNormal(new Point3D(0,0,4));
            fail("point is outside of the sphere");
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }

        //Test null point argument
        try{
            sp1.getNormal(null);
            fail("point is null.");
        }catch (Exception e){
            assertTrue(e instanceof NullPointerException);
        }
    }
}