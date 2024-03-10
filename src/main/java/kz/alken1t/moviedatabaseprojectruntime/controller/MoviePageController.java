package kz.alken1t.moviedatabaseprojectruntime.controller;

import jakarta.servlet.http.HttpSession;
import kz.alken1t.moviedatabaseprojectruntime.dto.FilterClass;
import kz.alken1t.moviedatabaseprojectruntime.dto.FilterMovie;
import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceMovie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping({"/movie"})
@AllArgsConstructor
public class MoviePageController {
    private final ServiceMovie serviceMovie;

    @GetMapping("/")
    public String getMoviePage(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Integer yearStart,
                               @RequestParam(required = false) Integer yearEnd,
                               @RequestParam(required = false) Double rating,
                               @RequestParam(required = false) String nameActor,
                               @RequestParam(required = false) String nameDirector,
                               @RequestParam(required = false) Integer start,
                               @RequestParam(required = false) Integer end,
                               @RequestParam(required = false) String page, Model model, HttpSession httpSession) {

        FilterClass filterClass = serviceMovie.getMoviesList(name, yearStart, yearEnd, rating, nameActor, nameDirector,start,end,page, httpSession);
        model.addAttribute("movies", filterClass.getMovies());
        model.addAttribute("filterPage", filterClass.getFilterMovie());
        return "movie_page";
    }
}