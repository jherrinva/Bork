

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
        GameState myGame = GameState.instance();
        CommandFactory myFactory = CommandFactory.instance();


        
        
        
        mainMethodAsk:
        while(true)
        {
            System.out.println("Please enter the file name to be processed: ");
            String userFileName = in.nextLine();    
            
           
            
            try
            {
                //if = user has a .sav file and wants to load dungeon and room data from it
                if(userFileName.substring(userFileName.length()-4 , userFileName.length()).equals(".sav"))
                {
                    myGame.restore(userFileName);
                    break;
                }
                else // user has .bork file, and wants to load a dungeon with no save data
                {
                    Dungeon newDungeon = new Dungeon (userFileName, true);
                    myGame.initialize(newDungeon);
                    break;
                }
               
            }catch(Exception e)
            {
                System.out.println("Your file either doesnt exist, or you have not included the file extension.");
                System.out.println("Valid extensions are only .sav and .bork");
                System.out.println("Please try again");
                System.out.println("__________________________________________________");
                continue mainMethodAsk;
            }
            
        }
        
        
        String direction = "";
        System.out.println("\n\nWelcome to " + myGame.getDungeon().getName() + ".");
        /**
         * While loop asks user what to do until 'q' is entered.
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
            
            String commandMessageToPrint =  myFactory.parse(direction).execute();
            System.out.println(commandMessageToPrint);
        }


    }
    
}
