package kz.alken1t.moviedatabaseprojectruntime.security;

import kz.alken1t.moviedatabaseprojectruntime.repository.RepositoryUsers;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final RepositoryUsers repositoryUsers;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryUsers.findByLogin(username).map(UserDetailsImp::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}