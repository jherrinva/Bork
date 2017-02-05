
import java.util.ArrayList;



/**
 *
 * @author John Herrin
 */
public class Room 
{
    private String title;
    private String desc;
    private boolean beenHere = false;
    ArrayList<Exit> exitList = new ArrayList<>();
    
    public Room(String title)
    {
        this.title = title;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
    protected String describe ()
    {
        if (!beenHere)
        {
            beenHere = true;
            return desc;
        }
            
        else 
            return title;
        
        //add printout for discription of each exit to this method
        
    }
    
    protected Room leaveBy(String dir)
    {
        //returns the room object reachable by the direction user inputs
        //return null if no exit in the users direction queried 
    }
    
    public void addExit(Exit exit)
    {
        // creates exit object, adds to exit arraylist
    }
    
    
}
