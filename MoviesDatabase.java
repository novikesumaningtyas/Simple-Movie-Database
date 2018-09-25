import java.util.*;
import java.io.*;

/**
 * Movies Databases Class 
 * Handle database system for movies storage
 * 
 * @author Novi Kesumaningtyas
 * @version 13 - 25 May 2017
 */
public class MoviesDatabase
{
    private FileOperation file;
    private Menu menu;
    private Validation validation;
   
    /**
     * Constructor for objects of class TestMovies
     */
    public MoviesDatabase()
    {
       file = new FileOperation();
       menu = new Menu();
       validation = new Validation();
    }
    
    /**
     * addMovie(ArrayList<Movie> movies)
     * Add new movie to movies array
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     */
    public void addMovie(ArrayList<Movie> movies)
    {
        String title = "";
        String director = "";
        String actor1 = "";
        String actor2 = "";
        String actor3 = "";
        int rating = 0;
      
        title = insertTitle();
        boolean checkResult = checkMovieRedundancyByTitle(movies, title);
        if (checkResult == true)
            return;
            
        director = insertDirector();
        actor1 = insertActor(1);
        actor2 = insertActor(2);
        if (actor2.length() != 0)
            actor3 = insertActor(3);
         
        rating = insertRating();
        
        Movie film = createNewMovie(title,director,actor1,actor2,actor3,rating);
        movies.add(film);
        displayOneFilm(film);
        System.out.println("\n\t\t\t >>>>> Yeayy, " + title.toUpperCase() + " successfully ADD to database! <<<<< ");
    }
    
    /**
     * addMovieToArray(ArrayList<Movie> movies, String movieName, String directorName, String act1, String act2, String act3, int star)
     * adding a movie to movies array list
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     * @param String movieName - title for movie
     * @param String directorName - directer name of the movie
     * @param String act1 - first actor in the movie 
     * @param String act2 - second actor in the movie
     * @param String act3 - third actor in the movie 
     * @param int star - rating for movie 
     */
    private void addMovieToArray(ArrayList<Movie> movies, String movieName, String directorName, String act1, String act2, String act3, int star)
    {
        Movie clip = new Movie(movieName, directorName, act1, act2, act3, star);
        movies.add(clip);
    }
    
    /**
     * checkExistence(ArrayList<Movie> movies, String title)
     * Checking existance for title input by user in original movies list
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     * @param String title - title for movie
     * 
     * @return ArrayList<Movie> movies - checked ArrayList movie
     */
    private ArrayList<Movie> checkExistence(ArrayList<Movie> movies, String title)
    {
        ArrayList<Movie> result = new ArrayList<Movie>();
        
        for (Movie film : movies)
        {
            String head = film.getTitle().trim().toLowerCase();
            
            if (head.contains(title))
                result.add(film);
        }
        
        return result;
    }
    
