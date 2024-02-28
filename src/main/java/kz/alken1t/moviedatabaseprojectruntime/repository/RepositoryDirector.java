package kz.alken1t.moviedatabaseprojectruntime.repository;

import kz.alken1t.moviedatabaseprojectruntime.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryDirector extends JpaRepository<Director,Long> {
}
