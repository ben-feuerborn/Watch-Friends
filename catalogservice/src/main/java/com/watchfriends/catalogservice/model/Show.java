package com.watchfriends.catalogservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    private String title;
    private Integer seasons;
    private String genre;
}