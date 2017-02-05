
/**
 *
 * @author John Herrin
 */

import java.util.Scanner;

public class Interpreter 
{
    
    private static Dungeon buildSampleDungeon()
    {
        Room zietzDungeonEntry = new Room("Zietz Dungeon Entry");
        zietzDungeonEntry.setDesc("This room is the Zietz Dungeon Entry! This room is very hot! Find the thermostat");
        Dungeon sampleDungeon = new Dungeon(zietzDungeonEntry,"Zietz Dungeon");
        
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
