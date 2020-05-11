package geometries;

import primitives.*;

import java.util.List;

/**
 * class represent a Geometries that can be intersect by ray
 *
 * @authors Yossef Matof & Simha Richard
 */
public interface Intersectable {
    List<GeoPoint> findIntersections(Ray ray);

    /**
     * class to represent a point on specific geometry
     */
    public static class GeoPoint {

        private Geometry geometry;
        private Point3D point;

        /**
         * Geometry getter
         *
         * @return geometry
         */
        public Geometry getGeometry() {
            return geometry;
        }

        /**
         * Point getter
         *
         * @return Point3D
         */
        public Point3D getPoint() {
            return point;
        }

        /**
         * constructor
         *
         * @param geometry the geometry on witch the point is on.
         * @param point    the point.
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof GeoPoint)) return false;
            GeoPoint oth = (GeoPoint) obj;
            return this.geometry.equals(oth.getGeometry()) && this.point.equals(oth.getPoint());
        }

    }

}
