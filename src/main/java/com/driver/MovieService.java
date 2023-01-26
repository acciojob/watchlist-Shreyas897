package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(Movie movie){
        return movieRepository.addMovie(movie);
    }
    public String addDirector(Director director){
        return movieRepository.addDirector(director);
    }
    public String addMovieDirectorPair(String movie_name,String director_name){
        return movieRepository.addMovieDirectorPair(movie_name,director_name);
    }
    public Movie getMovieByName(String name){
        return movieRepository.getMovieByName(name);
    }
    public Director getDirectorByName(String name){
        return movieRepository.getDirectorByName(name);
    }
    public List<String> getMoviesByDirectorName(String director){
        return movieRepository.getMoviesByDirectorName(director);
    }
    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }
    public String deleteDirectorByName(String name){
        return movieRepository.deleteDirectorByName(name);
    }
    public String deleteAllDirectors(){
        return  movieRepository.deleteAllDirectors();
    }
}
