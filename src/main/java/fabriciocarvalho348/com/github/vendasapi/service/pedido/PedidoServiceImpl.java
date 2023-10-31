package fabriciocarvalho348.com.github.vendasapi.service.pedido;

import fabriciocarvalho348.com.github.vendasapi.dto.ItemPedidoDto;
import fabriciocarvalho348.com.github.vendasapi.dto.PedidoDto;
import fabriciocarvalho348.com.github.vendasapi.exception.ResourceNotFoundException;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Cliente;
import fabriciocarvalho348.com.github.vendasapi.model.entity.ItemPedido;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Pedido;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Produto;
import fabriciocarvalho348.com.github.vendasapi.model.enums.StatusPedido;
import fabriciocarvalho348.com.github.vendasapi.model.repository.ClienteRepository;
import fabriciocarvalho348.com.github.vendasapi.model.repository.ItemPedidoRepository;
import fabriciocarvalho348.com.github.vendasapi.model.repository.PedidoRepository;
import fabriciocarvalho348.com.github.vendasapi.model.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Pedido incluir(PedidoDto pedidoDto) {
        Cliente cliente = findCliente(pedidoDto);
        Pedido pedido = newPedido(pedidoDto, cliente);
        List<ItemPedido> itensPedido = convertItemPedido(pedido, pedidoDto.getItens());

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> pedidoCompleto(Long id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    public void alterarStatus(Long id, StatusPedido statusPedido) {
        pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new ResourceNotFoundException("Código de pedido inválido"));
    }

    private Cliente findCliente(PedidoDto pedidoDto) {
        Long idCliente = pedidoDto.getCliente();
        return clienteRepository.findById(idCliente).orElseThrow(() -> new ResourceNotFoundException("Código de cliente inválido"));
    }

    private Pedido newPedido(PedidoDto pedidoDto, Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(pedidoDto.getTotal());
        pedido.setStatus(StatusPedido.REALIZADO);
        return pedido;
    }

    private List<ItemPedido> convertItemPedido(Pedido pedido, List<ItemPedidoDto> itensPedidoDto) {
        if (itensPedidoDto.isEmpty()) {
            throw new ResourceNotFoundException("Não é possivel realizar um pedido sem itens.");
        }
        return itensPedidoDto.stream()
                .map(itemPedidoDto -> {
                    Long idProduto = itemPedidoDto.getProduto();
                    Produto produto = produtoRepository.findById(idProduto)
                            .orElseThrow(() -> new ResourceNotFoundException("Código de produto inválido."));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    itemPedido.setQuantidade(itemPedidoDto.getQuantidade());

                    return itemPedido;
                })
                .collect(Collectors.toList());
    }

}
