package scene;

import elements.*;
import geometries.Geometries;
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
    };

    public String get_name() {
        return _name;
    }

    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }

    public Camera get_camera() {
        return _camera;
    }

    public Color get_background() {
        return _background;
    }

    public double get_distance() {
        return _distance;
    }

    public void set_ambientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public void set_background(Color _background) {
        this._background = _background;
    }

    public void set_camera(Camera _camera) {
        this._camera = _camera;
    }

    public void set_distance(double _distance) {
        this._distance = _distance;
    }

    public void set_geometries(Geometries _geometries) {
        this._geometries = _geometries;
    }


}



