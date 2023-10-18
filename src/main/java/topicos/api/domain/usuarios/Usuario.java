package topicos.api.domain.usuarios;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "login")
    private String login;

    private String email;
    private String contrasena;

    // Constructor que acepta los valores individuales
    public Usuario(String nombre, String login, String email, String contrasena) {
        this.nombre = nombre;
        this.login = login;
        this.email = email;
        this.contrasena = contrasena;
    }
    /*Las anotaciones Entity y Table se utilizan para indicar que esta clase es una entidad que se mapeará a una tabla en la base de datos. La tabla se llamará "usuarios".
     * Id y GeneratedValue(strategy = GenerationType.IDENTITY): Estas anotaciones indican que el campo id es la clave primaria de la entidad y que su valor se generará automáticamente de manera incremental.
     * private Long id: Representa el ID del usuario.
     * Column(name = "nombre"): Esta anotación especifica que el campo nombre se mapeará a una columna con el nombre "nombre" en la tabla de la base de datos.
     * La clase Usuario implementa la interfaz UserDetails, que es proporcionada por Spring Security para gestionar la autenticación y autorización de usuarios.
     * Esto significa que un objeto Usuario se puede utilizar directamente con Spring Security para representar a un usuario. A continuación, se implementan los métodos requeridos por UserDetails:
     *Collection<? extends GrantedAuthority>:  Esto especifica el tipo de valor que devuelve el método. En este caso, devuelve una colección de objetos que implementan la interfaz GrantedAuthority. La interfaz GrantedAuthority se utiliza en Spring Security para representar roles o autoridades de un usuario.
     * getAuthorities(): Este método devuelve una lista de autoridades asociadas al usuario. En este caso, se devuelve una autoridad llamada "ROLE_USER".
     * getPassword(): Devuelve la contraseña del usuario.
     * getUsername(): Devuelve el nombre del usuario.
     * isAccountNonExpired(): Indica si la cuenta del usuario no ha caducado (siempre se devuelve true).
     * isAccountNonLocked(): Indica si la cuenta del usuario no está bloqueada (siempre se devuelve true).
     * isCredentialsNonExpired(): Indica si las credenciales del usuario no han caducado (siempre se devuelve true).
     * isEnabled(): Indica si la cuenta del usuario está habilitada (siempre se devuelve true).
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
