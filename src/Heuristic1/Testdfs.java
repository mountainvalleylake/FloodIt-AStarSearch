package Heuristic1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by graphics on 10/4/2017.
 */
public class Testdfs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int board = scanner.nextInt();
        int component_count = 0, result = -1;
        int pass = 0, total = 0;
        int[][] init = new int[board][board];
        int randomNum;
        for (int n = 0; n < 1000; n++) {
            for (int i = 0; i < board; i++) {
                for (int j = 0; j < board; j++) {
                    randomNum = ThreadLocalRandom.current().nextInt(1, 7);
                    init[i][j] = randomNum;
                    //System.out.println(init[i][j]);
                }
            }
            System.out.println("The Problem ");
            for (int i = 0; i < board; i++) {
                for (int j = 0; j < board; j++) {
                    System.out.print(init[i][j]);
                }
                System.out.println();
            }
            DFS dfs = new DFS(board, init);
            //we are getting the components here
            for (int i = 0; i < board; i++) {
                for (int j = 0; j < board; j++) {
                    if (dfs.visited[i][j] == 0) {
                        pass++;
                        System.out.println("A component in "+i + ", "+j);
                        Component component = new Component();
                        component.lowesti = i;
                        component.lowestj = j;
                        component.color = init[i][j];
                        dfs.dfs_component(i, j, component);
                        dfs.componentArrayList.add(component);
                    }
                }
            }
            dfs.component_count = pass;
            dfs.find_neighbors();
            dfs.find_counts();
            result = dfs.merge_components();
            total += result;
            //System.out.println(component_count);
            //int result = component_count - 1;
            System.out.println("We required : " + (pass-1));
            System.out.println(result);
        //   pass = 0;
        }
        int avg = total/1000;
        System.out.println("Average is "+ avg);
    }
}
