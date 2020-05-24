package elements;

import primitives.Color;

/**
 * abstract class of lighting source
 *
 * @authors Yossef Matof & Simha Richard
 */
abstract class Light {
    /**
     * color of light source
     */
    protected Color _intensity;

    /**
     * constructor
     *
     * @param _intensity
     */
    public Light(Color _intensity) {
        this._intensity = _intensity;
    }

    /**
     * default ctor
     */
    public Light() { this._intensity = Color.BLACK; }

    /**
     * Intensity getter
     *
     * @return color
     */
    public Color getIntensity() {
        return _intensity;
    }


}
