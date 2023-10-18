package topicos.api.services;

import topicos.api.DTO.DatosModificarRespuesta;
import topicos.api.DTO.DatosObtenerRespuesta;
import topicos.api.DTO.DatosRegistroRespuesta;
import topicos.api.domain.respuesta.Respuesta;
import topicos.api.domain.respuesta.RespuestaRepository;
import topicos.api.domain.topico.TopicoRepository;
import topicos.api.domain.usuarios.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public DatosObtenerRespuesta obtenerRespuesta(Long id) {
        var respuesta = respuestaRepository.findById(id).get();

        return new DatosObtenerRespuesta(respuesta);
    }
    public Respuesta crearRespuesta(DatosRegistroRespuesta datosRegistroRespuesta){
        var respuesta = new Respuesta(datosRegistroRespuesta);

        var autor = usuarioRepository.getReferenceById(datosRegistroRespuesta.autor_id());
        var topico = topicoRepository.getReferenceById(datosRegistroRespuesta.topico_id());

        respuesta.setAutor(autor);
        respuesta.setTopico(topico);

        return respuestaRepository.save(respuesta);
    }

    public DatosModificarRespuesta modificarRespuesta(DatosModificarRespuesta datosModificarRespuesta) {
        var respuesta = respuestaRepository.getReferenceById(datosModificarRespuesta.id());

        respuesta.modificar(datosModificarRespuesta);

        return new DatosModificarRespuesta(respuesta);
    }
    public void eliminarRespuesta (Long id) {
        var respuesta = respuestaRepository.findById(id);

        if (respuesta.isPresent()) {
            Respuesta respuestaAEliminar = respuesta.get();
            respuestaRepository.delete(respuestaAEliminar);
        } else {
            throw new EntityNotFoundException(String.format("la respuesta con el id %d no fue encontrado", id));
        }
    }
}
/*
 *El servicio inyecta instancias de varios repositorios, que proporcionan métodos para interactuar con la base de datos en relación con las entidades Topico, Usuario, y Respuesta. Estos repositorios se utilizan para acceder y manipular los datos relacionados con las respuestas en la base de datos.
 *Este método se utiliza para obtener los detalles de una respuesta específica a través de su identificador único (id). Utiliza el método findById proporcionado por el repositorio respuestaRepository para recuperar la respuesta por su ID y luego crea un objeto DatosObtenerRespuesta a partir de la respuesta encontrada para devolverla como resultado.
 *Este método se utiliza para crear una nueva respuesta en la base de datos. Recibe un objeto DatosRegistroRespuesta que contiene los detalles de la respuesta a registrar. Luego, crea una instancia de la entidad Respuesta a partir de los datos proporcionados en el objeto DatosRegistroRespuesta.
 * Además, obtiene referencias a los objetos Usuario y Topico relacionados a través de sus ID y los asigna a la respuesta. Finalmente, utiliza el método save del repositorio respuestaRepository para guardar la respuesta en la base de datos y la devuelve como resultado.
 * El metodo elimina se utiliza para eliminar una respuesta específica de la base de datos por su ID. Primero, intenta obtener la respuesta por su ID utilizando el método findById. Si la respuesta existe, la elimina utilizando el método delete del repositorio respuestaRepository. Si no encuentra la respuesta, lanza una excepción de EntityNotFoundException indicando que la respuesta no fue encontrada.
 */