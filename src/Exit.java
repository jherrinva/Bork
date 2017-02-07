
/**
 *
 * @author John Herrin
 */
public class Exit 
{
    private String dir;
    private Room source;
    private Room destination;
    
    public Exit(String dir, Room src, Room dest)
    {
        this.dir = dir;
        this.source = src;
        this.destination = dest;
    }
    
   protected String describe()
   {
      String description = "You can go " + dir + " to " + destination.getTitle();
      return description;
   }
   
   public String getDir()
   {
       return dir;
   }
   
   public Room getSrc()
   {
       return source;
   }
   
   public Room getDest()
   {
       return destination;
   }
    
    
    
    
    
}
