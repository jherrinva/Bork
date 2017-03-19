

/**
 * This class processes a users request to take an item from a room object
 * @author John Herrin
 */
public class TakeCommand extends Command
{

    private String itemName;
    
    protected TakeCommand(String itemName)
    {
        this.itemName = itemName;
    }
    
    /**
     * Called by interpreter
     * @return message for interpreter to print
     */
    @Override
    protected String execute() 
    {
        
        
        Room roomToTakeFrom = GameState.instance().getAdventurersCurrentRoom();
        Item itemToTake = roomToTakeFrom.getItemNamed(itemName);
        
        if(itemName == null)
        {
            return "\nTake what?\n";
        }
        
        else if(itemToTake == null)
        {
            return "\nThere's no " + itemName + " here.\n";
        }
        
        
        else
        {
            roomToTakeFrom.remove(itemToTake);
            GameState.instance().addToInventory(itemToTake);
            return "\n" + itemName + " taken.\n";
        }
    }
    
}
