package fabriciocarvalho348.com.github.vendasapi.security.jwt;

import fabriciocarvalho348.com.github.vendasapi.VendasApiApplication;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.security.expiration}")
    private String jwtSecurityExpiration;

    @Value("${jwt.subscription.key}")
    private String jwtSubscriptionKey;

    /*
     * Este main é apenas para testarmos:
     * 1) A geração do token
     * 2) Se o token está valido (não expirado)
     * 3) Obter o payload do token
     *
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApiApplication.class);
        JwtService jwtService = contexto.getBean(JwtService.class);

        // Usuario usuario = new Usuario();
        // usuario.setLogin("fulano");

        Usuario usuario = Usuario.builder()
                .login("fulano")
                .build();

        String token = jwtService.gerarToken(usuario);
        System.out.println(token);

        boolean tokenValido = jwtService.tokenValido(token);
        System.out.println("O token está válido? " + tokenValido);

        System.out.println(jwtService.obterLoginUsuario(token));
    }

    public String gerarToken(Usuario usuario) {
        long minutesExpire = Long.valueOf(this.jwtSecurityExpiration);
        LocalDateTime plusMinutes = LocalDateTime.now().plusMinutes(minutesExpire);
        Instant toInstant = plusMinutes.atZone(ZoneId.systemDefault()).toInstant();
        Date from = Date.from(toInstant);

        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setExpiration(from)
                .signWith(SignatureAlgorithm.HS512, jwtSubscriptionKey)
                .compact();
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            Date expiration = claims.getExpiration();
            LocalDateTime localDateTime = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);
        } catch (Exception e) {
            return false;
        }

    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(jwtSubscriptionKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return (String) obterClaims(token).getSubject();
    }

}