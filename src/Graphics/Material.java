package Graphics;

import Geometry.Vector3D;

import java.awt.*;

public class Material {
    private Color color;
    private Vector3D albedo;
    private Double specularExp;

    public Vector3D getAlbedo() {
        return albedo;
    }

    public void setAlbedo(Vector3D albedo) {
        this.albedo = albedo;
    }

    public Double getSpecularExp() {
        return specularExp;
    }

    public void setSpecularExp(Double specularExp) {
        this.specularExp = specularExp;
    }

    public Material(Color color, Vector3D albedo, Double specularExp) {
        this.color = color;
        this.albedo = albedo;
        this.specularExp = specularExp;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
