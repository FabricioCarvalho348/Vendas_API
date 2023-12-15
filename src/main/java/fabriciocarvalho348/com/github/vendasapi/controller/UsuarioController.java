package fabriciocarvalho348.com.github.vendasapi.controller;

import javax.validation.Valid;

import fabriciocarvalho348.com.github.vendasapi.dto.CredentialDto;
import fabriciocarvalho348.com.github.vendasapi.dto.TokenDto;
import fabriciocarvalho348.com.github.vendasapi.exception.SenhaInvalidaException;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Usuario;
import fabriciocarvalho348.com.github.vendasapi.security.jwt.JwtService;
import fabriciocarvalho348.com.github.vendasapi.service.usuario.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    public Usuario save(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioService.save(usuario);
    }

    @PostMapping("/auth")
    public TokenDto authenticate(@RequestBody CredentialDto credentialDto) {
        try {
            Usuario usuario = Usuario.builder()
                    .login(credentialDto.getLogin())
                    .senha(credentialDto.getSenha())
                    .build();

            UserDetails usuarioAutenticado = usuarioService.authenticate(usuario);
            String token = jwtService.gerarToken(usuario);

            TokenDto tokenDto = new TokenDto();
            tokenDto.setLogin(usuario.getLogin());
            tokenDto.setSenha(token);

            return tokenDto;

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
