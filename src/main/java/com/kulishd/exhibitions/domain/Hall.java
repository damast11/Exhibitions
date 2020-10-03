package com.kulishd.exhibitions.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "halls")
    private Set<Exposition> expositions;
    public Hall() {
    }

    public Hall(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Exposition> getExpositions() {
        return expositions;
    }

    public void setExpositions(Set<Exposition> expositions) {
        this.expositions = expositions;
    }
}
