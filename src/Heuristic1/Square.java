package Heuristic1;

/**
 * Created by graphics on 10/12/2017.
 */
public class Square {
    int i;
    int j;
    int color;
    public Square(int i, int j, int color){
        this.i = i;
        this.j = j;
        this.color = color;
        //System.out.println("Created a square");
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
