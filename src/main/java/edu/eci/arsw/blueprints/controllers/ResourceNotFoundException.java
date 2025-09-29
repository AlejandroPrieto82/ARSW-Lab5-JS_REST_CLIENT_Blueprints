package edu.eci.arsw.blueprints.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para indicar que un recurso no fue encontrado.
 * Está mapeada automáticamente a la respuesta HTTP 404 (Not Found).
 *
 * @author Alejandro Prieto
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    
}
