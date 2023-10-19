package com.sumerge.moviesservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "adult")
    private boolean adult;

    @Column(name = "backdrop_path")
    private String backdrop_path;

    @Column(name = "original_language")
    private String original_language;

    @Column(name = "original_title")
    private String original_title;

    @Column(name = "overview")
    private String overview;

    @Column(name = "popularity")
    private double popularity;

    @Column(name = "poster_path")
    private String poster_path;

    @Column(name = "release_date")
    private String release_date;

    @Column(name = "title")
    private String title;

    @Column(name = "video")
    private boolean video;

    @Column(name = "vote_average")
    private double vote_average;

    @Column(name = "vote_count")
    private int vote_count;
}
