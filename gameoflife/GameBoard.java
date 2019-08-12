/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells, each of which is in one of
 * two possible states, alive or dead, (or populated and unpopulated, respectively). Every cell interacts with its eight neighbours, 
 * which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:
 * 
1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overpopulation.
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 *
 * @author miryn
 */
public class GameBoard {
    //Data
    private Resident[][] board;
    private int numAlive;
    private int numDead;
    private int board_size;
    private char game_state;
    
    //Constructor
    public GameBoard(int boardSize) {
        board = new Resident[boardSize][boardSize];
        board_size = boardSize;
    }
    public GameBoard() {
        board = new Resident[50][50];
        board_size = 50;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Resident();
            }
        }
        board[10][10].state = 'A';
        board[11][10].state = 'A';
        board[9][10].state = 'A';
        board[11][11].state = 'A';
        //second set
        board[30][27].state = 'A';
        board[31][27].state = 'A';
        board[29][28].state = 'A';
        board[29][29].state = 'A';
        
    }
    //Getters/Setters
    public int getNumAlive() {
        return numAlive;
    }
    public void setNumAlive(int newNum) {
        if(newNum >= 0)
            numAlive = newNum;
    }
    public Resident[][] getBoard() {
        return board;
    }
    public void setBoard(Resident[][] arr) {
        if (arr != null) {
            board = arr;
            board_size = arr.length;
        }
    }
    public int getNumDead() {
        return numDead;
    }
    public void setNumDead(int newNum) {
        if(newNum >= 0) 
            numDead = newNum;
    }
    //Public Methods
    public char checkStatus() {
        game_state = 'C';
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j].state != 'A')
                    numDead++;
                else
                    numAlive++;
            }
        }
        if(numDead == (board.length * board[0].length))
            game_state = 'L';
        else if(numAlive == (board.length * board[0].length))
            game_state = 'W'; //This Should not happen
        else
            game_state = 'C';
        numDead = 0;
        return game_state;
    }
    public void update() {
        //What bounds are we dealing with
        //Check if peice is 3 units away from all bounds if so, apply rules of life
        //If not, check how many units they have, if so apply rules of life to units available, treating the 3rd one as if it were dead.
        int neighbors = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //get y
                if(i - 1 < 0 || i + 1 > board.length)
                    neighbors  = 0;
                    //we know that there isn't space up or down
                else if(j - 1 < 0 || j + 1 > board[i].length)
                    neighbors = 0;
                    //we know that there isn't space to our left or right
                if((j-1 >= 0 && j+1 <= board[i].length-1) && (i-1 >= 0 && i+1 <= board.length-1)) {
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            if(board[i+k][j+l].state == 'A')
                                neighbors++;                       
                        }
                    }
                }
                board[i][j].setNumNeighbors(neighbors);
                neighbors = 0;
            }            
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j].getNumNeighbors() > 3)
                    board[i][j].state = 'D';
                else if(board[i][j].getNumNeighbors() < 2)
                    board[i][j].state = 'D';
                else if(board[i][j].state == 'D' && board[i][j].getNumNeighbors() == 3)
                    board[i][j].state = 'A';
                else
                    board[i][j].state = 'A';
            }
        }
    }
    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j].state == 'A')
                    System.out.print(" ▨ ");
                else
                    System.out.print(" ▢ ");
            }
            System.out.println("");
        }
        System.out.println("\n\n\n\n\n");
    }
    public char run() {
        displayBoard();
        while(game_state != 'L' && game_state != 'W') {            
            update();
            displayBoard();
            checkStatus();
        }
        return game_state;
    }
    //Private Methods
    
}
