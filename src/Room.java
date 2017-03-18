
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
    private boolean beenHere = false;
    private Hashtable<String, Exit> exitList = new Hashtable<>();
    ArrayList<Item> itemList = new ArrayList<>();
    
    
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
     * @param d dungeon being passed in
     * @param initState  hydrating from .sav file?
     * @throws IOException 
     */
    public Room (BufferedReader buffReader, Dungeon d, boolean initState) throws IOException
    {
        //  ROOM CONSTRUCTOR NEEDS TO USE INITSTATE TO FIGURE OUT IF ITEMS ADDED TO ROOMS YET
        String currentLine;
        
        mainRoom:
        while (true)
        {
            currentLine = buffReader.readLine();

            
            if (currentLine.equals("==="))
            {
                throw new EmptyStackException();
            }
            
            
            title = currentLine;
            currentLine = buffReader.readLine();
            
            
            
            
            ///////////////  CATCH CONTENTS LINE HERE/BELOW  /////////////////////////
            String[] checkIfContentsLine = currentLine.split(": ");
            
            // if line begins with "Contents",  perform action
            // Contents line could have been missing if the room has no items in .bork file, this is why this check must be made
            if (checkIfContentsLine[0].equals("Contents")) 
            {
                if(initState) // if initstate=true, we are going ahead and adding items to rooms, as we are starting with a fresh dungeon
                {
                    String[] contentsOfRoom = checkIfContentsLine[1].split(",");
                    
                    for (String nameOfItem : contentsOfRoom)
                    {
                        this.add(d.getItem(nameOfItem)); //adds item to this room object, by accessing dungeon and getting item object with this name
                    }
                    
                }
               
                
                currentLine = buffReader.readLine(); //move on from contents line
                
            }
            
            
            /////////////////////end of contents line checking////////////////////
            
            
            
            
            if (!currentLine.equals("---"))
            {
                desc = currentLine;
                currentLine = buffReader.readLine();
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
        String toPrintItemsInRoom = "";
        
        for (Item item : itemList)
        {
            toPrintItemsInRoom += "There is a " + item.getPrimaryName() + " here.  ";
        }
        
        
        for (String theKey : keys) // iterates through all keys by the string keyvalues
        {
            exitDescriptions += exitList.get(theKey).describe() + ". ";
        }
        
        if (!beenHere)
        {
            beenHere = true;
            return "You are in the " + title + ". " + desc + "\n" + exitDescriptions +
                    "\n" + toPrintItemsInRoom;
        }   
        else 
        {
            return ("You are in the " + title + ".\n" + exitDescriptions +
                    "\n" + toPrintItemsInRoom);
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
    
    protected void restoreState(BufferedReader s, Dungeon d) throws IOException
    {
        String currentLine;
        while ((currentLine = s.readLine()) != null)
        {
            if(currentLine.equals("---")) //end of this rooms info to restore, get out
            {
                break;
            }
            if() // check if beenHere line
            {
                
            }
            else // obviosly on contents line here
            {
                
            }
            
        }
        
        //need while loop with .readline
        // if ---, break
        // check if line begins with beenHere
                // if so , check if last 4 of the line is true.  if so, set beenhere to true
        // else , split line on a colon+space, split the second index again on comma
                //then iterate through every index of second list to set this rooms contents, calling add item
        // continue (which should eventually hit --- break)
         
        
        beenHere = true;  // this method needs modifying later
    }
    
    protected void add(Item item)
    {
        itemList.add(item);
    }
    
    protected void remove (Item item)
    {
        itemList.remove(item);
    }
    
    protected Item getItemNamed(String name)
    {
        for(Item item : itemList)
        {
            if (item.getPrimaryName().equals(name))
            {
                return item;
            }
        }
        return null;
    }
    
    protected ArrayList<Item> getContents()
    {
        return itemList;
    }
    
    
    
}
