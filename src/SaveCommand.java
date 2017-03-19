
import java.io.IOException;
import java.util.Scanner;




/**
 * Subclass of Command class.  Directs program to initiate process of saving users current dungeon state information to .sav file
 * @author John Herrin
 */
public class SaveCommand extends Command
{
 
    Scanner saveIn = new Scanner (System.in); //new scanner to take in a new file name from user if filecreation triggers an exception
    
    private String saveFilename; //starts as ".sav".  Updated by execute to contain final filename.  This is the filaname to be saved or overwritten

    /**
     * Constructor
     * @param f String ".sav" extension to be added to filename later in execute
     */
    protected SaveCommand(String f)
    {
        this.saveFilename = f;
    }

    /**
     * Overwritten abstract method from Command class
     * This instance begins the process of saving/overwriting a save file of users progress
     * @return message indicating to user that the file has been saved
     */
    @Override
    protected String execute()
    {
        this.saveFilename = GameState.instance().getDungeon().getName() + ".sav";  //gets dungeon name, adds original String ".sav" to it
        
        while(true)
        {
            try 
            {
                GameState.instance().store(saveFilename);
                break;
            }catch(IOException ex) 
            {
                System.out.println("For some reason, your dungeons name is illegal as a saveFileName");
                System.out.println("Below this, please do not use any special characters or spaces");
                System.out.println("Please enter a new filename to use for your save: ");
                saveFilename = saveIn.nextLine() + ".sav";
                continue;
            }
        }
        String explain = saveFilename + " save file has been created / updated \n";
        return explain;
        
    }
    
}
