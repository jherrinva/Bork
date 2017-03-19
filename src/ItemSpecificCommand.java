

/**
 *
 * @author John Herrin
 */
public class ItemSpecificCommand extends Command
{
    private String verb;
    private String noun;
    
    protected ItemSpecificCommand(String verb, String noun)
    {
        this.verb = verb;
        this.noun = noun;
    }

    @Override
    protected String execute() 
    {
        if(noun.equals("null"))   // null is passed in as the noun if user entered a valid command for an item somewhere in dungeon, but didnt include a second word after the verb in their request
        {
            return "\n" + verb + " what?\n";
        }
        
        else //we know we have 2+ words in user request
        {
            
            Item checkForItemInArea = GameState.instance().getItemInVicinityNamed(noun);  // checking for an item in the players immediate area
            
            if (checkForItemInArea == null)
            {
                return "\nThere's no " + noun + " here.\n";
            }
            else // found the noun in area
            {
                String messageToReturn = checkForItemInArea.getMessageForVerb(verb); //trying to verb the noun
                if (messageToReturn==null) //if doesnt find a message associated with that verb/noun combination, we know we cant perform that action
                {
                    return "\nYou cant " + verb + " the " + noun + "\n";
                }
                else
                {
                    return "\n" +  messageToReturn + "\n";
                }
            }
            
            
            //check if noun is in vicinity
                //if not, return "Theres no ...... here" (check if item null)
                //check if noun has that verb
                    //if yes return the appropriate message
                    //if not return You cant VERB the NOUN
            
            
            
            
            
        }
        
        
    }
}
