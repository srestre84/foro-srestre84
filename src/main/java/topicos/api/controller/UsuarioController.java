package topicos.api.controller;


import topicos.api.DTO.DatosRegistroUsuario;
import topicos.api.domain.usuarios.Usuario;
import topicos.api.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    /*
     * Las anotaciones @RestController y @RequestMapping se utilizan para definir este Java class como un controlador de Spring MVC y-
     *  para mapear todas las solicitudes que comienzan con "/usuarios" a este controlador.
     */

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping
    @Transactional
    public ResponseEntity RegistrarUsuario(@RequestBody @Valid  DatosRegistroUsuario datosRegistroUsuario) {
        Usuario usuario;
        usuario = new Usuario();
        usuario.setContrasena(passwordEncoder.encode(datosRegistroUsuario.contrasena()));
        repository.save(usuario);
        System.out.println(datosRegistroUsuario);
        return ResponseEntity.ok().body(datosRegistroUsuario);

        /*
         * PostMappingindica que este método manejará solicitudes HTTP POST.
         * Transactional: Esta anotación se utiliza en contexto de Java EE (Jakarta EE) para definir que el método es transaccional,
         * lo que generalmente implica que se está interactuando con una base de datos.
         * El método toma un objeto DatosRegistroUsuario del cuerpo de la solicitud que contiene los datos del usuario a registrar.
         * Se crea una nueva instancia de la entidad Usuario.
         *La contraseña proporcionada en datosRegistroUsuario se codifica de manera segura utilizando el passwordEncoder y se establece en el usuario.
         * Luego, el usuario se guarda en la base de datos utilizando el repositorio UsuarioRepository.
         * Opcionalmente, se imprime en la consola los datos de registro del usuario.
         * Finalmente, se devuelve una respuesta HTTP OK (código de estado 200) con los datos del usuario registrado como cuerpo de la respuesta.
         */

    }
}