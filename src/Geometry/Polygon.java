package Geometry;

import Structures.CompareDouble;
import Structures.Pair;

import static Geometry.GeometryMath.*;

public class Polygon extends GeometryObject {
    private Vector3D A, B, C, D;
    private Plane plane;
    private Double S2;

    public Polygon(Vector3D a, Vector3D b, Vector3D c, Vector3D d) {
        A = a;
        B = b;
        C = c;
        D = d;
        calculatePlane();
    }
    private void calculatePlane(){
        Vector3D normal1, normal2;
        normal1 = vectorMultiply(vectorSub(B, A), vectorSub(D, A));
        normal2 = vectorMultiply(vectorSub(B, C), vectorSub(D, C));
        if (length(vectorMultiply(normal1, normal2)) != 0)
            throw new IllegalArgumentException("Point is not on one plane!!!");
        plane = new Plane(A, normal1);
        S2 = length(vectorMultiply(vectorSub(B, A), vectorSub(D, A))) + length(vectorMultiply(vectorSub(B, C), vectorSub(D, C)));
    }
    @Override
    public Pair<Boolean, Double> rayIntersect(Ray ray) {
        Pair<Boolean, Double> pair = plane.rayIntersect(ray);
        if (!pair.getFist())
            return new Pair<>(false, 0d);
        Vector3D point = vectorSum(ray.getPosition(), multiply(ray.getDirection(), pair.getSecond()));
        Double Sab, Sbc, Scd, Sda;
        Sab = length(vectorMultiply(vectorSub(A, point), vectorSub(B, point)));
        Sbc = length(vectorMultiply(vectorSub(B, point), vectorSub(C, point)));
        Scd = length(vectorMultiply(vectorSub(C, point), vectorSub(D, point)));
        Sda = length(vectorMultiply(vectorSub(D, point), vectorSub(A, point)));
        if (CompareDouble.equal(Sab + Sbc + Scd + Sda, S2))
            return pair;
        else
            return new Pair<>(false, 0d);
    }

    @Override
    public Vector3D getNormal(Vector3D point) {
        return plane.getNormal();
    }

    public Vector3D getA() {
        return A;
    }

    public Vector3D getB() {
        return B;
    }

    public Vector3D getC() {
        return C;
    }

    public Vector3D getD() {
        return D;
    }
}
