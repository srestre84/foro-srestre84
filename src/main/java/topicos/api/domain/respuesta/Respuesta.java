package topicos.api.domain.respuesta;

import topicos.api.DTO.DatosModificarRespuesta;
import topicos.api.DTO.DatosRegistroRespuesta;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import topicos.api.domain.topico.Topico;
import topicos.api.domain.usuarios.Usuario;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@NoArgsConstructor
@Data
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;

	@ManyToOne
	@JoinColumn(name = "topico_id")
	private Topico topico;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	@OneToOne
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	private Boolean solucion = false;

	/*
	 *Las anotaciones Entity y Table se utilizan para indicar que esta clase es una entidad que se mapeará a una tabla en la base de datos.
	 *La tabla se llamará "respuestas".
	 *La anotación NoArgsConstructor proviene del proyecto Lombok y genera un constructor sin argumentos para la clase Respuesta.
	 *Este constructor es útil en el contexto de JPA (Java Persistence API) para la creación de instancias de entidades.
	 * La anotación Data de Lombok genera automáticamente los métodos toString, equals, hashCode, getters y setters para todos los campos de la clase, lo que simplifica el código y mejora la legibilidad.
	 * Id: Indica que el campo id es la clave primaria de la entidad.
	 * GeneratedValue(strategy = GenerationType.IDENTITY): Especifica que el valor de la clave primaria se generará automáticamente de manera incremental.
	 * private Long id: Representa el ID de la respuesta.
	 *private String mensaje: Representa el mensaje de la respuesta.
	 * ManyToOne y JoinColumn(name = "topico_id"): Estas anotaciones establecen una relación de muchos a uno con la entidad Topico.
	 * La respuesta pertenece a un tópico y se almacena mediante una clave externa llamada "topico_id".
	 * private LocalDateTime fechaCreacion: Representa la fecha de creación de la respuesta, que se establece en el momento de la creación.
	 * OneToOne y JoinColumn(name = "autor_id"): Estas anotaciones establecen una relación de uno a uno con la entidad Usuario.
	 *Cada respuesta tiene un autor y se almacena mediante una clave externa llamada "autor_id".
	 * private Boolean solucion: Indica si la respuesta es una solución o no. Por defecto, se establece en false
	 * El primer constructor toma un objeto DatosRegistroRespuesta como argumento y se utiliza para crear una instancia-
	 * de Respuesta a partir de datos proporcionados en la clase DatosRegistroRespuesta.
	 * Este método se utiliza para modificar una respuesta existente (Respuesta) en función de los datos proporcionados en un objeto DatosModificarRespuesta.
	 * Permite actualizar el mensaje y el estado de solución de la respuesta, si se proporcionan en el objeto DatosModificarRespuesta.
	 */
	public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
		this.mensaje = datosRegistroRespuesta.mensaje();
	}

	public void modificar(DatosModificarRespuesta datosModificarRespuesta) {
		if (datosModificarRespuesta.mensaje() != null){
			this.mensaje = datosModificarRespuesta.mensaje();
		}
		if (datosModificarRespuesta.solucion() != null){
			this.solucion = datosModificarRespuesta.solucion();
		}
	}
}