
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
      
   }
   
   public String getDir()
   {
       return dir;
   }
   
   public Room getSrc()
   {
       
   }
   
   public Room getDest()
   {
       
   }
    
    
    
    
    
}
