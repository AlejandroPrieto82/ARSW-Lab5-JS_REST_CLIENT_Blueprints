package edu.eci.arsw.blueprints.persistence;

/**
 * Excepción lanzada cuando no se encuentra un plano
 * en el sistema de persistencia.
 *
 * Se utiliza principalmente en operaciones de consulta
 * dentro de {@link BlueprintsPersistence}.
 * 
 * @author Alejandro Prieto
 */
public class BlueprintNotFoundException extends Exception {

    /**
     * Crea una nueva excepción con un mensaje descriptivo.
     *
     * @param message mensaje que describe la causa de la excepción
     */
    public BlueprintNotFoundException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción con un mensaje descriptivo y una causa anidada.
     *
     * @param message mensaje que describe la causa de la excepción
     * @param cause excepción original que causó el error
     */
    public BlueprintNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
