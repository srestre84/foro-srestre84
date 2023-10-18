package topicos.api.controller;

import topicos.api.DTO.DatosAutenticarUsuario;
import topicos.api.DTO.DatosTokenJWT;
import topicos.api.infra.security.TokenService;
import topicos.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
/*
 * Estas anotaciones son parte de Spring  y se utilizan para indicar
 * que esta clase es un controlador REST y que todas las solicitudes HTTP
 * que comiencen con login se gestionarán en este controlador.
 */
public class AuthController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    /*
     * Estos campos se anotan con Autowired para inyectar dependencias.
     * tokenService es una dependencia relacionada
     *  con la generación y gestión de tokens JWT,
     *  y authenticationManager se utiliza para manejar la autenticación de usuarios.
     */

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody DatosAutenticarUsuario datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.nombre(), datos.contrasena());
        /*
         *Aquí se declara el método autenticarUsuario, que se mapea a las
         *solicitudes POST a la ruta del login. Recibe un objeto
         * DatosAutenticarUsuario en el cuerpo de la solicitud.
         * Ademas se crea un objeto de autenticación (authToken)
         *  utilizando el nombre de usuario y la contraseña proporcionados
         *  en el objeto datos.
         */

        var usuario = authenticationManager.authenticate(authToken);

        /*
         * El objeto authToken se pasa al AuthenticationManager para realizar
         * la autenticación. El resultado se almacena en usuario.
         */

        var JWTtoken = tokenService.generarToken((Usuario) usuario.getPrincipal());

        /*
         * Una vez que el usuario se ha autenticado con éxito, se genera un token JWT utilizando el servicio
         * tokenService. Este token se asocia al usuario autenticado.
         */

        return ResponseEntity.ok(new DatosTokenJWT(JWTtoken, ((Usuario) usuario.getPrincipal()).getId()));
        /*
         Finalmente, se devuelve una respuesta 200 (OK) con un objeto DatosTokenJWT
         que contiene el token JWT y el ID del usuario autenticado.
         */

    }
}
