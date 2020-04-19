package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing Sphere
 *
 * @authors Yossef Matof & Simha Richard
 */
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

       /* //Test point in the sphere
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
        }*/

        //Test null point argument
        try {
            sp1.getNormal(null);
            fail("point is null.");
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                        new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX().get() > result.get(1).getX().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p2), result);

        // TC04: Ray starts after the sphere (0 points)
        assertEquals("Ray's start point out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(2, 1, 0),
                        new Vector(3, 1, 0))));


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(p1, new Vector(3, 1, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p2), result);

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray's start point the sphere and go outside", null,
                sphere.findIntersections(new Ray(p2, new Vector(3, 1, 0))));

        // **** Group: Ray's line goes through the center

        p1 = new Point3D(0.29, 0.71, 0);
        p2 = new Point3D(1.71, -0.71, 0);

        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point3D(0, 1, 0),
                new Vector(1, -1, 0)));

        assertEquals("Wrong number of points", 2, result.size());

        if (result.get(0).getX().get() > result.get(1).getX().get())
            result = List.of(result.get(1), result.get(0));

        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(p1, new Vector(1, -1, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p2), result);

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0),
                new Vector(1, -1, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p2), result);

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(sphere.getCenterPoint(),
                new Vector(1, -1, 0)));

        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(p2), result);

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray's start point on the sphere and go outside", null,
                sphere.findIntersections(new Ray(p2, new Vector(1, -1, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertEquals("Ray's start point the sphere and go outside", null,
                sphere.findIntersections(new Ray(new Point3D(2, -1, 0),
                        new Vector(1, -1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        assertEquals(null, sphere.findIntersections(new Ray(new Point3D(0, 2.42, 0),
                new Vector(1, -1, 0))));

        // TC20: Ray starts at the tangent point
        assertEquals(null, sphere.findIntersections(new Ray(new Point3D(1.71, 0.71, 0),
                new Vector(1, -1, 0))));

        // TC21: Ray starts after the tangent point
        assertEquals(null, sphere.findIntersections(new Ray(new Point3D(2.42, 0, 0),
                new Vector(1, -1, 0))));

        // **** Group: Special cases

        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertEquals(null, sphere.findIntersections(new Ray(new Point3D(0, 1.5, 0),
                new Vector(1, 0, 0))));
    }

}