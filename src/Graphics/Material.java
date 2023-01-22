package Graphics;

import java.awt.*;

public class Material {
    private Color color;
    private Double defuseAlbedo, specularAlbedo, reflectionAlbedo, refractionAlbedo;
    private Double specularExp,reflectionExp;

    public Double getReflectionExp() {
        return reflectionExp;
    }

    public void setReflectionExp(Double reflectionExp) {
        this.reflectionExp = reflectionExp;
    }

    public Double getDefuseAlbedo() {
        return defuseAlbedo;
    }

    public void setDefuseAlbedo(Double defuseAlbedo) {
        this.defuseAlbedo = defuseAlbedo;
    }

    public Double getSpecularAlbedo() {
        return specularAlbedo;
    }

    public void setSpecularAlbedo(Double specularAlbedo) {
        this.specularAlbedo = specularAlbedo;
    }

    public Double getReflectionAlbedo() {
        return reflectionAlbedo;
    }

    public void setReflectionAlbedo(Double reflectionAlbedo) {
        this.reflectionAlbedo = reflectionAlbedo;
    }

    public Double getRefractionAlbedo() {
        return refractionAlbedo;
    }

    public void setRefractionAlbedo(Double refractionAlbedo) {
        this.refractionAlbedo = refractionAlbedo;
    }

    public Double getSpecularExp() {
        return specularExp;
    }

    public void setSpecularExp(Double specularExp) {
        this.specularExp = specularExp;
    }

    public Material(Color color, Double defuseAlbedo, Double specularAlbedo, Double reflectionAlbedo, Double refractionAlbedo, Double specularExp,Double reflectionExp) {
        this.color = color;
        this.defuseAlbedo = defuseAlbedo;
        this.specularAlbedo = specularAlbedo;
        this.reflectionAlbedo = reflectionAlbedo;
        this.refractionAlbedo = refractionAlbedo;
        this.specularExp = specularExp;
        this.reflectionExp=reflectionExp;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
