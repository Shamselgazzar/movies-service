package com.sumerge.moviesservice.controller;

import com.sumerge.moviesservice.response.MoviesResponse;
import com.sumerge.moviesservice.entity.Movie;
import com.sumerge.moviesservice.security.JwtValidatorService;
import com.sumerge.moviesservice.service.MovieService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MoviesRestControllerTest {

    @InjectMocks
    private MoviesRestController moviesRestController;

    @Mock
    private MovieService movieService;

    @Mock
    private JwtValidatorService jwtValidatorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMovies() {
        // Arrange
        //when(jwtValidatorService.validateToken(any(String.class))).thenReturn(null);
        doNothing().when(jwtValidatorService).validateToken(anyString());
        when(movieService.findAll(any(Integer.class), any(Integer.class))).thenReturn(new MoviesResponse());

        // Act
        ResponseEntity<MoviesResponse> responseEntity = moviesRestController.getMovies(0, 20, "Bearer token");

        // Assert
        verify(jwtValidatorService, times(1)).validateToken(any(String.class));
        verify(movieService, times(1)).findAll(0, 20);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testGetMovieDetails() {
        // Arrange
        //when(jwtValidatorService.validateToken(any(String.class))).thenReturn(null);
        doNothing().when(jwtValidatorService).validateToken(anyString());
        when(movieService.findById(any(Integer.class))).thenReturn(new Movie());

        // Act
        ResponseEntity<Movie> responseEntity = moviesRestController.getMovieDetails(1, "Bearer token");

        // Assert
        verify(jwtValidatorService, times(1)).validateToken(any(String.class));
        verify(movieService, times(1)).findById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
    }
}
