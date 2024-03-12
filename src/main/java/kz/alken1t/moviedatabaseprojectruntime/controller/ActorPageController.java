package kz.alken1t.moviedatabaseprojectruntime.controller;

import jakarta.servlet.http.HttpSession;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.service.ServiceActor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/actor")
public class ActorPageController {
    private final ServiceActor serviceActor;

    @GetMapping
    public String getPage(@RequestParam(required = false) String page, Model model, HttpSession httpSession){
        List<Actor> actors = serviceActor.findAll(page,httpSession);
        model.addAttribute("actors",actors);
        return "actor_page";
    }
}
