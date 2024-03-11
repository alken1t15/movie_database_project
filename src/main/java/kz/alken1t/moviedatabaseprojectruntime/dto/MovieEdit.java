package kz.alken1t.moviedatabaseprojectruntime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MovieEdit {
    private Long id;
    private String name;
    private Integer year;
    private String genre;
    private Double rating;
    private List<String> actors;
    private List<String> directors;
}
