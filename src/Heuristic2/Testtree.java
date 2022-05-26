package Heuristic2;

import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by graphics on 10/4/2017.
 */
public class Testtree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int board = scanner.nextInt();
        int passcount = 0;
        int randomNum =0;
        int moves_number = 0;
        int total = 0;
        int[][] init = new int[board][board];
        for(int n=0;n<1000;n++){
            for(int i = 0; i < board; i++){
                for(int j = 0; j < board; j++){
                    randomNum = ThreadLocalRandom.current().nextInt(1, 7);
                    init[i][j] = randomNum;
                    //System.out.println(init[i][j]);
                }
            }
            System.out.println("The problem ");
            for (int i = 0; i < board; i++) {
                for (int j = 0; j < board; j++) {
                    System.out.print(init[i][j]);
                }
                System.out.println();
            }
            TREE tree = new TREE(board,init);
            int colorsaver = 9999;
            while (tree.square < (board * board)){
                for(int i = 0; i < board; i++) {
                    for (int j = 0; j < board; j++) {
                        if(tree.visited[i][j]==0){
                            if(passcount>=1){
                                colorsaver = init[i][j];
                            }
                            //System.out.println("color saver going is: "+colorsaver);
                            tree.construct_Tree(i,j,colorsaver);
                            passcount++;
//                        System.out.println("After pass : "+ passcount);
//                        for(int p = 0; p < board; p++) {
//                            for (int q = 0; q < board; q++) {
//                                System.out.print(init[p][q] + " ");
//                            }
//                            System.out.println();
//                        }
//                        System.out.println();
                        }
                    }
                }
            }
            moves_number = tree.moves;
            System.out.println("Number of moves " + moves_number);
            passcount = 0;
            total += moves_number;
            moves_number = 0;
        }
        int avg = total/1000;
        System.out.println("Average number is: "+ avg);
    }
}
