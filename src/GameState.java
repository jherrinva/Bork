
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Singleton Class, containing information on current state of users dungeon crawl
 * @author John Herrin
 */
public class GameState 
{
    
    private static GameState gameStateInstance = null;
    private Dungeon currentDungeon;
    private Room currentRoom;
    
    /**
     * Private constructor for singleton class
     */
    private GameState()
    {
        
    }
    
    /**
     * Creates instance for singleton class object
     * @return 
     */
    public static GameState instance()
    {
        if(gameStateInstance == null)
        {
            gameStateInstance = new GameState();
        }
        return gameStateInstance;
    }
    
    /**
     * Initializes dungeon.  Takes in dungeon passed into it, and saves to GameState
     * @param dungeon dungeon to use for the adventure and save info on
     */
    public void initialize (Dungeon dungeon)
    {
        this.currentDungeon = dungeon;
        this.currentRoom = dungeon.getEntry();
    }
    
    /**
     * Gets adventurers current room
     * @return current room
     */
    public Room getAdventurersCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Sets adventurers current room.  Used by executer() method in Command class.
     * @param room Room to set current room to.
     */
    public void setAdventurersCurrentRoom(Room room)
    {
        if (room != null)
            this.currentRoom = room;
        else    
            System.out.println("You cant go that direction");
    }
    
    /**
     * Gets dungeon object GameState currently has saved info on.
     * @return GameState's current dungeon.
     */
    public Dungeon getDungeon()
    {
        return currentDungeon;
    }
    
 
    protected void store(String saveName) throws IOException
    {
        File myFile = new File(saveName);
        myFile.createNewFile();
        PrintWriter myWriter = new PrintWriter(myFile);
        
        
        myWriter.println("Bork v2.0");
        currentDungeon.storeState(myWriter);
        
        //space here, because Current room should be last line written
        myWriter.println("Current room: " + getAdventurersCurrentRoom().getTitle());
        
        
        myWriter.close();
        
        
    }
    
    
    
}
