package Geometry;

import Structures.Pair;

public interface Intersect {
    public Pair<Boolean, Double> rayIntersect(final Ray ray);
}
