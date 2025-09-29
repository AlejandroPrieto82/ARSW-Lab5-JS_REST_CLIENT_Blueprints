package edu.eci.arsw.blueprints.test;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.RedundancyFilter;
import edu.eci.arsw.blueprints.persistence.SubsamplingFilter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {

    @Test
    void testRedundancyFilterRemovesConsecutiveDuplicates() {
        Point[] pts = new Point[]{
                new Point(10, 10),
                new Point(10, 10),
                new Point(20, 20),
                new Point(20, 20),
                new Point(30, 30)
        };
        Blueprint bp = new Blueprint("juan", "test1", pts);

        RedundancyFilter filter = new RedundancyFilter();
        Blueprint filtered = filter.filter(bp);

        assertEquals(3, filtered.getPoints().size());
        assertPointEquals(10, 10, filtered.getPoints().get(0));
        assertPointEquals(20, 20, filtered.getPoints().get(1));
        assertPointEquals(30, 30, filtered.getPoints().get(2));
    }

    @Test
    void testSubsamplingFilterKeepsEveryOtherPoint() {
        Point[] pts = new Point[]{
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4)
        };
        Blueprint bp = new Blueprint("ana", "test2", pts);

        SubsamplingFilter filter = new SubsamplingFilter();
        Blueprint filtered = filter.filter(bp);

        assertEquals(3, filtered.getPoints().size());
        assertPointEquals(0, 0, filtered.getPoints().get(0));
        assertPointEquals(2, 2, filtered.getPoints().get(1));
        assertPointEquals(4, 4, filtered.getPoints().get(2));
    }

    private void assertPointEquals(int expectedX, int expectedY, Point actual) {
        assertEquals(expectedX, actual.getX());
        assertEquals(expectedY, actual.getY());
    }
}
