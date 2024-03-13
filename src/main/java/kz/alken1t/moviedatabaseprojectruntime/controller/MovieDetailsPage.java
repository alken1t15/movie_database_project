package kz.alken1t.moviedatabaseprojectruntime.controller;

import jakarta.validation.Valid;
import kz.alken1t.moviedatabaseprojectruntime.dto.MovieEdit;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.entity.Director;
import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceActor;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceDirector;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceMovie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/detail")
@AllArgsConstructor
public class MovieDetailsPage {
    private final ServiceMovie serviceMovie;
    private final ServiceActor serviceActor;
    private final ServiceDirector serviceDirector;

    @GetMapping("/{id}")
    public String getPage(@PathVariable(name = "id") Long id, Model model) {
        Movie movie = serviceMovie.findById(id);
        model.addAttribute("movie", movie);
        return "movie_details_page";
    }

    @PostMapping("/delete")
    public String removeRecord(@RequestParam(name = "id") String id) {
        serviceMovie.remove(id);
        return "redirect:/movie/";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable(name = "id") Long id, Model model) {
        Movie movie = serviceMovie.findById(id);
        List<Actor> actorList = serviceActor.findAll();
        List<Director> directorList = serviceDirector.findAll();
        model.addAttribute("movie", movie);
        model.addAttribute("actors", actorList);
        model.addAttribute("directors", directorList);
        model.addAttribute("movieEdit",new MovieEdit());
        return "movie_details_edit_page";
    }

    @PostMapping("/edit")
    public String editRecord(@Valid @ModelAttribute MovieEdit movieEdit, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            Movie movie = serviceMovie.findById(movieEdit.getId());
            List<Actor> actorList = serviceActor.findAll();
            List<Director> directorList = serviceDirector.findAll();
            model.addAttribute("movie", movie);
            model.addAttribute("actors", actorList);
            model.addAttribute("directors", directorList);
            model.addAttribute("movieEdit",movieEdit);
            return "movie_details_edit_page";
        }
        serviceMovie.editMovie(movieEdit);
        return "redirect:/movie/";
    }
}