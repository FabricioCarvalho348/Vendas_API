package fabriciocarvalho348.com.github.vendasapi.model.repository;

import fabriciocarvalho348.com.github.vendasapi.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
