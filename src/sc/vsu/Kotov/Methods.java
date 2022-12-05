package sc.vsu.Kotov;

import java.awt.*;

public class Methods {

    private static int round(double num){
        return (int) Math.round(num + 0.5);
    }
    private static void swap(int num1,int num2){
        int tmp = num1;
        num1 = num2;
        num2 = tmp;
    }

    public static double floatPart(double num){
    return num - Math.floor(num);
    }

    public static void putPixel(Graphics g, int x, int y,double c){
        Color prevColor = g.getColor();
        int maxColorNum = 255;
        g.setColor(new Color((int) (maxColorNum*(1-c)),(int) (maxColorNum*(1-c)),(int) (maxColorNum*(1-c))));
        g.drawLine(x,y,x,y);
        g.setColor(prevColor);
    }

    public static void putPixel(Graphics g, int x, int y){
        g.drawLine(x,y,x,y);
    }


        public static void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {

            double dx = (x1 - x2);
            double dy = (y1 - y2);
            int step = 0;
            if (Math.abs(dx) >= Math.abs(dy)) {
                step = (int) Math.abs(dx);
            } else {
                step = (int) Math.abs(dy);
            }

            dx = -(dx / step);
            dy = -(dy / step);
            int i = 0;
            double x = x1;
            double y = y1;

            while (i <= step){
                putPixel(g2d,(int)Math.round(x),(int)Math.round(y));
                x += dx;
                y += dy;
                ++i;
            }
        }



    public static void paintLineColorInterpolation(Graphics2D g2d,int x1, int y1, Color c1, int x2, int y2, Color c2){

        int startR = c1.getRed();
        int startG = c1.getGreen();
        int startB = c1.getBlue();
        int endR = c2.getRed();
        int endG = c2.getGreen();
        int endB = c2.getBlue();
        int step;

        double dx = x2 - x1;
        double dy = y2 - y1;
        double path = (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double pathPassed;
        double pathRatio;
        Color currColor = c1;

        if (Math.abs(dx) >= Math.abs(dy)) {
            step = (int) Math.abs(dx);
        } else {
            step = (int) Math.abs(dy);
        }

        dx = -(dx / step);
        dy = -(dy / step);
        int i = 0;
        int x = x1;
        int y = y1;

        while (i <= step){
            g2d.setColor(currColor);
            putPixel(g2d,(int)Math.round(x),(int)Math.round(y));
            x += dx;
            y += dy;
            ++i;
            pathPassed = Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
            pathRatio = pathPassed / path;
            currColor = new Color((int) (startR + pathRatio*(endR - startR)),(int) (startG + pathRatio*(endG - startG)),(int) (startB + pathRatio*(endB - startB)));
        }
    }
}
