package topicos.api.DTO;

import topicos.api.domain.respuesta.Respuesta;

public record DatosModificarRespuesta(
        Long id,
        String mensaje,
        Boolean solucion
) {
    public DatosModificarRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getSolucion()
        );
    }
}
