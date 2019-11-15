/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.umontreal.iro.hackathon.loderunner;

import java.util.ArrayList;

/**
 *
 * @author Tere
 */
public class Ladder {
    
    public int numOfElements;
    public ArrayList<Integer> x = new ArrayList();
    public ArrayList<Integer> y = new ArrayList();
    
    
    //takes the x-coordinates of the ladders 
    public Ladder(ArrayList<Integer> x, ArrayList<Integer> y){
        this.x = x;
        this.y = y;
    }   
    
    //checks how long the ladders are(length being numOfElements 
    public void checkForElements(){
        //if in the same x-ccordinate, one ladder
        if(x.size() >= 1){
           numOfElements++;
        
            for (int i = 0; i < x.size(); i++) {
                for (int j = i +1 ; j < x.size(); j++) {
                    if(x.get(i) == x.get(j)){
                        numOfElements++;
                    }
                }
                
            }
        }
        else if (x.isEmpty()){
            System.out.println("No ladder");
        }
    }
    
}
