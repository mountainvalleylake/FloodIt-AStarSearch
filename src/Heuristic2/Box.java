package Heuristic2;

/**
 * Created by graphics on 10/9/2017.
 */
public class Box {
    int i;
    int j;
    int color;
    public Box(int i, int j, int color){
        this.i = i;
        this.j = j;
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getColor() {
        return color;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
