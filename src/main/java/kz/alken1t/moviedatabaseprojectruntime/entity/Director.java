package kz.alken1t.moviedatabaseprojectruntime.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "director")
@Getter
@Setter
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String surname;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
