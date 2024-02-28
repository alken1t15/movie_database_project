package kz.alken1t.moviedatabaseprojectruntime.repository;

import kz.alken1t.moviedatabaseprojectruntime.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsers extends JpaRepository<Users,Long> {

}
