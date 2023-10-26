package fabriciocarvalho348.com.github.vendasapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    @NotNull(message = "Informe o código do cliente.")
    private Long cliente;

    @NotNull(message = "campo total do pedido é obrigatório.")
    private BigDecimal total;

    // TODO: CRIAR A VALIDAÇÃO PERSONALIZADA @NotEmptyList
    // @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDto> itens;
}
