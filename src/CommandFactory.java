
import java.util.Hashtable;



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
        
        if(commandName[0].equals("save")) // if commandName list first index is "save" return save object
        {
            return save;
        }
        else if (movementCommandList.containsKey(commandName[0])) // if movementCommandList contains the key contained in commandName first index, return that specific movement command
        {
            return movementCommandList.get(commandString);
        }
        
        else  //temporarily, this else will return the UnknownCommand object
        {
            return unknown;
        }
        
       
    }
    
}
