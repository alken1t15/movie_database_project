package kz.alken1t.moviedatabaseprojectruntime.controller;

import jakarta.servlet.http.HttpSession;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.entity.Director;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceDirector;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/director")
public class DirectorPageController {
    private final ServiceDirector serviceDirector;

    @GetMapping
    public String getPage(@RequestParam(required = false) String page, Model model, HttpSession httpSession) {
        List<Director> directors = serviceDirector.findAll(page, httpSession);
        model.addAttribute("directors", directors);
        return "director_page";
    }
}