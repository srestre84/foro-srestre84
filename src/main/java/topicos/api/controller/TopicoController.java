package topicos.api.controller;

import org.springframework.data.domain.Page;
import topicos.api.DTO.DatosModificarTopico;
import topicos.api.DTO.DatosObtenerTopico;
import topicos.api.DTO.DatosObtenerTopicos;
import topicos.api.DTO.DatosRegistroTopico;
import topicos.api.domain.topico.Topico;
import topicos.api.services.RespuestaService;
import topicos.api.services.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    /*
     * Las anotaciones RestController y RequestMapping -
     * se utilizan para definir este Java class como un controlador de Spring MVC y -
     * para mapear todas las solicitudes que comienzan con /topicos a este controlador
     */


    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespuestaService respuestaService;

    /*
     * Estos campos se anotan con Autowired para inyectar dependencias. topicoService y respuestaService
     * son servicios que proporcionan funcionalidad
     *  para manipular tópicos y respuestas.
     */


    @PostMapping
    @Transactional
    public ResponseEntity<Topico> RegistrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        Topico topicoCreado = topicoService.RegistrarTopico(datosRegistroTopico);


        URI uri = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topicoCreado.getId()).toUri();
        return ResponseEntity.created(uri).body(topicoCreado);


        /*
         * PostMapping indica que este método manejará solicitudes HTTP POST.
         * Transactional: Esta anotación se utiliza en contexto de Java EE (Jakarta EE) para definir que el método es transaccional,-
         * lo que implica que se está interactuando con una base de datos.
         * *El método toma un objeto DatosRegistroTopico del cuerpo de la solicitud y llama al método RegistrarTopico del servicio topicoService para crear un nuevo tópico.
         * Después de crear el tópico, se genera una URI que apunta a este nuevo tópico y se devuelve una respuesta HTTP 201 Created con la URI y el tópico creado como cuerpo de la respuesta.
         */
        /*
         * La anotación @Valid se utiliza en Spring Framework para activar la validación de bean (objeto) en un objeto que se encuentra-
         *en una solicitud o un método controlador, generalmente en el contexto de una aplicación web. Esta anotación permite aplicar validaciones a los campos de ese objeto de acuerdo con -
         * las restricciones definidas en las anotaciones de validación, como @NotNull, @Size, @Email, entre otras.
         */

    }

    @GetMapping
    public ResponseEntity<Page<DatosObtenerTopicos>> obtenerTopicos(@PageableDefault(size = 6, sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable paginacion) {
        var topicos = topicoService.obtenerTopicos(paginacion);

        return ResponseEntity.ok().body(topicos);

        /*
         * @GetMapping("/{id}"): Esta anotación indica que este método manejará solicitudes HTTP GET
         * en la ruta "/topicos/{id}" donde {id} es una variable de ruta.
         * El método toma el parámetro id de la URL y llama al servicio topicoService para obtener un tópico por ese ID.
         * Luego, devuelve una respuesta HTTP OK (código de estado 200) con el tópico como cuerpo de la respuesta.
         * En cuanto a pageableDefault , size = 6: Esto establece el tamaño predeterminado de la página.
         *  sort = "fechaCreacion": Esto indica que se ordenarán los elementos por la propiedad "fechaCreacion".
         *  En otras palabras, los elementos se clasificarán según su fecha de creación.
         * direction = Sort.Direction.DESC: Define la dirección de clasificación predeterminada como descendente
         * (desde la fecha de creación más reciente hasta la más antigua).
         * paginacion: Este parámetro es el objeto Pageable que se utiliza en el método para controlar la paginación.
         * El objeto Pageable contiene información sobre la página actual, el tamaño de la página, la dirección de ordenación y el campo de ordenación.
         */

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosObtenerTopico> obtenerTopicoPorId(@PathVariable Long id) {
        var topico = topicoService.obtenerTopico(id);
        /*
         * GetMapping("/{id}"): Esta anotación indica que este método manejará solicitudes HTTP GET en la ruta "/topicos/{id}"
         *  donde {id} es una variable de ruta.
         * El método toma el parámetro id de la URL y llama al servicio topicoService para obtener un tópico por ese ID.
         * Luego, devuelve una respuesta HTTP OK (código de estado 200) con el tópico como cuerpo de la respuesta.
         */

        return ResponseEntity.ok().body(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosModificarTopico> modificarTopico(@PathVariable Long id, @RequestBody @Valid DatosModificarTopico datosModificarTopico) {
        datosModificarTopico.setId(id); // Asignamos el ID del tópico a los datos de modificación
        DatosModificarTopico topicoModificado = topicoService.modificarTopico(datosModificarTopico);
        /*PutMapping("/{id})  indica que este método manejará solicitudes HTTP PUT en la ruta "/topicos/{id}".
         * El método toma el parámetro id de la URL y un objeto DatosModificarTopico del cuerpo de la solicitud.
         * Llama al método modificarTopico del servicio topicoService para actualizar el tópico con el ID especificado.
         * Luego, devuelve una respuesta HTTP OK (código de estado 200) con el tópico modificado como cuerpo de la respuesta.
         */

        return ResponseEntity.ok().body(topicoModificado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        /*
         * DeleteMapping("/{id}): Esta anotación indica que este método manejará solicitudes HTTP DELETE en la ruta "/topicos/{id}".
         *  El método toma el parámetro id de la URL y llama al método eliminarTopico del servicio topicoService para eliminar el tópico con el ID especificado.
         * Luego, devuelve una respuesta HTTP sin contenido (código de estado 204).
         */

        return ResponseEntity.noContent().build();
    }
}