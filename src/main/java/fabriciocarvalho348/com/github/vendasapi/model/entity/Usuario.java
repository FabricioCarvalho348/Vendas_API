package fabriciocarvalho348.com.github.vendasapi.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo login obrigatório.")
    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    @NotEmpty(message = "Campo senha obrigatório.")
    private String senha;

    @Column(name = "admin")
    private boolean admin;

}