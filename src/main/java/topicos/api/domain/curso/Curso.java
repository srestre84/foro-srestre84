package topicos.api.domain.curso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.NoArgsConstructor;






import topicos.api.DTO.DatosRegistroCurso;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cursos")
@NoArgsConstructor
@Data
public class Curso {
    /*
     * Las anotaciones Entity y Table se utilizan para indicar que esta clase es-
     *  una entidad que se mapeará a una tabla en la base de datos. La tabla se llamará cursos.
     * La anotación @NoArgsConstructor proviene del proyecto Lombok y genera un constructor -
     * sin argumentos para la clase Curso. Este constructor es útil en el contexto de JPA (Java Persistence API)-
     *  para la creación de instancias de entidades.
     * La anotación @Data de Lombok genera automáticamente los métodos toString, equals, hashCode, getters y setters para todos los campos de la clase,
     * lo que simplifica el código y mejora la legibilidad.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    /*
     * Id: Indica que el campo id es la clave primaria de la entidad.
     * GeneratedValue(strategy = GenerationType.IDENTITY): Especifica que el valor de la clave primaria se generará automáticamente de manera incremental.
     */

    public Curso(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }


    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre();
        this.categoria = datosRegistroCurso.categoria();
    }
}
