package topicos.api.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    /*
     * El controlador tiene un constructor que inyecta una instancia de SessionFactory.
     *  Esto se hace utilizando la anotación Autowired y permite que el controlador
     *  acceda a la sesión de Hibernate para interactuar con la base de datos.
     */

    private final SessionFactory sessionFactory;

    @Autowired
    public LoginController(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /*
     * El controlador tiene un constructor que inyecta una instancia de SessionFactory.
     *  Esto se hace utilizando la anotación Autowired y permite que el controlador
     *  acceda a la sesión de Hibernate para interactuar con la base de datos.
     */

    @GetMapping("/buscarUsuario")
    public ResponseEntity<?> buscarUsuario(@RequestParam("nombre") String nombre) {
        try (Session session = sessionFactory.openSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT u.id, u.contrasena, u.email, u.login, u.nombre " +
                            "FROM Usuario u " +
                            "WHERE u.login = nombre", Object[].class
            );
            /*
             *GetMapping indica que este método manejará solicitudes HTTP GET-
             * en la ruta buscarUsuario.
             *RequestParam(nombre): Esto se utiliza para obtener el valor del parámetro "nombre" desde la URL.
             */
            /*
             * Se abre una sesión de Hibernate utilizando la instancia de SessionFactory.
             * Se crea una consulta que selecciona ciertos campos (id, contrasena, email, login y nombre)
             * de la entidad Usuario donde el campo login es igual al valor del parámetro nombre.
             * Se establece el valor del parámetro nombre en la consulta
             *  utilizando query.setParameter.
             * Los resultados de la consulta se almacenan en una lista de arrays de objetos.
             */

            query.setParameter("nombre", nombre);

            List<Object[]> results = query.list();

            /*
             Se devuelve una respuesta HTTP OK (código de estado 200)
             *con los resultados de la consulta como el cuerpo de la respuesta
             *Esto envía los datos de los usuarios encontrados de vuelta al cliente que realizó la solicitud.
             */

            return ResponseEntity.ok(results);
        } catch (Exception e) {
            /*
             * En caso de que ocurra alguna excepción durante la búsqueda de usuarios, se captura la excepción en un bloque catch,
             *  se imprime en la consola y se devuelve una respuesta HTTP 500 (Error en el servidor)
             * junto con un mensaje de error genérico.
             *
             */

            e.printStackTrace();
            return ResponseEntity.status(500).body("Error en el servidor");
        }
    }
}
