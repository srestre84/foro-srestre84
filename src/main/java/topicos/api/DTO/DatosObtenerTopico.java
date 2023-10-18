package topicos.api.DTO;

import topicos.api.domain.topico.StatusTopico;
import topicos.api.domain.topico.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DatosObtenerTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        StatusTopico statusTopico,
        String autor,
        Long autor_id,
        String curso,
        List<DatosObtenerRespuesta> respuestas
) {
    public DatosObtenerTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getAutor().getId(),
                topico.getCurso().getNombre(),
                topico.getRespuestas().stream().map(DatosObtenerRespuesta::new).collect(Collectors.toList())
        );
    }}