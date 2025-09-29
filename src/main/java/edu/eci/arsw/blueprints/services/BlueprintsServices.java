package edu.eci.arsw.blueprints.services;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

/**
 * Servicio de gestión de planos.
 * 
 * Provee operaciones de negocio sobre {@link Blueprint}, incluyendo:
 * - Registro de nuevos planos.
 * - Consulta de planos por autor o nombre.
 * - Consulta de todos los planos.
 * - Actualización de planos existentes.
 *
 * Además, aplica un {@link BlueprintFilter} para optimizar
 * los planos antes de retornarlos.
 * 
 * Es gestionado por Spring como un {@link Service}.
 * 
 * @author Alejandro Prieto
 */
@Service
public class BlueprintsServices {

    @Autowired
    private BlueprintsPersistence bpp;

    @Autowired
    @Qualifier("redundancyFilter")
    private BlueprintFilter filter;

    /**
     * Registra un nuevo plano en el sistema.
     *
     * @param bp el plano a registrar
     * @throws BlueprintPersistenceException si ya existe un plano con el mismo autor y nombre
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    /**
     * Recupera todos los planos almacenados en el sistema,
     * aplicando el filtro configurado.
     *
     * @return conjunto de planos filtrados
     */
    public Set<Blueprint> getAllBlueprints() {
        return bpp.getAllBlueprints().stream()
                .map(filter::filter)
                .collect(Collectors.toSet());
    }

    /**
     * Obtiene un plano específico por autor y nombre,
     * aplicando el filtro configurado.
     *
     * @param author autor del plano
     * @param name nombre del plano
     * @return el plano solicitado
     * @throws BlueprintNotFoundException si no existe un plano con los datos dados
     */
    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        return filter.filter(bpp.getBlueprint(author, name));
    }

    /**
     * Recupera todos los planos de un autor,
     * aplicando el filtro configurado.
     *
     * @param author autor cuyos planos se desean consultar
     * @return conjunto de planos filtrados del autor
     * @throws BlueprintNotFoundException si el autor no tiene planos registrados
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        return bpp.getBlueprintsByAuthor(author).stream()
                .map(filter::filter)
                .collect(Collectors.toSet());
    }

    /**
     * Actualiza un plano existente en el sistema.
     *
     * @param author autor del plano
     * @param name nombre del plano
     * @param bp nuevo contenido del plano
     * @throws BlueprintNotFoundException si no existe un plano con ese autor y nombre
     */
    public void updateBlueprint(String author, String name, Blueprint bp) throws BlueprintNotFoundException {
        bpp.updateBlueprint(author, name, bp);
    }
}
