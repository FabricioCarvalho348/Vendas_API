package fabriciocarvalho348.com.github.vendasapi.controller;

import fabriciocarvalho348.com.github.vendasapi.dto.AtualizacaoStatusPedidoDto;
import fabriciocarvalho348.com.github.vendasapi.dto.InformacaoItemPedidoDto;
import fabriciocarvalho348.com.github.vendasapi.dto.InformacaoPedidoDto;
import fabriciocarvalho348.com.github.vendasapi.dto.PedidoDto;
import fabriciocarvalho348.com.github.vendasapi.exception.ApiErros;
import fabriciocarvalho348.com.github.vendasapi.exception.ResourceNotFoundException;
import fabriciocarvalho348.com.github.vendasapi.model.entity.ItemPedido;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Pedido;
import fabriciocarvalho348.com.github.vendasapi.model.enums.StatusPedido;
import fabriciocarvalho348.com.github.vendasapi.service.pedido.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    // @Autowired - comentado para demonstrar como injetar um classe manualmente
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ApiOperation(value = "Cria um pedido.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "O pedido foi criado com sucesso."),
            @ApiResponse(code = 400, message = "Contém erro(s) de validação.", response = ApiErros.class)
    })
    public Long incluir(@RequestBody @Valid PedidoDto pedidoDTO) {
        Pedido pedido = pedidoService.incluir(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Apresenta informações do pedido.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Apresenta informações do pedido.")
    })
    public InformacaoPedidoDto pedidoCompleto(@PathVariable Long id) {
        return pedidoService.pedidoCompleto(id)
                .map(pedido -> builderInformacaoPedidoDTO(pedido))
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado."));
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Altera o status do pedido.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Status do pedido alterado.")
    })
    public void alterarStatus(@PathVariable Long id,
                              @RequestBody AtualizacaoStatusPedidoDto atualizacaoStatusPedidoDTO) {
        String novoStatus = atualizacaoStatusPedidoDTO.getNovoStatus();
        pedidoService.alterarStatus(id, StatusPedido.valueOf(novoStatus));
    }


    private InformacaoPedidoDto builderInformacaoPedidoDTO(Pedido pedido) {
        return InformacaoPedidoDto.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(builderInformacaoItemPedidoDTO(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDto> builderInformacaoItemPedidoDTO(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens))
            return Collections.emptyList();

        return itens.stream()
                .map(item -> InformacaoItemPedidoDto.builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }

}
