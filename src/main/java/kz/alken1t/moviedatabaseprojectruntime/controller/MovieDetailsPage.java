package kz.alken1t.moviedatabaseprojectruntime.controller;

import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceMovie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detail")
@AllArgsConstructor
public class MovieDetailsPage {
    private final ServiceMovie serviceMovie;

    @GetMapping("/{id}")
    public String getPage(@PathVariable(name = "id") Long id, Model model){
        Movie movie = serviceMovie.findById(id);
        model.addAttribute("movie",movie);
        return "movie_details_page";
    }
    @PostMapping("/delete")
    public String removeRecord(@RequestParam(name = "id") String id){
        serviceMovie.remove(id);
        return "redirect:/movie/";
    }
}
