package topicos.api.services;


import topicos.api.DTO.DatosRegistroCurso;
import topicos.api.domain.curso.Curso;
import topicos.api.domain.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso registraCurso(DatosRegistroCurso datosRegistroCurso) {
        Curso curso = new Curso(datosRegistroCurso);

        return cursoRepository.save(curso);
    }

    public List<Curso> obtenerCursos() {
        var cursos = cursoRepository.findAll();

        return cursos;
    }
}
/*
 *El servicio inyecta una instancia de CursoRepository, que es una interfaz que proporciona métodos para interactuar con la base de datos en relación con la entidad Curso. Esto se hace para poder acceder y manipular los datos de los cursos en la base de datos.
 *Este método se utiliza para registrar un nuevo curso en la base de datos. Recibe un objeto DatosRegistroCurso que contiene los detalles del curso a registrar.
 *Luego, crea una instancia de la entidad Curso a partir de los datos proporcionados en el objeto DatosRegistroCurso. Finalmente, utiliza el método save del repositorio cursoRepository para guardar el curso en la base de datos y lo devuelve como resultado.
 *Este método se utiliza para obtener la lista de todos los cursos registrados en la base de datos. Utiliza el método findAll proporcionado por el repositorio cursoRepository para recuperar todos los cursos y los devuelve como una lista.
 * En resumen, el servicio CursoService facilita la creación y recuperación de cursos en la base de datos. Es una parte esencial de la lógica de negocio de la aplicación, lo que permite la gestión de cursos en el sistema.
 */