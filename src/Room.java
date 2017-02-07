
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;



/**
 *
 * @author John Herrin
 */
public class Room 
{
    private String title;
    private String desc;
    private boolean beenHere = false;
    public Hashtable<String, Exit> exitList = new Hashtable<>();
    
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
        
        Set<String> keys = exitList.keySet(); //adds all keys inside hashtable to this set
        String exitDescriptions = "";
        
        for (String theKey : keys) // iterates through all keys by the string keyvalues
        {
            exitDescriptions += exitList.get(theKey).describe() + ". ";
        }
        
        if (!beenHere)
        {
            beenHere = true;
            return "You are in the " + title + ". " + desc + ". " + exitDescriptions;
        }   
        else 
        {
            return ("You are in the " + title + ". " + exitDescriptions);
        }
        
        
    }
    
    protected Room leaveBy(String dir)
    {
        if (!exitList.containsKey(dir))
        {
            return null;
        }
        else
        {
            Room roomToEnterInto = exitList.get(dir).getDest();
            return roomToEnterInto;
        }
        //returns the room object reachable by the direction user inputs
        //return null if no exit in the users direction queried 
    }
    
    public void addExit(Exit exit)
    {
        exitList.put(exit.getDir(),exit);
        // creates exit object, adds to exit arraylist
    }
    
    
}
