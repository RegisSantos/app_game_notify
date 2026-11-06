package com.gametime.gamenotify.adapters.outbound.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String genre;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    public GameEntity() {
    }

    public GameEntity(Long id, String title, String description, String genre, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}