package fabriciocarvalho348.com.github.vendasapi.service.pedido;

import fabriciocarvalho348.com.github.vendasapi.dto.PedidoDto;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Pedido;
import fabriciocarvalho348.com.github.vendasapi.model.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {
    Pedido incluir(PedidoDto pedidoDto);

    Optional<Pedido> pedidoCompleto(Long id);

    void alterarStatus(Long id, StatusPedido statusPedido);
}
