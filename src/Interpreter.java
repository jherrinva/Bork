
/**
 *
 * @author John Herrin
 */

import java.util.Scanner;

public class Interpreter 
{
    public static void main (String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        String userInput = new String();
        
        while (!userInput.equals("q"))
        {
            System.out.println("What would you like to do? : ");
            userInput = in.nextLine();
        }
    }
}
