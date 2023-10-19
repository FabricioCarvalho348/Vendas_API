package fabriciocarvalho348.com.github.vendasapi.service.pedido;

import fabriciocarvalho348.com.github.vendasapi.dto.PedidoDto;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Pedido;
import fabriciocarvalho348.com.github.vendasapi.model.enums.StatusPedido;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Override
    public Pedido incluir(PedidoDto pedidoDto) {
        return null;
    }

    @Override
    public Optional<Pedido> pedidoCompleto(Integer id) {
        return Optional.empty();
    }

    @Override
    public void alterarStatus(Integer id, StatusPedido statusPedido) {

    }
}
