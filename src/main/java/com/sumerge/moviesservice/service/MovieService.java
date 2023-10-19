package com.sumerge.moviesservice.service;

import com.sumerge.moviesservice.response.MoviesResponse;
import com.sumerge.moviesservice.entity.Movie;

public interface MovieService {
    MoviesResponse findAll(int pageNumber, int pageSize);

    Movie findById(int movieId);
}
