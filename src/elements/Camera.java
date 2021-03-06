package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

public class Camera {
    private Point3D location;
    private Vector vTo, vUp, vRight;

    /**
     * Constructor receive to orthogonal vectors and calculate the third vector.
     *
     * @param location of camera in space
     * @param vTo      vector going to front
     * @param vUp      vector going up
     * @throws IllegalArgumentException if vectors not orthogonal or equal to zero.
     */
    public Camera(Point3D location, Vector vTo, Vector vUp) throws IllegalArgumentException {
        if (vTo.dotProduct(vUp) != 0 || vTo.equals(Vector.zero) || vUp.equals(Vector.zero))
            throw new IllegalArgumentException("Vectors vTo and vUp must be orthogonal to each other.");

        this.vTo = vTo.normalized();
        this.vUp = vUp.normalized();
        this.vRight = vTo.crossProduct(vUp).normalized();
        this.location = new Point3D(location);
    }

    /**
     * Side to side direction vector getter.
     *
     * @return normalized Vector
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Up and down direction vector getter.
     *
     * @return normalized Vector
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Front and back direction vector getter.
     *
     * @return normalized Vector
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Camera location point in space getter.
     *
     * @return Point location
     */
    public Point3D getLocation() {
        return location;
    }

    /**
     * Construct ray through given pixel in the view Plane.
     *
     * @param nX             number of pixel in a row
     * @param nY             number of pixel in a column
     * @param j              index of the pixel in shape of (i,j)
     * @param i              index of the pixel in shape of (i,j)
     * @param screenDistance distance of screen from camera
     * @param screenWidth    actual width of screen
     * @param screenHeight   actual height of screen
     * @return Ray going from camera through center of Pixel(i,j).
     */
    public Ray constructRayThroughPixel(int nX, int nY,
                                        int j, int i, double screenDistance,
                                        double screenWidth, double screenHeight) {
        if (nX == 0 || nY == 0 || screenDistance == 0 || screenHeight == 0 || screenWidth == 0) {
            return null;
        }
        Point3D Pc = location.add(vTo.scale(screenDistance));
        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;
        double Yi = Util.alignZero((i - nY / 2.0) * Ry + (Ry / 2.0));
        double Xj = Util.alignZero((j - nX / 2.0) * Rx + (Rx / 2.0));
        Point3D Pij = new Point3D(Pc);
        if (Xj != 0)
            Pij = Pij.add(vRight.scale(Xj));
        if (Yi != 0)
            Pij = Pij.add(vUp.scale(-Yi));
        Vector Vij = Pij.subtract(location);
        return new Ray(new Point3D(location), Vij.normalized());
    }

}
