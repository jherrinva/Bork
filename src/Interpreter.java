

import java.io.IOException;
import java.util.Scanner;

/**
 * Contains main method, and a method to build a sample dungeon
 * @author John Herrin
 */
public class Interpreter 
{
    /**
     * Used to create a prebuilt dungeon
     * @return prebuilt dungeon object
     */
    private static Dungeon buildSampleDungeon()
    {
        ////create rooms///////
        Room livingRoom = new Room("Living Room");
        Room bedroom = new Room("Bedroom");
        Room closet = new Room("Closet");
        Room kitchen = new Room("Kitchen");
        Room pantry = new Room("Pantry");
        //////////////////////////
        
        
        
        ////set descriptions////////////////
        livingRoom.setDesc("This room is very colorful.  There is one chair, and a scary rabbit is sitting in it");
        bedroom.setDesc("This room is very hot! But there are no windows");
        closet.setDesc("This closet is bigger than the bedroom itself.  But all the clothes inside have been eaten by moths");
        kitchen.setDesc("This place hasnt been cleaned in years.  There is a basketball in the sink for some reason");
        pantry.setDesc("The cupboards are bare.  All thats left is an unopened twinkie.  Suprisingly in good condition");
        /////////////////////////////////////
        
        
        
        ////////add exits to all rooms//////////////
        Exit lrToBedroom = new Exit("w",livingRoom,bedroom);
        livingRoom.addExit(lrToBedroom);
        
        Exit bedToLr = new Exit("e",bedroom,livingRoom);
        bedroom.addExit(bedToLr);
        
        Exit bedToCloset = new Exit("w",bedroom,closet);
        bedroom.addExit(bedToCloset);
        
        Exit closetToBed = new Exit("e",closet,bedroom);
        closet.addExit(closetToBed);
        
        Exit lrToKitchen = new Exit("s",livingRoom,kitchen);
        livingRoom.addExit(lrToKitchen);
        
        Exit kitchenToLr = new Exit("n",kitchen,livingRoom);
        kitchen.addExit(kitchenToLr);
        
        Exit kitchenToPantry = new Exit("w",kitchen,pantry);
        kitchen.addExit(kitchenToPantry);
        
        Exit pantryToKitchen = new Exit("e",pantry,kitchen);
        pantry.addExit(pantryToKitchen);
        
        //////////////////////////////////////
        
        
        
        Dungeon sampleDungeon = new Dungeon(livingRoom,"John's Dungeon");
        sampleDungeon.add(bedroom);
        sampleDungeon.add(closet);
        sampleDungeon.add(kitchen);
        sampleDungeon.add(pantry);
        
        
        return sampleDungeon;
    }
    
    
    
    public static void main (String[] args) throws IOException
    {   
        Scanner in = new Scanner(System.in);
        
        System.out.println("Please enter the file name to be processed: ");
        String userFileName = in.nextLine();
        Dungeon newDungeon = new Dungeon (userFileName);
        
        
        
        GameState myGame = GameState.instance();
        myGame.initialize(newDungeon);
        CommandFactory myFactory = CommandFactory.instance();
        
        
        
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
