package kz.alken1t.moviedatabaseprojectruntime.service;

import jakarta.servlet.http.HttpSession;
import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryActor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceActor {
    private final RepositoryActor repositoryActor;
    private final ServiceCommon serviceCommon;

    public List<Actor> findAll() {
        return repositoryActor.findAll();
    }

    public List findAll(String page, HttpSession httpSession) {
      return  serviceCommon.findAll(page,"numberPageActor",httpSession,repositoryActor,Actor.class);
    }
}