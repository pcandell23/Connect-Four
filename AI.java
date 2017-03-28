/**
 * Write a description of class AI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;

public class AI extends Player
{
    // coor is the coordinate of the column in which the AI will place a Black piece
    int coor = -1;
    static boolean[] dontUse = new boolean[7];
    
    /**
     * Constructor for objects of class AI
     */
    public AI(String PlayerColor, String PlayerType)
    {
        super(PlayerColor,PlayerType);
    }
    
    //This method returns which column the AI will play next
    public static void restart(){
        for(int i = 0; i < 7; i++){
            dontUse[i] = false;
        }
    }
    public int whatColumn() {
        //checks if it can win and if it can, it returns the column for a win
        if (canIWin() != -1) {
            return canIWin();
        }
        //checks if it needs to block the other player from winning and if it can, returns the column for block
        else if (block() != -1) {
            return block();
        }
        //checks if their are two in a row for either B or R and returns column to either get three in a row or block three
        else if (blockOrWinTwo() != -1) {
            return blockOrWinTwo();
        }
        //if no logical move, it randomly places a piece
        else {
            return randomCol();
        }
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
    
    //method randomly picks a column to to place a piece
    public int randomCol() {
        Random rand = new Random();
        coor = rand.nextInt(7);
        //checks that the column randomly selected isn't full
        //if it is full, it recusively calls itself to get another random column until one works
        if (ConnectFour.colHigh[coor] == -1) {
            randomCol();
        }
        //once it finds a column that is not full, it returns the randomly chosen column
        return coor;
    }
   
    //method checks for three Rs in a row and returns the column to block
    public int block() {
        //checks horizontal three
        if (checkThreeHori("R") != -1) {
            coor = checkThreeHori("R");
            return coor;
        }
        //checks vertical three
        else if (checkThreeVert("R") != -1) {
            coor = checkThreeVert("R");
            return coor;
        }
        else {
            return -1;
        }
    }
    
    //method checks for three Bs in a row and returns the column to win
    public int canIWin(){
        //checks vertical three
        if (checkThreeVert("B") != -1) {
            coor = checkThreeVert("B");
            return coor;
        }
        //checks horizontal three
        else if (checkThreeHori("B") != -1) {
            coor = checkThreeHori("B");
            return coor;
        }
        else {
            return -1;
        }
    }
    
    //method checks for two in a row of either R or B and returns column to either help win or help block
    public int blockOrWinTwo() {
        if (checkTwoHori("R") != -1) {
            coor = checkTwoHori("R");
            return coor;
        }
        else if (checkTwoHori("B") != -1) {
            coor = checkTwoHori("B");
            return coor;
        }
        else if (checkTwoVert("R") != -1) {
            coor = checkTwoVert("R");
            return coor;
        }
        else if (checkTwoVert("B") != -1) {
            coor = checkTwoVert("B");
            return coor;
        }
        else {
            return -1;
        }
    }
    
    //method checks for three in a row horizontally
    public int checkThreeHori(String color){
        int coorHor = -1;
        //r is rows, c is columns
        for (int r = 0; r < ConnectFour.board.length; r++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int c = 0; c < ConnectFour.board[0].length; c++) {
                if (ConnectFour.board[r][c].equals(color)) {
                    number ++;
                }
                else{
                    number = 0;
                }
                //if three in a row
                if (number == 3) {
                    if(c<ConnectFour.board[0].length-1) {
                        //if the spot to the right of the three in a row is empty
                        if (!ConnectFour.board[r][c+1].equals("R") && !ConnectFour.board[r][c+1].equals("B")){
                            //if the column to the right is at the same height as the three in a row
                            if (ConnectFour.colHigh[c+1] == r) {
                                coorHor = c+1;
                            }
                            else {
                                coorHor = -1;
                            }
                        }
                        //if the spot to the left of the three in a row is empty
                        else if (!ConnectFour.board[r][c-3].equals("R") && !ConnectFour.board[r][c-2].equals("B")) {
                            //if the column to the left is at the same height as the three in a row
                            if (ConnectFour.colHigh[c-3] == r) {
                                coorHor = c-3;
                            }
                            else {
                                coorHor = -1;
                            }
                        }
                    }
                    else {
                        coorHor = -1;
                    }
                }
            }
        }
        return coorHor;
    }
    
    //method checks for three in a row vertically
    public int checkThreeVert(String color){
        int coorVert = -1;
        //r is rows, c is columns
        for (int c = 0; c < ConnectFour.board[0].length; c++) {
            //number is how many of the same color there are in a row
            int number = 0;
            //if column isn't full
            if (attemptMove(c)) {
                for (int r = 0; r < ConnectFour.board.length; r++) {
                    if (ConnectFour.board[r][c].equals(color)) {
                        number ++;
                    }
                    else{
                        number = 0;
                    }
                    //if three in a row
                    if (number == 3) {
                        //if spot above three in a row is empty
                        if (dontUse[c] == false){
                            coorVert = c;
                            dontUse[c] = true;
                        }
                        else {
                            coorVert = -1;
                        }
                    }
                }
            }
            //if column is full, moves to next column
            else {
                c++;
            }
        }
        return coorVert;
    }
    
    //method checks for two in a row horizontally
    public int checkTwoHori(String color){
        int coorHor = -1;
        //r is rows, c is columns
        for (int r = 0; r < ConnectFour.board.length; r++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int c = 0; c < ConnectFour.board[0].length; c++) {
                if (ConnectFour.board[r][c].equals(color)) {
                    number ++;
                }
                else{
                    number = 0;
                }
                //if two in a row
                if (number == 2) {
                    if(c<ConnectFour.board[0].length-1) {
                        //if the spot to the right of the two in a row is empty
                        if (!ConnectFour.board[r][c+1].equals("R") && !ConnectFour.board[r][c+1].equals("B")){
                            //if the column to the right is at the same height as the two in a row
                            if (ConnectFour.colHigh[c+1] == r) {
                                coorHor = c+1;
                            }
                            else {
                                coorHor = -1;
                            }
                        }
                        //if the spot to the left of the two in a row is empty
                        else if (!ConnectFour.board[r][c-2].equals("R") && !ConnectFour.board[r][c-2].equals("B")) {
                            //if the column to the left is at the same height as the two in a row
                            if (ConnectFour.colHigh[c-2] == r) {
                                coorHor = c-2;
                            }
                            else {
                                coorHor = -1;
                            }
                        }
                    }
                    else {
                        coorHor = -1;
                    }
                }
            }
        }
        return coorHor;
    }
    
    //method checks for two in a row vertically
    public int checkTwoVert(String color){
        int coorVert = -1;
        //r is rows, c is columns
        for (int c = 0; c < ConnectFour.board[0].length; c++) {
            //number is how many of the same color there are in a row
            int number = 0;
            //if column isn't full
            if (attemptMove(c)) {
                for (int r = 0; r < ConnectFour.board.length; r++) {
                    if (ConnectFour.board[r][c].equals(color)) {
                        number ++;
                    }
                    else{
                        number = 0;
                    }
                    //if two in a row
                    if (number == 2) {
                        if(r>0) {
                            //if spot above three in a row is empty
                            if (!ConnectFour.board[r-1][c].equals("B") || !ConnectFour.board[r-1][c].equals("R")){
                                coorVert = c;
                            }
                        }
                        else {
                            coorVert = -1;
                        }
                    }
                }
            }
            //if column is full, moves to next column
            else {
                c++;
            }
        }
        return coorVert;
    }
}
