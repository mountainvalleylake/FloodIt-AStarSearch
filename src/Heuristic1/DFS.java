package Heuristic1;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;


/**
 * Created by graphics on 10/4/2017.
 */
public class DFS {
    public int board,component_count;
    public int[][] visited;
    public int[][] init;
    public int count_comp = 0;
    Vector<Component> componentArrayList;//the main thing
    Vector<Component> componentArray;
    Vector<Component> anotherlist;
    Vector<Component> another_list;//keep the very primitive list
    public DFS(int board, int init[][]){
        this.board = board;
        visited = new int[board][board];
        this.init = init;
        componentArrayList = new Vector<>();
//        for(int i = 0; i < board; i++){
//            for(int j = 0; j < board; j++){
//                //System.out.println(visited[i][j]);
//            }
//        }
    }
    public void print_Box(){
        for(int i = 0; i < board; i++){
            for(int j = 0; j < board; j++){
                System.out.print(init[i][j]);
            }
            System.out.println();
        }
    }
    public void color_change(int color){
//        for(int i = 0; i < board; i++) {
//            for (int j = 0; j < board; j++) {
//                if(i==endi && j==endj){
//                    return;
//                }
//                init[i][j] = color;//change color sequentially to new component until return
//            }
//        }

    }
    public void dfs_component(int i, int j,Component component){
        if(i<0 || j<0 || i>=board || j>=board) {//the incoming number is out of range
            return;
        }
        if(visited[i][j]==1){//already visited
            return;
        }
        visited[i][j] = 1;//now visiting
        Square square = new Square(i,j,init[i][j]);
        component.members.add(square);
        component.mem_count++;
        count_comp++;
        //j+1 < board && j-1 >=0 && i+1 < board && i-1>=0
        if(j-1>=0){//neighbors withing range
           if(init[i][j]==init[i][j-1]){
               dfs_component(i,j-1,component);//run dfs if same color
           }
           else{
               if(!(component.highesti.contains(i)&&component.highestj.contains(j))){
                   component.highesti.add(i);
                   component.highestj.add(j);
                   //System.out.println("Adding Border Index : "+ i + " , "+j);
               }
           }
        }
        if(j+1<board){//neighbors within range
            if(init[i][j]==init[i][j+1]){
                dfs_component( i,j+1,component);//run dfs if same color
            }
            else{
                if(!(component.highesti.contains(i)&&component.highestj.contains(j))){
                    component.highesti.add(i);
                    component.highestj.add(j);
                    //System.out.println("Adding Border Index : "+ i + " , "+j);
                }
            }
        }
        if(i-1>=0){//neighbors within range
            if(init[i][j]==init[i-1][j]){
                dfs_component(i-1,j,component);//run dfs if same color
            }
            else{
                if(!(component.highesti.contains(i)&&component.highestj.contains(j))){
                    component.highesti.add(i);
                    component.highestj.add(j);
                    //System.out.println("Adding Border Index : "+ i + " , "+j);
                }
            }
        }
        if(i+1<board){
            if(init[i][j]==init[i+1][j]){
                dfs_component(i+1,j,component);//run dfs if same color
            }
            else{
                if(!(component.highesti.contains(i)&&component.highestj.contains(j))){
                    component.highesti.add(i);
                    component.highestj.add(j);
                    //System.out.println("Adding Border Index : "+ i + " , "+j);
                }
            }
        }
    }
    public void find_neighbors(){
        componentArray = new Vector<>(componentArrayList);
        anotherlist = new Vector<>(componentArrayList);
        another_list = new Vector<>(componentArrayList);
        //now we will see who is the neighbor and where should we go
        int idx = 0;
        Enumeration enumeration = componentArray.elements();
        int fpass = 0;
        while (enumeration.hasMoreElements()){
            fpass++;
            Component component = (Component) enumeration.nextElement();
            Component neighborfinder = new Component();
            neighborfinder.lowesti = component.lowesti;
            neighborfinder.lowestj = component.lowestj;
            neighborfinder.mem_count = component.mem_count;
            neighborfinder.color = component.color;
            neighborfinder.members.addAll(component.members);
            neighborfinder.highesti.addAll(component.highesti);
            neighborfinder.highestj.addAll(component.highestj);
            neighborfinder.neighbor_index.addAll(component.neighbor_index);
            idx = componentArray.indexOf(component);
            Iterator iterator = componentArrayList.iterator();
            int cpass = 0;
            while (iterator.hasNext()){
                cpass++;
                Component comp = (Component) iterator.next();
                int iidx = componentArrayList.indexOf(comp);
                if(!(idx==iidx)){
                    Vector<Integer> vecompi = component.highesti;
                    Vector<Integer> vecompj = component.highestj;
                    Vector<Integer> vecompii = comp.highesti;
                    Vector<Integer> vecompjj = comp.highestj;
                    Enumeration envi = vecompi.elements();
                    Enumeration envj = vecompj.elements();
                    //neighborfinder has all of components
                    //another neighborfinder has all of comp
                    while (envi.hasMoreElements()&&envj.hasMoreElements()){
                        int borderi = (int) envi.nextElement();
                        int borderj = (int) envj.nextElement();
                        int index = another_list.indexOf(comp);
                        //System.out.println("Finding neighbors of : "+borderi + " , "+borderj + " in the component "+ cpass + " of the component "+fpass);
                        if((vecompii.contains(borderi+1)&&vecompjj.contains(borderj))||((borderi+1)==comp.lowesti&&borderj==comp.lowestj)){
                            if(!neighborfinder.neighbor_index.contains(index)){
                                neighborfinder.neighbor_index.add(index);//adding that neighbor to list
                                //System.out.println("Has a neighbor "+ (borderi+1) + " , "+borderj);
                            }
                            else{
                                //System.out.println("Has a neighbor but already added "+ (borderi+1) + " , "+borderj);
                            }
                        }
                        if((vecompii.contains(borderi-1)&&vecompjj.contains(borderj))||((borderi-1)==comp.lowesti&&borderj==comp.lowestj)){
                            if(!neighborfinder.neighbor_index.contains(index)){
                                neighborfinder.neighbor_index.add(index);//adding that neighbor to list
                                //System.out.println("Has another neighbor " + (borderi-1) + " , "+borderj);
                            }
                            else{
                                //System.out.println("Has another neighbor but already added " + (borderi-1) + " , "+borderj);
                            }
                        }
                        if((vecompii.contains(borderi)&&vecompjj.contains(borderj-1))||(borderi==comp.lowesti&&(borderj-1)==comp.lowestj)){
                            if(!neighborfinder.neighbor_index.contains(index)){
                                neighborfinder.neighbor_index.add(index);//adding that neighbor to list
                                //System.out.println("Has another another neighbor "+borderi + " , "+(borderj-1));
                            }
                            else{
                                //System.out.println("Has another another neighbor but already added "+borderi + " , "+(borderj-1));
                            }
                        }
                        if((vecompii.contains(borderi)&&vecompjj.contains(borderj+1))||(borderi==comp.lowesti&&(borderj+1)==comp.lowestj)){
                            if(!neighborfinder.neighbor_index.contains(index)){
                                neighborfinder.neighbor_index.add(index);//adding that neighbor to list
                                //System.out.println("Has another neighbor again "+borderi+ " , "+(borderj+1));
                            }
                            else{
                                //System.out.println("Has another neighbor again but already added "+borderi+ " , "+(borderj+1));
                            }
                        }
                    }
                }
            }
            anotherlist.remove(component);
            anotherlist.add(neighborfinder);
        }
        componentArrayList.removeAllElements();
        componentArrayList.addAll(anotherlist);
        componentArray.removeAllElements();
        componentArrayList.addAll(anotherlist);
        anotherlist.removeAllElements();
    }
    public void find_counts(){
        Enumeration enumeration = componentArrayList.elements();
        anotherlist = new Vector<>(componentArrayList);
        int idx = 0;
        while (enumeration.hasMoreElements()){
            Component c = anotherlist.firstElement();
            Component component = (Component) enumeration.nextElement();
            Vector<Integer> neighbor_components = new Vector<>(component.neighbor_index);
            //Vector<Component> dup_neighbors = new Vector<>(component.neighbor);
            Component neighborfinder = new Component();
            neighborfinder.lowesti = component.lowesti;
            neighborfinder.lowestj = component.lowestj;
            neighborfinder.mem_count = component.mem_count;
            neighborfinder.color = component.color;
            neighborfinder.members.addAll(component.members);
            neighborfinder.highesti.addAll(component.highesti);
            neighborfinder.highestj.addAll(component.highestj);
            neighborfinder.neighbor_index.addAll(component.neighbor_index);
            Enumeration en = neighbor_components.elements();
            while (en.hasMoreElements()){
                int neigh_idx = (int) en.nextElement();
                Component compo = another_list.elementAt(neigh_idx);
                int col = compo.color;
                neighborfinder.sq_track[col] += compo.mem_count;
                neighborfinder.color_track[col] += 1;
                //System.out.println("Now we have a component of " + col);
            }
            //System.out.println(neighborfinder.color + " " + neighborfinder.lowesti+ " "+ neighborfinder.lowestj+" ");
            anotherlist.remove(c);
            anotherlist.add(neighborfinder);
        }
        componentArrayList.removeAllElements();
        componentArrayList.addAll(anotherlist);
        anotherlist.removeAllElements();
    }
    public int merge_components() {
        anotherlist = new Vector<>(componentArrayList);
        int passcount = 0;
        int color_tobe=0,red_mem=0;
        int avgi = 0,avgj = 0,avg_dist = 0;
        int [] dist_list = new int[7];
        int sqleft = board * board;
        Component first_component = componentArrayList.firstElement();
        //componentArrayList.remove(first_component);
        while (sqleft>0){
            //Component c = anotherlist.firstElement();
            int maxmem = -1,color = 0, compo = -1;
            for(int j = 1 ; j < 7; j++){
                if(first_component.color_track[j]!=0){//ei color er neighbor ase
                    //System.out.println("Has a neighbor of color "+j);
                    Enumeration enumeration = first_component.neighbor_index.elements();
                    while (enumeration.hasMoreElements()){
//                        Cneighbor = (Component) enumeration.nextElement();
                        int idx = (int) enumeration.nextElement();
                        //System.out.println("Neighbor index "+idx+" is of color "+j);
                        Component c = componentArrayList.get(idx);
                        if(j==c.color){//yes this component has the color we are looking for
                            for(int k = 0; k < c.highestj.size(); k++){
                                avgi += board - c.highesti.elementAt(k);
                                avgj += board - c.highestj.elementAt(k);
                            }
                            avg_dist += (avgi+avgj)/c.highesti.size();
                        }

                        //System.out.println("The value of color j is "+j);
                    }
                    dist_list[j] = avg_dist;
                    avg_dist = 0;
                    avgi = 0;
                    avgj = 0;
                }
            }
            int min_cost = 9999;
            for(int i = 1 ; i < 7; i++){
                int temp = dist_list[i]+ (sqleft-first_component.sq_track[i]);
                if(temp < min_cost){
                    color_tobe = i;
                    //red_mem = sqleft-first_component.sq_track[i];
                    min_cost = temp;
                }
            }
            //c.color = color_tobe;
            Enumeration enumeration = first_component.neighbor_index.elements();
//            //first_component.highesti.removeAllElements();
//            //first_component.highestj.removeAllElements();
            first_component.color = color_tobe;
            //System.out.println("we are gonna color "+color_tobe);
            while (enumeration.hasMoreElements()){
                int index= (int) enumeration.nextElement();
                Component neigh = componentArrayList.get(index);
                if(neigh.color==color_tobe){//neighbor is of the color I want to do
                    first_component.mem_count += neigh.mem_count;
                    //first_component.members.addAll(neigh.members);
                    first_component.color_track[color_tobe] -= 1;
                    first_component.sq_track[color_tobe] -= neigh.mem_count;
                    for(int l = 0; l < neigh.neighbor_index.size();l++){
                        if(!first_component.neighbor_index.contains(neigh.neighbor_index.elementAt(l))){
                            first_component.neighbor_index.add(neigh.neighbor_index.elementAt(l));//jake merge korbo tar neighbor ekhn amr neigbor
                            //first_component.color_track[neigh.neighb]
                            int color_of = componentArrayList.elementAt(neigh.neighbor_index.elementAt(l)).color;
                            int mems = componentArrayList.elementAt(neigh.neighbor_index.elementAt(l)).mem_count;
                            first_component.color_track[color_of] += 1;
                            first_component.sq_track[color_of] += mems;
                        }
                    }

                }
            }
            sqleft -= first_component.mem_count;
            //System.out.println("squares left "+sqleft);
            passcount++;
            //System.out.println("done with pass "+passcount);
        }
        //System.out.println("Passes we need " + passcount);
        return component_count;
        //return passcount;
    }
}
