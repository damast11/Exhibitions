package com.kulishd.exhibitions.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Exposition {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String theme;
    private Double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exposition_hall",
            joinColumns = { @JoinColumn(name = "exposition_id") },
            inverseJoinColumns = { @JoinColumn(name = "hall_id") })
    private Set<Hall>  halls = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Exposition() {
    }

    public Exposition(String theme, Double price, LocalDate date, User author) {
        this.theme = theme;
        this.price = price;
        this.date = date;
        this.author = author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTheme(String text) {
        this.theme = text;
    }

    public String getTheme() {
        return theme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Hall> getHalls() {
        return halls;
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
    }


    public boolean hasHall(Hall hall) {
        for (Hall expositionHall: getHalls()) {
            if (expositionHall.getId().equals(hall.getId())) {
                return true;
            }
        }
        return false;
    }
}
