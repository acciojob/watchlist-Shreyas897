package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);
    }
    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director) {
        return new ResponseEntity<>(movieService.addDirector(director), HttpStatus.CREATED);
    }
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie_name,@RequestParam("director") String director_name){
        String res=movieService.addMovieDirectorPair(movie_name,director_name);
        if(res.equals("Movie Doesn't Exist")||res.equals("Director Doesn't Exist")){
            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
        Movie res=movieService.getMovieByName(name);
        if(res==null)
            return new ResponseEntity<>("Movies doesn't exist",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res,HttpStatus.CREATED);

    }
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director res=movieService.getDirectorByName(name);
        if(res==null)
            return new ResponseEntity<>("Director doesn't exist",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res,HttpStatus.FOUND);
    }
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String director){
        List<String>res=movieService.getMoviesByDirectorName(director);
        if(res==null)
            return new ResponseEntity<>("Director Doesn't exist",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res,HttpStatus.FOUND);
    }
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(),HttpStatus.FOUND);
    }
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name){
        return new ResponseEntity<>(movieService.deleteDirectorByName(name),HttpStatus.CREATED);
    }
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        return new ResponseEntity<>(movieService.deleteAllDirectors(),HttpStatus.CREATED);
    }
}
