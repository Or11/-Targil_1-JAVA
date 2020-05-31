package primitives;

public class Material {
    private double _kD; //diffusion
    private double _kS; //specular
    private int _nShininess; //shininess

    /**
     * constructor
     *
     * @param _kD         diffusion
     * @param _kS         specular
     * @param _nShininess shininess
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    /**
     * diffusion getter
     *
     * @return diffusion level
     */
    public double getKd() {
        return _kD;
    }

    /**
     * specular getter
     *
     * @return specular level
     */
    public double getKs() {
        return _kS;
    }

    /**
     * shininess getter
     *
     * @return shininess level
     */
    public int getNShininess() {
        return _nShininess;
    }

}
