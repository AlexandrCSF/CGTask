package sc.vsu.Kotov;

public class WeightedPoint {

    int x;
    int y;
    int weight;

    WeightedPoint(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    WeightedPoint(int x, int y) {
        this.x = x;
        this.y = y;
        this.weight = 1;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
