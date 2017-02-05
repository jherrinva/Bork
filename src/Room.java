

/**
 *
 * @author John Herrin
 */
public class Room 
{
    private String title;
    private String desc;
    private boolean beenHere;
    
    public Room(String title)
    {
        this.title = title;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
    protected String describe ()
    {
        if (beenHere)
            return desc;
        else 
            return desc;
    }
    
    
    
}
