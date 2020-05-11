package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * Class Render
 *
 * @authors Yossef Matof & Simha Richard
 */
public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;

    /**
     * ctor
     *
     * @param imageWriter
     * @param scene
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    /**
     * Scene getter
     *
     * @return Scene
     */
    public Scene getScene() {
        return _scene;
    }

    public void renderImage() {
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        java.awt.Color background = _scene.getBackground().getColor();
        double distance = _scene.getDistance();

        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();

        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();
        Ray ray;

        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<Intersectable.GeoPoint> result = geometries.findIntersections(ray);
                if (result == null)
                    _imageWriter.writePixel(column, row, background);
                else
                    _imageWriter.writePixel(column - 1, row - 1,
                            calcColor(getClosestPoint(result)).getColor());

            }
        }
    }

    /**
     * Calculate a color in given point
     *
     * @param p point on a geometry
     * @return Color
     */
    private Color calcColor(Intersectable.GeoPoint p) {
        return _scene.getAmbientLight().getIntensity();
    }

    /**
     * Get the closest intersection point of a ray with a geometry
     *
     * @param points list of intersection points
     * @return
     */
    private Intersectable.GeoPoint getClosestPoint(List<Intersectable.GeoPoint> points) {
        Point3D p0 = _scene.getCamera().getLocation();
        Intersectable.GeoPoint p = null;
        double currentMin = Double.MAX_VALUE;
        for (Intersectable.GeoPoint point : points) {
            double d = p0.distance(point.getPoint());
            if (d < currentMin) {
                currentMin = d;
                p = point;
            }
        }
        return p;
    }

    /**
     * print a grid
     *
     * @param interval gap between to lines
     * @param color    what color of grid
     */
    public void printGrid(int interval, java.awt.Color color) {
        double rows = this._imageWriter.getNx();
        double columns = _imageWriter.getNy();
        //Writing the lines.
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                if (col % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(row, col, color);
                }
            }
        }
    }

    public void writeToImage() {
        _imageWriter.writeToImage();
    }

}
