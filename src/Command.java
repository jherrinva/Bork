
import java.io.IOException;

/**
 *
 * @author John Herrin
 */
public class Command 
{
    
    private String dir;
    
    /**
     * Constructor
     * @param dir String associated with the command
     */
    protected Command(String dir) 
    {
        this.dir = dir;
    }
    
    /**
     * executes user command.  Currently only used to switch users currentRoom
     * @return String containing info on command.  Currently blank and unused
     */
    protected String execute() throws IOException
    {
        if (dir.equals("save")) //if command is to save, do this
        {
            GameState.instance().store(GameState.instance().getDungeon().getName() + ".sav");
        }
        else //else it is a directional movement
        {
        Room commandCurrentRoom = GameState.instance().getAdventurersCurrentRoom();
        GameState.instance().setAdventurersCurrentRoom(commandCurrentRoom.leaveBy(dir));
        
        }
        
        String emptyString="";
        return emptyString;    
        
        //returns the text that should be printed to the user in
        //response to the command begin executed
        // but for now it can just return an empty string since therees nothing
        // to print for just the movement commands here
    }
    
}
