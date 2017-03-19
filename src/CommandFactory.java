
import java.util.Hashtable;
import java.util.Set;



/**
 * This class contains all valid commands user can use, determines what user wants when they type, and returns the necessary command
 * @author John Herrin
 */
public class CommandFactory 
{
    
    private static CommandFactory commandFactoryInstance = null;
    public Hashtable<String, MovementCommand> movementCommandList = new Hashtable<>(); //list containing all MovementCommand objects
    
    ///movementCommands below/////////////////
    private MovementCommand east = new MovementCommand("e");
    private MovementCommand west = new MovementCommand ("w");
    private MovementCommand north = new MovementCommand ("n");
    private MovementCommand south = new MovementCommand ("s");
    private MovementCommand up  = new MovementCommand ("u");
    private MovementCommand down = new MovementCommand ("d");
    
    
    //only SaveCommand below//////////////////
    private SaveCommand save = new SaveCommand (".sav");
    
    
    //only UnknownCommand below///////////////
    private UnknownCommand unknown = new UnknownCommand("Unknown Command");
    
   
    
    ///add all  MovementCommands to list
    {
    movementCommandList.put("n",north);
    movementCommandList.put("s",south);
    movementCommandList.put("e",east);
    movementCommandList.put("w",west);
    movementCommandList.put("u",up);
    movementCommandList.put("d",down);
    }
    
    /**
     * Private constructor for a Singleton class
     */
    private CommandFactory()
    {
        
    }
    
    /**
     * initializer for singleton class
     * @return returns first creation of the object, or the already existing object
     */
    public static CommandFactory instance()
    {
        if(commandFactoryInstance == null)
        {
            commandFactoryInstance = new CommandFactory();
        }
        return commandFactoryInstance;
    }

    /**
     * Takes in user command, sorts through hashtable of commands, and returns the correct command
     * @param commandString users command
     * @return the command user wanted
     */
    protected Command parse(String commandString)
    {
        
        String[] commandName = commandString.split(" ");  // split commandString into a list of strings, based on when a space occurs
        
        if(commandName[0].equalsIgnoreCase("save")) // if commandName list first index is "save" return save object
        {
            return save;
        }
        else if (movementCommandList.containsKey(commandName[0])) // if movementCommandList contains the key contained in commandName first index, return that specific movement command
        {
            return movementCommandList.get(commandString);
        }
        
        else if(commandName[0].toLowerCase().equals("take")) //if user requesting to take something
        {
            if (commandName.length < 2)
            {
                TakeCommand attemptToTake = new TakeCommand(null);
                return attemptToTake;
            }
            else
            {
                TakeCommand attemptToTake = new TakeCommand(commandName[1]);
                return attemptToTake;
            }
            
        }
        
        else if(commandName[0].toLowerCase().equals("drop")) //if user requesting to drop something
        {
            if (commandName.length < 2)
            {
                DropCommand attemptToDrop = new DropCommand(null);
                return attemptToDrop;
            }
            else
            {
                DropCommand attemptToDrop = new DropCommand(commandName[1]);
                return attemptToDrop;
            }
        }
        
        
        else if (commandName[0].toLowerCase().equals("i") || commandName[0].toLowerCase().equals("inventory") ) //if user requesting to see their inventory 
        {
            InventoryCommand seeInventory = new InventoryCommand();
            return seeInventory;
        }
        
        else  //check each item in dungeon and see if commandName[0] matches any items verb command.  if not, return unknown command
        {
            
            
            Hashtable<String, Item> tempItemTable = GameState.instance().getDungeon().getItemHashtable();
            Set<String> itemKeys = tempItemTable.keySet();
            boolean foundAnItem = false;
            
            for (String theKey : itemKeys) // iterates through all itemKeys by the string keyvalues
            {
                String theKeyWithAmessage = tempItemTable.get(theKey).getMessageForVerb(commandName[0]);
                if (theKeyWithAmessage!=null)
                {
                    foundAnItem = true;
                    break;
                }
            }
            
            if(foundAnItem && commandName.length > 1)
            {
                ItemSpecificCommand iscCommand = new ItemSpecificCommand(commandName[0], commandName[1]);
                return iscCommand;
                //call ISC with 1st and 2nd index
            }
            else if (foundAnItem && commandName.length < 2)
            {
                ItemSpecificCommand iscCommand = new ItemSpecificCommand(commandName[0], "null");
                return iscCommand;
                //call ISC with "null" as second param
            }
            
            else //users request cannot be processed, return unknown command
            {
                return unknown;    
            }  
        }  
    }
}
