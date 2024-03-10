package kz.alken1t.moviedatabaseprojectruntime.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int year;

    private String genre;

    private double rating;

    private int count;

    @OneToMany(mappedBy = "movie")
    private List<Actor> actors;

    @OneToMany(mappedBy = "movie")
    private List<Director> directors;
}