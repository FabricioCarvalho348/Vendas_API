package fabriciocarvalho348.com.github.vendasapi.model.repository;

import java.util.Optional;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

}