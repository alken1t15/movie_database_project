package kz.alken1t.moviedatabaseprojectruntime.repository;

import kz.alken1t.moviedatabaseprojectruntime.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryMovie extends JpaRepository<Movie,Long> {
    Optional<Movie> findById(Long id);
}
