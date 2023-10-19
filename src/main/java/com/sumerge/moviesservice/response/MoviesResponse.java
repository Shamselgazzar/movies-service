package com.sumerge.moviesservice.response;

import com.sumerge.moviesservice.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MoviesResponse {
    private int page;
    private List<Movie> results;
    private int page_size;
    private int total_pages;
    private long total_results;
    private boolean last;
}
