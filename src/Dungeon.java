
import java.util.Hashtable;


/**
 *
 * @author John Herrin
 */
public class Dungeon 
{
    public String name;
    public Hashtable<String, Room> roomCollection = new Hashtable<>();
    
    
    
    public Dungeon(Room entry, String name)
    {
        this.name = name;
    }
    
    public Room getEntry()
    {
        return SOMEROOM; //fix later
    }
    
    public String getName()
    {
        return name;
    }
    
    public void add(Room room)
    {
        roomCollection.put(room.getTitle(),room);
    }
    
    
    
    
}
