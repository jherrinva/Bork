
import java.util.Hashtable;


/**
 * Used to create dungeon.
 * @author John Herrin
 */
public class Dungeon 
{
    private String name;
    public Hashtable<String, Room> roomCollection = new Hashtable<>();
    public Room dungeonEntry;
    
    
    /**
     * Constructor
     * @param entry room to be used as entry point
     * @param name Name of actual dungeon
     */
    protected Dungeon(Room entry, String name)
    {
        this.name = name;
        this.dungeonEntry = entry;
        roomCollection.put(this.dungeonEntry.getTitle(),this.dungeonEntry);
        
    }
   
    
    public Dungeon(String fileName)
    {
        //needs to read the first line of .bork and use it as dungeon name
        //second line needs to have the literal line "Bork v2.0" exactly.
        //if match, needs to throw away the line and continue
        //if no match, needs to print an error message indicating dungeon file is not 
        //compatible, and exit the program
        //third line will be === dilimiter, this is when room construction begins
        //4th line is "Rooms:"    throw this away to
        // first line of each room will be room title
        //all following lines will be room description until --- is found, which denotes next room
        //continues until === is found which begins exits
        
        
    }
    
    /**
     * Used to get info on dungeons entry
     * @return Room currently set as entry point
     */
    public Room getEntry()
    {
        Room entryToReturn = roomCollection.get(dungeonEntry.getTitle());
        return entryToReturn;
    }
    
    /**
     * USed to get Dungeons name
     * @return String containing dungeons name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Adds a room to dungeons list of rooms
     * @param room Room to be added
     */
    public void add(Room room)
    {
        roomCollection.put(room.getTitle(),room);
    }
    
    /**
     * Takes in string containing title of room, searches through hashtable of rooms, and returns the room found with that title
     * @param roomTitle title of room to be searched for
     * @return Room found in list matching roomTitle
     */
    public Room getRoom(String roomTitle)
    {
        Room roomToGet = roomCollection.get(roomTitle);
        return roomToGet;
    }
    
    
}
