package primitives;

import java.lang.Math.*;

public class Point3D {
    private Coordinate x, y, z;

    /**
     * static zero point
     */
    public static final Point3D ZERO = new Point3D(0, 0, 0);

    /**
     * constructor Coordinate parameters
     *
     * @param newX
     * @param newY
     * @param newZ
     */
    public Point3D(Coordinate newX, Coordinate newY, Coordinate newZ) {
        x = new Coordinate(newX);
        y = new Coordinate(newY);
        z = new Coordinate(newZ);
    }

    /**
     * constructor double parameters
     *
     * @param newX
     * @param newY
     * @param newZ
     */
    public Point3D(double newX, double newY, double newZ) {
        x = new Coordinate(newX);
        y = new Coordinate(newY);
        z = new Coordinate(newZ);
    }

    /**
     * copy constructor
     *
     * @param other
     */
    public Point3D(Point3D other) {
        x = new Coordinate(other.getX());
        y = new Coordinate(other.getY());
        z = new Coordinate(other.getZ());
    }

    /**
     * get x
     *
     * @return x
     */
    public Coordinate getX() {
        return x;
    }

    /**
     * get y
     *
     * @return y
     */
    public Coordinate getY() {
        return y;
    }

    /**
     * get z
     *
     * @return z
     */
    public Coordinate getZ() {
        return z;
    }

    /**
     * vector subtract point from a point
     *
     * @param other point that will be the start of the vector
     * @return Vector from other to this
     */
    public Vector subtract(Point3D other) {
        return new Vector(x.get() - other.getX().get(),
                y.get() - other.getY().get(),
                z.get() - other.getZ().get());
    }

    /**
     * add vector to a point
     *
     * @param vector
     * @return Point3D
     */
    public Point3D add(Vector vector) {
        Point3D temp = vector.getHead();
        return new Point3D(x.get() + temp.getX().get(),
                y.get() + temp.getY().get(),
                z.get() + temp.getZ().get());
    }

    /**
     * norm of a vector between two points
     *
     * @param other
     * @return
     */
    public double distanceSquared(Point3D other) {
        double _x = other.getX().get() - x.get();
        double _y = other.getY().get() - y.get();
        double _z = other.getZ().get() - z.get();
        return _x * _x + _y * _y + _z * _z;
    }

    /**
     * Norm Square Root
     *
     * @param other
     * @return
     */
    public double distance(Point3D other) {
        return Math.sqrt(distanceSquared(other));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D) obj;
        return x.equals(oth.getX()) && y.equals(oth.getY()) && z.equals(oth.getZ());
    }

    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", x.get(), y.get(), z.get());
    }
}
