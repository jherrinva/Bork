
import java.util.Hashtable;



/**
 * This class contains all valid commands user can use, determines what user wants when they type, and returns the necessary command
 * @author John Herrin
 */
public class CommandFactory 
{
    
    private static CommandFactory commandFactoryInstance = null;
    public Hashtable<String, Command> commandList = new Hashtable<>();
    private Command east = new Command("e");
    private Command west = new Command ("w");
    private Command north = new Command ("n");
    private Command south = new Command ("s");
    private Command up  = new Command ("u");
    private Command down = new Command ("d");
    
    ///add all commands to command list
    {
    commandList.put("n",north);
    commandList.put("s",south);
    commandList.put("e",east);
    commandList.put("w",west);
    commandList.put("u",up);
    commandList.put("d",down);
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
     * @return the command user waanted
     */
    protected Command parse(String commandString)
    {
        Command tempCommand = commandList.get(commandString);
        return tempCommand;
    }
    
}
