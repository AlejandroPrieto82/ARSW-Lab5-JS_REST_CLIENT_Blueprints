package edu.eci.arsw.blueprints.persistence;

/**
 * Excepción lanzada cuando ocurre un error al persistir un plano,
 * por ejemplo, al intentar guardar uno duplicado.
 *
 * Se utiliza en operaciones de almacenamiento dentro de
 * {@link BlueprintsPersistence}.
 * 
 * @author Alejandro Prieto
 */
public class BlueprintPersistenceException extends Exception {

    /**
     * Crea una nueva excepción con un mensaje descriptivo.
     *
     * @param message mensaje que describe la causa de la excepción
     */
    public BlueprintPersistenceException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción con un mensaje descriptivo y una causa anidada.
     *
     * @param message mensaje que describe la causa de la excepción
     * @param cause excepción original que causó el error
     */
    public BlueprintPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
