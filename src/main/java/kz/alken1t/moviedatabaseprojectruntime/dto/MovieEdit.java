package kz.alken1t.moviedatabaseprojectruntime.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MovieEdit {
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private Integer year;
    @NotNull
    @NotEmpty
    private String genre;
    @NotNull
    @Min(value = 0)
    @Max(value = 10)
    private Double rating;
    private List<String> actors;
    private List<String> directors;

    public MovieEdit(Long id, String name, Integer year, String genre, Double rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }
}