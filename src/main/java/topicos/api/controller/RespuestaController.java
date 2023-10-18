package topicos.api.controller;

import topicos.api.DTO.DatosModificarRespuesta;
import topicos.api.DTO.DatosObtenerRespuesta;
import topicos.api.DTO.DatosRegistroRespuesta;
import topicos.api.services.RespuestaService;
import topicos.api.services.TopicoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos/respuestas")
public class RespuestaController {
    /*
     * Las anotaciones RestController y RequestMapping se utilizan para definir este Java class
     * como un controlador de Spring MVC y para mapear todas
     *  las solicitudes que comienzan con topicos/respuestas a este controlador.
     */

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespuestaService respuestaService;
    /*
     *Estos campos se anotan con Autowired para inyectar dependencias. topicoService y respuestaService
     * son servicios que proporcionan funcionalidad para manipular tópicos y respuestas.
     */

    @GetMapping("/{id}")
    public ResponseEntity obtenerRespuesta(@PathVariable Long id) {
        var respuesta = respuestaService.obtenerRespuesta(id);

        return ResponseEntity.ok().body(respuesta);
    }
    /*
     *GetMapping("/{id}"): Esta anotación indica que este método manejará solicitudes HTTP GET en la ruta
     * /topicos/respuestas/{id}" donde {id} es una variable de ruta.
     * El método toma el parámetro id de la URL y llama al servicio respuestaService para obtener una respuesta por ese ID.
     * Luego, devuelve una respuesta HTTP OK (código de estado 200) con la respuesta como cuerpo de la respuesta.
     */


    @PostMapping
    @Transactional
    public ResponseEntity crearRespuesta( @RequestBody DatosRegistroRespuesta datosRegistroRespuesta) {
        var respuesta = respuestaService.crearRespuesta(datosRegistroRespuesta);

        return ResponseEntity.ok().body(new DatosObtenerRespuesta(respuesta));
    }

    /*
     * PostMapping  indica que este método manejará solicitudes HTTP POST.
     * Transactionalse utiliza en contexto de Java EE (Jakarta EE) para definir que el método es transaccional,-
     * lo que implica que se está interactuando con una base de datos.
     * El método toma un objeto DatosRegistroRespuesta del cuerpo de la solicitud y llama al método crearRespuesta
     * del servicio respuestaService para crear una nueva respuesta.
     * Luego, devuelve una respuesta HTTP OK (código de estado 200) con la respuesta creada como cuerpo de la respuesta,-
     *  pero se convierte a un objeto DatosObtenerRespuesta.
     */


    @PutMapping
    @Transactional
    public ResponseEntity modificarRespuesta(@RequestBody DatosModificarRespuesta datosModificarRespuesta){
        var respuesta = respuestaService.modificarRespuesta(datosModificarRespuesta);

        return ResponseEntity.ok().body(respuesta);
    }

    /*
  * PutMapping indica que este método manejará solicitudes HTTP PUT.
  *  El método toma un objeto DatosModificarRespuesta del cuerpo de la solicitud y llama al método modificarRespuesta
 * del servicio respuestaService para actualizar una respuesta existente.
 * Luego, devuelve una respuesta HTTP OK (código de estado 200) con la respuesta modificada como cuerpo de la respuesta.
     */


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaService.eliminarRespuesta(id);
        /*
         * @DeleteMapping("/{id}"): Esta anotación indica que este método manejará solicitudes HTTP DELETE
         * en la ruta /topicos/respuestas/{id}.El método toma el parámetro id de la URL -
         * y llama al método eliminarRespuesta del servicio respuestaService para eliminar una respuesta por ese ID.
         *Luego, devuelve una respuesta HTTP sin contenido (código de estado 204).
         */
        return ResponseEntity.noContent().build();
    }

}
