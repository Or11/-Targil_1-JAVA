package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class PlaneTests {

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        //Test create with Point3D and Vector
        Plane p1 = new Plane(new Point3D(1, 2, 3), new Vector(1, 2, 3));
        double sqrt = Math.sqrt(14d);
        assertEquals(new Vector(1 / sqrt, 2 / sqrt, 3 / sqrt), p1.getNormal(new Point3D(0, 4, 2)));

        //Test create with 3 Point3D
        sqrt = Math.sqrt(1d / 3);
        Plane p2 = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
        assertEquals(new Vector(1 * sqrt, 1 * sqrt, 1 * sqrt), p2.getNormal(new Point3D(-1, 1, 0)));
        // =============== Boundary Values Tests ==================

        /*try{
            p2.getNormal(new Point3D(1,1,1));
            fail("point not on the plane Exception expected. ");
        }catch (Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }*/
        //Test null point
        try{
            p2.getNormal(null);
            fail("point is null Exception expected. ");
        }catch (Exception e){
            assertTrue(e instanceof NullPointerException);
        }
    }
}