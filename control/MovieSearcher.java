package control;
import model.Movie;

public class MovieSearcher{
    public Movie searchMovie(String name){
        return DatabaseController.getOnlyInstance().searchMovie(name);
    }
}