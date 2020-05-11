package unittests;

import geometries.Intersectable;
import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Testing Plane
 *
 * @authors Yossef Matof & Simha Richard
 */

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

    /**
     * Test method for {@link geometries.Plane#findIntersections(Ray ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point3D(1, 1, 0), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane  (1 point)
        List<Intersectable.GeoPoint> result =  plane.findIntersections(new Ray(new Point3D(0, -1, -2),
                new Vector(1, 2, 3)));
        assertEquals("Ray intersects the plane", 1,
               result.size());

        // TC02: Ray does not intersect the plane (0 points)
        assertEquals("Ray intersects the plane", null,
                plane.findIntersections(new Ray(new Point3D(2, 3, 4),
                        new Vector(1, 2, 3))));

        // =============== Boundary Values Tests ==================

        // TC11: Ray is parallel to the plane - included in plane (0 points)
        assertEquals("Ray is parallel to the plane - included in plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 1, 0),
                        new Vector(0, 0, 1))));

        // TC12: Ray is parallel to the plane - not included in plane (0 points)
        assertEquals("Ray is parallel to the plane - not included in plane", null,
                plane.findIntersections(new Ray(new Point3D(2, 1, 0),
                        new Vector(0, 0, 1))));

        // TC13: Ray is orthogonal to the plane - starts before the plane (1 points)
        result =  plane.findIntersections(new Ray(new Point3D(0, 1, 0),
                new Vector(1, 0, 0)));
        assertEquals("Ray is orthogonal to the plane - starts before the plane", 1,
                result.size());
        // TC14: Ray is orthogonal to the plane - starts on the plane (0 points)
        assertEquals("Ray is orthogonal to the plane - starts on the plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 1, 0),
                        new Vector(1, 0, 0))));
        // TC15: Ray is orthogonal to the plane - starts after the plane (0 points)
        assertEquals("Ray is orthogonal to the plane - starts after the plane", null,
                plane.findIntersections(new Ray(new Point3D(2, 1, 0),
                        new Vector(1, 0, 0))));

        // TC16: Ray is neither orthogonal nor parallel to and begins at the plane (0 points)
        assertEquals("Ray is neither orthogonal nor parallel to and begins at the plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 1, 1),
                        new Vector(1, 2, 3))));

        // TC17: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (0 points)
        assertEquals("Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane", null,
                plane.findIntersections(new Ray(new Point3D(1, 1, 0),
                        new Vector(1, 2, 3))));

    }

}