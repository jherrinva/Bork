
/**
 * Creates exits for dungeon rooms
 * @author John Herrin
 */
public class Exit 
{
    private String dir;
    private Room source;
    private Room destination;
    
    
    /**
     * Constructor
     * @param dir Direction the exit leads
     * @param src Room you are in that contains this exit
     * @param dest Room the exit leads to
     */
    public Exit(String dir, Room src, Room dest)
    {
        this.dir = dir;
        this.source = src;
        this.destination = dest;
    }
    
    /**
     * Gets you info on where you can go
     * @return  Returns string of the direction you can go, and what title of room the exit leads to
     */
   protected String describe()
   {
      String description = "You can go " + dir + " to " + destination.getTitle();
      return description;
   }
   
   /**
    * Gets you the direction of the exit
    * @return String of the direction exit is headed
    */
   public String getDir()
   {
       return dir;
   }
   
   /**
    * Gets you the room that contains this exit
    * @return Room object containing this exit
    */
   public Room getSrc()
   {
       return source;
   }
   
   /**
    * Gets you the room this exit leads to
    * @return Room object that this exit leads to
    */
   public Room getDest()
   {
       return destination;
   }
    
    
    
    
    
}
