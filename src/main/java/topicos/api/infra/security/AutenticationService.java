package topicos.api.infra.security;

import topicos.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByNombre(username);
    }
    /*
     * Esta es una clase llamada AutenticationService que está anotada con Service. La anotación @Service es una anotación de Spring que indica que esta clase es un componente de servicio y se encargará de la lógica de autenticación.
     * Además, esta clase implementa la interfaz UserDetailsService. La interfaz UserDetailsService es parte de Spring Security y se utiliza para cargar información de usuario durante el proceso de autenticación.
     * Aquí se inyecta una instancia de UsuarioRepository en la clase AutenticationService utilizando la anotación Autowired. Esto permite que la clase acceda a los datos de usuario almacenados en la base de datos.
     * Este método es parte de la interfaz UserDetailsService y se utiliza para cargar los detalles de un usuario por su nombre de usuario (username) durante el proceso de autenticación.
     * Cuando se llama a este método, se le proporciona un nombre de usuario como argumento.
     * La implementación del método utiliza el UsuarioRepository para buscar un usuario en la base de datos por su nombre de usuario.
     * Si el usuario se encuentra en la base de datos, se devuelve un objeto que implementa la interfaz UserDetails, que contiene información sobre el usuario, como su nombre de usuario, contraseña y roles.
     * Si el usuario no se encuentra en la base de datos, se lanza una excepción UsernameNotFoundException para indicar que el usuario no existe.
     * En resumen, esta clase AutenticationService actúa como un servicio de autenticación y se encarga de cargar los detalles de un usuario por su nombre de usuario durante el proceso de autenticación.
     * Utiliza un repositorio de usuarios (UsuarioRepository) para acceder a los datos de usuario almacenados en la base de datos y proporcionar los detalles necesarios para la autenticación.
     */

}
