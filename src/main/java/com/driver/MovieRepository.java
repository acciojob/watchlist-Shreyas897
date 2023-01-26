package com.driver;


import org.springframework.stereotype.Repository;


import java.util.*;

@Repository

public class MovieRepository {

    Map<String,Movie> movdB=new HashMap<>();
    Map<String,Director> dirdB=new HashMap<>();
    Map<String,String> mddB=new HashMap<>();
    //    Add a movie: POST /movies/add-movie
//    Pass the Movie object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovie
    public String addMovie(Movie movie){
        String name=movie.getName();
        movdB.put(name,movie);
        return "Movie Added Successfully";
    }
//    Add a director: POST /movies/add-director
//    Pass the Director object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addDirector
    public String addDirector(Director director) {
        String name = director.getName();
        dirdB.put(name, director);
        return "Director Added Successfully";
    }
//    Pair an existing movie and director: PUT /movies/add-movie-director-pair
//    Pass movie name and director name as request parameters
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovieDirectorPair
    public String addMovieDirectorPair(String movie_name,String director_name){
        if(!movdB.containsKey(movie_name)) {
            return "Movie Doesn't Exist";
        }
        if(!dirdB.containsKey(director_name)){
            return "Director Doesn't Exist";
        }
        mddB.put(movie_name, director_name);
        return "Added Successfully";
    }
//    Get Movie by movie name: GET /movies/get-movie-by-name/{name}
//    Pass movie name as path parameter
//    Return Movie object wrapped in a ResponseEntity object
//    Controller Name - getMovieByName
    public Movie getMovieByName(String name){
        if(!movdB.containsKey(name)){
            return null;
        }
        return movdB.get(name);
    }
//    Get Director by director name: GET /movies/get-director-by-name/{name}
//    Pass director name as path parameter
//    Return Director object wrapped in a ResponseEntity object
//    Controller Name - getDirectorByName
    public Director getDirectorByName(String name) {
        if (!dirdB.containsKey(name)) {
            return null;
        }
        return dirdB.get(name);
    }
//    Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
//    Pass director name as path parameter
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - getMoviesByDirectorName
    public List<String> getMoviesByDirectorName(String director){
        List<String>m=new ArrayList<>();
        if(!dirdB.containsKey(director)){
            return null;
        }
        for(String x: mddB.keySet()){
            if(mddB.get(x).equals(director)){
                m.add(x);
            }
        }
        return m;
    }
//    Get List of all movies added: GET /movies/get-all-movies
//    No params or body required
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - findAllMovies
    public List<String> findAllMovies() {

        return new ArrayList<>(movdB.keySet());

    }
//    Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
//    Pass director’s name as request parameter
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteDirectorByName
    public String deleteDirectorByName(String name){
        HashSet<String>hs= new HashSet<>();
        dirdB.remove(name);
        for(String x:mddB.keySet()){
            if(mddB.get(x).equals(name)){
                hs.add(x);
            }

        }
        for(String x:hs) {
            movdB.remove(x);
            mddB.remove(x);
        }

        return "Deleted Successfully";
    }
//    Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
//    No params or body required
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteAllDirectors
//    (Note that there can be some movies on your watchlist that aren’t mapped to any of the director.
//    Make sure you do not remove them.)
    public String deleteAllDirectors() {
        HashSet<String>hs= new HashSet<>();
        for (String x : mddB.keySet()) {

            hs.add(x);
        }
        for(String x:hs) {
            movdB.remove(x);
            mddB.remove(x);
        }
        dirdB.clear();
        return "Deleted Successfully";
    }

}
