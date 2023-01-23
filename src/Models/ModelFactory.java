package Models;

import Geometry.GeometryMath;
import Geometry.Polygon;
import Geometry.Vector3D;
import Graphics.GraphicsObject;
import Graphics.Material;

import java.awt.*;
import java.util.ArrayList;

import static Geometry.GeometryMath.transport;

public class ModelFactory {
    public static Model3D getCube(Vector3D position, Double size) {
        Model3D cube = new Model3D();
        //Down
        cube.add(new GraphicsObject(transport(new Polygon(new Vector3D(-size, 0d, 0d), new Vector3D(0d, size, 0d), new Vector3D(size, 0d, 0d), new Vector3D(0d, -size, 0d)), position), new Material(Color.red, 1d, 1d, 1d, 1d, 1d, 1d, 0d)));
        //UP
        cube.add(new GraphicsObject(transport(new Polygon(new Vector3D(-size, 0d, size), new Vector3D(0d, size, size), new Vector3D(size, 0d, size), new Vector3D(0d, -size, size)), position), new Material(Color.red, 1d, 1d, 1d, 1d, 1d, 1d, 0d)));
        //Front
        cube.add(new GraphicsObject(transport(new Polygon(new Vector3D(-size, 0d, 0d), new Vector3D(-size, 0d, size), new Vector3D(0d, -size, size), new Vector3D(0d, -size, 0d)), position), new Material(Color.green, 1d, 1d, 1d, 1d, 1d, 1d, 0d)));
        //Back
        cube.add(new GraphicsObject(transport(new Polygon(new Vector3D(0d, size, 0d), new Vector3D(0d, size, size), new Vector3D(size, 0d, size), new Vector3D(size, 0d, 0d)), position), new Material(Color.green, 1d, 1d, 1d, 1d, 1d, 1d, 0d)));
        //Left
        cube.add(new GraphicsObject(transport(new Polygon(new Vector3D(-size, 0d, 0d), new Vector3D(-size, 0d, size), new Vector3D(0d, size, size), new Vector3D(0d, size, 0d)), position), new Material(Color.blue, 1d, 1d, 1d, 1d, 1d, 1d, 0d)));
        //Right
        cube.add(new GraphicsObject(transport(new Polygon(new Vector3D(0d, -size, 0d), new Vector3D(0d, -size, size), new Vector3D(size, 0d, size), new Vector3D(size, 0d, 0d)), position), new Material(Color.blue, 1d, 1d, 1d, 1d, 1d, 1d, 0d)));
        return cube;
    }
}
