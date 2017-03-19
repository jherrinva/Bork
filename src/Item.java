
import java.io.BufferedReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Hashtable;
import java.util.Set;

/**
 * This class creates an item, store in Rooms / Dungeon / and players inventory in GameState
 * @author John Herrin
 */
public class Item 
{
    private String primaryName;
    private int weight;
    private Hashtable<String, String> messages = new Hashtable<>(); //verb,message
    
    /**
     * Constructor
     * @param s file being read
     * @throws IOException 
     */
    public Item(BufferedReader s) throws IOException
    {
        //make sure if the first line this constructor comes into is ===, then to return an exception to break the while loop
        String currentLine;
        boolean nameAndWeightDone = false;
        
        while ((currentLine = s.readLine()) != null)
        {
            if(currentLine.equals("===")) //all items done
            {
                throw new EmptyStackException();
            }
            
            else if(currentLine.equals("---")) //end of single item creation
            {
                break;
            }
            
            else if (!nameAndWeightDone) //setting primaryName and weight for an item
            {
                this.primaryName = currentLine;
                currentLine = s.readLine();
                this.weight = Integer.parseInt(currentLine);
                nameAndWeightDone = true;
                continue;
            }
            else //on verbs for an item
            {
                String[] entireVerbLine = currentLine.split(":");
                messages.put(entireVerbLine[0],entireVerbLine[1]);
            }   
        }
    }
    
    /**
     * Checks if this itemobject exists by this name
     * @param name name to check
     * @return boolean true if it is this item, false if not
     */
    public boolean goesBy(String name)
    {
        if(primaryName.equalsIgnoreCase(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * get Item name of this itemobject
     * @return items name
     */
    public String getPrimaryName()
    {
        return primaryName;
    }
    
    /**
     * Gets appropriate message for a verb being passed in.
     * @param verb Verb to get message for
     * @return appropriate message, null if verb doesnt exists for item
     */
    public String getMessageForVerb(String verb)
    {
        String verbMessageToReturn = messages.get(verb);
        return verbMessageToReturn;
    }
    
    
    /**
     * Used to print item object in testcases
     * @return 
     */
    @Override
    public String toString()
    {
        String info = "The item name is: " + primaryName + 
                "\n" + "The item weight is: " + weight + "\n";
        if(!messages.isEmpty())
        {
            Set<String> keys = messages.keySet();

            for (String aKey: keys)
            {
                info += "Item has verb '" + aKey + "' with message '" + messages.get(aKey) + "'\n";
            }
        }
        return info;
    }
    
    
    
}
