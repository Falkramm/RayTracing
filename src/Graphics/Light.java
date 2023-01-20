package Graphics;

import Geometry.Vector3D;

public class Light {
    private Vector3D position;
    private Double intensity;

    public Light(Vector3D position, Double intensity) {
        this.position = position;
        this.intensity = intensity;
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Double getIntensity() {
        return intensity;
    }

    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }
}
