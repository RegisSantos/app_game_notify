package com.gametime.gamenotify.domain.model;

import java.time.LocalDate;

public class Game {

    private Long id;
    private String title;
    private String description;
    private String genre;
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(Long id, String title, String description, String genre, LocalDate releaseDate) {
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