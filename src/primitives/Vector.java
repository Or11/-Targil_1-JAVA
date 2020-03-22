package primitives;

public class Vector {
    private Point3D head;
    public final static Vector zero = new Vector(0.0, 0.0, 0.0);

    /**
     * constructor Coordinate parameters
     *
     * @param newX
     * @param newY
     * @param newZ
     */
    public Vector(Coordinate newX, Coordinate newY, Coordinate newZ) throws IllegalArgumentException {
        if (newX.equals(0) && newY.equals(0) && newZ.equals(0))
            throw new IllegalArgumentException("zero Vector is illegal");
        head = new Point3D(newX, newY, newZ);
    }

    /**
     * constructor double parameters
     *
     * @param newX
     * @param newY
     * @param newZ
     */
    public Vector(double newX, double newY, double newZ) throws IllegalArgumentException {
        if (newX == 0.0 && newY == 0.0 && newZ == 0.0)
            throw new IllegalArgumentException("zero Vector is illegal");
        head = new Point3D(newX, newY, newZ);
    }

    /**
     * copy constructor
     *
     * @param other
     */
    public Vector(Vector other) {
        if (other == null || other.equals(zero))
            throw new IllegalArgumentException("zero Vector is illegal");
        head = new Point3D(other.getHead());
    }

    /**
     * constructor point parameter
     *
     * @param other
     */
    public Vector(Point3D other) {
        if (other == null || other.equals(new Point3D(0.0, 0.0, 0.0)))
            throw new IllegalArgumentException("Vector is illegal");
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

    /**
     * subtract Vector from Vector
     *
     * @param other Vector
     * @return result Vector
     */
    public Vector subtract(Vector other) {
        return new Vector(this.head.getX().get() - other.head.getX().get(),
                this.head.getY().get() - other.head.getY().get(),
                this.head.getZ().get() - other.head.getZ().get());
    }

    /**
     * add Vector from Vector
     *
     * @param other Vector
     * @return result Vector
     */
    public Vector add(Vector other) {
        return new Vector(this.head.getX().get() + other.head.getX().get(),
                this.head.getY().get() + other.head.getY().get(),
                this.head.getZ().get() + other.head.getZ().get());
    }

    /**
     * scale a vector
     *
     * @param scalar scalar
     * @return new scaled Vector
     */
    public Vector scale(double scalar) {
        return new Vector(this.head.getX().get() * scalar,
                this.head.getY().get() * scalar,
                this.head.getZ().get() * scalar);
    }

    /**
     * Dot product
     *
     * @param other
     * @return double
     */
    public double dotProduct(Vector other) {
        return head.getX().get() * other.head.getX().get() +
                head.getY().get() * other.head.getY().get() +
                head.getZ().get() * other.head.getZ().get();
    }

    /**
     * cross product
     *
     * @param other Vector
     * @return new Vector
     */
    public Vector crossProduct(Vector other) {
        return new Vector(this.head.getY().get() * other.head.getZ().get()
                - this.head.getZ().get() * other.head.getY().get(),
                this.head.getZ().get() * other.head.getX().get()
                        - this.head.getX().get() * other.head.getZ().get(),
                this.head.getX().get() * other.head.getY().get()
                        - this.head.getY().get() * other.head.getX().get());
    }

    /**
     * calculate Vector Squared norma
     *
     * @return double
     */
    public double lengthSquared() {
        return (this.head.getX().get() * this.head.getX().get() +
                this.head.getY().get() * this.head.getY().get() +
                this.head.getZ().get() * this.head.getZ().get());
    }

    /**
     * calculate the vector norma
     *
     * @return double
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * normalize the vector
     *
     * @return the vector after normalization
     */
    public Vector normalize() {
        double size = length();
        this.head = new Point3D(
                this.head.getX().get() / size,
                this.head.getY().get() / size,
                this.head.getZ().get() / size);
        return this;
    }

    /**
     * normalized vector
     *
     * @return normalized copy of the Vector
     */
    public Vector normalized() {
        Vector v = new Vector(this);
        v.normalize();
        return v;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
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
