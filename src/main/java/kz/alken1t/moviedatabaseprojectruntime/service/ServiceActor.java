package kz.alken1t.moviedatabaseprojectruntime.service;

import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryActor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceActor {
    private final RepositoryActor repositoryActor;
}
