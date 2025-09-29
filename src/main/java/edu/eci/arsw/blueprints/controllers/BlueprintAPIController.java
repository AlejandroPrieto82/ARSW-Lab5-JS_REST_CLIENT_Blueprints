package edu.eci.arsw.blueprints.controllers;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador REST para la gestión de {@link Blueprint}.
 * Proporciona operaciones CRUD básicas expuestas como servicios REST.
 *
 * @author Alejandro Prieto
 */
@RestController
@RequestMapping("/blueprints")
public class BlueprintAPIController {

    @Autowired
    private BlueprintsServices blueprintServices;

    private static final Logger LOG = Logger.getLogger(BlueprintAPIController.class.getName());

    /**
     * Obtiene todos los planos almacenados en el sistema.
     *
     * @return {@link ResponseEntity} con el conjunto de {@link Blueprint} y estado 202 (Accepted),
     *         o un mensaje de error con estado 404 si ocurre un problema.
     */
    @GetMapping
    public ResponseEntity<?> getAllBlueprints() {
        try {
            Set<Blueprint> all = blueprintServices.getAllBlueprints();
            return new ResponseEntity<>(all, HttpStatus.ACCEPTED); // 202 según enunciado
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error retrieving blueprints", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene todos los planos creados por un autor específico.
     *
     * @param author nombre del autor.
     * @return {@link ResponseEntity} con el conjunto de {@link Blueprint} del autor y estado 202,
     *         o un mensaje de error con estado 404 si no se encuentra el autor,
     *         o un error 500 si ocurre un problema interno.
     */
    @GetMapping("/{author}")
    public ResponseEntity<?> getBlueprintsByAuthor(@PathVariable String author) {
        try {
            Set<Blueprint> bps = blueprintServices.getBlueprintsByAuthor(author);
            return new ResponseEntity<>(bps, HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene un plano específico a partir de un autor y el nombre del plano.
     *
     * @param author nombre del autor.
     * @param bpname nombre del plano.
     * @return {@link ResponseEntity} con el {@link Blueprint} solicitado y estado 202,
     *         o un mensaje de error con estado 404 si no se encuentra el plano.
     */
    @GetMapping("/{author}/{bpname}")
    public ResponseEntity<?> getBlueprint(@PathVariable String author, @PathVariable String bpname) {
        try {
            Blueprint bp = blueprintServices.getBlueprint(author, bpname);
            return new ResponseEntity<>(bp, HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Blueprint not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea un nuevo plano en el sistema.
     *
     * @param blueprint objeto {@link Blueprint} recibido en el cuerpo de la petición.
     * @return {@link ResponseEntity} con estado 201 (Created) si se crea exitosamente,
     *         o un mensaje con estado 403 si ya existe,
     *         o un error 500 en caso de fallo interno.
     */
    @PostMapping
    public ResponseEntity<?> createBlueprint(@RequestBody Blueprint blueprint) {
        try {
            blueprintServices.addNewBlueprint(blueprint);
            return new ResponseEntity<>(HttpStatus.CREATED); // 201
        } catch (BlueprintPersistenceException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error creating blueprint", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza un plano existente en el sistema.
     *
     * @param author   nombre del autor del plano.
     * @param bpname   nombre del plano.
     * @param blueprint objeto {@link Blueprint} con la información actualizada.
     * @return {@link ResponseEntity} con estado 202 si se actualiza exitosamente,
     *         o un mensaje de error con estado 404 si no se encuentra el plano,
     *         o un error 500 en caso de fallo interno.
     */
    @PutMapping("/{author}/{bpname}")
    public ResponseEntity<?> updateBlueprint(@PathVariable String author,
                                             @PathVariable String bpname,
                                             @RequestBody Blueprint blueprint) {
        try {
            blueprintServices.updateBlueprint(author, bpname, blueprint);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error updating blueprint", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
