import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit; 

/**
 * Class ConnectFour - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class ConnectFour extends JApplet implements MouseListener {
    // instance variables - replace the example below with your own
    public static String[][] board = new String[6][7];
    FindFour testWin = new FindFour();
    private int APPLET_WIDTH = 640;
    private Image gameBoard, redPiece, blackPiece,white;
    private int[] colPos = {11,100,190,280,370,460,550};  
    private int[] rowPos = {3,80,160,240,320,400};  
    private int colPicked = -1;
    private String player = "R";
    public static int[] colHigh = {5,5,5,5,5,5,5};
    private String turn = "R";
    private int x = 0;
    private int y = 0;
    private String gameOver = "W";
    private Font stringFont = new Font("SansSerif", Font.PLAIN, 36);
    private int moves = -1;
    private int gameMode = 0;
    public void init()
    {
       gameBoard = getImage(getDocumentBase(), "board.png");
       redPiece = getImage(getDocumentBase(), "red.png");
       blackPiece = getImage(getDocumentBase(), "black.png");
       white = getImage(getDocumentBase(), "white.png");
       addMouseListener(this);
    }
    
    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
        resetBoard();
    }
    
    public boolean checkFull() {
        //variable that is true if board is full and false if board is not full
        boolean full = true;
        for(int col = 0; col < 7; col++){
           //checks that each column is not full
           //if it is, full is true
            if (colHigh[col] == -1) {
               full = true;
           }
           //if not, full is false
           else {
               full = false;  
           }
           //returns false if not full
           if (full == false) {
              return false; 
           }
        } 
        //returns true if full
        return true;
    }
    
    public void resetBoard(){
       for(int col = 0; col < 7; col++){
           for(int row = 0; row < 6; row++){
               board[row][col] = col +" " + row;
           }
        }  
    }
    
    public void resetBoardVars(){ 
       colPicked = -1;
       player = "R";
       for(int col = 0; col < 7; col++){
               colHigh[col] = 5;
       }
       turn = "R";
       x = 0;
       y = 0;
       gameOver = "W";
       moves = -1;
       gameMode = 0;
       AI.restart();
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {
        // simple text displayed on applet
        drawPictures(APPLET_WIDTH, g);
    }
    
    public void drawPictures(int size, Graphics page){
        page.drawImage(white, 0, 0,640,70, this);
        if(moves == 0) {
            page.drawImage(white, 0, 0,640,640, this);
        }
        page.drawImage(gameBoard, 0, 70, this);
        page.setFont(stringFont);
        if(moves == -1){
            page.drawImage(white, 0, 550,640,70, this);
            page.drawString("Connect Four",210, 50);
            page.drawString("Click far right column for one player",50, 580);
            page.drawString("Click far left column for two player",50, 620);
        } else if(checkFull()){
            page.drawString("Cat's Game!",250, 50);
            page.drawString("Click anywhere to play again!",100, 580);
            turn = " ";
            resetBoardVars();
        }else if(gameOver.equals("B")){
            page.drawString("Black Wins!",250, 50);
            page.drawString("Click anywhere to play again!",100, 580);
            turn = " ";
            resetBoardVars();
        }  else if(gameOver.equals("R")){
            page.drawString("Red Wins!",250, 50);
            page.drawString("Click anywhere to play again!",100, 580);
            turn = " ";
            resetBoardVars();
        } else{    
            page.drawString(turn + "'s Turn",250, 50);
        }
        for(int col = 0; col < 7; col++){
            for(int row = 0; row < 6; row++){
                if(board[row][col].equals("R")){
                   page.drawImage(redPiece,colPos[col],rowPos[row]+70,80,80,this); 
                }
                else if(board[row][col].equals("B")){
                   page.drawImage(blackPiece,colPos[col],rowPos[row]+70,80,80,this); 
                }
            }
        }
    }
    
    public void mouseClicked(MouseEvent arg0) {
        x = arg0.getX();
        y = arg0.getY();
        repaint();
        for(int i = 0; i < 7; i++){
            if(Math.abs(x-(colPos[i]+40)) < 35){
                colPicked = i;
            }
        }
        if(moves == -1){
            resetBoard();
            if(colPicked == 0){
                gameMode = 2;
                moves = 0;
            } else if(colPicked == 6) {
                gameMode = 1;
                moves = 0;
            }
        } 
        //player vs player game
        else if (gameMode == 2){
            Player p1 = new Person("R", "person");
            Player p2 = new Person("B", "person");
            if(Math.abs(x-(colPos[colPicked]+40)) < 35){
                if(turn.equals("R")){
                    if(p1.attemptMove(colPicked)){
                        p1.move(colPicked);
                        player = "B";
                        turn = "B";
                        moves++;
                        gameOver = testWin.testForWin();
                        repaint();
                    }
                } else if(turn.equals("B")){
                    if(p2.attemptMove(colPicked)){
                        p2.move(colPicked);
                        player = "R";
                        turn = "R";
                        moves++;
                        gameOver = testWin.testForWin();
                        repaint();
                    }
                }
            }
        }
        //player vs computer game
        else if (gameMode == 1){
            Player p1 = new Person("R", "person");
            Player p2 = new AI("B", "AI");
            if(Math.abs(x-(colPos[colPicked]+40)) < 35){
                    if(p1.attemptMove(colPicked)){
                        p1.move(colPicked);
                        moves++;
                        gameOver = testWin.testForWin();
                        repaint();
                    }
                    //TimeUnit.SECONDS.sleep(3);
                    if(p2.attemptMove(p2.whatColumn())){
                        p2.move(p2.whatColumn());
                        moves++;
                        gameOver = testWin.testForWin();
                        repaint();
                    }
                    else {
                        p2.move(p2.randomCol());
                        moves++;
                        gameOver = testWin.testForWin();
                        repaint();
                    }
            }
        }
    }
    
    public void mouseExited(MouseEvent arg0) {}
    public void mouseEntered(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
}
