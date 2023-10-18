package topicos.api.DTO;

import topicos.api.domain.curso.Curso;

public record DatosObtenerCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DatosObtenerCurso(Curso curso){
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }
}
