package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Clase principal de la aplicación.
 *
 * Configura el contexto de Spring Boot y prueba las funcionalidades del servicio:
 * - Registrar planos.
 * - Consultar planos por autor.
 * - Consultar un plano específico.
 * - Listar todos los planos.
 */
@SpringBootApplication  
public class BlueprintApp {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comando
     * @throws Exception si ocurre un error durante el registro o consulta de planos
     */
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(BlueprintApp.class, args);

        BlueprintsServices bps = ctx.getBean(BlueprintsServices.class);

        // Crear y registrar un plano de Juan
        Blueprint bp1 = new Blueprint("Juan", "Casa1", new Point[]{
                new Point(10, 10), new Point(20, 20), new Point(30, 30)
        });
        bps.addNewBlueprint(bp1);

        // Crear y registrar un plano de Maria
        Blueprint bp2 = new Blueprint("Maria", "Carro", new Point[]{
                new Point(5, 5), new Point(15, 15)
        });
        bps.addNewBlueprint(bp2);

        // Consultas de prueba
        System.out.println("Plano de Juan: " + bps.getBlueprint("Juan", "Casa1"));
        System.out.println("Planos de Maria: " + bps.getBlueprintsByAuthor("Maria"));
        System.out.println("Todos los planos: " + bps.getAllBlueprints());
    }
}
