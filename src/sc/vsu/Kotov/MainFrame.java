package sc.vsu.Kotov;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel panelMain;

    @Override
    public void paint(Graphics gr) {
        Graphics2D g2d = (Graphics2D) gr;

    }

    public MainFrame(){
        this.setTitle("CGTask");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        repaint();
    }

}
