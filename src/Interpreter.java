

import java.io.IOException;
import java.util.Scanner;

/**
 * Contains main method, and a method to build a sample dungeon
 * @author John Herrin
 */
public class Interpreter 
{

    
    public static void main (String[] args) throws IOException
    {   
        Scanner in = new Scanner(System.in);
        
        System.out.println("Please enter the file name to be processed: ");
        String userFileName = in.nextLine();
        //Dungeon newDungeon = new Dungeon (userFileName);
        //need to check if line ends in .sav, .bork, or doesnt exist
        
        
        GameState myGame = GameState.instance();
        //myGame.initialize(newDungeon); // remove this, move to GameState restore() method
        CommandFactory myFactory = CommandFactory.instance();
        myGame.restore(userFileName);
        
        
        String direction = "";
        System.out.println("Welcome to " + myGame.getDungeon().getName() + ".");
         
        
        
        
        
        /**
         * While loop asks user what do until 'q' is entered.
         */
        while (true)
        {
           
            System.out.println(myGame.getAdventurersCurrentRoom().describe());
            System.out.print("\n<");
            direction = in.nextLine();
            if (direction.equals("q"))
            {
                break;
            }
            boolean commandExists = myFactory.commandList.containsKey(direction);
            if (commandExists)
            {
                myFactory.parse(direction).execute();
            }
            else
            {
                System.out.println("Unknown Command.  Please try again");
            }
            
        }


    }
    
}
