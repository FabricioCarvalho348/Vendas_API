package fabriciocarvalho348.com.github.vendasapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTES")
public class Cliente {

        @Id
        @Getter
        @Setter
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Getter
        @Column(name = "nome")
        private String nome;

        @Getter
        @Column(name = "endereco")
        private String endereco;

        @Getter
        @Column(name = "email")
        private String email;

        @Getter
        @Column(name = "telefone")
        private String telefone;

        @Getter
        @Column(name = "cpf")
        private String cpf;

        @JsonIgnore
        @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
        private Set<Pedido> pedidos;

    }

