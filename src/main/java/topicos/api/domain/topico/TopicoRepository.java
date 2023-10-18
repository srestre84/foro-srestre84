package topicos.api.domain.topico;


import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloOrMensaje(String titulo, String mensaje);
}
