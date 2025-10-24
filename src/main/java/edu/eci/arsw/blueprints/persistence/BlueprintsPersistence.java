package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import java.util.Set;

/**
 * Interfaz que define los servicios de persistencia para planos.
 *
 * Permite almacenar, consultar y recuperar planos
 * según diferentes criterios de búsqueda.
 * 
 * Implementaciones típicas: en memoria, en bases de datos, etc.
 * 
 * @author Alejandro Prieto
 */
public interface BlueprintsPersistence {

    /**
     * Guarda un nuevo plano en el sistema.
     *
     * @param bp el nuevo plano
     * @throws BlueprintPersistenceException si ya existe un plano con el mismo
     * nombre y autor, o si ocurre un error en la persistencia.
     */
    void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    /**
     * Obtiene un plano específico a partir de su autor y nombre.
     *
     * @param author autor del plano
     * @param bprintname nombre del plano
     * @return el plano correspondiente
     * @throws BlueprintNotFoundException si no existe un plano con el autor y nombre indicados
     */
    Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException;

    /**
     * Obtiene todos los planos creados por un autor específico.
     *
     * @param author autor cuyos planos se desean consultar
     * @return un conjunto con los planos del autor dado
     * @throws BlueprintNotFoundException si el autor no tiene planos registrados
     */
    Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;

    /**
     * Recupera todos los planos almacenados en el sistema.
     *
     * @return un conjunto con todos los planos disponibles
     */
    Set<Blueprint> getAllBlueprints();

    /**
     * Actualiza un plano existente en el sistema.
     *
     * @param author autor del plano
     * @param bprintname nombre del plano
     * @param bp nuevo contenido del plano
     * @throws BlueprintNotFoundException si no existe un plano con ese autor y nombre
     */
    void updateBlueprint(String author, String bprintname, Blueprint bp) throws BlueprintNotFoundException;
}
