package unittests;

import org.junit.Assert;
import org.junit.Test;
import primitives.*;

import java.lang.Math.*;

import static org.junit.Assert.*;

/**
 * Testing Vector
 *
 * @authors Yossef Matof & Simha Richard
 */
public class VectorTests {

    /**
     * Test method for
     * {@link primitives.Vector#subtract(primitives.Vector)}
     */
    @Test
    public void subtract() {

        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1.0, 0.0, 1.0);
        Vector v2 = new Vector(0.0, -1.0, -1.0);

        assertEquals(new Vector(1, 1, 2), v1.subtract(v2));
        assertTrue(!(new Vector(1, 1, 1).equals(v1.subtract(v2))));


        // =============== Boundary Values Tests ==================
        try {
            v1.subtract(v1);
            fail("subtract not throwing Exception on Zero Vector result");
        } catch (Exception e) {
            assertTrue("zero Exception", e instanceof IllegalArgumentException);
        }
        assertTrue(v1.subtract(Vector.zero).equals(v1));
    }

    /**
     * Test method for
     * {@link primitives.Vector#add(primitives.Vector)}
     */
    @Test
    public void add() {
        Vector v1 = new Vector(1.0, 0.0, 1.0);
        Vector v2 = new Vector(0.0, -1.0, -1.0);
        Vector v3 = new Vector(-1, 0, -1);
        // ============ Equivalence Partitions Tests ==============

        assertEquals(v1.add(v2), new Vector(1, -1, 0));
        assertEquals(v1.add(v1), new Vector(2, 0, 2));
        // =============== Boundary Values Tests ==================
        try {
            v1.add(v3);
            fail("add not throwing Exception on Zero Vector result");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    /**
     * Test method for
     * {@link primitives.Vector#scale(double)}
     */
    @Test
    public void scale() {
        Vector v1 = new Vector(1.0, 0.0, 1.0);
        // ============ Equivalence Partitions Tests ==============

        assertEquals(v1.scale(2), new Vector(2, 0, 2));
        // =============== Boundary Values Tests ==================
        try {
            v1.scale(0);
            fail("result zero vector Exception not thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    /**
     * Test method for
     * {@link Vector#dotProduct(Vector)}
     */
    @Test
    public void dotProduct() {
        Vector v1 = new Vector(1, 0, 1);


        // ============ Equivalence Partitions Tests ==============
        //Test angle between two Vectors > 90 deg.
        double result = v1.dotProduct(new Vector(-1, 0, 0));
        assertTrue("bad dotProduct result angle > 90 deg.", Util.isZero(result + 1));
        //Test angle between two Vectors < 90 deg.
        result = v1.dotProduct(new Vector(1, 1, 5));
        assertTrue("bad dotProduct result angle < 90 deg.", Util.isZero(result - 6));
        //Test angle between two Vectors == 180 deg.
        result = v1.dotProduct(new Vector(-1, 0, -1));
        assertTrue("bad dotProduct result angle == 180 deg.", Util.isZero(result + 2));
        // =============== Boundary Values Tests ==================

        //orthogonal vectors
        assertTrue("bad dotProduct result orthogonal vectors.", Util.isZero(v1.dotProduct(new Vector(-1, 0, 1))));

        //null argument Exception check
        try {
            v1.dotProduct(null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    /**
     * Test method for
     * {@link Vector#crossProduct(Vector)}
     */
    @Test
    public void crossProduct() {
        Vector v1 = new Vector(1, 0, 1);


        // ============ Equivalence Partitions Tests ==============
        //Test angle between two Vectors >90 deg.
        Vector result = v1.crossProduct(new Vector(-1, 0, 0));
        assertEquals("bad crossProduct result angle > 90 deg.", new Vector(0, -1, 0), result);
        //Test angle between two Vectors <90 deg.
        result = v1.crossProduct(new Vector(1, 1, 5));
        assertEquals("bad crossProduct result angle < 90 deg.", new Vector(-1, -4, 1), result);
        //Test angle between two Vectors == 90 deg.
        result = v1.crossProduct(new Vector(-1, 0, 1));
        assertEquals("bad crossProduct result angle < 90 deg.", new Vector(0, -2, 0), result);

        // =============== Boundary Values Tests ==================
        try {
            //Test Test angle between two Vectors == 180 deg.
            v1.crossProduct(new Vector(-1, 0, -1));
            fail("not thrown Exception on zero vector");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void length() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        assertTrue(Util.isZero(v1.length() - Math.sqrt(14)));
        // =============== Boundary Values Tests ==================

    }

    @Test
    public void normalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        // ============ Equivalence Partitions Tests ==============

        assertTrue("normalized() create new Vector", vCopy == vCopyNormalize);

        vCopy = v.normalized();
        assertTrue("normalized() not create new Vector", vCopy != vCopyNormalize);

        assertTrue("not normalized correctly", Util.isZero(vCopy.length() - 1));
        // =============== Boundary Values Tests ==================

    }
}