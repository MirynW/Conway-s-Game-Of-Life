/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author miryn
 */
public class Resident {
    //data
    char state;
    int numNeighbors;
    
    
    //Constructor
    public Resident(char init_state) {
        state = init_state;
    }
    public Resident() {
        setState();
    }
    //Getter/Setters
    public char getState() {
        return state;
    }
    public void setState(char newState) {
        if(newState == 'A' || newState == 'D')
            state = newState;
    }
    public int getNumNeighbors() {
        return numNeighbors;
    }
    public void setNumNeighbors(int newNum) {
        if(newNum >= 0) 
            numNeighbors = newNum;
    }
    //Public Methods
    public void setState() {
        if(getRand(1000) < 20)
            state = 'A';
        else
            state = 'D';
    }
    
    //Private Methods
    private int getRand(int size) {
        return (int)(Math.random() * size);
    }
}