    /**
     * checkMovieRedundancyByTitle(ArrayList<Movie> movies, String title)
     * Check if the title that the user input is already in existing movie arraylist 
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     * @param String title - title for movie
     * 
     * return true - if input title is already exist
     * return false - if the input title is not in arraylist
     */
    private boolean checkMovieRedundancyByTitle(ArrayList<Movie> movies, String title)
    { 
        for (Movie film : movies)
        {
            String head = film.getTitle();
            
            if (head.equalsIgnoreCase(title))
            {
                System.out.println("\n\t\t >>>>> Sorry, " + title.toUpperCase() + " is already EXIST in database <<<<< ");
                displayOneFilm(film);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * createNewMovie(String movieName, String directorName, String act1, String act2, String act3, int star)
     * create new object movie
     * 
     * @param String movieName - title for movie
     * @param String directorName - directer name of the movie
     * @param String act1 - first actor in the movie 
     * @param String act2 - second actor in the movie
     * @param String act3 - third actor in the movie 
     * @param int star - rating for movie 
     * 
     * @return Movie - object movie
     */
    private Movie createNewMovie(String movieName, String directorName, String act1, String act2, String act3, int star)
    {
        Movie clip = new Movie(movieName, directorName, act1, act2, act3, star);
        return clip;
    }
    
    /**
     * deleteMovie(ArrayList<Movie> movies)
     * Show delete option for the user
     * User input the movies' title that want to be deleted 
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     */
    public void deleteMovie(ArrayList<Movie> movies)
    {
        ArrayList<Movie> result = new ArrayList<Movie>();
        
        String deleteMovie = insertTitle();
        result = checkExistence(movies, deleteMovie);
        boolean value = displayExistanceResult(result, deleteMovie);
        
        if (value == false)
            return;
      
        int options = insertNumberOption(result, "delete");
        options = options - 1 ;
        String head = result.get(options).getTitle();
        removeFilm(movies, head);
    }
    
    /**
     * displayExistanceResult(ArrayList<Movie> result, String title)
     * Show movies that already checked the existancies in the movie arraylist
     * 
     * @param ArrayList<Movie> result - ArrayList movie type 
     * @String title - title for movie
     * 
     * @return true if the movie existed
     * @return false if the movie is not in arraylist
     */
    private boolean displayExistanceResult(ArrayList<Movie> result, String title)
    {
        boolean value = false;
        
        if (result.size() > 0)
        {
            displayMovie(result);
            value = true;
        }
        else
        {
            System.out.println("\n\t\t >>>>> Sorry, " + title.toUpperCase() + " is NOT EXIST in movie database <<<<< ");
            value = false;
        }
        
        return value;
    }
    
    /**
     * displayExistanceResultByDir(ArrayList<Movie> result)
     * Show movies that already checked the existancies search by the director in the movie arraylist
     * 
     * @param ArrayList<Movie> result - ArrayList movie type 
     */
    private void displayExistanceResultByDir(ArrayList<Movie> result)
    {
        if (result.size() > 0)
            displayMovie(result);
        else
            System.out.println("\n\t\t >>>>> Sorry, we don't have any movie by the input directors <<<<< ");
    }
    
    /**
     * displayFavourite(ArrayList<Movie> movies)
     * Show movies sort by the input rating by the user
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     */
    public void displayFavourite(ArrayList<Movie> movies)
    {
        int rate = 0;
        ArrayList<Movie> rateResult = new ArrayList<Movie>();
        Scanner console = new Scanner(System.in);
        boolean valid = false;
        String answer = "";
        
        while (!valid)
        {
            System.out.print("\t\tPlease insert the least rating you want to display (1-10): ");
            answer = console.nextLine().trim();
            valid = validation.integerValidation(answer,1,10);
        }
        
        rate = Integer.parseInt(answer);
        for (Movie film : movies)
        {
            int rating = film.getRating();
            
            if (rating >= rate)
                rateResult.add(film);
        }
        
        Collections.sort(rateResult);
        displayMovie(rateResult);
    }
    
    /**
     * displayMovie(ArrayList<Movie> movies)
     * Display the movies list from the array
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     */
    public void displayMovie(ArrayList<Movie> movies)
    {
        int index = 1;
        System.out.println("\n\t\t **::::::::::::::::::::::::::MOVIES LIST::::::::::::::::::::::::::::**");
        
        for(Movie film : movies)
        {
            String head = film.getTitle();
            String director = film.getDirector();
            String actor1 = film.getActor1();
            String actor2 = film.getActor2();
            String actor3 = film.getActor3();
            int rating = film.getRating();
            
            System.out.println("\t\t\t Movie number : [" + index +"]");
            System.out.println();
            System.out.println("\t\t\t Movies' Title       : " + head);
            System.out.println("\t\t\t Movies' Director    : " + director);
            
            if (actor2.length() == 0)
                System.out.println("\t\t\t Movies' Actors      : " + actor1);
            else
                if (actor3.length() == 0)
                    System.out.println("\t\t\t Movies' Actors      : " + actor1 + ", " + actor2);
            else
                if (actor3.length() != 0)
                    System.out.println("\t\t\t Movies' Actors      : " + actor1 + ", " + actor2 + ", " + actor3);

            System.out.println("\t\t\t Movies' Rating      : " + rating);
            System.out.println("\t\t **:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**");
            index = index + 1;
        }
    }
    
    /**
     * displayOneFilm(Movie film)
     * Display movie from Movie object
     * 
     * @param film - Movie object to be displayed
     */
    private void displayOneFilm(Movie film)
    {
        String head = film.getTitle();
        String director = film.getDirector();
        String actor1 = film.getActor1();
        String actor2 = film.getActor2();
        String actor3 = film.getActor3();
        int rating = film.getRating();
        
        System.out.println("\n\t\t **::::::::::::::::::::::::::MOVIE DETAILS::::::::::::::::::::::::::::**");
        System.out.println();
        System.out.println("\t\t\t Movies' Title       : " + head);
        System.out.println("\t\t\t Movies' Director    : " + director);
        
        if (actor2.length() == 0)
            System.out.println("\t\t\t Movies' Actors      : " + actor1);
        else
            if (actor3.length() == 0)
                System.out.println("\t\t\t Movies' Actors      : " + actor1 + ", " + actor2);
        else
            if (actor3.length() != 0)
                System.out.println("\t\t\t Movies' Actors      : " + actor1 + ", " + actor2 + ", " + actor3);

        System.out.println("\t\t\t Movies' Rating      : " + rating);
        System.out.println("\t\t **:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::**");
    }
    
    /**
     * edit(ArrayList<Movie> movies)
     * Modify movie detail that already in the array list
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     */
    public void edit(ArrayList<Movie> movies)
    {
        String deleteMovie = "";
        String newact1 = "";
        String newact2 = "";
        String newact3 = "";
        int newrating = 0; 
        String title = "";
        int movieIndex = 0; 
        ArrayList<Movie> result = new ArrayList<Movie>();
       
        title = insertTitle();
        result = checkExistence(movies, title);
        boolean value = displayExistanceResult(result, title);
        
        if (value == false)
            return;
        
        int options = insertNumberOption(result, "modify");
        
        options = options - 1;
        String head = result.get(options).getTitle();
        String director = result.get(options).getDirector();
        String actor1 = result.get(options).getActor1();
        String actor2 = result.get(options).getActor2(); 
        String actor3 = result.get(options).getActor3();
        int rating = result.get(options).getRating();
                
        newact1 = actor1;
        newact2 = actor2;
        newact3 = actor3;
        newrating = rating; 
                
        int ans = insertEditMenuAnswer();
        
        if (ans == 1)
        {
            newact1 = insertActor(1);
            newact2 = insertActor(2);
            
            if (newact2.length() != 0)
            {
                newact3 = insertActor(3);
                
                if (newact3.length() == 0)
                    newact3 = actor3;
            }
            else 
               if (newact2.length() == 0)
                    newact2 = actor2;
        }
        else 
            if (ans == 2)
                newrating = insertRating();
        else
            if (ans == 3)
        {
            newact1 = insertActor(1);
            newact2 = insertActor(2);
            
            if (newact2.length() != 0)
            {
                newact3 = insertActor(3);
                
                if (newact3.length() == 0)
                    newact3 = actor3;
            }
            else 
               if (newact2.length() == 0)
                    newact2 = actor2;    
            newrating = insertRating();
        }
        else
            if (ans == 4)
                return;
                  
        actor1 = newact1;
        actor2 = newact2;
        actor3 = newact3;
        rating = newrating;
               
        for (Movie film : movies)
        {
            String titles = film.getTitle();
            
            if (head.equalsIgnoreCase(titles))
                break;
  
            movieIndex = movieIndex + 1;
        }
            
        Movie film = new Movie(head,director,actor1,actor2,actor3,rating);
        movies.set(movieIndex, film);
        
        System.out.println("\n\t\t >>>>>  As you want, " + head.toUpperCase() + " has been UPDATED! <<<<<");
        displayOneFilm(film);
    }
    
    /**
     * exit(String filename, ArrayList<Movie> movies)
     * Exit the movie database system
     * 
     * @param filename - the name of the file to be written 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     */
    public void exit(String filename, ArrayList<Movie> movies)
    {
        char decision;
        char reply;
        Scanner answer = new Scanner(System.in);
        
        int ans = insertExitMenuAnswer();
        
        if (ans == 1)
            exitAndSave(filename, movies);
        else
            if (ans == 2)
                exitWithoutSave();     
        else 
            if (ans == 3)
                return;
    }
    
    /**
     * exitAndSave(String filename, ArrayList<Movie> movies)
     * Exit the system and save the arraylist to the text file
     * Overwrite the existing file
     * 
     * @param filename - the name of the file to be written 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     */
    private void exitAndSave(String filename, ArrayList<Movie> movies)
    {
        String decision = "";
        Scanner console = new Scanner(System.in);
        System.out.println("\t\tThese are the movies list that will be write to " + filename + " if you choose to save your modify : ");
        displayMovie(movies);
        
        do 
        {
            boolean valid = false;
            while (!valid)
            {
                System.out.print("\t\tWould you like to continue?(y/n) ");
                decision = console.nextLine().trim().toLowerCase();
                valid = validation.checkNoBlank(decision);
            }
            
            decision = decision.substring(0, 1);

            if (decision.equals("y"))
            {
                String moviesDetails = getStoredMovieCollectionList(movies);
                String lastLine = getLastLine(movies);
                file.writeToText(filename, moviesDetails, lastLine);
                exitRegards();
            }    
            else
                if (decision.equals("n"))
                    return; 
            else
                 System.out.println("\t\tPlease enter your answer again (y/n) ");   
        }while (!decision.equals("n") && !decision.equals("y"));
    }
    
    /**
     * exitRegards()
     * Exit greeting display 
     */
    private void exitRegards()
    {
        System.out.println("\t\tSystem exit...");
        System.out.println("\t\t================Thankyou, have a wonderful day! ===============");
        System.exit(0);
    }
    
    /**
     * exitWithoutSave()
     * Exit the system without save the arraylist to the text file
     * Leave the original file as it is
     */
    private void exitWithoutSave()
    {
        String reply = "";
        Scanner console = new Scanner(System.in);
        
        do
        {
            boolean valid = false;
            while (!valid)
            {
                System.out.print("\t\tSo, you want to exit without saving your changes?(y/n) ");
                reply = console.nextLine().trim().toLowerCase();
                valid = validation.checkNoBlank(reply);
            }
            reply = reply.substring(0, 1);
            if (reply.equals("y"))
                exitRegards();
            else
                if (reply.equals("n"))
                    return; 
            else
                 System.out.println("\t\tPlease enter your answer again (y/n) ");
        }while (!reply.equals("n") && !reply.equals("y"));       
    }
    
    /**
     * getLastLine(ArrayList<Movie> movies)
     * store the last detail from movies arraylist
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     * 
     * @return String - string last detail from movies arraylist
     */
    private String getLastLine(ArrayList<Movie> movies)
    {
        int last = movies.size() - 1;
        
        String lastLine = movies.get(last).getTitle()+ "," + movies.get(last).getDirector()+ "," + movies.get(last).getActor1()+","
        + movies.get(last).getActor2()+","+movies.get(last).getActor3()+","+movies.get(last).getRating();
        
        return lastLine;
    }
    
    /**
     * getStoredMovieCollectionList(ArrayList<Movie> movies)
     * store the movie details from the movies array except the last line
     * 
     * @param ArrayList<Movie> movies - ArrayList movie type 
     * 
     * @return String - movies detail from movies arraylist
     */
    private String getStoredMovieCollectionList(ArrayList<Movie> movies)
    {
        StringBuilder linesOfMovieDetails = new StringBuilder();
        
        for (int line = 0; line < (movies.size() - 1); line++)
        {
            linesOfMovieDetails.append(movies.get(line).getTitle() + "," + movies.get(line).getDirector() + "," + movies.get(line).getActor1() + "," 
            + movies.get(line).getActor2() + "," + movies.get(line).getActor3() + "," + movies.get(line).getRating() + ";");
        }
        
        return linesOfMovieDetails.toString();
    }
    
    /**
     * insertActor(int order)
     * insert actor for the new movie
     * 
     * @param order - int order for the input actor
     * 
     * @return String - the actor name that had been validated
     */
    private String insertActor(int order)
    {
        boolean valid = false; 
        String actor = "";
        Scanner console = new Scanner(System.in);
        
        if (order == 1)
        {
            while (!valid)
            {
                System.out.print("\t\tPlease insert the movies' actor(" + order +"): ");
                actor = console.nextLine().trim();
                valid = validation.checkNoBlank(actor);
            }
        }
        else
            if (order != 1)
        {
                System.out.print("\t\tPlease insert the movies' actor(" + order + ") - press enter to leave blank: ");
                actor = console.nextLine().trim();
        }
        
        return actor;
    }
    
    /**
     * insertDirector()
     * insert the director name for the movie
     * 
     * @return String - the name of director that had been validated
     */
    private String insertDirector()
    {
        boolean valid = false; 
        String director = "";
        Scanner console = new Scanner(System.in);
        
        while (!valid)
        {
            System.out.print("\t\tPlease insert the movies' director name: ");
            director = console.nextLine().trim();
            valid = validation.checkNoBlank(director);
        }
       
        return director;
    }
    
    /**
     * insertEditMenuAnswer()
     * insert the answer for choosen edit options and validate it
     * 
     * @return int - the answer number
     */
    private int insertEditMenuAnswer()
    {
        boolean valid = false;
        String choice = "";
        Scanner content = new Scanner(System.in);
        
        while (!valid)
        {
            menu.editOptions();
            choice = content.nextLine().trim();
            valid = validation.integerValidation(choice, 1, 4);
        }
        
        int ans = Integer.parseInt(choice);
        
        return ans;
    }
    
    /**
     * insertExitMenuAnswer()
     * insert the answer for choosen exit options and validate it
     * 
     * @return int - the answer number
     */
    private int insertExitMenuAnswer()
    {
        boolean valid = false;
        String choice = "";
        Scanner content = new Scanner(System.in);
        
        while (!valid)
        {
            menu.exitOptions();
            choice = content.nextLine().trim();
            valid = validation.integerValidation(choice, 1, 3);
        }
        
        int ans = Integer.parseInt(choice);
        return ans;
    }
    
    /**
     * insertNumberOption(ArrayList<Movie> result, String mode)
     * insert the choosen movie number from the movies display and validate it
     * 
     * @param ArrayList<Movie> result - ArrayList movie type 
     * @param String mode - the mode we want to do with movies list
     * 
     * @return int - the answer number
     */
    private int insertNumberOption(ArrayList<Movie> result, String mode)
    {
        boolean valid = false;
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        
        while (!valid)
        {
            System.out.print("\t\tPlease choose the movie you want to "+ mode +" by input the movies' number : ");
            answer = scanner.nextLine().trim();
            valid = validation.integerValidation(answer, 1, result.size());
        }
        
        int options = Integer.parseInt(answer);
        return options;
    }
    
    /**
     * insertRating()
     * insert rating by the user and validate it
     * 
     * @return int for the movies' rating
     */
    private int insertRating()
    {
        boolean valid = false;
        String score = "";
        Scanner console = new Scanner(System.in);
        
        while (!valid)
        {
            System.out.print("\t\tInsert movies' rating (1-10) - higher number indicates better movie:");
            score = console.nextLine().trim();
            valid = validation.integerValidation(score, 1, 10);
        }
            
        int rating = Integer.parseInt(score);
        
        return rating;
    }
    
    /**
     * insertSearchMenuAnswer()
     * insert the choosen answer from the search menu options and validate it
     * 
     * @return int for the search menu options answer
     **/
    private int insertSearchMenuAnswer()
    {
        boolean valid = false;
        String answer = "";
        Scanner content = new Scanner(System.in);
        
        while (!valid)
        {
          menu.searchOptions();
          answer = content.nextLine().trim();
          valid = validation.integerValidation(answer, 1, 3);
        }
  
        int ans = Integer.parseInt(answer);
        return ans;
    }
    
    /**
     * insertTitle()
     * Insert the title for the movie and validate it
     * 
     * @return String - the title for the movie
     */
    private String insertTitle()
    {
        boolean valid = false;
        String title = "";
        
        Scanner console = new Scanner(System.in);
        
        while (!valid)
        {
           System.out.print("\t\tPlease insert the movies' title: ");
           title = console.nextLine().trim().toLowerCase();
           valid = validation.checkNoBlank(title);
        }
        
        return title;
    }
    
    /**
     * removeFilm(ArrayList<Movie> movies, String head)
     * remove the film from the arraylist by its match title input
     * 
     * @param ArrayList<Movie> result - ArrayList movie type 
     * @String head - movies title
     */
    private void removeFilm(ArrayList<Movie> movies, String head)
    {
        for (Movie film : movies)
        {
            String title = film.getTitle();
            
            if (title.equalsIgnoreCase(head))
            {
                movies.remove(film);
                System.out.println("\n\t\t >>>>>  As you wish, " + title.toUpperCase() + " has DELETED <<<<<");
                return;
            }
        }
    }
    
    /**
     * runDatabase()
     * Main method where user interface with movie database system
     */
     public void runDatabase()
    {
        int option;
        String answer = "";
        String filename = ("myvideos.txt");
        ArrayList<Movie> movies = new ArrayList<Movie>();
        String lines = file.readText(filename);
        storeFilesToCollection(lines, movies);
        
        while (true)
        {
            Scanner console = new Scanner(System.in);
            boolean valid = false;
            
            while (!valid)
            {
               menu.menu();
               answer = console.nextLine().trim();
               valid = validation.integerMenuValidation(answer);
            }
            
            option = Integer.parseInt(answer);
            
            switch (option)
            {
                case 1:
                    searchMovie(movies);break;
                
                case 2:
                    addMovie(movies); break;
                
                case 3:
                    deleteMovie(movies); break;
                
                case 4:
                    displayFavourite(movies);break;
                
                case 5:
                    displayMovie(movies);break;
                
                case 6:
                    edit(movies);break;
                    
                case 7:
                    exit(filename, movies);break;
                    
                default:
                    System.out.println("\n\t\tERROR : Invalid Input. Please enter a valid number (1-7) \n ");
            }

        }
    }
    
    /**
     * searchedByDirectors(ArrayList<Movie> movies)
     * Search method that searched movie by its director
     * 
     * @param ArrayList<Movie> result - ArrayList movie type 
     */
    private void searchedByDirectors(ArrayList<Movie> movies)
    {
        boolean valid = false;
        Scanner console = new Scanner(System.in);
        String dir1="";
        ArrayList<String> dirSearch = new ArrayList<String>();
        ArrayList<Movie> filmByDir = new ArrayList<Movie>();
        ArrayList<Movie> listMovieNew = new ArrayList<Movie>();
        dir1 = insertDirector();
        dirSearch.add(dir1.toLowerCase());
        
        if (dir1.length() != 0)
        {
            for(int index = 2 ; index > 0 ; index++)
            {
                System.out.print("\t\tInsert the directors' name(" + index + ")- press enter to leave blank: ");
                String dirs = console.nextLine().trim().toLowerCase();
                
                if (dirs.length() != 0)
                    dirSearch.add(dirs);
                else
                    if (dirs.length() == 0)
                        break;
            }
        }
                
        for (int index = 0; index < movies.size(); index++)
        {
            listMovieNew.add(movies.get(index));
        }
            
        for (int order = 0; order < dirSearch.size() ; order++)
        {
            for (int sequence = 0; sequence < listMovieNew.size() ; sequence++)
            {
                if ((listMovieNew.get(sequence).getDirector().toLowerCase().contains(dirSearch.get(order).toLowerCase())))
                {
                    filmByDir.add(listMovieNew.get(sequence)); 
                    listMovieNew.remove(sequence);
                }
            }
        }
         
        displayExistanceResultByDir(filmByDir);
    }
    
    /**
     * searchedByTitle(ArrayList<Movie> movies)
     * Search method that searched movie by its title
     * 
     * @param ArrayList<Movie> result - ArrayList movie type 
     */
    private void searchedByTitle(ArrayList<Movie> movies)
    {
        ArrayList<Movie> searchResult = new ArrayList<Movie>();
        
        String title = insertTitle();
        searchResult = checkExistence(movies, title);
        displayExistanceResult(searchResult, title);
    }
    
    /**
     * searchMovie(ArrayList<Movie> movies)
     * searchMovie based on title and the directors
     * 
     * @param ArrayList<Movie> result - ArrayList movie type 
     */
    public void searchMovie(ArrayList<Movie> movies)
    {
        int answer = insertSearchMenuAnswer();
 
        if (answer == 1)
            searchedByTitle(movies);
        else
            if (answer == 2)
               searchedByDirectors(movies);
        else
            if (answer == 3)
               return;
    }
    
    /**
     * storeFilesToCollection(String movieDetails, ArrayList<Movie> movies) 
     * storing movies detail to the movies array
     * 
     * @param String movieDetails - String that consist the movies detail
     * @param ArrayList<Movie> movies - the array where the movie object is save and created from the movieDetails string
     */
    private void storeFilesToCollection(String movieDetails, ArrayList<Movie> movies) 
    {
       String[] allMovieInfo = movieDetails.split(";");
       
       for (int line = 0; line < allMovieInfo.length ; line++)
       {
           String[] details = allMovieInfo[line].split(",");
           String title = details[0];
           String director = details[1];
           String actorOne = details[2];
           String actorTwo = details[3];
           String actorThree = details[4];
           int rating = Integer.parseInt(details[5]);
           addMovieToArray(movies,title, director, actorOne, actorTwo, actorThree, rating);
       }
    }
}
