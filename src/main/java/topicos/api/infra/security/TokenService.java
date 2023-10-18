package topicos.api.infra.security;

import topicos.api.domain.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret-key}")
    private String secretKey; // Lee la clave secreta desde la configuración

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer("foro srestre84")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExp(2))
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException(e);
        }
    }

    public String getSubject(String token) {
        if (token == null || token.isEmpty()){
            throw new RuntimeException("Token no proporcionado");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("foro srestre84") // Debe coincidir con el emisor al generar el token
                    .build()
                    .verify(token);

            return verifier.getSubject();
        } catch (JWTVerificationException e){
            throw new RuntimeException("Token inválido: " + e.getMessage());
        }
    }

    private Instant generarFechaExp(int horas) {
        return LocalDateTime.now().plusHours(horas).toInstant(ZoneOffset.of("-05:00"));
    }

}
/*
 * El servicio obtiene la clave secreta para firmar y verificar tokens desde la configuración de la aplicación, que se define en un archivo de propiedades. Esta clave se utiliza para garantizar la autenticidad y la integridad de los tokens.
 *Este método se encarga de generar un token JWT. Utiliza la clave secreta para firmar el token y establece ciertas reclamaciones en el token:
 * withIssuer: Establece el emisor del token.
 *withSubject: Establece el sujeto del token, que normalmente es el nombre de usuario.
 * withClaim: Permite agregar reclamaciones personalizadas al token. En este caso, se agrega el identificador del usuario.
 * withExpiresAt: Establece la fecha de vencimiento del token. En este ejemplo, se configura para que expire en 2 horas.
 * El token se firma con el algoritmo HMAC256 y se devuelve como una cadena.
 *Este método verifica un token JWT proporcionado. Primero, se verifica si el token está presente y no es una cadena vacía.
 *Luego, se utiliza la clave secreta y el algoritmo HMAC256 para verificar la firma del token.
 *withIssuer: Establece el emisor del token y debe coincidir con el emisor al generar el token.
 *verify: Realiza la verificación del token y devuelve un objeto DecodedJWT que contiene la información del token.
 *Si el token es válido y la verificación tiene éxito, se obtiene el sujeto del token (en este caso, el nombre de usuario) y se devuelve como resultado.
 *Si la verificación falla, se lanza una excepción.
 *Este método se utiliza para generar la fecha de vencimiento del token en función del número de horas proporcionado. Se crea una marca de tiempo que representa el momento en que el token expirará.
 */

