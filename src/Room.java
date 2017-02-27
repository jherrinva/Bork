
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Hashtable;
import java.util.Set;



/**
 * This class contains all information pertaining to a single room
 * @author John Herrin
 */
public class Room 
{
    private String title ="";
    private String desc;
    public boolean beenHere = false;
    public Hashtable<String, Exit> exitList = new Hashtable<>();
    
    
    /**
     * Constructor for room
     * @param title Name of room
     */
    protected Room(String title)
    {
        this.title = title;
    }
    
    /**
     * This constructor is able to read the lines of a .bork file to create a room from the information
     * 
     * @param buffReader  passed from Dungeon constructor, reads the lines of the file it contains
     * @throws IOException 
     */
    public Room (BufferedReader buffReader) throws IOException
    {
        
        String currentLine;
        
        mainRoom:
        while (true)
        {
            currentLine = buffReader.readLine();
            System.out.println("CurLine = " + currentLine);
            if (currentLine.equals("==="))
            {
                throw new EmptyStackException();
            }
            
            
            title = currentLine;
            currentLine = buffReader.readLine();
            if (!currentLine.equals("---"))
            {
                desc = currentLine;
                currentLine = buffReader.readLine();
                //System.out.println("testCurDesc =   " + desc);
            }
            else
            {
                break mainRoom;
            }
            
            while (!currentLine.equals("---"))
            {
                desc+= " " + currentLine;
                currentLine = buffReader.readLine();
            }
            System.out.println("testCurDesc =   " + desc);
            break mainRoom;
        }  
    }
    
    /**
     * Returns string containing title of room
     * @return Title of room
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Sets the description of room to instance variable
     * @param desc description of room to take in
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
    
    /**
     * Returns string containing description of room
     * @return string containing description of room
     */
    protected String describe()
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
    
    /**
     * Used to leave a room and go to another.  
     * @param dir  direction to leave by
     * @return  returns room you will enter
     */
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
    
    /**
     * used to add an exit to the list of exits the Room contains
     * @param exit exit to add to list
     */
    public void addExit(Exit exit)
    {
        exitList.put(exit.getDir(),exit);
        // creates exit object, adds to exit arraylist
    }
    
    protected void storeState(PrintWriter w)
    {
        if (beenHere)
        {
            w.println(title + ":");
            w.println("beenHere=true");
            w.println("---");
        }
    }
    
    protected void restoreState()
    {
        beenHere = true;
    }
    
}
