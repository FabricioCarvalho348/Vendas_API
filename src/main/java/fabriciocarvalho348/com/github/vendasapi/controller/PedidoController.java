package fabriciocarvalho348.com.github.vendasapi.controller;

import fabriciocarvalho348.com.github.vendasapi.dto.AtualizacaoStatusPedidoDto;
import fabriciocarvalho348.com.github.vendasapi.dto.InformacaoPedidoDto;
import fabriciocarvalho348.com.github.vendasapi.dto.PedidoDto;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Pedido;
import fabriciocarvalho348.com.github.vendasapi.service.pedido.PedidoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    // @Autowired - Comentado para demonstrar como injetar uma classe manualmente
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Integer incluir(@RequestBody @Valid PedidoDto pedidoDTO) {
//        Pedido pedido = pedidoService.incluir(pedidoDTO);
//        return pedido.getId();
        return null;
    }

    @GetMapping("/{id}")
    public InformacaoPedidoDto pedidoCompleto(@PathVariable Integer id) {
        // return pedidoService.pedidoCompleto(id)
        //      .map(pedido -> builderInformacaoPedidoDTO(pedido))
        //      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
        return null;
    }

    @PatchMapping("{id}")
    public void alterarStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDto) {
//        String novoStatus = atualizacaoStatusPedidoDTO.getNovoStatus();
//        pedidoService.alterarStatus(id, StatusPedido.valueOf(novoStatus));
    }

}
