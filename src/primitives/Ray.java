package primitives;

public class Ray {
    private Point3D point;
    private Vector direction;

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
