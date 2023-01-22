package Rendering;

import Geometry.Sphere;
import Geometry.Vector3D;
import Graphics.*;
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
    private final Integer WIDTH = 1080, HEIGHT = 760;

    public ScreenPanel() {
        super();
        scene=new Scene();
        scene.addGraphicalObject(new GraphicsObject(new Sphere(new Vector3D(-116.667,-26.666,40d),50d),new Material(Color.darkGray,new Vector3D(1d,0.4d,0d),500d)));
        scene.addGraphicalObject(new GraphicsObject(new Sphere(new Vector3D(-206.667,33.333,10d),15d),new Material(Color.ORANGE,new Vector3D(1.4d,0.9d,0d),300d)));
        scene.addGraphicalObject(new GraphicsObject(new Sphere(new Vector3D(-146.667,-83.333,10d),30d),new Material(Color.YELLOW,new Vector3D(1d,0d,0d),200d)));
        scene.addLight(new LightObject(new Vector3D(-1000.774353,-300.995270,1004.748230),1d));
        scene.addLight(new LightObject(new Vector3D(-500.774353,1000.995270,504.748230),1d));
        setSize(new Dimension(scene.getCamera().getWIDTH(),scene.getCamera().getHEIGHT()));
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
