package fabriciocarvalho348.com.github.vendasapi.model.repository;


import fabriciocarvalho348.com.github.vendasapi.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // TODO: Estudar JPQL
    @Query("select p from Pedido p left join fetch p.itens where p.id= :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Long id);

}
