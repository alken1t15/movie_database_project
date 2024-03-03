package kz.alken1t.moviedatabaseprojectruntime.service;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import kz.alken1t.moviedatabaseprojectruntime.dto.FilterClass;
import kz.alken1t.moviedatabaseprojectruntime.dto.FilterMovie;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.entity.Director;
import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryMovie;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceMovie {
    private final RepositoryMovie repositoryMovie;
    private final EntityManager entityManager;


    public FilterClass getMoviesList(String name, Integer yearStart, Integer yearEnd, Double rating, String nameActor, String nameDirector, Integer startPage, Integer endPage, String page) {
        FilterClass filterClass = new FilterClass();
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);

        Root<Movie> movie = cq.from(Movie.class);

        Join<Movie, Actor> actorJoin = movie.join("actors");

        Join<Movie, Director> directorJoin = movie.join("directors");

        if (!StringUtils.isBlank(name)) {
            Predicate nameFilter = cb.like(movie.get("name"), "%" + name + "%");
            predicates.add(nameFilter);
        }

        if (page != null && startPage != null) {
            if (page.equals("+")) {
                startPage += 20;
                endPage += 20;
            } else {
                startPage -= 20;
                endPage -= 20;
            }
        }

        if (yearStart != null && yearEnd != null) {
            Predicate yearStartEndFilter = cb.between(movie.get("year"), yearStart, yearEnd);
            predicates.add(yearStartEndFilter);
        } else if (yearStart != null) {
            Predicate yearStartFilter = cb.greaterThanOrEqualTo(movie.get("yearStart"), yearStart);
            predicates.add(yearStartFilter);
        } else if (yearEnd != null) {
            Predicate yearEndFilter = cb.lessThanOrEqualTo(movie.get("yearEnd"), yearEnd);
            predicates.add(yearEndFilter);
        }

        if (rating != null) {
            Predicate ratingFilter = cb.equal(movie.get("rating"), rating);
            predicates.add(ratingFilter);
        }

        if (!StringUtils.isBlank(nameActor)) {
            Predicate nameActorFilter = cb.like(actorJoin.get("name"), "%" + nameActor + "%");
            predicates.add(nameActorFilter);
        }

        if (!StringUtils.isBlank(nameDirector)) {
            Predicate nameDirectorFilter = cb.like(directorJoin.get("name"), "%" + nameDirector + "%");
            predicates.add(nameDirectorFilter);
        }


        if (!predicates.isEmpty()) {

            Predicate predicateFinal = cb.and(predicates.toArray(new Predicate[0]));
            cq.where(predicateFinal);
            TypedQuery<Movie> query = entityManager.createQuery(cq);
            List<Movie> list = query.getResultList();
            List<Movie> subList;
            if (startPage == null)
                if (list.isEmpty()){
                    subList=null;
                }else {
                    subList = list.subList(0, 20);
                }
            else {
                if (list.size()<=endPage){
                subList = list.subList(startPage, endPage);
                }
                else {
                    int temp = endPage - list.size();
                    int temp2 = endPage - temp;
                    subList = list.subList(startPage,temp2);
                }

                //size 54  page 60       temp = 60 - 54   6

            }
            FilterMovie filterMovie = new FilterMovie(name,yearStart,yearEnd,rating,nameActor,nameDirector,startPage,endPage);
            filterClass.setFilterMovie(filterMovie);
            filterClass.setMovies(subList);
            return filterClass;
        }

        List<Movie> list = entityManager.createQuery("select m from Movie m ").getResultList();

        List<Movie> subList;
        if (startPage == null)
            subList = list.subList(0, 20);
        else {
            subList = list.subList(startPage, endPage);
        }
        FilterMovie filterMovie = new FilterMovie(name,yearStart,yearEnd,rating,nameActor,nameDirector,startPage,endPage);
        filterClass.setFilterMovie(filterMovie);
        filterClass.setMovies(subList);

        return filterClass;
    }
}