package Rendering;

import Geometry.Ray;
import Geometry.Sphere;
import Geometry.Vector3D;
import Structures.Pair;
import Graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Geometry.GeometryMath.*;
import static javax.swing.text.html.HTML.Attribute.N;

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
        this.camera = new Camera(800, 600, 35, 30d / 180d * Math.PI, 30d / 180d * Math.PI, new Ray(new Vector3D(-400d, 0d, 0d), new Vector3D(1d, 0d, 0d)));
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

    public Color castRay(final Ray ray) {
        Pair<GraphicsObject, Pair<Boolean, Double>> answer = new Pair<>(new GraphicsObject(), new Pair<>(false, Double.MAX_VALUE));
        for (GraphicsObject object : objects) {
            Pair<Boolean, Double> pair = rayIntersect(ray, object.getGeometryObject());
            if (pair.getFist() && pair.getSecond() < answer.getSecond().getSecond()) {
                answer = new Pair(object, pair);
            }
        }
        if (!answer.getSecond().getFist())
            return Color.gray;
        Vector3D point = vectorSum(ray.getPosition(), multiply(ray.getDirection(), answer.getSecond().getSecond()));
        Vector3D normal = null;
        if (answer.getFist().getGeometryObject() instanceof Sphere)
            normal = vectorSub(point, ((Sphere) answer.getFist().getGeometryObject()).getPosition()).normalized();
        double defuseLightIntensity = 0d;
        double specularLightIntensity = 0d;
        Material material = answer.getFist().getMaterial();
        for (Light light : lights) {
            Vector3D lightDirection = vectorSub(light.getPosition(), point).normalized();
            assert normal != null;
            defuseLightIntensity += light.getIntensity() * Math.max(0d, scalarMultiply(lightDirection, normal));
            specularLightIntensity += Math.pow(material.getSpecularExp(), Math.max(0.d, scalarMultiply(reflect(lightDirection, normal), ray.getDirection()))) * light.getIntensity();
        }
        Color color = answer.getFist().getMaterial().getColor();
        Vector3D defuseColor = multiply(new Vector3D(color), defuseLightIntensity * answer.getFist().getMaterial().getAlbedo().getX());
        Vector3D specularColor = multiply(new Vector3D(1d, 1d, 1d), specularLightIntensity * answer.getFist().getMaterial().getAlbedo().getY());
        color = vectorSum(defuseColor, specularColor).toColor();
        return color;
    }

    public BufferedImage getFrame() {
        BufferedImage frame = new BufferedImage(camera.getWIDTH(), camera.getHEIGHT(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < frame.getWidth(); ++x)
            for (int y = 0; y < frame.getHeight(); ++y) {
                Color pixelColor = castRay(camera.getRay(x, y));
                frame.setRGB(x, y, pixelColor.getRGB());
            }
        return frame;
    }

    public ArrayList<BufferedImage> getVideo() {
        ArrayList<BufferedImage> video = new ArrayList<>();
        video.add(getFrame());
        return video;
    }

}
