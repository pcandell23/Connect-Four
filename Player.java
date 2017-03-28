/**
 * Abstract class Player - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Player
{
    //"B" or "R"
    public String color;
    //If AI or human
    public String type;

    public Player(String PlayerColor, String PlayerType)
    {
        color = PlayerColor;
        type = PlayerType;
    }
    
    public abstract boolean move(int colP);
    
    public abstract boolean attemptMove(int colP);
    
    public abstract int whatColumn();
    
    public abstract int randomCol();
}
