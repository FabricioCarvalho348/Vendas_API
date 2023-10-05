package fabriciocarvalho348.com.github.vendasapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
        @Column(name = "nome", length = 40)
        @Length(min = 2, max = 100, message = "O campo NOME deve ter entre 2 e 100 caracteres")
        @NotNull(message = "O campo NOME não deve ser nulo")
        @NotEmpty(message = "O campo NOME é obrigatório")
        private String nome;

        @Getter
        @Column(name = "endereco", length = 70)
        @Length(min = 2, max = 70, message = "O campo ENDEREÇO deve ser entre 2 e 70 caracteres")
        @NotEmpty(message = "O campo ENDEREÇO é obrigatório")
        private String endereco;

        @Getter
        @Column(name = "email", length = 20)
        @Email(message = "Informe um E-MAIL válido.")
        private String email;

        @Getter
        @Column(name = "telefone", length = 15)
        @NotNull(message = "Campo TELEFONE nao deve ser nulo")
        @NotEmpty(message = "O campo TELEFONE é obrigatório.")
        private String telefone;

        @Getter
        @Column(name = "cpf", length = 14)
        @CPF(message = "Informe um CPF válido.")
        private String cpf;

        @JsonIgnore
        @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
        private Set<Pedido> pedidos;

    }

