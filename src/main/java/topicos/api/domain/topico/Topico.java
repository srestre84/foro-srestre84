package topicos.api.domain.topico;
import topicos.api.DTO.DatosModificarTopico;
import topicos.api.DTO.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import topicos.api.domain.curso.Curso;
import topicos.api.domain.respuesta.Respuesta;
import topicos.api.domain.usuarios.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Enumerated
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    @OneToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas = new ArrayList<>();


    /*
     * Las anotaciones Entity y Table se utilizan para indicar que esta clase es una entidad que se mapeará a una tabla en la base de datos.
     * La tabla se llamará "topicos"
     *Las anotaciones Getter y Setter de Lombok generan automáticamente los métodos getters y setters para los campos de la clase, lo que simplifica el código y mejora la legibilidad.
     *La anotación NoArgsConstructor proviene del proyecto Lombok y genera un constructor sin argumentos para la clase Topico. Este constructor es útil en el contexto de JPA (Java Persistence API) para la creación de instancias de entidades.
     *La anotación EqualsAndHashCode de Lombok genera automáticamente los métodos equals y hashCode para la clase, lo que facilita la comparación y el uso de instancias de Topico.
     *Id: Indica que el campo id es la clave primaria de la entidad.
     *GeneratedValue(strategy = GenerationType.IDENTITY): Especifica que el valor de la clave primaria se generará automáticamente de manera incremental.
     * Enumerated: Se utiliza para mapear una enumeración de tipo StatusTopico.
     * ManyToOne y @JoinColumn(name = "autor_id"): Estas anotaciones establecen una relación de muchos a uno con la entidad Usuario. El tópico tiene un autor y se almacena mediante una clave externa llamada "autor_id".
     * OneToOne y @JoinColumn(name = "curso_id"): Estas anotaciones establecen una relación de uno a uno con la entidad Curso. Cada tópico está asociado a un curso y se almacena mediante una clave externa llamada "curso_id".
     *OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY): Esta anotación establece una relación de uno a muchos con la entidad Respuesta. Cada tópico puede tener múltiples respuestas.
     * La opción cascade = CascadeType.ALL indica que las operaciones de cascada se aplicarán a las respuestas asociadas a este tópico, y fetch = FetchType.LAZY especifica una recuperación perezosa de las respuestas.
     * private List<Respuesta> respuestas = new ArrayList<>();: Representa la lista de respuestas asociadas a este tópico.
     */
    public Topico(String titulo, String mensaje, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
    }

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
    }

    public void modificar(DatosModificarTopico datosModificarTopico) {
        if (datosModificarTopico.titulo() != null){
            this.titulo = datosModificarTopico.titulo();
        }
        if (datosModificarTopico.mensaje() != null){
            this.mensaje = datosModificarTopico.mensaje();
        }
        if (datosModificarTopico.statusTopico() != null){
            this.status = datosModificarTopico.statusTopico();
        }
    }

    public void agregarRespuesta (Respuesta respuesta) {
        this.respuestas.add(respuesta);
    }
}
