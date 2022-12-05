package sc.vsu.Kotov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class NURBS extends JPanel implements MouseListener {
    int x;
    int y;
    ArrayList<WeightedPoint> Points = new ArrayList<WeightedPoint>();
    int Degree;

    public NURBS(int degree) {
        addMouseListener(this);
        this.Degree = degree;
    }


    public void paintComponent(Graphics g) {
        g.clearRect(0,0, 800, 600);
        g.setColor(Color.BLACK);
        for (WeightedPoint i: Points){
            g.fillOval(i.getX(), i.getY(), 8, 8);
        }
        //
        if (Points.size() > 2)
            PlotPoints(g, BSplineCurve(calculateKnotVector(), 0.01));
    }

    public void mouseClicked(MouseEvent e) {

        x = e.getX();
        y = e.getY();
        WeightedPoint newPoint = new WeightedPoint(x, y);
        System.out.println("X : " + x + " y : " + y);

        Points.add(newPoint);

        repaint();
    }

    private double Nip(int i, int p, Double[] U, double u) {
        double[] N = new double[p + 1];
        double saved, temp;

        int m = U.length - 1;
        if ((i == 0 && u == U[0]) || (i == (m - p - 1) && u == U[m]))
            return 1;

        if (u < U[i] || u >= U[i + p + 1])
            return 0;

        for (int j = 0; j <= p; j++) {
            if (u >= U[i + j] && u < U[i + j + 1])
                N[j] = 1.0;   //1.d
            else
                N[j] = 0.0;   //0.d
        }

        for (int k = 1; k <= p; k++) {
            if (N[0] == 0)
                saved = 0.0;   //0.d
            else
                saved = ((u - U[i]) * N[0]) / (U[i + k] - U[i]);
            for (int j = 0; j < p - k + 1; j++) {
                double Uleft = U[i + j + 1];
                double Uright = U[i + j + k + 1];

                if (N[j + 1] == 0) {
                    N[j] = saved;
                    saved = 0.0;   //0.d
                } else {
                    temp = N[j + 1] / (Uright - Uleft);
                    N[j] = saved + (Uright - u) * temp;
                    saved = (u - Uleft) * temp;
                }
            }
        }
        return N[0];
    }


    private Double[] calculateKnotVector() {
        int n = Points.size();

        ArrayList<Double> arr = new ArrayList<Double>();

        double m = n + Degree + 1;
        double divisor = m - 1 - 2 * Degree;
            arr.add(0.0);
            for (int i = 1; i < m; i++) {
                if (i <= Degree) {
                    arr.add(0.0);
                } else if (i >= m - Degree - 1) {
                    arr.add(1.0);
                } else {
                    double dividend = i - Degree;
                    arr.add(dividend / divisor);
                }
            }
        return arr.toArray(new Double[0]);
    }


    private void PlotPoints(Graphics g, WeightedPoint[] points) {
        for (int i = 0; i <= points.length - 2; i++) {
            g.drawLine(points[i].getX(), points[i].getY(), points[i + 1].getX(), points[i + 1].getY());
        }
    }



    WeightedPoint RationalBSplinePoint(WeightedPoint[] points, int degree, Double[] KnotVector, double t) {

        double x = 0, y = 0;
        double rationalWeight = 0d;

        for (int i = 0; i < points.length; i++) {
            double temp = Nip(i, degree, KnotVector, t) * points[i].getWeight();
            rationalWeight += temp;
        }

        for (int i = 0; i < points.length; i++) {
            double temp = Nip(i, degree, KnotVector, t);
            x += points[i].getX() * points[i].getWeight() * temp / rationalWeight;
            y += points[i].getY() * points[i].getWeight() * temp / rationalWeight;
        }
        return new WeightedPoint((int) x, (int) y);
    }


    public WeightedPoint[] BSplineCurve(Double[] KnotVector, double StepSize) {
        ArrayList<WeightedPoint> Result = new ArrayList<>();
        WeightedPoint[] arr = Points.toArray(new WeightedPoint[0]);

        for (double i = 0; i < 1; i += StepSize) {
            Result.add(RationalBSplinePoint(arr, Degree, KnotVector, i));
        }

        if ((arr.length > 1) && (!Result.contains(arr[arr.length - 1])))
            Result.add(arr[arr.length - 1]);

        WeightedPoint[] resultArr = new WeightedPoint[Result.size()];
        for (int i = 0; i < Result.size(); i++) {
            resultArr[i] = Result.get(i);
        }

        return resultArr;
    }



    public static double[] toPrimitive(Double[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return null;
        }
        final double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }










































    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}

