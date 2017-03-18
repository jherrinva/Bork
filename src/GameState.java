
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Singleton Class, containing information on current state of users dungeon crawl
 * @author John Herrin
 */
public class GameState 
{
    
    private static GameState gameStateInstance = null;
    private Dungeon currentDungeon;
    private Room currentRoom;
    ArrayList<Item> inventory = new ArrayList<>();
    
    
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
        currentSaveLine = saveReader.readLine(); // second line is "save data".  unimportant.  skip
        currentSaveLine = saveReader.readLine();         // third line contains dungeon file name
        currentSaveLine = currentSaveLine.substring(14,currentSaveLine.length()); // trim to just .bork file name
        
        Dungeon restoredDungeon = new Dungeon (currentSaveLine, false);//create dungeon object with constructor, using 2nd line for .bork filename
        initialize(restoredDungeon); // set this as the dungeon
        
        
        restoredDungeon.restoreState(saveReader);//call restore dungeon
        
        //readline is on "===" still before the next call
        currentSaveLine = saveReader.readLine(); // now on Adventurer: line after this call
        currentSaveLine = saveReader.readLine(); // now on current room: after this call
        
        setAdventurersCurrentRoom(currentDungeon.getRoom(currentSaveLine.substring(14,currentSaveLine.length()))); //set current room inside this class with last line
        
        
        
        //Next line (Inventory:) may not exist if save was create with no items in players inventory
        //Need to catch it with try/catch so it doesnt trigger a reset in Interpreter/main method
        try 
        {
            currentSaveLine = saveReader.readLine(); //after this readline call, now on Inventory: line
            String[] inventoryLineToSplit = currentSaveLine.split(": "); 
            
            if (inventoryLineToSplit[1].contains(",")) 
            {
                String[] inventoryItems = inventoryLineToSplit[1].split(",");
                for (String anItem : inventoryItems)
                {
                    addToInventory(restoredDungeon.getItem(anItem));
                    System.out.println("added " + anItem);
                }
                
            }
            else
            {
                addToInventory(restoredDungeon.getItem(inventoryLineToSplit[1]));
            }


            
        } 
        catch (Exception e) 
        {
            //nothing.  Inventory line didnt exist.  Move along, nothing to see here buddy
        }
        
        
        
        
        
        
        
        
        // AFTER RESTORESTATE CALLED ON DUNGEON, AND AFTER THE SETADVETURERESCURRENTOOM LINE,
         // CALL READLINE AGAIN
         //BUFFERED READER SHOULD NOW ON THE .SAV FILES INVENTORY LINE 
           /// THIS NEEDS TO THEN ADD THE FOLLOWING ITEMS TO PLAYERS INVENTORY IN THIS .JAVA 
        
    }
    
    
    protected ArrayList<String> getInventoryNames()
    {
        ArrayList<String> namesOfItemsInInventory = new ArrayList<>();
        
        for (Item theItem : inventory)
        {
            namesOfItemsInInventory.add(theItem.getPrimaryName());
        }
        return namesOfItemsInInventory;
    }
    
    /**
     * Adds item to players inventory arraylist
     * @param item Item to add
     */
    protected void addToInventory(Item item)
    {
        inventory.add(item);
    }
    
    /**
     * Removes item from players inventory arraylist
     * @param item Item to remove
     */
    protected void removeFromInventory(Item item)    
    {
        inventory.remove(item);
    }
    
    

    
    /**
     * This method looks in the players inventory AND the playersCurrentRoom for an item
     * @param name Title of item to look for
     * @return Item object found.  Null if not found in either
     */
    protected Item getItemInVicinityNamed(String name)
    {
        Item itemToGet = getAdventurersCurrentRoom().getItemNamed(name);
        
        if (itemToGet == null)
        {
            itemToGet = getItemFromInventoryNamed(name);
        }
        
        return itemToGet;
    }
    
    
    /**
     * Checks only players inventory for certain item name
     * @param name name of item to look for in inventory
     * @return Item to return if found in invetory.  Null if not there
     */
    protected Item getItemFromInventoryNamed(String name)
    {
        for (Item anItem : inventory)
        {
            if(anItem.goesBy(name))
            {
                return anItem;
            }
        }
        
        return null;
    }
        
    
}
