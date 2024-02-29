package kz.alken1t.moviedatabaseprojectruntime.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilterMovie {
    private String name;
    private Integer yearStart;
    private Integer yearEnd;
    private Double rating;
    private String nameActor;
    private String nameDirector;
}