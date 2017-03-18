
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
    ArrayList<Item> contents = new ArrayList<>();
    
    
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
        
        for (Item item : contents)
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
        w.println(title + ":");
        
        if (beenHere)
        {
            w.println("beenHere=true");
        }
        else
        {
            w.println("beenHere=false");
        }
        
        if (!contents.isEmpty()) //if there are items still contained in room, add them to .sav file for this room
        {
            String itemLine = "Contents: ";
            for(Item anItem : getContents())
            {
                itemLine+= anItem.getPrimaryName() + ",";
            }
            
            itemLine = itemLine.substring(0, itemLine.length() - 1); //cuts off last comma, which for loops add each itteration
            w.println(itemLine);
            
        }
        
        w.println("---");
    }
    
    protected void restoreState(BufferedReader s, Dungeon d) throws IOException
    {
        
        String currentLine;
        boolean beenHereSetAlready = false;
        while ((currentLine = s.readLine()) != null)
        {
            if(currentLine.equals("---")) //end of this rooms info to restore, get out
            {
                break;
            }
            if(!beenHereSetAlready) //beenHere line always exists in .sav file.  
            {
                String[] beenHereLine = currentLine.split("=");
                if (beenHereLine[1].equals("true"))
                {
                    this.beenHere = true; // no need to use else statement for false, as thats the default
                }
                beenHereSetAlready = true;
            }
            else // If beenhere already set, and not on "---", you know current line will be Contentsline
            {
                String[] contentsLineToSplit = currentLine.split(": ");
                
                //If there are multiple items in this line, will be seperated by comma.
                //Single items on room, there will be no comma
                //Therefore, need to check for it, or .split will break functionality.
                if (contentsLineToSplit[1].contains(",")) 
                {
                    String[] contentsOfRoom = contentsLineToSplit[1].split(",");
                    for (String anItem : contentsOfRoom)
                    {
                        this.add(d.getItem(anItem));
                        System.out.println("added " + anItem);
                    }
                }
                else // only one item, only need a simple room.add item line
                {
                    this.add(d.getItem(contentsLineToSplit[1]));
                    System.out.println("added " + contentsLineToSplit[1]);
                }
                
                
            }
            
        }
        
       
    }
    
    protected void add(Item item)
    {
        contents.add(item);
    }
    
    protected void remove (Item item)
    {
        contents.remove(item);
    }
    
    protected Item getItemNamed(String name)
    {
        for(Item item : contents)
        {
            if (item.getPrimaryName().equalsIgnoreCase(name))
            {
                return item;
            }
        }
        return null;
    }
    
    protected ArrayList<Item> getContents()
    {
        return contents;
    }
    
    
    
}
