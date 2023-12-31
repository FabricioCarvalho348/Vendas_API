package fabriciocarvalho348.com.github.vendasapi.model.entity;

import fabriciocarvalho348.com.github.vendasapi.model.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PEDIDOS")
public class Pedido {

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Getter
    @Setter
    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Getter
    @Setter
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @Getter
    @Setter
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Getter
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

}