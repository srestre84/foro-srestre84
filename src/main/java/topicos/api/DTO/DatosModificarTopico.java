package topicos.api.DTO;

import topicos.api.domain.topico.StatusTopico;
import topicos.api.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosModificarTopico(
        Long id,
        String titulo,
        String mensaje,
//        LocalDateTime fecha,
        StatusTopico statusTopico
//        String autor,
//        String curso
) {
    public DatosModificarTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
//                topico.getFechaCreacion(),
                topico.getStatus()
//                topico.getAutor().getNombre(),
//                topico.getCurso().getNombre()
        );
    }

    public void setId(Long id) {
    }
}
