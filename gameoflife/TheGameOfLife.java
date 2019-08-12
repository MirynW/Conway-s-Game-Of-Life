/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameoflife;

/**
 ================================================================================
author: Michael R. Nickerson    
project: Game of Life
version:
date:   3/28/2019
description: I wanted to recreate Conway's Game of Life in java, The current issues are that sometimes a block may not die if surrounded by more than 3 blocks. The iterator has some issues
               
================================================================================
 */
public class TheGameOfLife {

 
    public static void main(String[] args) {
        GameBoard gb = new GameBoard();
        char event = gb.run();
    }

}
