package Rendering;

import Geometry.Sphere;
import Geometry.Vector3D;
import Graphics.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ScreenPanel extends JPanel {
    private ArrayList<BufferedImage> frames;
    private Scene scene;
    private Integer currentIndex;
    private Timer timerFPS;
    private final Integer FPS = 12;
    private final Integer WIDTH = 1080, HEIGHT = 760;

    public ScreenPanel() {
        super();
        scene=new Scene();
        scene.addGraphicalObject(new GraphicsObject(new Sphere(new Vector3D(56.667,-26.666,40d),50d),new Material(Color.blue)));
        //scene.add(new GraphicsObject(new Sphere(new Vector3D(66.667,-83.333,40d),30d),new Material(Color.red)));
        //scene.add(new GraphicsObject(new Sphere(new Vector3D(16.667,-83.333,10d),30d),new Material(Color.cyan)));
        setSize(new Dimension(scene.getCamera().getWIDTH(),scene.getCamera().getHEIGHT()));
        frames = scene.getVideo();
        currentIndex = 0;
        timerFPS = new Timer(1000 / FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextScreen();
            }
        });
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
