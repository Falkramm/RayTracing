package Geometry;

import java.util.Objects;

public class Vector3D extends GeometryObject {
    private Double x, y, z;

    public Vector3D(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D() {
        this.x = 0d;
        this.y = 0d;
        this.z = 0d;
    }

    public Vector3D(Vector3D vector3D) {
        this(vector3D.getX(), vector3D.getY(), vector3D.getZ());
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3D vector3D = (Vector3D) o;
        return x.equals(vector3D.x) && y.equals(vector3D.y) && z.equals(vector3D.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Vector3D normalized() {
        Double length = GeometryMath.length(this);
        return new Vector3D(getX() / length, getY() / length, getZ() / length);
    }

    public void normalize() {
        Vector3D normal = normalized();
        setX(normal.getX());
        setY(normal.getY());
        setZ(normal.getZ());
    }
    public void rotateXY(Double angle){
        Double x0=getX();
        Double y0=getY();
        setX(x0*Math.cos(angle)-y0*Math.sin(angle));
        setY(x0*Math.sin(angle)+y0*Math.cos(angle));
    }
    public void rotateXZ(Double angle){
        Double x0=getX();
        Double z0=getZ();
        setX(x0*Math.cos(angle)-z0*Math.sin(angle));
        setZ(x0*Math.sin(angle)+z0*Math.cos(angle));
    }
    public void rotateYZ(Double angle){
        Double y0=getY();
        Double z0=getZ();
        setX(y0*Math.cos(angle)-z0*Math.sin(angle));
        setZ(y0*Math.sin(angle)+z0*Math.cos(angle));
    }
}
