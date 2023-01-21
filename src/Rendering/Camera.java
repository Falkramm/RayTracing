package Rendering;

import Geometry.Ray;
import Geometry.Vector3D;

public class Camera {
    private Ray mainRay;

    public Ray getRay(int x, int y) {
        return rays[x][y];
    }

    public Double getVIEW_ANGLE_HORIZONTAL() {
        return VIEW_ANGLE_HORIZONTAL;
    }

    public Double getVIEW_ANGLE_VERTICAL() {
        return VIEW_ANGLE_VERTICAL;
    }

    private Ray[][] rays;
    private final Integer WIDTH, HEIGHT, FOCAL_LENGTH;
    private final Double VIEW_ANGLE_HORIZONTAL, VIEW_ANGLE_VERTICAL;

    public Camera(Integer WIDTH, Integer HEIGHT, Integer FOCAL_LENGTH, Double VIEW_ANGLE_HORIZONTAL, Double VIEW_ANGLE_HEIGHT, Ray ray) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.FOCAL_LENGTH = FOCAL_LENGTH;
        this.VIEW_ANGLE_HORIZONTAL = VIEW_ANGLE_HORIZONTAL;
        this.VIEW_ANGLE_VERTICAL = VIEW_ANGLE_HEIGHT;
        this.mainRay = ray;
        initRays();
    }

    private int gcd(int a, int b) {
        if (a > b)
            return gcd(b, a);
        if (b % a == 0)
            return a;
        return gcd(b % a, a);
    }

    private void initRays() {
        rays = new Ray[WIDTH][HEIGHT];
        double screenWidth = 2 * FOCAL_LENGTH * Math.tan(VIEW_ANGLE_HORIZONTAL / 2);
        double screenHeight = 2 * FOCAL_LENGTH * Math.tan(VIEW_ANGLE_VERTICAL / 2);
        double gcdWH = gcd(WIDTH, HEIGHT);
        for (int i = 0; i < WIDTH; ++i)
            for (int j = 0; j < HEIGHT; ++j) {
                rays[i][j] = new Ray(mainRay.getPosition(), new Vector3D((double) FOCAL_LENGTH, (VIEW_ANGLE_VERTICAL) * (WIDTH / gcdWH) * (mainRay.getPosition().getY() + (i - WIDTH / 2) * screenWidth / WIDTH), (VIEW_ANGLE_HORIZONTAL) * (HEIGHT / gcdWH) * (mainRay.getPosition().getZ() - (j - HEIGHT / 2) * screenHeight / HEIGHT)));
            }
        System.out.println(screenWidth + "===" + screenHeight);
    }

    public Ray getMainRay() {
        return mainRay;
    }

    public void setMainRay(Ray mainRay) {
        this.mainRay = mainRay;
    }

    public Integer getWIDTH() {
        return WIDTH;
    }

    public Integer getHEIGHT() {
        return HEIGHT;
    }

    public Integer getFOCAL_LENGTH() {
        return FOCAL_LENGTH;
    }
}
