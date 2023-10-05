package fabriciocarvalho348.com.github.vendasapi.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_PEDIDO")
public class ItemPedido {

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Getter
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Getter
    @Column(name = "quantidade")
    private Integer quantidade;

}
