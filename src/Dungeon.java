
import java.util.Hashtable;


/**
 *
 * @author John Herrin
 */
public class Dungeon 
{
    private String name;
    public Hashtable<String, Room> roomCollection = new Hashtable<>();
    public Room dungeonEntry;
    
    
    public Dungeon(Room entry, String name)
    {
        this.name = name;
        this.dungeonEntry = entry;
        roomCollection.put(this.dungeonEntry.getTitle(),this.dungeonEntry);
        
    }
    
    public Room getEntry()
    {
        Room entryToReturn = roomCollection.get(dungeonEntry.getTitle());
        return entryToReturn;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void add(Room room)
    {
        roomCollection.put(room.getTitle(),room);
    }
    
    public Room getRoom(String roomTitle)
    {
        Room roomToGet = roomCollection.get(roomTitle);
        return roomToGet;
    }
    
    
}
