package com.kulishd.exhibitions.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Exposition {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String theme;
    private Double price;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Exposition() {
    }

    public Exposition(String theme, Double price, Date date, User user) {
        this.author = user;
        this.theme = theme;
        this.price = price;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
