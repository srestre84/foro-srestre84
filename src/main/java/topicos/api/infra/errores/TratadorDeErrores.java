package topicos.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
    /*
     * RestControllerAdvice indica que la clase TratadorDeErrores es un componente de Spring que se encarga de manejar excepciones en un contexto de controladores. En otras palabras, este componente manejará errores específicos lanzados por los controladores de la aplicación.
     * Aquí se define un método anotado con ExceptionHandler que maneja excepciones de tipo EntityNotFoundException. Cuando se lanza una excepción de este tipo, este método devuelve una respuesta HTTP 404 (Not Found) utilizando ResponseEntity.notFound().build().
     *Este método maneja excepciones de tipo MethodArgumentNotValidException. Estas excepciones suelen lanzarse cuando se realizan validaciones de datos en los controladores y los datos no cumplen con las restricciones especificadas.
     * Se obtienen los errores de validación de los campos (FieldError) a partir de la excepción MethodArgumentNotValidException.
     * Luego, se mapean estos errores de validación a objetos de tipo DatosErrorValidacion. La clase DatosErrorValidacion se utiliza para representar los detalles de un error de validación, incluyendo el campo y el mensaje de error.
     * Finalmente, se devuelve una respuesta HTTP 400 (Bad Request) que contiene una lista de objetos DatosErrorValidacion, lo que permite al cliente de la API conocer los detalles de los errores de validación.
     *La clase DatosErrorValidacion es una clase interna que se utiliza para representar los detalles de un error de validación. La anotación record en Java se utiliza para crear una clase simple que contiene datos inmutables. En este caso, tiene dos atributos: campo y error,-
     * que representan el nombre del campo que tiene el error de validación y el mensaje de error.
     */
}