package com.sumerge.moviesservice.service;

import com.sumerge.moviesservice.dao.MovieRepository;
import com.sumerge.moviesservice.entity.Movie;
import com.sumerge.moviesservice.exceptions.MovieNotFoundException;
import com.sumerge.moviesservice.response.MoviesResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

public class MovieServiceImplTest {
    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
       // movieService = new MovieServiceImpl(movieRepository);
    }

    @Test
    public void testFindById_findSuccessfully() {
        // Mock the behavior of movieRepository.findById
        int movieId = 1;
        Movie mockMovie = new Movie();
        mockMovie.setId(movieId);
        Mockito.when(movieRepository.findById(movieId)).thenReturn(Optional.of(mockMovie));

        // Call the method and assert the result
        Movie result = movieService.findById(movieId);
        assert(result.getId() == movieId);
        assertEquals(movieId, result.getId());
    }
    @Test//(expected = MovieNotFoundException.class)
    public void testFindById_throwErrorNotFoundMovie() {
        // Mock the behavior of movieRepository.findById
        int movieId = 1;
        Movie mockMovie = new Movie();
        mockMovie.setId(movieId);
        Mockito.when(movieRepository.findById(movieId)).thenReturn(Optional.empty());


        assertThrows(MovieNotFoundException.class, () -> {
            movieService.findById(movieId);
        });
    }
    @Test
    public void testFindAll() {
        // Mock the behavior of movieRepository.findAll
        int pageNumber = 1;
        int pageSize = 10;
        Page<Movie> mockPage = new PageImpl<>(Arrays.asList(new Movie(), new Movie()));

        // Set up mockResponse with expected values
        when(movieRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        // Call the method and assert the result
        MoviesResponse result = movieService.findAll(pageNumber, pageSize);
        // Add your assertions here
        assertEquals(2,result.getPage_size());
    }
}
