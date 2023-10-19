package com.sumerge.moviesservice.dao;

import com.sumerge.moviesservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
//@RepositoryRestResource(path = "movies",collectionResourceRel = "movies", exported = false)
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
