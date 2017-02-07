
/**
 *
 * @author John Herrin
 */

import java.util.Scanner;

public class Interpreter 
{
    
    private static Dungeon buildSampleDungeon()
    {
        ////create rooms///////
        Room livingRoom = new Room("Living Room");
        Room bedroom = new Room("Bedroom");
        Room closet = new Room("Closet");
        Room kitchen = new Room("Kitchen");
        Room pantry = new Room("Pantry");
        //////////////////////////
        
        
        
        ////set descriptoins////////////////
        livingRoom.setDesc("This room is very colorful.  There is one chair, and a scary rabbit is sitting in it.");
        bedroom.setDesc("This room is very hot! But there are no windows");
        closet.setDesc("This closet is bigger than the bedroom itself.  But all the clothes inside have been eaten by moths.");
        kitchen.setDesc("This place hasnt been cleaned in years.  There is a basketball in the sink for some reason.");
        pantry.setDesc("The cupboards are bare.  All thats left is an unopened twinkie.  Suprisingly in good condition.");
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
        
        
        
        Dungeon sampleDungeon = new Dungeon(livingRoom,"Zietz Dungeon");
        
        return sampleDungeon;
    }
    
    
    
    public static void main (String[] args)
    {   
        
        Dungeon startingDungeon = buildSampleDungeon();
        System.out.println("Welcome to " + startingDungeon.getName() + ". " + startingDungeon.getEntry().describe());
        System.out.println(startingDungeon.getEntry().describe()); // tests to make sure describe ran twice only provides the title
        
        
        /////////Sample query below/////////
        /**
        Scanner in = new Scanner(System.in);
        String userInput = new String();
        while (!userInput.equals("q"))
        {
            System.out.println("What would you like to do? : ");
            userInput = in.nextLine();
        }
        */
        /////////////////////////////////////
        
        
        //////////Test room below////////////
        /**
        Room newRoom = new Room("Dr Zietz Room");
        newRoom.setDesc("This room is very hot.  Find the thermostat!");
        System.out.println(newRoom.describe());
        System.out.println(newRoom.describe());
        */
        ///////////////////////////////////////////
    }
    
}
