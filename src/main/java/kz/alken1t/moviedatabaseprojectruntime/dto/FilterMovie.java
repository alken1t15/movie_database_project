package kz.alken1t.moviedatabaseprojectruntime.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FilterMovie {
    private String name;
    private Integer yearStart;
    private Integer yearEnd;
    private Double rating;
    private String nameActor;
    private String nameDirector;
    private Integer startPage;
    private Integer endPage;
}