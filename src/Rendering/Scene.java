package Rendering;

import Geometry.GeometryMath;
import Geometry.Ray;
import Geometry.Vector3D;
import Structures.Pair;
import Graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Scene {
    private final ArrayList<GraphicsObject> objects;
    private final ArrayList<Light> lights;
    private Camera camera;

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Scene(ArrayList<GraphicsObject> objects, ArrayList<Light> lights) {
        this.objects = objects;
        this.lights = lights;
        this.camera = new Camera(800, 800, 35, 30d / 180d * Math.PI, 30d / 180d * Math.PI, new Ray(new Vector3D(-400d, 0d, 0d), new Vector3D(1d, 0d, 0d)));
    }

    public Scene() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public void addGraphicalObject(GraphicsObject graphicsObject) {
        objects.add(graphicsObject);
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    public Pair<GraphicsObject, Pair<Boolean, Double>> castRay(final Ray ray) {
        Pair<GraphicsObject, Pair<Boolean, Double>> answer = new Pair<>(new GraphicsObject(), new Pair<>(false, Double.MAX_VALUE));
        for (GraphicsObject object : objects) {
            Pair<Boolean, Double> pair = GeometryMath.rayIntersect(ray, object.getGeometryObject());
            if (pair.getFist() && pair.getSecond() < answer.getSecond().getSecond()) {
                answer = new Pair(object, pair);
            }
        }
        return answer;
    }

    public BufferedImage getFrame() {
        BufferedImage frame = new BufferedImage(camera.getWIDTH(), camera.getHEIGHT(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < frame.getWidth(); ++x)
            for (int y = 0; y < frame.getHeight(); ++y) {
                frame.setRGB(x, y, Color.white.getRGB());
                Pair<GraphicsObject, Pair<Boolean, Double>> pair = castRay(camera.getRay(x, y));
                if (pair.getSecond().getFist())
                    frame.setRGB(x, y, pair.getFist().getMaterial().getColor().getRGB());
            }
        return frame;
    }

    public ArrayList<BufferedImage> getVideo() {
        ArrayList<BufferedImage> video = new ArrayList<>();
        video.add(getFrame());
        return video;
    }

}
