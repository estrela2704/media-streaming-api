package br.com.media_streaming_api.infrastructure.storage.pesistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.media_streaming_api.domain.model.User.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
