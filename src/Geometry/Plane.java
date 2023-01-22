package Geometry;

import Structures.CompareDouble;
import Structures.Pair;

public class Plane extends GeometryObject {
    private Vector3D position, normal;

    public Plane(Vector3D position, Vector3D normal) {
        this.position = position;
        this.normal = normal.normalized();
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    @Override
    public Vector3D getNormal(Vector3D point) {
        return getNormal();
    }

    public Vector3D getNormal() {
        return normal;
    }

    public void setNormal(Vector3D normal) {
        this.normal = normal;
    }

    @Override
    public Pair<Boolean, Double> rayIntersect(Ray ray) {
        Double A, B, C, D, F, G;
        A = normal.getX();
        B = normal.getY();
        C = normal.getZ();
        D = -(A * position.getX() + B * position.getY() + C * position.getZ());
        F = A * ray.getPosition().getX() + B * ray.getPosition().getY() + C * ray.getPosition().getZ() + D;
        G = A * ray.getDirection().getX() + B * ray.getDirection().getY() + C * ray.getDirection().getZ();
        if (Math.abs(G) >0 && -F / G > 0) {
            return new Pair<>(true, -F / G);
        } else
            return new Pair<>(false, 0d);
    }
}
