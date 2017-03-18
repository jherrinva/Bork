
/**
 *
 * @author John Herrin
 */
public class InventoryCommand extends Command
{
    private String myInventory = "\nYou are carrying: ";
    
    
    protected InventoryCommand()
    {
        for (String itemName : GameState.instance().getInventoryNames())
        {
            myInventory+= "A " + itemName + " ";
        }
        
        myInventory+="\n";
    }
    
    @Override
    protected String execute() 
    {
        return myInventory;
    }
}
