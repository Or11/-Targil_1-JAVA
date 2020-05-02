package primitives;

/**
 * Class for Ray in 3D space point and direction
 *
 * @authors Yossef Matof & Simha Richard
 */
public class Ray {
    private Point3D point;
    private Vector direction;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray oth = (Ray) obj;
        return direction.equals(oth.GetDirection()) && point.equals(oth.GetPoint());
    }

    /**
     * constructor
     *
     * @param newPoint
     * @param unnormlizedVector
     */
    public Ray(Point3D newPoint, Vector unnormlizedVector) {
        point = new Point3D(newPoint);
        direction = new Vector(unnormlizedVector.normalized());
    }

    /**
     * point getter
     *
     * @return point
     */
    public Point3D GetPoint() {
        return new Point3D(point);
    }

    /**
     * direction getter
     *
     * @return direction
     */
    public Vector GetDirection() {
        return new Vector(direction);
    }

    /**
     * get point on the ray
     *
     * @param t scale the vector
     * @return Point3D
     */
    public Point3D getPoint(double t) {
        return point.add(direction.scale(t));
    }

}
