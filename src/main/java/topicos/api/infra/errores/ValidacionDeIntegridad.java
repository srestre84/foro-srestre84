package topicos.api.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String s) {
        super(s);
    }


    /*
     * Esta línea declara la clase ValidacionDeIntegridad, que es una subclase de RuntimeException.
     * En Java, RuntimeException es una clase que representa excepciones que no están verificadas, lo que significa que no es necesario declararlas o manejarlas en una cláusula throws.
     * Estas excepciones suelen utilizarse para indicar problemas que ocurren en tiempo de ejecución,
     * como errores de validación, y no es necesario atraparlas explícitamente.
     * La clase ValidacionDeIntegridad tiene un constructor que acepta una cadena (String) como argumento. Cuando se crea una instancia de esta excepción, se le puede proporcionar un mensaje de error que describe la razón de la excepción.
     * super(s): Llama al constructor de la clase base (RuntimeException) y le pasa la cadena s como mensaje de error. Esto establece el mensaje que se mostrará cuando se capture y maneje la excepción.
     * En resumen, esta clase ValidacionDeIntegridad se utiliza para representar excepciones que indican problemas de validación o integridad de datos en la aplicación.
     * Cuando se lanza una excepción de esta clase, se puede proporcionar un mensaje de error que describe la razón de la excepción. Estas excepciones pueden ser útiles para señalar errores específicos relacionados con la validación de datos en una aplicación y permitir que el código cliente las capture y maneje adecuadamente.
     */
}
