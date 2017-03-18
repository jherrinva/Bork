

/**
 *
 * @author John Herrin
 */
public class TakeCommand extends Command
{

    private String itemName;
    
    protected TakeCommand(String itemName)
    {
        this.itemName = itemName;
    }
    
    @Override
    protected String execute() 
    {
        Room roomToTakeFrom = GameState.instance().getAdventurersCurrentRoom();
        Item itemToTake = roomToTakeFrom.getItemNamed(itemName);
        
        if (itemToTake == null)
        {
            return "There's no " + itemName + " here.";
        }
        else
        {
            roomToTakeFrom.remove(itemToTake);
            GameState.instance().addToInventory(itemToTake);
            return itemName + " taken.";
        }
    }
    
}
