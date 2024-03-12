package kz.alken1t.moviedatabaseprojectruntime.repository;

import kz.alken1t.moviedatabaseprojectruntime.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryActor extends JpaRepository<Actor,Long> {
    Actor findByName(String name);

}
