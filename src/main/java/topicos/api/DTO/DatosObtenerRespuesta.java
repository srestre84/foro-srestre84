package topicos.api.DTO;

import topicos.api.domain.respuesta.Respuesta;

import java.time.LocalDateTime;

public record DatosObtenerRespuesta(
        Long id,
        String autor,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion
) {
    public DatosObtenerRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getAutor().getNombre(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion()
        );
    }
}
