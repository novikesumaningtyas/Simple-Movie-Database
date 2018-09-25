import java.util.*;

/**
 * Validation class 
 * For validate user input
 * 
 * @author Novi Kesumaningtyas
 * @version 1 - 25 May 2017
 */
public class Validation
{
    /**
     * Constructor for objects of class Validation
     */
    public Validation()
    {
    }

    /**
     * checkNoBlank(String word)
     * Validate input, check if string input have at least one character.
     * 
     * @param String word - word from user to be checked by the validation
     * 
     * @return true if word have at least one character
     * @return false if word have any character / just blank space
     */
    public boolean checkNoBlank(String word)
    {
        boolean value = true;

        if (word.length() > 0)
            value = true;
        else 
            if (word.length() == 0)
        {
               System.out.println();
               System.out.println("\t\t!!ERROR : Invalid Input. Please do not enter a blank space");
               System.out.println();
               value = false;
        }
        
        return value;
    }
    
    /**
     * integerMenuValidation(String check)
     * Validate input to make sure the input is an integer
     * This validation is for main menu validation
     * 
     * @param String input - input from user, it should be an integer
     * if it is not, validation will show some error
     * 
     * @return true if the input is an integer
     * @return false if the input is not an integer
     */
    public boolean integerMenuValidation(String input)
    {
        Scanner console = new Scanner(input);
        boolean value = false;
        
        if (console.hasNextInt())
            value = true;
        else
        {
            System.out.println();
            System.out.println("\t\t!!ERROR : Invalid Input.Please enter a valid number");
            System.out.println();
            value = false;
        }    
        
        return value;
    }
    
    /**
     * integerValidation(String input, int min, int max)
     * Validate input to make sure the input is an integer
     * Also show the range for the integer should be given
     * 
     * @param String input - input from user, it should be an integer
     * @param int min - minimum integer should be input by user
     * @param int max - maximum integer should be input by user
     * 
     * @return true - if input given is an integer and in the range
     * @return false - if input given is an integer out of range
     * @return false - if input is not an integer
     */
    
    public boolean integerValidation(String input, int min, int max)
    {
        Scanner console = new Scanner(input);
        boolean value = false;
        int numberToCheck;
        
        if (console.hasNextInt())
        {
            numberToCheck = Integer.parseInt(input);
            if (numberToCheck <= max && numberToCheck > 0)
                value = true;
            else
            {
                System.out.println();
                System.out.println("\t\t!!ERROR : Invalid Input.Please enter a valid number (" + min + "-" + max +")");
                System.out.println();
                value = false;
            }
        }        
        else
        {
            System.out.println();
            System.out.println("\t\t!!ERROR : Invalid Input.Please enter a valid number (" + min + "-" + max +")");
            System.out.println();
            value = false;
        }   
        
        return value;
    }
}
