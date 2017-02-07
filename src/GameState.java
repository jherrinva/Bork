
/**
 *
 * @author John Herrin
 */
public class GameState 
{
    
    private static GameState gameStateInstance = null;
    
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
        
    }
    
    public Room getAdventurersCurrentRoom()
    {
        
    }
    
    public void setAdventurersCurrentRoom(Room room)
    {
        
    }
    
    public Dungeon getDungeon()
    {
        
    }
    
    
}
