package fabriciocarvalho348.com.github.vendasapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produtos")
public class Produto {

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "descricao", length = 100)
    @Length(min = 2, max = 100, message = "O campo PRODUTO deve ter entre 2 e 100 caracteres")
    @NotEmpty(message = "Campo DESCRICAO deve ser informado")
    @NotNull(message = "Campo DESCRICAO nao deve ser nulo")
    private String descricao;

    @Getter
    @Column(name = "preco_unitario", precision = 10, scale = 2)
    @NotNull(message = "Campo PRECO deve ser informado")
    private BigDecimal preco;

}
