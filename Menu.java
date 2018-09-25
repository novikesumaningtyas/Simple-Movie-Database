/**
 * Menu Class
 * Display menu for user options in Movie Database System
 * 
 * @author Novi Kesumaningtyas
 * @version 1 - 22 May 2017
 */
public class Menu
{
    /**
     * Constructor for objects of class Menu
     */
    public Menu()
    {
        
    }
    
    /**
     * editOptions()
     * Options for edit menu
     */
    public void editOptions()
    {
        System.out.println("\n\t\t:::::::::::::::::::::::::::::::::::");
        System.out.println("\t\t|   Select Options Below :        |");
        System.out.println("\t\t:::::::::::::::::::::::::::::::::::");
        System.out.println("\t\t|   [1] Modify Actors             |");
        System.out.println("\t\t|   [2] Modify Rating             |");
        System.out.println("\t\t|   [3] Modify Actors & Rating    |");
        System.out.println("\t\t|   [4] Back To Menu              |");
        System.out.println("\t\t===================================");
        System.out.print("\t\t  Input the option number : ");
    }
    
    /**
     * exitOptions()
     * Options for exit menu
     */
    public void exitOptions()
    {
        System.out.println("\n\t\t:::::::::::::::::::::::::::::::::::::::::");
        System.out.println("\t\t|   Select Options Below :              |");
        System.out.println("\t\t:::::::::::::::::::::::::::::::::::::::::");
        System.out.println("\t\t|  [1] Exit and Save Changes            |");
        System.out.println("\t\t|  [2] Exit Without Save Any Changes    |");
        System.out.println("\t\t|  [3] Back To Menu                     |");
        System.out.println("\t\t=========================================");
        System.out.print("\t\t  Input the option number : ");
    }
    
    /**
     * menu()
     * Main Menu for Movies Databases
     */
    public void menu()
    {
        System.out.println("\n\t\t============================================");
        System.out.println("\t\t||    Welcome to the Movie Database       ||");
        System.out.println("\t\t============================================");
        System.out.println("\t\t||       (1) Search Movie                 ||");
        System.out.println("\t\t||       (2) Add Movie                    ||");
        System.out.println("\t\t||       (3) Delete Movie                 ||");
        System.out.println("\t\t||       (4) Display Favourite Movies     ||");
        System.out.println("\t\t||       (5) Display All Movies           ||");
        System.out.println("\t\t||       (6) Edit movies' detail          ||");
        System.out.println("\t\t||       (7) Exit System                  ||");
        System.out.println("\t\t=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.print(   "\t\tPlease insert your option: ");
    }
    
    /**
     * searchOptions()
     * Options for search menu
     */
    public void searchOptions()
    {
        System.out.println("\n\t\t:::::::::::::::::::::::::::::::::");
        System.out.println("\t\t| Select Options Below :        |");
        System.out.println("\t\t:::::::::::::::::::::::::::::::::");
        System.out.println("\t\t|  [1] Search by - Title        |");
        System.out.println("\t\t|  [2] Search by - Directors    |");
        System.out.println("\t\t|  [3] Back To Menu             |");
        System.out.println("\t\t=================================");
        System.out.print("\t\t Input the option number :  ");  
    }
}
