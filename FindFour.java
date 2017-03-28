/**
 * Write a description of class FindFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindFour
{
    public FindFour()
    {
        
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
        for (int r = 0; r < ConnectFour.board.length; r++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int c = 0; c < ConnectFour.board[0].length; c++) {
                if (c == 0) {
                    if (ConnectFour.board[r][c] != null) {
                        number = 1;
                    }
                }
                //if the slot is not empty and the slot contains the same string as slot to the left of it
                else if (ConnectFour.board[r][c] != null && ConnectFour.board[r][c].equals(ConnectFour.board[r][c - 1])) {
                    number++;
                }
                else {
                    number = 1;
                }
                //if there are four in a row it returns the string of the winner (B or R)
                if (number == num) {
                    return ConnectFour.board[r][c];
                }
            }
        }
        return "No winner horizontally";
    }
    
    public String checkColumns(int num)
    {
        //r is rows, c is columns
        for (int c = 0; c < ConnectFour.board[0].length; c++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int r = 0; r < ConnectFour.board.length; r++) {
                if (r == 0) {
                    if (ConnectFour.board[r][c] != null) {
                        number = 1;
                    }
                }
                //if the slot is not empty and the slot contains the same string as slot above it
                else if (ConnectFour.board[r][c] != null && ConnectFour.board[r][c].equals(ConnectFour.board[r - 1][c])) {
                    number++;
                }
                else {
                    number = 1;
                }
                //if there are four in a column it returns the string of the winner (B or R)
                if (number == num) {
                    return ConnectFour.board[r][c];
                }
            }
        }
        return "No winner vertically";
    }
    
    public String checkDiagonals(int num)
    {
        //diagonals that go along rows (top left to bottom right)
        //r is rows, c is columns
        for (int r = 0; r < ConnectFour.board.length; r++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int c = 0; c < ConnectFour.board[0].length; c++) {
                if (c == 0) {
                    if (ConnectFour.board[r + c][c] != null) {
                        number = 1;
                    }
                }
                else if (c + r < ConnectFour.board.length) {
                    //if the slot is not empty and the slot contains the same string as slot above and to the left of it
                    if (ConnectFour.board[r + c][c] != null && ConnectFour.board[r + c][c].equals(ConnectFour.board[(r + c) - 1][c - 1])) {
                        number++;
                    }
                    else {
                        number = 1;
                    }
                    //if there are four in a diagonal it returns the string of the winner (B or R)
                    if (number == num) {
                        return ConnectFour.board[r + c][c];
                    }
                }
            }
        }
        //diagonals that go along rows (top right to bottom left)
        //r is rows, c is columns
        for (int r = 0; r < ConnectFour.board.length; r++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int c = ConnectFour.board[0].length - 1; c >= 0; c--) {
                if(r - c == 0) {
                    if (ConnectFour.board[r - c][c] != null) {
                        number = 1;
                    }
                }
                else if(r - c > 0) {
                    //if the slot is not empty and the slot contains the same string as slot above and to the right of it
                    if (ConnectFour.board[r - c][c] != null && ConnectFour.board[r - c][c].equals(ConnectFour.board[(r - c) - 1][c + 1])) {
                        number++;
                    }
                    else {
                        number = 1;
                    }
                    //if there are four in a diagonal it returns the string of the winner (B or R)
                    if (number == num) {
                        return ConnectFour.board[r - c][c];
                    }
                }
            }
        }
        //diagonals that go along columns (top left to bottom right)
        //r is rows, c is columns
        for (int c = 0; c < ConnectFour.board[0].length; c++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int r = 0; r < ConnectFour.board.length; r++) {
                if (r == 0) {
                    if (ConnectFour.board[r][c + r] != null) {
                        number = 1;
                    }
                }
                else if (c + r < ConnectFour.board[0].length) {
                    //if the slot is not empty and the slot contains the same string as slot above and to the left of it
                    if (ConnectFour.board[r][c + r] != null && ConnectFour.board[r][c + r].equals(ConnectFour.board[r - 1][(c + r) - 1])) {
                        number++;
                    }
                    else {
                       number = 1;
                    }
                    //if there are four in a diagonal it returns the string of the winner (B or R)
                    if (number == num) {
                        return ConnectFour.board[r][c + r];
                    }
                }
            }
        }
        //diagonals that go along columns (top right to bottom left)
        //r is rows, c is columns
        for (int c = ConnectFour.board[0].length - 1; c < ConnectFour.board[0].length + ConnectFour.board.length ; c++) {
            //number is how many of the same color there are in a row
            int number = 0;
            for (int r = 0; r < ConnectFour.board.length; r++) {
                if (c - r == ConnectFour.board[0].length - 1) {
                    number = 1;
                }
                else if (c - r < ConnectFour.board[0].length - 1) {
                    //if the slot is not empty and the slot contains the same string as slot above and to the right of it
                    if (ConnectFour.board[r][c - r] != null && ConnectFour.board[r][c - r].equals(ConnectFour.board[r - 1][(c - r) + 1])) {
                        number++;
                    }
                    else {
                        number = 1;
                    }
                    //if there are four in a diagonal it returns the string of the winner (B or R)
                    if (number == num) {
                        return ConnectFour.board[r][c - r];
                    }
                }
            }
        }
        return "No winner diagonally";
    }
}
