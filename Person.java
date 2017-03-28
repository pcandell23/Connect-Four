/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;

public class Person extends Player
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Person
     */
    public Person(String PlayerColor, String PlayerType)
    {
        super(PlayerColor,PlayerType);
    }

    public boolean move(int colP){
        if(colP != -1 && ConnectFour.colHigh[colP] >= 0){
            ConnectFour.board[ConnectFour.colHigh[colP]][colP] = color;
            ConnectFour.colHigh[colP]--;
            return true;
        } else {
            return false;
        }
    }

    public boolean attemptMove(int colP){
        if(colP != -1 && ConnectFour.colHigh[colP] >= 0){
            return true;
        } else {
            return false;
        }
    }
    
    public int whatColumn() {
        return -1;
    }
    
    public int randomCol() {
        Random rand = new Random();
        int coor = rand.nextInt(7);
        return coor;
    }
}
