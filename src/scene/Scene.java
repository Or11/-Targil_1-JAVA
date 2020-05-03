package scene;

import elements.*;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;


/**
 * class scene represents the actual scene to be built and write to the image
 * the class combines the light source, the background color, the elements in the scene
 * and the position and distance of the viewer from the scene.
 *
 * @author simharichard & yosseef maatuf
 */
public class Scene {
    /**
    * the name of this scene
     */
   private String _name;

    /**
     * the background color for this scene
     */
   private Color _background;

    /**
     * the lightning source end light setting for this scene
     */
   private AmbientLight _ambientLight;

    /**
     * the geometric shapes and elements that will b shown in the scene
     */
   private Geometries _geometries;

    /**
     * the position and settings of the viewpoint on the scene
     */
   private Camera _camera;

    /**
     * the distance between the viewer to the scene
     */
   private double _distance;

    public Scene(String sceneName){
        _geometries = new Geometries();
    };

    public String getName() {
        return _name;
    }

    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Camera getCamera() {
        return _camera;
    }

    public Color getBackground() {
        return _background;
    }

    public double getDistance() {
        return _distance;
    }

    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public void setBackground(Color _background) {
        this._background = _background;
    }

    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    public void setDistance(double _distance) {
        this._distance = _distance;
    }

    public void addGeometries(Intersectable... geometries) {

    }
}



