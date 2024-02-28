package kz.alken1t.moviedatabaseprojectruntime.service;

import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryMovie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceMovie {
    private final RepositoryMovie repositoryMovie;
}
