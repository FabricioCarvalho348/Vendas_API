package fabriciocarvalho348.com.github.vendasapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoItemPedidoDto {

    private String descricaoProduto;
    private BigDecimal precounitario;
    private Integer quantidade;
}
