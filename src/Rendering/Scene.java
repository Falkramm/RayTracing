package Rendering;

import Geometry.*;
import Structures.Pair;
import Graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Geometry.GeometryMath.*;

public class Scene {
    private final Integer MAX_REFLECTION_NUMBER = 4;
    private final Color BACKGROUND_COLOR = Color.GRAY;
    private final ArrayList<GraphicsObject> objects;
    private final ArrayList<LightObject> lightObjects;
    private Camera camera;

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Scene(ArrayList<GraphicsObject> objects, ArrayList<LightObject> lightObjects, Integer WIDTH, Integer HEIGHT, Integer FOCAL_LENGTH, Double VIEW_ANGLE_HORIZONTAL, Double VIEW_ANGLE_VERTICAL) {
        this.objects = objects;
        this.lightObjects = lightObjects;
        this.camera = new Camera(WIDTH, HEIGHT, FOCAL_LENGTH, VIEW_ANGLE_HORIZONTAL / 180d * Math.PI, VIEW_ANGLE_VERTICAL / 180d * Math.PI, new Ray(new Vector3D(-400d, 0d, 0d), new Vector3D(1d, 0d, 0d)));
    }

    public Scene() {
        this(new ArrayList<>(), new ArrayList<>(), 800, 600, 35, 30d, 30d);
    }

    public void addGraphicalObject(GraphicsObject graphicsObject) {
        objects.add(graphicsObject);
    }

    public void addLight(LightObject lightObject) {
        lightObjects.add(lightObject);
    }

    private Vector3D sceneIntersect(Ray ray) {
        Vector3D hit = null;
        Double spheresDistance = Double.MAX_VALUE;
        for (GraphicsObject object : objects) {
            Pair<Boolean, Double> inter = object.getGeometryObject().rayIntersect(ray);
            if (inter.getFist() && inter.getSecond() < spheresDistance) {
                spheresDistance = inter.getSecond();
                hit = vectorSum(ray.getPosition(), multiply(ray.getDirection(), spheresDistance));
            }
        }
        if (spheresDistance < 1000)//TODO
            return hit;
        else
            return null;

    }

    public Color castRay(final Ray ray, Integer reflectionStep) {
        Pair<GraphicsObject, Pair<Boolean, Double>> answer = new Pair<>(new GraphicsObject(), new Pair<>(false, Double.MAX_VALUE));
        for (GraphicsObject object : objects) {
            Pair<Boolean, Double> pair = object.getGeometryObject().rayIntersect(ray);
            if (pair.getFist() && pair.getSecond() < answer.getSecond().getSecond()) {
                answer = new Pair(object, pair);
            }
        }
        if (!answer.getSecond().getFist() || reflectionStep >= MAX_REFLECTION_NUMBER)
            return BACKGROUND_COLOR;
        Vector3D point = vectorSum(ray.getPosition(), multiply(ray.getDirection(), answer.getSecond().getSecond()));
        Vector3D normal = null;
        normal=answer.getFist().getGeometryObject().getNormal(point);
        double defuseLightIntensity = 0d;
        double specularLightIntensity = 0d;
        Material material = answer.getFist().getMaterial();
        for (LightObject lightObject : lightObjects) {
            Vector3D lightDirection = vectorSub(lightObject.getPosition(), point).normalized();
            //check shadow
            Double lightDistance = length(vectorSub(lightObject.getPosition(), point));
            Vector3D shadowOrig = scalarMultiply(lightDirection, normal) < 0 ? vectorSub(point, multiply(normal, 1e-3)) : vectorSum(point, multiply(normal, 1e-3)); // checking if the point lies in the shadow of the lights[i]
            Vector3D shadowPt = sceneIntersect(new Ray(shadowOrig, lightDirection));
            if (shadowPt != null && length(vectorSub(shadowPt, shadowOrig)) < lightDistance)
                continue;
            //
            assert normal != null;
            defuseLightIntensity += lightObject.getIntensity() * Math.max(0d, scalarMultiply(lightDirection, normal));
            specularLightIntensity += Math.pow(material.getSpecularExp(), Math.max(0.d, scalarMultiply(reflect(lightDirection, normal, material.getReflectionExp()), ray.getDirection()))) * lightObject.getIntensity();

        }
        Color color = answer.getFist().getMaterial().getColor();
        //get defuse Color
        Vector3D defuseColor = multiply(new Vector3D(color), defuseLightIntensity * answer.getFist().getMaterial().getDefuseAlbedo());
        //get specular Color
        Vector3D specularColor = multiply(new Vector3D(1d, 1d, 1d), specularLightIntensity * answer.getFist().getMaterial().getSpecularAlbedo());
        //get reflectColor
        Vector3D reflectDirection = reflect(ray.getDirection(), normal, material.getReflectionExp()).normalized();
        assert normal != null;
        Vector3D reflectOrigin = scalarMultiply(reflectDirection, normal) < 0 ? vectorSub(point, multiply(normal, 1e-3)) : vectorSum(point, multiply(normal, 1e-3));
        Vector3D reflectColor = multiply(new Vector3D(castRay(new Ray(reflectOrigin, reflectDirection), reflectionStep + 1)), material.getReflectionAlbedo());
        //get refraction
        Vector3D refractDirection = refract(ray.getDirection(), normal, material.getRefractionExp());
        Vector3D refractOrig = scalarMultiply(refractDirection, normal) < 0 ? vectorSub(point, multiply(normal, 1e-3)) : vectorSum(point, multiply(normal, 1e-3));
        Vector3D refractColor = multiply(new Vector3D(castRay(new Ray(refractOrig, refractDirection), reflectionStep + 1)), material.getRefractionExp());
        //get shadow

        //sum colors
        color = vectorSum(vectorSum(vectorSum(defuseColor, specularColor), reflectColor), refractColor).toColor();
        //color=defuseColor.toColor();
        return color;
    }

    public BufferedImage getFrame() {
        BufferedImage frame = new BufferedImage(camera.getWIDTH(), camera.getHEIGHT(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < frame.getWidth(); ++x)
            for (int y = 0; y < frame.getHeight(); ++y) {
                Color pixelColor = castRay(camera.getRay(x, y), 0);
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
