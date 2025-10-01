package com.watch_friends.media_service.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString
@Entity
@Table(name="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tmdbId;
    private String title;
    private String overview;
    private String posterPath;
    private String releaseDate;
}
