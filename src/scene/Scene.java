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

    /**
     * constructor - create a new empty list of geometries shapes
     * and sets the name
     * @param sceneName
     */
    public Scene(String sceneName){
        _geometries = new Geometries();
        _name = sceneName;
    };

    /**
     * name getter
     * @return _name
     */
    public String getName() { return _name; }

    /**
     * AmbientLight getter
     * @return _ambientLight
     */
    public AmbientLight getAmbientLight() { return _ambientLight; }

    /**
     * Camera getter
     * @return _camera
     */
    public Camera getCamera() { return _camera; }

    /**
     * Background color getter
     * @return _background
     */
    public Color getBackground() { return _background; }

    /**
     * distance getter
     * @return _distance
     */
    public double getDistance() { return _distance; }

    /**
     * Geometries getter
     * @return _geometries
     */
    public Geometries getGeometries() { return _geometries; }

    public void setAmbientLight(AmbientLight ambientLight) { this._ambientLight = ambientLight; }

    public void setBackground(Color background) { this._background = background; }

    public void setCamera(Camera camera) { this._camera = camera; }

    public void setDistance(double distance) { this._distance = distance; }

    public void addGeometries(Intersectable... geometries) {
       if (geometries == null) return;
       for (Intersectable shape : geometries) {
           _geometries.add(shape);
       }
    }
}



