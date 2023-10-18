package topicos.api.services;

import topicos.api.DTO.*;
import topicos.api.domain.topico.Topico;
import topicos.api.domain.curso.CursoRepository;
import topicos.api.domain.respuesta.RespuestaRepository;
import topicos.api.domain.topico.TopicoRepository;
import topicos.api.domain.usuarios.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private RespuestaService respuestaService;


    public Topico RegistrarTopico(DatosRegistroTopico datosRegistroTopico) {

        if (topicoRepository.existsByTituloOrMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())){
            throw new ValidationException("no se topicos duplicados");
        }

        var usuario = usuarioRepository.findById(datosRegistroTopico.autor_id()).get();
        var curso = cursoRepository.findById(datosRegistroTopico.curso_id()).get();

        Topico topico = new Topico(datosRegistroTopico);
        topico.setAutor(usuario);
        topico.setCurso(curso);
        return topicoRepository.save(topico);
    }

    public Page<DatosObtenerTopicos> obtenerTopicos(Pageable paginacion) {
        Page<Topico> topicos = topicoRepository.findAll(paginacion);

//        return topicos.stream().map(topico -> new DatosObtenerTopicos(topico)).toList();
        return topicoRepository.findAll(paginacion).map(DatosObtenerTopicos::new);
    }

    public DatosObtenerTopico obtenerTopico (Long id) {
        Topico topico = topicoRepository.findById(id).get();
        System.out.println(topico.getRespuestas().stream().toList());
        return new DatosObtenerTopico(topico);
    }

    public DatosModificarTopico modificarTopico(DatosModificarTopico datosModificarTopico){
        var topico = topicoRepository.getReferenceById(datosModificarTopico.id());
        topico.modificar(datosModificarTopico);
        return new DatosModificarTopico(topico);
    }

    public void eliminarTopico (Long id) {
//        topicoRepository.deleteById(id);
        var topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            Topico topico1 = topico.get();
            topicoRepository.delete(topico1);
        } else {
            throw new EntityNotFoundException(String.format("el topico con el id %d no fue encontrado", id));
        }
    }

}
/*
 *El servicio inyecta instancias de varios repositorios y otros servicios que se utilizan para interactuar con la base de datos y otras funcionalidades relacionadas con los tópicos, usuarios, cursos y respuestas.
 *Este método se utiliza para registrar un nuevo tópico en la base de datos. Recibe un objeto DatosRegistroTopico que contiene los detalles del tópico a registrar.
 * Registrar: Antes de registrar el tópico, verifica si ya existe un tópico con el mismo título o mensaje para evitar duplicados. Luego, obtiene referencias al autor (usuario) y curso relacionados y crea una instancia de la entidad Topico. Finalmente, utiliza el método save del repositorio topicoRepository para guardar el tópico en la base de datos y lo devuelve como resultado.
 *ver si hay duplicados: Este método se utiliza para obtener una página de tópicos. Toma un objeto Pageable para la paginación y utiliza el método findAll del repositorio topicoRepository para recuperar una página de tópicos desde la base de datos. Luego, mapea estos tópicos a objetos DatosObtenerTopicos y devuelve la página resultante.
 * Id: se utiliza para obtener los detalles de un tópico específico a través de su identificador único (id). Utiliza el método findById proporcionado por el repositorio topicoRepository para recuperar el tópico por su ID y luego crea un objeto DatosObtenerTopico a partir del tópico encontrado para devolverlo como resultado
 *Este método se utiliza para eliminar un tópico específico de la base de datos por su ID. Primero, intenta obtener el tópico por su ID utilizando el método findById. Si el tópico existe, lo elimina utilizando el método delete del repositorio topicoRepository. Si no encuentra el tópico, lanza una excepción de EntityNotFoundException indicando que el tópico no fue encontrado.
 */
