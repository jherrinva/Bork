
import java.io.BufferedReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Hashtable;
import java.util.Set;

/**
 *
 * @author John Herrin
 */
public class Item 
{
    private String primaryName;
    private int weight;
    private Hashtable<String, String> messages = new Hashtable<>(); //verb,message
    
    
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
                System.out.println("About to break from one item!");
                break;
            }
            
            else if (!nameAndWeightDone) //setting primaryName and weight for an item
            {
                //System.out.println("setting name and weight!");
                this.primaryName = currentLine;
                currentLine = s.readLine();
                this.weight = Integer.parseInt(currentLine);
                nameAndWeightDone = true;
                //System.out.println(primaryName);
                //System.out.println(weight);
                continue;
            }
            else //on verbs for an item
            {
                System.out.println("doing verbs!");
                String[] entireVerbLine = currentLine.split(":");
                messages.put(entireVerbLine[0],entireVerbLine[1]);
                System.out.println(entireVerbLine[0]);
                System.out.println(entireVerbLine[1]);
            }
            
            
        }
        
        
        
    }
    
    public boolean goesBy(String name)
    {
        //will use later in items future
        return true;
    }
    
    public String getPrimaryName()
    {
        return primaryName;
    }
    
    public String getMessageForVerb(String verb)
    {
        String verbMessageToReturn = messages.get(verb);
        return verbMessageToReturn;
    }
    
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
