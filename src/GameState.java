
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
        this.currentRoom = room;
    }
    
    public Dungeon getDungeon()
    {
        return currentDungeon;
    }
    
    
}
