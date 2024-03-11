package kz.alken1t.moviedatabaseprojectruntime.controller;

import kz.alken1t.moviedatabaseprojectruntime.entity.Users;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryUsers;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/","/main"})
@AllArgsConstructor
public class MainPageController {
    RepositoryUsers repositoryUsers;
    PasswordEncoder passwordEncoder;

    @GetMapping
    public String mainPage(){
//        Users users = new Users("alex", passwordEncoder.encode("alex"));
//        repositoryUsers.save(users);
        return "main_page";
    }
}
