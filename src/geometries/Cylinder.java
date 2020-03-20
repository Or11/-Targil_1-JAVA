package geometries;

public class Cylinder extends Tube {
    double _height;

    /**
     * constructor
     * @param radius
     * @param height
     */
    public Cylinder(double radius, double height) {
        super(radius);
        _height = height;
    }
}
