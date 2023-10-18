package topicos.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors().and()
                .authorizeHttpRequests
                        (req -> req.requestMatchers(HttpMethod.GET, "/topicos", "/topicos/**", "/cursos")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/login", "/usuarios")
                                .permitAll()
                                .anyRequest().authenticated())
//                        (req -> req.requestMatchers(HttpMethod.POST, "/login").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
//                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    /*
     * Estas anotaciones marcan esta clase como una configuración de Spring (@Configuration) y habilitan la seguridad web de Spring (@EnableWebSecurity). Indican que esta clase se utilizará para configurar la seguridad en la aplicación.
     *Aquí se inyecta un componente llamado SecurityFilter en la clase. Este filtro es esencial para procesar la autenticación y la autorización de las solicitudes HTTP en la aplicación.
     * Este método configura la seguridad en la aplicación. Aquí se realizan las siguientes acciones:
     *Se deshabilita la protección contra ataques CSRF (Cross-Site Request Forgery) con .csrf(csrf -> csrf.disable()).
     * Se configura la gestión de sesiones para que no se cree una sesión con sessionCreationPolicy(SessionCreationPolicy.STATELESS)-
     * Esto implica que la aplicación no mantendrá información de sesión del usuario en el servidor.
     * Se configura la política CORS con .cors().and(). Esto permite el intercambio de recursos entre dominios y controla las solicitudes de origen cruzado.
     * Se define la autorización de solicitudes HTTP utilizando authorizeHttpRequests.
     * Se permite el acceso público a rutas específicas mediante permitAll(), mientras que se requiere autenticación (authenticated) para cualquier otra solicitud.
     *  Se agrega el securityFilter antes del filtro UsernamePasswordAuthenticationFilter.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
/*
 * Aquí se define un AuthenticationManager utilizando la configuración de autenticación (AuthenticationConfiguration) proporcionada como argumento. El AuthenticationManager es responsable de autenticar a los usuarios.
 *En este método, se configura un PasswordEncoder. En este caso, se utiliza BCryptPasswordEncoder para codificar y verificar contraseñas. El PasswordEncoder es esencial para garantizar que las contraseñas se almacenen de manera segura y se puedan comparar correctamente durante el proceso de autenticación.
 *En el código proporcionado, se utilizan anotaciones Bean para definir y configurar ciertos componentes esenciales, como SecurityFilterChain, AuthenticationManager, y PasswordEncoder, que son fundamentales para la configuración de seguridad-
 * en la aplicación. Estos permiten al desarrollador personalizar la configuración de estos componentes y proporcionarlos como beans administrados por Spring.
 */