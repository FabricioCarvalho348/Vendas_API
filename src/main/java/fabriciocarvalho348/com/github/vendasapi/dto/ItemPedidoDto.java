package fabriciocarvalho348.com.github.vendasapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDto {

    private Integer produto;
    private Integer quantidade;
}
