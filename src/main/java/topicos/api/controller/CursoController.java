package topicos.api.controller;

import topicos.api.DTO.DatosObtenerCurso ;
import topicos.api.DTO.DatosRegistroCurso;
import topicos.api.services.CursoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    /*
     * Las anotaciones RestController y RequestMapping se utilizan
     * para definir este Java class como un controlador de Spring MVC
     *  y para mapear todas las solicitudes que comienzan con cursos a
     *  este controlador.
     * Spring MVC es un subproyecto Spring, un marco de Java para crear aplicaciones
     * web y servicios Restful de forma óptima y fácil. Sigue el patrón de diseño
     * Modelo-Vista-Controlador
     */

    @Autowired
    private CursoService cursoService;
    /*
     * Normalmente estamos acostumbrados a usar @AutoWired a nivel de la propiedad
     * que deseamos inyectar. Spring funciona como una mega factoria de objetos.
     *  Cada clase se registra para instanciar objetos con alguna
     *  de las anotaciones @Controller ,@Service ,@repository o @RestController Se
     * inyecta una instancia de CursoService en este controlador
     *  mediante la anotación Autowired. Esto permite que el controlador
     * utilice los métodos y funcionalidades proporcionados por CursoService.
     */

    @PostMapping
    @Transactional
    public ResponseEntity registrarCurso(@RequestBody DatosRegistroCurso datosRegistroCurso) {
        var curso = cursoService.registraCurso(datosRegistroCurso);
        return ResponseEntity.ok().body(datosRegistroCurso);
    }
    /*
  * @PostMapping: Esta anotación indica que este método manejará solicitudes HTTP POST.
* @Transactional: Esta anotación se utiliza en contexto de Java EE (Jakarta EE) para definir que
* el método es transaccional, lo que generalmente implica que se está interactuando con una base de datos.
* El método toma un objeto DatosRegistroCurso del cuerpo de la solicitud y llama al método registraCurso
* del servicio cursoService para registrar un curso. Luego, se devuelve una respuesta HTTP 200 OK
* con el mismo objeto datosRegistroCurso en el cuerpo de la respuesta.
     */

    @GetMapping
    public ResponseEntity obtenerCursos() {
        var cursos = cursoService.obtenerCursos().stream().map(DatosObtenerCurso::new).toList();

        return ResponseEntity.ok().body(cursos);
    }

    /*   GetMapping: Esta anotación indica que este método manejará solicitudes HTTP GET.
    *El método llama al método obtenerCursos del servicio cursoService,
    * Y recupera una lista de cursos.
    *Luego, mapea estos cursos a objetos DatosObtenerCurso y los almacena en una lista.
    *Finalmente, devuelve una respuesta HTTP 200 OK con la lista de cursos en el cuerpo de la respuesta.
*/
}
