package control;
import model.Movie;

public class MovieSearcher{
    //uses the database controller to search the database for a movie with the name name
    public Movie searchMovie(String name){
        return DatabaseController.getOnlyInstance().searchMovie(name);
    }
}