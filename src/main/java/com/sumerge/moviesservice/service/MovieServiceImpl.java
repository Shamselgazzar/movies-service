package com.sumerge.moviesservice.service;

import com.sumerge.moviesservice.exceptions.MovieNotFoundException;
import com.sumerge.moviesservice.response.MoviesResponse;
import com.sumerge.moviesservice.entity.Movie;
import com.sumerge.moviesservice.dao.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie findById(int movieId) {
        Optional<Movie> dbMovie = movieRepository.findById(movieId);
        if(dbMovie.isPresent()){
            return dbMovie.get();
        }else {
            throw new MovieNotFoundException("Movie was not find. Id: "+ movieId);
        }
    }

    @Override
    public MoviesResponse findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Movie> page =  movieRepository.findAll(pageable);

        return createMoviesResponse(page);
    }

    private MoviesResponse createMoviesResponse(Page<Movie> page){

        MoviesResponse moviesResponse = new MoviesResponse();

        moviesResponse.setPage(page.getNumber());
        moviesResponse.setResults(page.getContent());
        moviesResponse.setPage_size(page.getSize());
        moviesResponse.setTotal_pages(page.getTotalPages());
        moviesResponse.setTotal_results(page.getTotalElements());
        moviesResponse.setLast(page.isLast());

        return moviesResponse;

    }
}
