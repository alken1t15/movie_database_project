package kz.alken1t.moviedatabaseprojectruntime.service;

import kz.alken1t.moviedatabaseprojectruntime.entity.Director;
import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryDirector;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceDirector {
    private final RepositoryDirector repositoryDirector;

    public List<Director> findAll() {
        return repositoryDirector.findAll();
    }
}
