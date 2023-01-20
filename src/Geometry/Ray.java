package Geometry;

import java.util.Objects;

public class Ray extends GeometryObject {
    Vector3D position, direction;

    public Ray(Vector3D position, Vector3D direction) {
        this.position = position;
        this.direction = direction.normalized();
    }

    public Ray(Ray ray) {
        super();
        this.position=ray.getPosition();
        this.direction=ray.getDirection();
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction.normalized();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return position.equals(ray.position) && direction.equals(ray.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "position=" + position +
                ", direction=" + direction +
                '}';
    }

    public void rotateXY(Double angle) {
        direction.rotateXY(angle);
    }

    public void rotateXZ(Double angle) {
        direction.rotateXZ(angle);
    }

    public void rotateYZ(Double angle) {
        direction.rotateYZ(angle);
    }

}
