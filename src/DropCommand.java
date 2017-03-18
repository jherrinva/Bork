
/**
 *
 * @author John Herrin
 */
public class DropCommand extends Command
{
    private String itemName;
    
    protected DropCommand(String itemName)
    {
        this.itemName = itemName;
    }
    
    
    @Override
    protected String execute() 
    {
        Room roomToDropTo = GameState.instance().getAdventurersCurrentRoom();
        Item itemToDrop = GameState.instance().getItemFromInventoryNamed(itemName);
        
        if(itemName == null)
        {
            return "\nDrop what?\n";
        }
        
        else if (itemToDrop == null)
        {
            return "\nYou dont have a " + itemName + ".\n";
        }
        
      
        else
        {
            roomToDropTo.add(itemToDrop);
            GameState.instance().removeFromInventory(itemToDrop);
            return "\n" + itemName + " dropped.\n";
        }
    }
}
