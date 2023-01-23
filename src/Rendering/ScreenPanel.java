package Rendering;

import Geometry.Plane;
import Geometry.Sphere;
import Geometry.Vector3D;
import Graphics.*;
import Models.ModelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ScreenPanel extends JPanel {
    private final ArrayList<BufferedImage> frames;
    private final Scene scene;
    private Integer currentIndex;
    private final Timer timerFPS;
    private final Integer FPS = 12;
    private final Integer WIDTH = 800, HEIGHT = 600;

    public ScreenPanel() {
        super();
        scene = new Scene(new ArrayList<>(),new ArrayList<>(),WIDTH,HEIGHT,35,30d,30d);
        //Polygons
//        scene.addGraphicalObject(new GraphicsObject(new Polygon(new Vector3D(-300d,10d,0d),new Vector3D(-300d,50d,0d),new Vector3D(-350d,30d,-20d),new Vector3D(-350d,-10d,-20d)),new Material(Color.cyan.darker().darker(),1d,0d,0d,1d,0d,0d,1d)));
//        scene.addGraphicalObject(new GraphicsObject(new Polygon(new Vector3D(-200d,70d,15d),new Vector3D(-200d,120d,15d),new Vector3D(-300d,50d,0d),new Vector3D(-300d,10d,0d)),new Material(Color.BLACK.darker().darker().darker().darker(),0.3d,0d,1d,0d,0d,1d,0d)));
        //Models
        scene.addModel3D(ModelFactory.getCube(new Vector3D(-160d,20d,90d),50d));
        //Planes
        scene.addGraphicalObject(new GraphicsObject(new Plane(new Vector3D(0d,0d,-10d),new Vector3D(0d,0d,1d)),new Material(Color.blue.darker(),1d,0d,0d,0d,0d,0d,0d)));
        scene.addGraphicalObject(new GraphicsObject(new Plane(new Vector3D(100d,0d,0d),new Vector3D(-1d,0d,0d)),new Material(Color.red.darker(),1d,0d,0d,0d,0d,0d,0d)));
        scene.addGraphicalObject(new GraphicsObject(new Plane(new Vector3D(0d,200d,0d),new Vector3D(0d,1d,0d)),new Material(Color.GREEN.darker(),1d,0d,0d,0d,0d,0d,0d)));
        //Spheres
        scene.addGraphicalObject(new GraphicsObject(new Sphere(new Vector3D(-116.667, -26.666, 40d), 50d), new Material(Color.darkGray, 1d, 0.4d, 0d, 0d, 500d,1d,0d)));
        scene.addGraphicalObject(new GraphicsObject(new Sphere(new Vector3D(-216.667, -66.666, 70d), 50d), new Material(Color.RED, 1d, 0.4d, 0d, 0d, 500d,1d,0d)));
        scene.addGraphicalObject(new GraphicsObject(new Sphere(new Vector3D(-206.667, 33.333, 10d), 15d), new Material(Color.ORANGE, 1.4d, 0.9d, 0d, 0d, 300d,1d,0d)));
        scene.addGraphicalObject(new GraphicsObject(new Sphere(new Vector3D(-246.667, 3.333, 10d), 15d), new Material(Color.YELLOW, 0.3d, 0d, 0.3d, 0.5d, 200d,0.2d,0.7d)));
        scene.addGraphicalObject(new GraphicsObject(new Sphere(new Vector3D(-106.667, 106.666, 80d), 50d), new Material(Color.darkGray, 1d, 1d, 1d, 0d, 300d,1d,0d)));
        //Light
        scene.addLight(new LightObject(new Vector3D(-1000.774353, -300.995270, 1004.748230), 1d));
        scene.addLight(new LightObject(new Vector3D(-500.774353, 1000.995270, 504.748230), 1d));
        setSize(new Dimension(scene.getCamera().getWIDTH(), scene.getCamera().getHEIGHT()));
        frames = scene.getVideo();
        currentIndex = 0;
        timerFPS = new Timer(1000 / FPS, e -> nextScreen());
    }

    public void startTimer() {
        timerFPS.start();
    }

    public void stopTimer() {
        timerFPS.stop();
    }

    private void nextScreen() {
        currentIndex = (currentIndex + 1) % frames.size();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(frames.get(currentIndex), 0, 0, this);
    }
}
