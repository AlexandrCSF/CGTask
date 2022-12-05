package sc.vsu.Kotov;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        NURBS nurbsUtil = new NURBS(3);
        JFrame f = new JFrame("BURBS");
        f.setSize(800,600);
        f.setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(nurbsUtil);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
