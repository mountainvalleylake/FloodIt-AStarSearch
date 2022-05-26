package Heuristic1;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by graphics on 10/4/2017.
 */
public class Component {
    Vector<Square> members;
    int color;
    int mem_count;
    int lowesti,lowestj;
    int [] color_track;
    int [] sq_track;
    int [] dist;
    Vector<Integer> highesti;
    Vector<Integer> highestj;
    Vector<Integer> neighbor_index;
    public Component(){
        members = new Vector<>();
        lowesti = 9999;
        lowestj = 9999;
        highesti = new Vector<>();
        highestj = new Vector<>();
        neighbor_index = new Vector<>();
        color_track = new int[7];
        sq_track = new  int[7];
        dist = new int[7];
    }
}
