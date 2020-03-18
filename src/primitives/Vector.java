package primitives;

public class Vector {
    private Point3D head;

    /**
     * constructor Coordinate parameters
     *
     * @param newX
     * @param newY
     * @param newZ
     */
    public Vector(Coordinate newX, Coordinate newY, Coordinate newZ) {
        head = new Point3D(newX, newY, newZ);
    }

    /**
     * constructor double parameters
     *
     * @param newX
     * @param newY
     * @param newZ
     */
    public Vector(double newX, double newY, double newZ) {
        head = new Point3D(newX, newY, newZ);
    }

    /**
     * copy constructor
     *
     * @param other
     */
    public Vector(Vector other) {
        head = new Point3D(other.getHead());
    }

    /**
     * constructor point parameter
     *
     * @param other
     */
    public Vector(Point3D other) {
        head = new Point3D(other);
    }

    /**
     * get head
     *
     * @return
     */
    public Point3D getHead() {
        return head;
    }

    @Override
    public String toString() {
        return head.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Point3D oth = ((Vector) obj).getHead();
        return this.head.getX().equals(oth.getX()) && this.head.getY().equals(oth.getY()) && this.head.getZ().equals(oth.getZ());
    }
}
