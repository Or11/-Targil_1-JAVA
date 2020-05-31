package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;

import static geometries.Intersectable.*;
import static primitives.Util.alignZero;

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
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx(); //columns
        int Ny = _imageWriter.getNy(); //rows
        //pixels grid
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint).getColor();
                    _imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }
    }

    /**
     * Calculate a color in given point
     *
     * @param p point on a geometry
     * @return Color
     */
    private Color calcColor(GeoPoint p) {
        Color color = _scene.getAmbientLight().getIntensity();
        color = color.add(p.getGeometry().getEmission());
        List<LightSource> lights = _scene.getLights();

        Vector v = p.getPoint().subtract(_scene.getCamera().getLocation()).normalized();
        Vector n = p.getGeometry().getNormal(p.getPoint());

        Material material = p.getGeometry().getMaterial();
        int shine = material.getNShininess();
        double kd = material.getKd();
        double ks = material.getKs();

        if (lights != null)
            for (LightSource light : lights) {

                Vector l = light.getL(p.getPoint());
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));

                if (sign(nl) == sign(nv)) {
                    Color ip = light.getIntensity(p.getPoint());
                    color = color.add(
                            calcDiffusive(kd, nl, ip),
                            calcSpecular(ks, l, n, nl, v, shine, ip));
                }
            }
        return color;

    }

    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int shine, Color ip) {
        double p = shine;

        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(R.dotProduct(v));
        if (minusVR <= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        return ip.scale(ks * Math.pow(minusVR, p));
    }

    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) nl = -nl;
        return ip.scale(nl * kd);
    }

    private boolean sign(double val) {
        return (val > 0d);
    }

    /**
     * Get the closest intersection point of a ray with a geometry
     *
     * @param points list of intersection points
     * @return
     */
    private GeoPoint getClosestPoint(List<GeoPoint> points) {
        GeoPoint result = null;
        double mindist = Double.MAX_VALUE;

        Point3D p0 = this._scene.getCamera().getLocation();

        for (GeoPoint geo : points) {
            Point3D pt = geo.getPoint();
            double distance = p0.distance(pt);
            if (distance < mindist) {
                mindist = distance;
                result = geo;
            }
        }
        return result;
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
