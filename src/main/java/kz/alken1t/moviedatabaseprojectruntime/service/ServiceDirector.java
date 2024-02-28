package kz.alken1t.moviedatabaseprojectruntime.service;

import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryDirector;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceDirector {
    private final RepositoryDirector repositoryDirector;
}
