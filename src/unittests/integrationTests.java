package unittests;

import static org.junit.Assert.*;

import elements.Camera;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class integrationTests {
    Camera camera1 = new Camera(Point3D.ZERO,
            new Vector(0, 0, 1),
            new Vector(0, -1, 0));
    Camera camera2 = new Camera(new Point3D(0, 0, -0.5),
            new Vector(0, 0, 1),
            new Vector(0, -1, 0));

    @Test
    public void CameraIntegrationWithSphere() {

        // TC01: integration with sphere1 (2 Points)
        Sphere sph = new Sphere(1, new Point3D(0, 0, 3));

        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = sph.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("Wrong number", 2, count);

        // TC02: integration with sphere2 (18 Points)
        sph = new Sphere(2.5, new Point3D(0, 0, 2.5));
        count = 0;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = sph.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("Wrong number", 18, count);

        // TC03: integration with sphere3 (10 Points)
        sph = new Sphere(2, new Point3D(0, 0, 2));
        count = 0;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = sph.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("Wrong number", 10, count);

        // TC04: integration with sphere4 (9 Points)
        sph = new Sphere(4, new Point3D(0, 0, 2));
        count = 0;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = sph.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("Wrong number", 9, count);

        // TC05: integration with sphere2 (0 Points)
        sph = new Sphere(0.5, new Point3D(0, 0, -1));
        count = 0;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = sph.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("Wrong number", 0, count);
    }

    @Test
    public void CameraIntegrationWithPlane() {

        // TC01: Plane is orthogonal to vTo (9 points)
        Plane plane = new Plane(new Point3D(0, 0, 2),
                camera1.getVTo().scale(-1));
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = plane.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("Wrong number", 9, count);

        // TC02: plane not orthogonal to vTo but all rays intersect (9 Points)
        plane = new Plane(new Point3D(0, 0, 2),
                new Vector(0, -0.5, -1));
        count = 0;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = plane.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("Wrong number", 9, count);

        // TC03: plane not orthogonal to vTo not all rays intersect (6 Points)
        plane = new Plane(new Point3D(0, 0, 2),
                new Vector(0, -1, -1));
        count = 0;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = plane.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("Wrong number", 6, count);
    }

    @Test
    public void CameraIntegrationWithPlygon() {
        // TC01: only center ray intersect with polygon (1 point)
        Polygon polygon = new Polygon(new Point3D(0, -1, 2),
                new Point3D(1, 1, 2),
                new Point3D(-1, 1, 2));

        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = polygon.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("Wrong number", 1, count);

        // TC02: center column to upper rows of rays intersect with polygon (2 point)
        polygon = new Polygon(new Point3D(0, -20, 2),
                new Point3D(1, 1, 2),
                new Point3D(-1, 1, 2));

        count = 0;
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = camera1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3);
                List<Point3D> results = polygon.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("Wrong number", 2, count);

    }
}

