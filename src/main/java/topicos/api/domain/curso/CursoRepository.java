package topicos.api.domain.curso;



import org.springframework.data.jpa.repository.JpaRepository;

/*
 * La interfaz CursoRepository hereda una variedad de métodos predefinidos de JpaRepository, como save, findById, findAll, delete, count, entre otros. Estos métodos permiten -
 * realizar operaciones comunes de acceso a datos sin necesidad de escribir consultas SQL manualmente.
* Además, Spring Data JPA es capaz de generar implementaciones de estos métodos de forma automática basándose en las convenciones de nombres y las firmas de los métodos en la interfaz.-
*  Por ejemplo, si necesitas buscar cursos por nombre, puedes simplemente declarar un método
*  en CursoRepository como findByNombre(String nombre), y Spring Data JPA generará la implementación correspondiente para ejecutar la consulta SQL adecuada.
* En resumen, CursoRepository es una interfaz de repositorio que extiende JpaRepository y está diseñada para proporcionar operaciones de acceso a datos relacionadas con la entidad Curso.
* Esto simplifica en gran medida la interacción con la base de datos y facilita la gestión de datos relacionados con cursos en una aplicación Spring Boot.
 * explicar detalladamente el código.
 */


public interface CursoRepository extends JpaRepository<Curso, Long> {
}
