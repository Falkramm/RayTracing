package Graphics;

import Geometry.GeometryObject;

public class GraphicsObject {
    private GeometryObject geometryObject;
    private Material material;

    public GraphicsObject(GeometryObject geometryObject, Material material) {
        this.geometryObject = geometryObject;
        this.material = material;
    }

    public GraphicsObject() {
        this(null,null);
    }

    public GeometryObject getGeometryObject() {
        return geometryObject;
    }

    public void setGeometryObject(GeometryObject geometryObject) {
        this.geometryObject = geometryObject;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
