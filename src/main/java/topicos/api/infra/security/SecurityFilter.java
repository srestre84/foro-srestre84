package topicos.api.infra.security;

import topicos.api.domain.usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");

        System.out.println("SF: " +  authHeader);
        if (authHeader != null){
            var token = authHeader.replace("Bearer ", "");
            var nombreUsuario = tokenService.getSubject(token);
            System.out.println("token: " + token);
            if (nombreUsuario != null){
                //token valido
                var usuario = usuarioRepository.findByNombre(nombreUsuario);
                var authentication = new UsernamePasswordAuthenticationToken
                        (usuario, null, usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
    /*
     * La anotación Component marca esta clase como un componente administrado por Spring.
     * En este caso, es un filtro de seguridad personalizado que se ejecutará en cada solicitud.
     * Aquí se inyectan dos dependencias en la clase: UsuarioRepository y TokenService. Estas dependencias se utilizan en el filtro para autenticar a los usuarios y validar los tokens
     * Este método doFilterInternal es el corazón del filtro de seguridad y se ejecuta en cada solicitud entrante. Su función es verificar y autenticar las solicitudes.
     * Primero, se obtiene el encabezado Authorization de la solicitud, que normalmente contiene un token de autenticación. El token se extrae y se quita la parte "Bearer " del encabezado para obtener solo el token.
     * Luego, se utiliza TokenService para verificar si el token es válido y obtener el nombre de usuario (nombreUsuario) del token. Si el token es válido y se puede extraer el nombre de usuario, se procede a la autenticación.
     * Se busca al usuario correspondiente en la base de datos a través de usuarioRepository.findByNombre(nombreUsuario). Si se encuentra, se crea un objeto de autenticación UsernamePasswordAuthenticationToken para el usuario con sus roles o autoridades.
     * Finalmente, se establece este objeto de autenticación en el contexto de seguridad de Spring (SecurityContextHolder). Esto significa que el usuario está autenticado y autorizado para acceder a recursos protegidos por el sistema de seguridad.
     * Después de la autenticación, la solicitud se pasa al siguiente filtro en la cadena de filtros (filterChain.doFilter(request, response)). Esto permite que la solicitud continúe su procesamiento normal, ahora con el usuario autenticado y autorizado.
     * En resumen, este filtro de seguridad se encarga de autenticar a los usuarios basados en tokens de autorización y establecer la información de autenticación en el contexto de seguridad de Spring, lo que permite proteger recursos y rutas específicas en la aplica
     */
}
