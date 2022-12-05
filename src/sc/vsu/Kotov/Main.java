package sc.vsu.Kotov;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        JFrame f = new JFrame("BURBS");
        int DefaultHeight = 1000;
        int DefaultWidth = 500;
        NURBS nurbsUtil = new NURBS(3,DefaultWidth,DefaultHeight);
        f.setSize(DefaultWidth,DefaultHeight);
        f.setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(nurbsUtil);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
