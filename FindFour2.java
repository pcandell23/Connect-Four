
/**
 * Write a description of class FindFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindFour2
{
    public String[][] board = {
       {"0 0","1 0","2 0","3 0","4 0","5 0","6 0"},
       {"0 1","1 1","2 1","3 1","4 1","5 1","6 1"},
       {"0 2","1 2","2 2","3 2","4 2","5 2","6 2"},
       {"0 3","1 3","2 3","3 3","4 3","5 3","6 3"},
       {"0 4","1 4","2 4","3 4","4 4","5 4","6 4"},
       {"0 5","1 5","2 5","3 5","4 5","5 5","6 5"}};
    public FindFour2()
    {
        System.out.println(testForWin());
    }
    
    public String testForWin() {
        if (!checkRows(4).equals("No winner horizontally")) {
            return checkRows(4);
        }
        else if (!checkColumns(4).equals("No winner vertically")) {
            return checkColumns(4);
        }
        else if (!checkDiagonals(4).equals("No winner diagonally")) {
            return checkDiagonals(4);
        }
        else {
            return "No winner";
        }
    }
    
    public String testForThree() {
        if (!checkRows(3).equals("No winner horizontally")) {
            return checkRows(3);
        }
        else if (!checkColumns(3).equals("No winner vertically")) {
            return checkColumns(3);
        }
        else if (!checkDiagonals(3).equals("No winner diagonally")) {
            return checkDiagonals(3);
        }
        else {
            return "No three in a row";
        }
    }

    public String checkRows(int num)
    {
        //r is rows, c is columns
        for (int r = 0; r < board.length; r++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int c = 0; c < board[0].length; c++) {
                if (c == 0) {
                    if (board[r][c] != null) {
                        number = 1;
                    }
                }
                //if the slot is not empty and the slot contains the same string as slot to the left of it
                else if (board[r][c] != null && board[r][c].equals(board[r][c - 1])) {
                    number++;
                }
                else {
                    number = 1;
                }
                //if there are four in a row it returns the string of the winner (B or R)
                if (number == num) {
                    return board[r][c];
                }
            }
        }
        return "No winner horizontally";
    }
    
    public String checkColumns(int num)
    {
        //r is rows, c is columns
        for (int c = 0; c < board[0].length; c++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int r = 0; r < board.length; r++) {
                if (r == 0) {
                    if (board[r][c] != null) {
                        number = 1;
                    }
                }
                //if the slot is not empty and the slot contains the same string as slot above it
                else if (board[r][c] != null && board[r][c].equals(board[r - 1][c])) {
                    number++;
                }
                else {
                    number = 1;
                }
                //if there are four in a column it returns the string of the winner (B or R)
                if (number == num) {
                    return board[r][c];
                }
            }
        }
        return "No winner vertically";
    }
    
    public String checkDiagonals(int num)
    {
        //diagonals that go along rows (top left to bottom right)
        //r is rows, c is columns
        for (int r = 0; r < board.length; r++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int c = 0; c < board[0].length; c++) {
                if (c == 0) {
                    if (board[r + c][c] != null) {
                        number = 1;
                    }
                }
                else if (c + r < board.length) {
                    //if the slot is not empty and the slot contains the same string as slot above and to the left of it
                    if (board[r + c][c] != null && board[r + c][c].equals(board[(r + c) - 1][c - 1])) {
                        number++;
                    }
                    else {
                        number = 1;
                    }
                    //if there are four in a diagonal it returns the string of the winner (B or R)
                    if (number == num) {
                        return board[r + c][c];
                    }
                }
            }
        }
        //diagonals that go along rows (top right to bottom left)
        //r is rows, c is columns
        for (int r = 0; r < board.length; r++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int c = board[0].length - 1; c >= 0; c--) {
                if(r - c == 0) {
                    if (board[r - c][c] != null) {
                        number = 1;
                    }
                }
                else if(r - c > 0) {
                    //if the slot is not empty and the slot contains the same string as slot above and to the right of it
                    if (board[r - c][c] != null && board[r - c][c].equals(board[(r - c) - 1][c + 1])) {
                        number++;
                    }
                    else {
                        number = 1;
                    }
                    //if there are four in a diagonal it returns the string of the winner (B or R)
                    if (number == num) {
                        return board[r - c][c];
                    }
                }
            }
        }
        //diagonals that go along columns (top left to bottom right)
        //r is rows, c is columns
        for (int c = 0; c < board[0].length; c++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int r = 0; r < board.length; r++) {
                if (r == 0) {
                    if (board[r][c + r] != null) {
                        number = 1;
                    }
                }
                else if (c + r < board[0].length) {
                    //if the slot is not empty and the slot contains the same string as slot above and to the left of it
                    if (board[r][c + r] != null && board[r][c + r].equals(board[r - 1][(c + r) - 1])) {
                        number++;
                    }
                    else {
                       number = 1;
                    }
                    //if there are four in a diagonal it returns the string of the winner (B or R)
                    if (number == num) {
                        return board[r][c + r];
                    }
                }
            }
        }
        //diagonals that go along columns (top right to bottom left)
        //r is rows, c is columns
        for (int c = board[0].length - 1; c < board[0].length + board.length ; c++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int r = 0; r < board.length; r++) {
                if (c - r == board[0].length - 1) {
                    number = 1;
                }
                else if (c - r < board[0].length - 1) {
                    //if the slot is not empty and the slot contains the same string as slot above and to the right of it
                    if (board[r][c - r] != null && board[r][c - r].equals(board[r - 1][(c - r) + 1])) {
                        number++;
                    }
                    else {
                        number = 1;
                    }
                    //if there are four in a diagonal it returns the string of the winner (B or R)
                    if (number == num) {
                        return board[r][c - r];
                    }
                }
            }
        }
        return "No winner diagonally";
    }
    
    public void printBoard() {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}








