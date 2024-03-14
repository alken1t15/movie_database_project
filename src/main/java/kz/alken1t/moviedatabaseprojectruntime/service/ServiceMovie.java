package kz.alken1t.moviedatabaseprojectruntime.service;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.servlet.http.HttpSession;
import kz.alken1t.moviedatabaseprojectruntime.dto.FilterClass;
import kz.alken1t.moviedatabaseprojectruntime.dto.FilterMovie;
import kz.alken1t.moviedatabaseprojectruntime.dto.MovieEdit;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.entity.Director;
import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryActor;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryDirector;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryMovie;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ServiceMovie {
    private final RepositoryMovie repositoryMovie;
    private final EntityManager entityManager;
    private final RepositoryActor repositoryActor;
    private final RepositoryDirector repositoryDirector;


    public FilterClass getMoviesList(String name, Integer yearStart, Integer yearEnd, Double rating, String nameActor, String nameDirector, String page, HttpSession httpSession) {
        FilterClass filterClass = new FilterClass();
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);

        if (httpSession.getAttribute("startPage") == null) {
            httpSession.setAttribute("startPage", 0);
            httpSession.setAttribute("endPage", 20);
            httpSession.setAttribute("name", name);
            httpSession.setAttribute("yearStart", yearStart);
            httpSession.setAttribute("yearEnd", yearEnd);
            httpSession.setAttribute("rating", rating);
            httpSession.setAttribute("nameActor", nameActor);
            httpSession.setAttribute("nameDirector", nameDirector);
        }

        Root<Movie> movie = cq.from(Movie.class);

        Join<Movie, Actor> actorJoin = movie.join("actors");

        Join<Movie, Director> directorJoin = movie.join("directors");

        if (page != null) {
            name = (String) httpSession.getAttribute("name");
            yearStart = (Integer) httpSession.getAttribute("yearStart");
            yearEnd = (Integer) httpSession.getAttribute("yearEnd");
            rating = (Double) httpSession.getAttribute("rating");
            nameActor = (String) httpSession.getAttribute("nameActor");
            nameDirector = (String) httpSession.getAttribute("nameDirector");
        }

        if (!StringUtils.isBlank(name)) {
            Predicate nameFilter = cb.like(movie.get("name"), "%" + name + "%");
            predicates.add(nameFilter);
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


            Integer startPage = (Integer) httpSession.getAttribute("startPage");
            Integer endPage = (Integer) httpSession.getAttribute("endPage");

            if (page != null && startPage != null) {
                if (page.equals("+")) {
                    if (startPage + 20 > list.size()) {

                    } else {
                        startPage += 20;
                        endPage += 20;
                    }
                } else {
                    if (startPage - 20 < 0) {

                    } else {
                        startPage -= 20;
                        endPage -= 20;
                    }
                }
            }
            httpSession.setAttribute("startPage", startPage);
            httpSession.setAttribute("endPage", endPage);


            List<Movie> subList;
            if (startPage == 0)
                if (list.isEmpty()) {
                    subList = null;
                } else {
                    subList = list.subList(0, 20);
                }
            else {
                if (list.size() >= endPage) {
                    subList = list.subList(startPage, endPage);
                } else {
                    int temp = endPage - list.size();
                    int temp2 = endPage - temp;
                    subList = list.subList(startPage, temp2);
                }

            }
            FilterMovie filterMovie = new FilterMovie(name, yearStart, yearEnd, rating, nameActor, nameDirector, startPage, endPage);
            filterClass.setFilterMovie(filterMovie);
            filterClass.setMovies(subList);
            return filterClass;
        }

        List<Movie> list = entityManager.createQuery("select m from Movie m ").getResultList();

        Integer startPage = (Integer) httpSession.getAttribute("startPage");
        Integer endPage = (Integer) httpSession.getAttribute("endPage");

        if (page != null && startPage != null) {
            if (page.equals("+")) {
                if (startPage + 20 > list.size()) {

                } else {
                    startPage += 20;
                    endPage += 20;
                }
            } else {
                if (startPage - 20 < 0) {

                } else {
                    startPage -= 20;
                    endPage -= 20;
                }
            }
        }
        httpSession.setAttribute("startPage", startPage);
        httpSession.setAttribute("endPage", endPage);


        List<Movie> subList;
        if (startPage == 0)
            if (list.isEmpty()) {
                subList = null;
            } else {
                subList = list.subList(0, 20);
            }
        else {
            if (list.size() >= endPage) {
                subList = list.subList(startPage, endPage);
            } else {
                int temp = endPage - list.size();
                int temp2 = endPage - temp;
                subList = list.subList(startPage, temp2);
            }
        }
        FilterMovie filterMovie = new FilterMovie(name, yearStart, yearEnd, rating, nameActor, nameDirector, startPage, endPage);
        filterClass.setFilterMovie(filterMovie);
        filterClass.setMovies(subList);

        return filterClass;
    }

    public Movie findById(Long id) {
        return repositoryMovie.findById(id).orElseThrow();
    }

    public void remove(String id) {
        repositoryMovie.deleteById(Long.valueOf(id));
    }

    @Transactional
    @Modifying
    public void editMovie(MovieEdit movieEdit) {
        Movie movie = repositoryMovie.findById(movieEdit.getId()).orElseThrow(() -> new IllegalArgumentException("Movie not found with id: " + movieEdit.getId()));

        movie.setName(movieEdit.getName());
        movie.setYear(movieEdit.getYear());
        movie.setGenre(movieEdit.getGenre());
        movie.setRating(movieEdit.getRating());
        for (Actor actor : movie.getActors()) {
            actor.setMovie(null);
            repositoryActor.save(actor);
        }
        for (Director director : movie.getDirectors()) {
            director.setMovie(null);
            repositoryDirector.save(director);
        }
        repositoryMovie.save(movie);


        if (movieEdit.getActors() != null) {
            if (!movieEdit.getActors().isEmpty()) {
                for (String actorId : movieEdit.getActors()) {
                    if (Long.parseLong(actorId) < 0) {
                        continue;
                    }
                    Actor actor = repositoryActor.findById(Long.valueOf(actorId))
                            .orElseThrow(() -> new IllegalArgumentException("Actor not found with id: " + actorId));
                    actor.setMovie(movie);
                    movie.getActors().add(actor);
                }
            }
        }
        if (movieEdit.getDirectors() != null) {
            if (!movieEdit.getDirectors().isEmpty()) {
                for (String directorId : movieEdit.getDirectors()) {
                    if (Long.parseLong(directorId) < 0) {
                        continue;
                    }
                    Director director = repositoryDirector.findById(Long.valueOf(directorId))
                            .orElseThrow(() -> new IllegalArgumentException("Director not found with id: " + directorId));
                    director.setMovie(movie);
                    movie.getDirectors().add(director);
                }
            }
        }

    }

    public List<Movie> findTop() {
        List<Movie> movies = repositoryMovie.findAllByOrderByRatingDesc();
        return movies.subList(0, 10);
    }
}