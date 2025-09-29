package edu.eci.arsw.blueprints.test;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryPersistenceTest {

    @Test
    void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts = new Point[]{new Point(0,0), new Point(10,10)};
        Blueprint bp = new Blueprint("testAuthor1", "testBlueprint1", pts);
        ibpp.saveBlueprint(bp);

        assertNotNull(ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        assertEquals(bp, ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
    }

    @Test
    void saveExistingBpTest() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts = new Point[]{new Point(0,0), new Point(10,10)};
        Blueprint bp = new Blueprint("testAuthor2", "testBlueprint2", pts);
        ibpp.saveBlueprint(bp);

        Point[] pts2 = new Point[]{new Point(10,10), new Point(20,20)};
        Blueprint bp2 = new Blueprint("testAuthor2", "testBlueprint2", pts2);

        assertThrows(BlueprintPersistenceException.class, () -> ibpp.saveBlueprint(bp2));
    }

    @Test
    void getNonExistingBlueprintThrowsException() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        assertThrows(BlueprintNotFoundException.class, () -> ibpp.getBlueprint("noauthor", "noname"));
    }

    @Test
    void getBlueprintsByAuthorReturnsAll() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Set<Blueprint> juanBps = ibpp.getBlueprintsByAuthor("juan");
        assertEquals(2, juanBps.size());

        Point[] pts = new Point[]{new Point(10,10), new Point(20,20)};
        Blueprint bp = new Blueprint("juan", "plano3", pts);
        ibpp.saveBlueprint(bp);

        Set<Blueprint> updatedJuanBps = ibpp.getBlueprintsByAuthor("juan");
        assertEquals(3, updatedJuanBps.size());
        assertTrue(updatedJuanBps.contains(bp));
    }

    @Test
    void getAllBlueprintsReturnsAll() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Set<Blueprint> all = ibpp.getAllBlueprints();
        assertEquals(3, all.size()); // Inicialmente 3 planos

        Blueprint bp1 = new Blueprint("testA", "bp1", new Point[]{new Point(1,1)});
        Blueprint bp2 = new Blueprint("testB", "bp2", new Point[]{new Point(2,2)});
        ibpp.saveBlueprint(bp1);
        ibpp.saveBlueprint(bp2);

        Set<Blueprint> allUpdated = ibpp.getAllBlueprints();
        assertEquals(5, allUpdated.size());
        assertTrue(allUpdated.contains(bp1));
        assertTrue(allUpdated.contains(bp2));
    }
}
