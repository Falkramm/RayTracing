package Geometry;

import java.util.Objects;

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
}
