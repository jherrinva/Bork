
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    /**
     * Takes in .sav file to process.  Restores .sav data and creates the dungeon and rooms, with data 
     * on which rooms were visited, and where adventurer left off
     * @param fileName location of .sav file
     */
    protected void restore(String fileName) throws FileNotFoundException, IOException
    {
        FileReader saveFiler = new FileReader(fileName);
        BufferedReader saveReader = new BufferedReader(saveFiler);
        
        String currentSaveLine = saveReader.readLine(); // read first line and skip
        currentSaveLine = saveReader.readLine();         // second line contains dungeon file name
        currentSaveLine = currentSaveLine.substring(14,currentSaveLine.length()); // trim to just .bork file name
        
        Dungeon restoredDungeon = new Dungeon (currentSaveLine);//create dungeon object with constructor, using 2nd line for .bork filename
        initialize(restoredDungeon); // set this as the dungeon
        
        restoredDungeon.restoreState(saveReader);//call restore dungeon
        
        currentSaveLine = saveReader.readLine();
        setAdventurersCurrentRoom(currentDungeon.getRoom(currentSaveLine.substring(14,currentSaveLine.length()))); //set current room inside this class with last line
        
        currentSaveLine = saveReader.readLine(); //after this readline call, now on Inventory: line
        
        String[] inventoryItems = currentSaveLine.split(","); // String text of allitems in players inventory now in this list. will need to check for these item objects and add to actual item object list
        
        // AFTER RESTORESTATE CALLED ON DUNGEON, AND AFTER THE SETADVETURERESCURRENTOOM LINE,
         // CALL READLINE AGAIN
         //BUFFERED READER SHOULD NOW ON THE .SAV FILES INVENTORY LINE 
           /// THIS NEEDS TO THEN ADD THE FOLLOWING ITEMS TO PLAYERS INVENTORY IN THIS .JAVA 
        
    }
    
    
    
}
