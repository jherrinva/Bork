
import java.util.Hashtable;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;


/**
 * Used to create dungeon.
 * @author John Herrin
 */
public class Dungeon 
{
    private String name;
    private Hashtable<String, Room> roomCollection = new Hashtable<>();
    private Hashtable<String, Item> itemCollection = new Hashtable<>();
    private Room dungeonEntry;
    private String fileName;
    
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
   
    
    public Dungeon(String fileName, boolean initState) throws FileNotFoundException, IOException
    {
        
        Scanner in = new Scanner(System.in);
        String currentFileName = fileName;
        
        String currentLine = "";
        int lineCounter = 1;
        
        Boolean stillOnItems = true;
        Boolean stillOnRooms = false;
        Boolean stillOnExits = false;
        Boolean entryCreated = false;
        
        main:
        while (true)  //main loop, can be restarted if file is incompatible with current Bork version
        {
            FileReader classFiler = new FileReader(currentFileName);
            BufferedReader buffReader = new BufferedReader(classFiler);
            
            lineRead:
            while ((currentLine = buffReader.readLine()) != null) //going through the file line by line
            {

                if (lineCounter == 1)
                {
                    this.name = currentLine;
                    lineCounter++;
                }

                else if (lineCounter == 2)
                {
                    if (!currentLine.equals("Bork v3.0"))
                    {
                        System.out.println("Your .bork file is not compatible with this programs version of Bork");
                        System.out.println("Please enter the file name to be processed: ");
                        currentFileName = in.nextLine();
                        continue main;
                    }
                    else //after checking version is compatible, the dungeon objects fileName variable is set and confirmed
                    {
                        this.fileName = currentFileName;
                    }
                    lineCounter++;
                    
                    
                }
                else if (lineCounter == 3)
                {
                    lineCounter++; // 3rd line always the delimiter '===' and is unneccesary to process
                    continue;
                }
                /**
                else if (lineCounter == 4)
                {
                    lineCounter++; //4th line always "Rooms: "  , and is to be thrown away
                    continue;
                }
                 */
                else // code block containing item creation, room creation and exit creation
                {
                    
                    
                    
                    try// keep creating items until Item class throws exception .... i.e., no more items to create
                    {
                        while (stillOnItems)
                        {
                            Item newItem = new Item(buffReader);
                            this.add(newItem);
                        }
                    } 
                    catch (Exception e) 
                    {
                        stillOnItems = false;
                        stillOnRooms = true;
                    }
                    
                    
                    
                    
                    
                    
                    try // keep creating rooms until Room class throws exception .... i.e., no more rooms to create
                    {
                        while (stillOnRooms)
                        {
                            if (!entryCreated) // if first room hasnt been created yet, use as dungeon entry
                            {
                                this.dungeonEntry = new Room (buffReader, this, initState);
                                roomCollection.put(this.dungeonEntry.getTitle(),this.dungeonEntry);
                                entryCreated = true;
                            }
                            else
                            {
                                Room newRoom = new Room (buffReader, this, initState);
                                roomCollection.put(newRoom.getTitle(),newRoom);
                            }
                        }
                    } 
                    catch (Exception e) 
                    {
                        stillOnRooms = false;
                        stillOnExits = true;
                    }
                    
                    
                    
                    
                    
                    
                    
                    try  // keep creating exits until Exit class throws exception..... i.e. no more exits to create 
                    {
                        while (stillOnExits)  //starts creating exits
                        {
                            Exit newExit = new Exit(buffReader,this);
                            newExit.getSrc().addExit(newExit);
                        }
                    } 
                    catch (Exception e) 
                    {
                        stillOnExits = false;
                        break main;
                    }
                    
                    
                    
                    
                    
                }
            }
        }
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
    
    protected void storeState(PrintWriter w)
    {
        w.println("Dungeon file: " + fileName);
        w.println("Room states:");
        
        Set<String> roomKeys = roomCollection.keySet(); //adds all roomKeys inside hashtable to this set
     
        for (String theKey : roomKeys) // iterates through all roomKeys by the string keyvalues
        {
            roomCollection.get(theKey).storeState(w);       
        }
        
        
        //for loop for each room store() here
        //insert check for beenHere =true
        
        w.println("===");
    }
    
    
    protected void restoreState (BufferedReader w) throws IOException
    {
        String currentLine = w.readLine();//currently on Room states: now with this line
        
        while ((currentLine = w.readLine()) != null)//now on title of first room in .sav file  
        {
            if(currentLine.equals("==="))
            {
                break;
            }
            roomCollection.get(currentLine.substring(0,currentLine.length()-1)).restoreState(w,this);
            //w.readLine();//now on beenHere line.  thrown away
            //w.readLine();//now on '---' line.  thrown away
            
        }
    }
    
    public Item getItem(String primaryName)
    {
        return itemCollection.get(primaryName);   
    }
    
    public void add(Item item)
    {
        itemCollection.put(item.getPrimaryName(), item);
    }
    
    
    
    
    
}
