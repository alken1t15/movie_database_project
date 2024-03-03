package kz.alken1t.moviedatabaseprojectruntime.dto;

import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class FilterClass {
    private List<Movie> movies;
    private FilterMovie filterMovie;
}