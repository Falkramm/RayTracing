package Geometry;

import Structures.Pair;

import java.util.Objects;

import static Geometry.GeometryMath.vectorSub;

public class Sphere extends GeometryObject{
    Vector3D position;
    Double radius;

    public Sphere(Vector3D position, Double radius) {
        this.position = position;
        this.radius = radius;
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return position.equals(sphere.position) && radius.equals(sphere.radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, radius);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "position=" + position +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Pair<Boolean, Double> rayIntersect(Ray ray) {
        Vector3D L = vectorSub(this.getPosition(),ray.getPosition());
        Double tca = GeometryMath.scalarMultiply(L, ray.getDirection());
        double d2 = GeometryMath.length(L) * GeometryMath.length(L) - tca * tca;
        if (d2 > this.getRadius() * this.getRadius()) return new Pair<>(false, 0d);
        Double thc;
        thc = Math.sqrt(this.getRadius() * this.getRadius() - d2);
        double t0 = tca - thc;
        double t1 = tca + thc;
        if (t0 < 0) t0 = t1;
        if (t0 < 0) return new Pair<>(false, 0d);
        return new Pair<>(true, t0);
    }
}
