

/**
 * This class returns message to user that their request cannot be identified
 * @author John Herrin
 */
public class UnknownCommand extends Command
{
    
    private String bogusCommand;
    
    protected UnknownCommand(String bogusCommand)
    {
        this.bogusCommand = bogusCommand;
    }
    
    @Override
    protected String execute()
    {
        return "\n" + bogusCommand + "\n";
    }
    
    
}
