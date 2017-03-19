
/**
 * This class processes a users request to see their inventory
 * @author John Herrin
 */
public class InventoryCommand extends Command
{
    private String myInventory = "\nYou are carrying: ";
    
    /**
     * Constructor.  Generates the appropriate message with list of users items
     */
    protected InventoryCommand()
    {
        for (String itemName : GameState.instance().getInventoryNames())
        {
            myInventory+= "A " + itemName + " ";
        }
        
        myInventory+="\n";
    }
    
    /**
     * Called by interpreter
     * @return message for interpreter to print
     */
    @Override
    protected String execute() 
    {
        return myInventory;
    }
}
