package kz.alken1t.moviedatabaseprojectruntime.repository;

import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryMovie extends JpaRepository<Movie,Long> {
}
