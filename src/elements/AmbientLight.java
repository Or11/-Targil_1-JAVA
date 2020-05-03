package elements;

import primitives.Color;

public class AmbientLight {
    private Color _intensity;

    /**
     * constructor
     *
     * @param color color
     * @param iK    intensity
     */
    public AmbientLight(Color color, double iK) {
        this._intensity = color.scale(iK);
    }

    /**
     * color intensity getter
     *
     * @return Color
     */
    public Color getIntensity() {
        return _intensity;
    }
}
