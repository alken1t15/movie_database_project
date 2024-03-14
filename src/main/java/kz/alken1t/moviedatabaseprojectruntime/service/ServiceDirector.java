package kz.alken1t.moviedatabaseprojectruntime.service;

import jakarta.servlet.http.HttpSession;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.entity.Director;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryDirector;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceDirector {
    private final RepositoryDirector repositoryDirector;
    private final ServiceCommon serviceCommon;

    public List<Director> findAll() {
        return repositoryDirector.findAll();
    }

    public List findAll(String page, HttpSession httpSession) {
        return serviceCommon.findAll(page,"numberPageDirector",httpSession,repositoryDirector,Director.class);
    }
}