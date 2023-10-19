package com.sumerge.moviesservice.controller;


import com.sumerge.moviesservice.response.MoviesResponse;
import com.sumerge.moviesservice.entity.Movie;
import com.sumerge.moviesservice.security.JwtValidatorService;
import com.sumerge.moviesservice.service.MovieService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MoviesRestController {

    private final MovieService movieService;
    private final JwtValidatorService jwtValidatorService;
    public MoviesRestController(MovieService movieService, JwtValidatorService jwtValidatorService) {
        this.movieService = movieService;
        this.jwtValidatorService = jwtValidatorService;
    }

    @GetMapping("/movies")
    public ResponseEntity<MoviesResponse> getMovies(
            @RequestParam(name = "page", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestHeader(name="authorization") String authHeader
    ){

        jwtValidatorService.validateToken(authHeader);

        return ResponseEntity.ok(movieService.findAll(pageNumber, pageSize));

    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<Movie> getMovieDetails(
            @PathVariable int movieId,
            @RequestHeader(name="authorization") String authHeader
    ){

        jwtValidatorService.validateToken(authHeader);

        return ResponseEntity.ok( movieService.findById(movieId));

    }



}
