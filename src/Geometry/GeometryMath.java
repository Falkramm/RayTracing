package Geometry;

import Structures.CompareDouble;
import Structures.Pair;

import java.security.KeyPair;

public class GeometryMath {
    public static Double length(Vector3D u) {
        return Math.sqrt(u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ());
    }

    public static Double length(Vector3D u, Vector3D v) {
        return length(new Vector3D(u.getX() - v.getX(), u.getY() - v.getY(), u.getZ() - v.getZ()));
    }

    public static Vector3D multiply(Vector3D u, Double k) {
        return new Vector3D(u.getX() * k, u.getY() * k, u.getZ() * k);
    }
    public static Vector3D vectorSum(Vector3D u,Vector3D v){
        return new Vector3D(u.getX()+v.getX(),u.getY()+v.getY(),u.getZ()+v.getZ());
    }
    public static Vector3D vectorSub(Vector3D u,Vector3D v){
        return new Vector3D(u.getX()-v.getX(),u.getY()-v.getY(),u.getZ()-v.getZ());
    }
    public static Double scalarMultiply(Vector3D u, Vector3D v) {
        return u.getX() * v.getX() + u.getY() * v.getY() + u.getZ() * v.getZ();
    }

    public static Vector3D vectorMultiply(Vector3D u, Vector3D v) {
        return new Vector3D(u.getY() * v.getZ() - u.getZ() * v.getY(),
                -(u.getX() * v.getZ() - u.getZ() * v.getX()),
                u.getX() * v.getY() - u.getY() * v.getX());
    }

    public static Pair<Boolean, Double> rayIntersect(final Ray ray, final GeometryObject object) {
        if (object instanceof Sphere)
            return rayIntersect(ray, (Sphere) object);
        return new Pair<>(false, 0d);
    }
    public static Vector3D negative(Vector3D u){
        return GeometryMath.vectorSub(new Vector3D(0d,0d,0d),u);
    }
    public static Pair<Boolean, Double> rayIntersect(final Ray ray, final Sphere sphere) {
        Vector3D L = vectorSub(sphere.getPosition(),ray.getPosition());
        Double tca = GeometryMath.scalarMultiply(L, ray.getDirection());
        double d2 = GeometryMath.length(L) * GeometryMath.length(L) - tca * tca;
        if (d2 > sphere.getRadius() * sphere.getRadius()) return new Pair<>(false, 0d);
        Double thc;
        thc = Math.sqrt(sphere.getRadius() * sphere.getRadius() - d2);
        double t0 = tca - thc;
        double t1 = tca + thc;
        if (t0 < 0) t0 = t1;
        if (t0 < 0) return new Pair<>(false, 0d);
        return new Pair<>(true, t0);
    }
    public static Vector3D reflect(final Vector3D u,final Vector3D normal){
        return vectorSub(u,multiply(multiply(normal,2d),scalarMultiply(u,normal)));
    }

}
