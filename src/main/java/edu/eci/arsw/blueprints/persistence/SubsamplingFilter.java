package edu.eci.arsw.blueprints.persistence;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

/**
 * Implementación del filtro de submuestreo.
 *
 * Este filtro conserva únicamente un punto de cada dos,
 * reduciendo la resolución de un plano a la mitad.
 *
 * Ejemplo:
 * (1,1), (2,2), (3,3), (4,4) → (1,1), (3,3)
 * 
 * @author Alejandro Prieto
 */
@Component("subsamplingFilter")
public class SubsamplingFilter implements BlueprintFilter {

    /**
     * Aplica el filtro de submuestreo al plano dado.
     * 
     * @param bp el plano original
     * @return un nuevo plano con solo la mitad de los puntos,
     * conservando de manera intercalada.
     */
    @Override
    public Blueprint filter(Blueprint bp) {
        List<Point> filtered = new ArrayList<>();
        for (int i = 0; i < bp.getPoints().size(); i++) {
            if (i % 2 == 0) {
                filtered.add(bp.getPoints().get(i));
            }
        }
        return new Blueprint(bp.getAuthor(), bp.getName(), filtered.toArray(new Point[0]));
    }
}
