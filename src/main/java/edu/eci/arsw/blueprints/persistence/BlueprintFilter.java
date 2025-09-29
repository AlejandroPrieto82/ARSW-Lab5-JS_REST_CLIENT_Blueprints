package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

/**
 * Interfaz que define el contrato para los filtros de planos.
 * 
 * Los filtros permiten modificar la lista de puntos de un {@link Blueprint}
 * aplicando diferentes estrategias (como eliminar redundancias o
 * reducir el número de puntos).
 *
 * @author Alejandro Prieto
 */
public interface BlueprintFilter {

    /**
     * Aplica el filtro al plano dado.
     *
     * @param bp el plano a filtrar
     * @return un nuevo {@link Blueprint} con los puntos filtrados
     */
    Blueprint filter(Blueprint bp);
}
