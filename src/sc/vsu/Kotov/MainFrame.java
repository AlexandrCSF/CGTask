package sc.vsu.Kotov;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private JPanel panelMain;

    final int DefaultHeight = 1000;
    final int DefaultWidth = 1000;

    @Override
    public void paint(Graphics gr) {
        Graphics2D g2d = (Graphics2D) gr;
        Methods.paintLineColorInterpolation(g2d,400,300,new Color(100, 15, 200),500,600,new Color(0,205,50));
    }
    public MainFrame(){
        this.setTitle("CGTask");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(DefaultWidth,DefaultHeight);

    }
}
