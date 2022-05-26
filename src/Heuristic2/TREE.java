package Heuristic2;

import java.util.*;


/**
 * Created by graphics on 10/5/2017.
 */
public class TREE {
    public int board;
    public int[][] visited;
    public int[][] init;
    public int cost = 0,moves = 0,square=0;
    int newc;
    private int path_cost1 = 0,path_cost2 = 0,path_cost3 = 0,path_cost4 = 0;
    private int ipath1 = 0,ipath2 = 0,ipath = 0;
    private int nexti = 0,nexti1 = 0,nexti2 = 0,nextj = 0,nextj1 = 0,nextj2 = 0;
    public List<Box> queue = new ArrayList<Box>();
    //public List<Box> different = new ArrayList<Box>();
    public TREE(int board, int[][] init){
        this.init = init;
        this.board = board;
        visited = new int[board][board];
    }
    public void construct_Tree(int i, int j,int colortobe){
        if(i<0 || j<0 || i>=board || j>=board) {//the incoming number is out of range
            return;
        }
        if(visited[i][j]==1){//already visited
            return;
        }
        visited[i][j] = 1;
        square++;
        if(j-1>=0){
            if(init[i][j]==init[i][j-1]){//neighbors within range
                path_cost1 = (int) Math.sqrt((board-j+1)^2 + (board-i)^2); // add zero
                //Todo: recursively color same colored component
                //color_change(i,j-1,init[i][j]);
            }
            else{
                path_cost1 = 1 + (int) Math.sqrt((board-j+1)^2 + (board-i)^2);
            }
            //nexti = i;
            //nextj = j-1;
        }
        else{
            path_cost1 = 99999;
        }
        if(j+1<board){
            if(init[i][j]==init[i][j+1]){//neighbors within range
                path_cost2 = (int) Math.sqrt((board-j-1)^2 + (board-i)^2);//add zero
                //Todo: recursively color same colored component
                //color_change(i,j+1,init[i][j]);
            }
            else{
                path_cost2 = 1 + (int) Math.sqrt((board-j+1)^2 + (board-i)^2);
            }
            //nexti = i;
            //nextj = j+1;
        }
        else {
            path_cost2 = 99999;
        }
        if(i-1>=0) {//neighbors within range
            if (init[i][j] == init[i - 1][j]) {
                path_cost3 = (int) Math.sqrt((board-j)^2 + (board-i+1)^2);//add zero
                //Todo: recursively color same colored component
                //color_change(i-1,j,init[i][j]);
            }
            else {
                path_cost3 = 1 + (int) Math.sqrt((board-j)^2 + (board-i+1)^2);
            }
            //nexti = i-1;
            //nextj = j;
        }
        else{
            path_cost3 = 99999;
        }
        if(i+1<board) {
            if (init[i][j] == init[i + 1][j]) {
                path_cost4 = (int) Math.sqrt((board-j)^2 + (board-i-1)^2);//add zero
                //Todo: recursively color same colored component
                //color_change(i+1,j,init[i][j]);
            }
            else {
                path_cost4 = 1 + (int) Math.sqrt((board-j)^2 + (board-i-1)^2);
            }
        }
        else{
            path_cost4 = 99999;
        }
        //nexti = i+1;
        //nextj = j;
        if(path_cost1 < path_cost3){
            ipath1 = path_cost1;
            nexti1 = i;
            nextj1 = j-1;
        }
        else{
            ipath1 = path_cost3;
            nexti1 = i-1;
            nextj1 = j;
        }
        if(path_cost2 < path_cost4){
            ipath2 = path_cost2;
            nexti2 = i;
            nextj2 = j+1;
        }
        else {
            ipath2 = path_cost4;
            nexti2 = i+1;
            nextj2 = j;
        }
        //getting the minimum path cost
        if(ipath1<ipath2){
            ipath = ipath1;
            nexti = nexti1;
            nextj = nextj1;
        }
        else{
            ipath = ipath2;
            nexti = nexti2;
            nextj = nextj2;
        }
        cost += ipath;
        // add those different queue things in this queue
        //mark them as visited

//        Iterator iterator = different.iterator();
//        while (iterator.hasNext()){
//            Box box = (Box) iterator.next();
//            if(!queue.contains(box)){
//                queue.add(box);
//            }
//        }
//        visited[nexti][nextj] = 0;
        Box b = new Box(i,j,init[i][j]);
        if(!queue.contains(b)){
            queue.add(b);
        }
        //System.out.println("The next index are " + nexti + " "+ nextj);
        //System.out.println("Now queue size is : outside " + queue.size());
        //System.out.println("Now color: "+ init[i][j]+ " Next color: "+ init[nexti][nextj]);
        if(init[i][j] != init[nexti][nextj]){
            moves +=1;
            System.out.println("Now color: "+ init[i][j]+ " Next color: "+ init[nexti][nextj]);
            System.out.println("Move number " + moves );
            if(colortobe != 9999){//nijer tar color e color koro
                newc = init[i][j];
                //System.out.println("New color is: colortobe "+ newc);
            }
            else {//pasher tar color e color koro
                newc = init[nexti][nextj];
                //System.out.println("New color is: nexts "+newc);
            }
            Vector<Box> same = new Vector<>(queue);
            Enumeration e = same.elements();
            while(e.hasMoreElements()){
                Box bb = (Box) e.nextElement();
                int samec = bb.getColor();
                int ni = bb.getI();
                int nj = bb.getJ();
                color_change(ni-1,nj,samec);
                color_change(ni+1,nj,samec);
                color_change(ni,nj-1,samec);
                color_change(ni,nj+1,samec);
            }
            Vector<Box> helper = new Vector<Box>(queue);
            Enumeration en = helper.elements();
            while (en.hasMoreElements()){
                Box dummy = (Box) en.nextElement();
                //System.out.println("The new color to be is: " +newc);
                int newi = dummy.getI();
                //System.out.println("The i got iterating " + newi );
                int newj = dummy.getJ();
                //System.out.println("The j got iterating " + newj );
                queue.remove(dummy);
                //System.out.println("Now queue size is : " + queue.size());
                init[newi][newj] = newc;
                queue.add(new Box(newi,newj,newc));
                //System.out.println("Now queue size is : " + queue.size() + " after adding");
                recursive_color(newc,newi,newj-1,nexti,nextj);
                recursive_color(newc,newi,newj+1,nexti,nextj);
                recursive_color(newc,newi-1,newj,nexti,nextj);
                recursive_color(newc,newi+1,newj,nexti,nextj);
            }
            helper.removeAllElements();
            same.removeAllElements();
            System.out.println("After each move : "+ moves);
            for(int p = 0; p < board; p++) {
                for (int q = 0; q < board; q++) {
                    System.out.print(init[p][q] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
//        else{
//            newc = init[i][j];
//            System.out.println("Same color in recursion: "+ newc);
//        }
        //Box b = new Box(i,j,init[i][j]);

//        for(int p = 0; p < board; p++) {
//            for (int q = 0; q < board; q++) {
//                System.out.print(init[p][q] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        construct_Tree(nexti,nextj,colortobe);//whole pass jure colortobe ektai thakbe
    }
    public void color_change(int i, int j, int color){
        if(i<0 || j<0 || i>=board || j>=board) {//the incoming number is out of range
            //System.out.println("hellow");
            return ;
        }
        if(visited[i][j]==1){//already visited
            //System.out.println("Its me");
            return ;
        }
        if(init[i][j]==color){
            square++;
            visited[i][j] = 1;
            queue.add(new Box(i,j,color)); // add in a different queue

            color_change(i-1,j,color);
            color_change(i+1,j, color);
            color_change(i,j-1,color);
            color_change(i,j+1,color);
        }
    }
    public void recursive_color(int color,int i, int j,int nexi,int nexj){
        if(i<0 || j<0 || i>=board || j>=board) {//the incoming number is out of range
            //System.out.println("hellow");
            return ;
        }
        if(visited[i][j]==1){//already visited
            //System.out.println("Its me");
            return ;
        }
        if(init[i][j]==color &&!(i >= nexi && j >= nexj)){//test
            //System.out.println("Here");
            visited[i][j] = 1;
            square++;
            queue.add(new Box(i,j,color));
            //System.out.println("The queue size in recursive add is : "+ queue.size());
            recursive_color(color,i,j-1,nexi,nexj);
            recursive_color(color,i,j+1,nexi,nexj);
            recursive_color(color,i-1,j,nexi,nexj);
            recursive_color(color,i+1,j,nexi,nexj);
        }
//        for(int p = 0; p < board; p++) {
//            for (int q = 0; q < board; q++) {
//                System.out.print(visited[p][q] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        return;
    }
}
