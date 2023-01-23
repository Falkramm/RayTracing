package Models;

import Graphics.GraphicsObject;

import java.util.ArrayList;

public class Model3D {
    private ArrayList<GraphicsObject> objects;

    public Model3D(ArrayList<GraphicsObject> objects) {
        this.objects = objects;
    }
    public Model3D(){
        this(new ArrayList<>());
    }

    public boolean add(GraphicsObject graphicsObject) {
        return objects.add(graphicsObject);
    }

    public GraphicsObject get(int index) {
        return objects.get(index);
    }

    public ArrayList<GraphicsObject> getObjects() {
        return objects;
    }
}
