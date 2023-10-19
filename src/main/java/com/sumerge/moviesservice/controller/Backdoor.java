package com.sumerge.moviesservice.controller;

import com.sumerge.moviesservice.dao.MovieRepository;
import com.sumerge.moviesservice.entity.Movie;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/backdoor")
public class Backdoor {

    private final MovieRepository movieRepository;
    public Backdoor(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping("/more")
    public String addMovies(
            @RequestParam(name = "password") String password,
            @RequestBody List<Movie> movies
    ){
        if (password.equals("12345")){
            movieRepository.saveAll(movies);
        return "request successful- movies were add to database";
        }else {
            return "request failed - no movies were added to database";
        }
    }


    @PostMapping("/one")
    public String addMovie(
            @RequestParam(name = "password") String password,
            @RequestBody Movie movie
    ){
        if (password.equals("12345")){
            movieRepository.save(movie);
            return "request successful- movie was add to database";
        }else {
            return "request failed - movie was not added to database";
        }
    }





}
