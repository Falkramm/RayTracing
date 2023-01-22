package Geometry;

import Graphics.GraphicsObject;
import Graphics.Material;
import Structures.Pair;

import java.util.ArrayList;

public interface Intersect {
    public Pair<Boolean, Double> rayIntersect(final Ray ray);
}
