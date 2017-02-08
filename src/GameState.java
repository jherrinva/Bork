
/**
 *
 * @author John Herrin
 */
public class GameState 
{
    
    private static GameState gameStateInstance = null;
    private Dungeon currentDungeon;
    private Room currentRoom;
    
    private GameState()
    {
        
    }
    
    public static GameState instance()
    {
        if(gameStateInstance == null)
        {
            gameStateInstance = new GameState();
        }
        return gameStateInstance;
    }
    
    public void initialize (Dungeon dungeon)
    {
        this.currentDungeon = dungeon;
        this.currentRoom = dungeon.getEntry();
    }
    
    public Room getAdventurersCurrentRoom()
    {
        return currentRoom;
    }
    
    public void setAdventurersCurrentRoom(Room room)
    {
        if (room != null)
            this.currentRoom = room;
        else    
            System.out.println("You cant go that direction");
    }
    
    public Dungeon getDungeon()
    {
        return currentDungeon;
    }
    
    
}
