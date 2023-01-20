import Rendering.ScreenPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] arr){
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScreenPanel screenPanel=new ScreenPanel();
        frame.add(screenPanel);
        frame.setSize(screenPanel.getWidth(),screenPanel.getHeight());
        frame.setVisible(true);
    }
}
