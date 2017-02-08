
import java.util.Hashtable;



/**
 *
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
    
    ///add all commands to command list
    {
    commandList.put("n",north);
    commandList.put("s",south);
    commandList.put("e",east);
    commandList.put("w",west);
    }
    
    private CommandFactory()
    {
        
    }
    
    public static CommandFactory instance()
    {
        if(commandFactoryInstance == null)
        {
            commandFactoryInstance = new CommandFactory();
        }
        return commandFactoryInstance;
    }

    protected Command parse(String commandString)
    {
        Command tempCommand = commandList.get(commandString);
        return tempCommand;
    }
    
}
