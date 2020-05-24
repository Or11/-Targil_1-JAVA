package elements;

import primitives.Color;

public class AmbientLight extends Light {

    /**
     * constructor
     *
     * @param color color
     * @param iK    intensity
     */
    public AmbientLight(Color color, double iK) {
        super(color.scale(iK));
    }
}
