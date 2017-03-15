

/**
 * Subclass of Command class.  Moves a player in a direction
 * @author John Herrin
 */
public class MovementCommand extends Command
{
    private String dir; // direction this command will move the player in
    
    protected MovementCommand(String dir)
    {
        this.dir = dir;
    }
    
    /**
     * Overrides abstract method from Command parent class.  Creates a room object to point to users current room
     * Then moves player in direction this Object specifies by using the exit of that room object
     * Sets users new current room to where this exit led
     * @return returns empty string, as the interpreter will be printing the returned string of all executes, and you dont need a print line for a movement command
     */
    @Override
    protected String execute()
    {
        Room commandCurrentRoom = GameState.instance().getAdventurersCurrentRoom();
        GameState.instance().setAdventurersCurrentRoom(commandCurrentRoom.leaveBy(dir));
        
        String uselessString = "";
        return uselessString;
    }
}
