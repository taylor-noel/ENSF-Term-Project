package control;

import model.*;

public class MovieSearcher {
    Database dataB;

    public MovieSearcher(Database db) {
        dataB = db;
    }

    public Movie searchMovie(String name) {
        for (Movie mov : dataB.getMovies()) {
            if (mov.getName().equals(name)) {
                return mov;
            }
        }
        return null;
    }
}