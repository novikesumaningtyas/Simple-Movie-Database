import java.util.*;

/**
 * Movie class 
 * Stored movie attribute
 * 
 * @author Novi Kesumaningtyas
 * @version 1 - 25 May 2017
 */
public class Movie implements Comparable<Movie>
{
    private String title;
    private String director;
    private ArrayList<String> actorList;
    private int rating;
    private Validation validation;

    /**
     * Movie()
     * Default Constructor for objects of Movie class
     * title - store movies' title
     * director - store movie directors' name
     * actorlist - arrayList<String> hold string list for actor name
     * rating - store movies' rating
     * validation - object from class Validation for validate input in Movie class
     */
    public Movie()
    {
        title = "unknownTitle";
        director = "unknownDirector";
        actorList = new ArrayList<String>(Arrays.asList("unknown1","unknown2","unknown3"));
        rating = 0;
        validation = new Validation();
    }
    
    /**
     * Movie(String movieName, String directorName, String act1, String act2, String act3, int star)
     * Constructor non-default for Movie class 
     * if the input paremeter is not sufficient, field will have default value
     * 
     * @param String movieName - title for movie
     * @param String directorName - directer name of the movie
     * @param String act1 - first actor in the movie 
     * @param String act2 - second actor in the movie
     * @param String act3 - third actor in the movie 
     * @param int star - rating for movie 
     */
    public Movie(String movieName, String directorName, String act1, String act2, String act3, int star)
    {
        boolean valid;
        validation = new Validation();
        
        valid = validation.checkNoBlank(movieName.trim());
        if (valid)
            title = movieName;
        else
            title = "unknownTitle";
 
        valid = validation.checkNoBlank(directorName.trim());
        if (valid)
            director = directorName;
        else
            director = "unknownDirector";
        
        valid = validation.checkNoBlank(act1.trim());    
        if (valid)
            actorList = new ArrayList<String>(Arrays.asList(act1,act2,act3));
        else
            actorList = new ArrayList<String>(Arrays.asList("unknown1",act2,act3));
       
        if (star <= 10 && star > 0)
            rating = star;
        else
        {
            System.out.println("\t\t!!ERROR : Invalid Input.Please enter a valid rating number (1-10)");
            rating = 0;
        }
    }
    
    /**
     * public int compareTo(Movie compareFilm)
     * Sort movie object by its property (in this method by the rating)
     * Has to override the compareTo method 
     * and make it return ascending
     */
    @Override
    public int compareTo(Movie compareFilm)
    {
        int compareRate = ((Movie)compareFilm).getRating();
        return rating - compareRate;
    }
    
    /**
     * displayMovieDetails()
     * Display the movies' detail to the terminal
     */
    public void displayMovieDetails()
    {
        System.out.println("Movies' title: " + title);
        System.out.println("Director: " + director);
        System.out.println("Actors : " + actorList.get(0) + "," + actorList.get(1) + "," + actorList.get(2));
        System.out.println("Rating: " + rating);
    }
    
    /**
     * getActor1()
     * get the first actor name
     * 
     * @return String - first actor name
     */
    public String getActor1()
    {
        return actorList.get(0);
    }
    
    /**
     * getActor2()
     * get the second actor name
     * 
     * @return String - second actor name
     */
    public String getActor2()
    {
        return actorList.get(1);
    }
    
    /**
     * getActor3()
     * get the third actor name
     * 
     * @return String - third actor name
     */
    public String getActor3()
    {
        return actorList.get(2);
    }
    
    /**
     * getDirector()
     * get the directors' name
     * 
     * @return String - director name
     */
    public String getDirector()
    {
        return director;
    }
    
    /**
     * getRating()
     * get the movies' rating
     * 
     * @return int - movies' rating
     */
     public int getRating()
    {
        return rating;
    }
    
    /**
     * getTitle()
     * get movies' title
     * 
     * @return String - movies' title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * setActor1(String actorName)
     * set first actor name
     * 
     * @param actorName - first actor name in the movie
     */
     public void setActor1(String actorName)
    {
        boolean valid = validation.checkNoBlank(actorName.trim());
        
        if (valid)
            actorList.set(0,actorName);
    }
    
    /**
     * setActor2(String actorName)
     * set second actor name
     * 
     * @param actorName - second actor name in the movie
     */
    public void setActor2(String actorName)
    {
        actorList.set(1,actorName);
    }
    
    /**
     * setActor3(String actorName)
     * set third actor name
     * 
     * @param actorName - third actor name in the movie
     */
    public void setActor3(String actorName)
    {
        actorList.set(2,actorName);
    }
    
    /**
     * setDirector(String directorName)
     * set the director name
     * 
     * @param directorName - directors' name for the movie
     */
    public void setDirector(String directorName)
    {
        boolean valid = validation.checkNoBlank(directorName.trim());
        
        if (valid)
            director = directorName;
    }
    
    /**
     * setRating(int star)
     * set the movies' rating
     * 
     * @param int star - input rating for movie
     */
    public void setRating(int star)
    {
        if (star <= 10 && star > 0)
            rating = star;
        else
            System.out.println("\t\t!!ERROR : Invalid Input.Please enter a valid rating number (1-10)");
    }
    
    /**
     * setTitle(String movieName)
     * set movies' title
     * 
     * @param movieName - input for set the movies' title
     */
    public void setTitle(String movieName)
    {
       boolean valid = validation.checkNoBlank(movieName.trim());
       
       if (valid)
         title = movieName;
    }
}
