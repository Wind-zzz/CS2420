package assign10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class GraphUtilityTest {
    // Test shortestWeightedPathWithPriorityQueue
    @Test
    public void testShortestPathWithPriorityQueue() {
        List<String> sources = Arrays.asList("A", "A", "B", "C");
        List<String> destinations = Arrays.asList("B", "C", "C", "D");
        List<Double> weights = Arrays.asList(1.0, 5.0, 1.0, 1.0);

        List<String> path = GraphUtility.shortestWeightedPathWithPriorityQueue(sources, destinations, weights, "A", "D");
        assertEquals(Arrays.asList("A", "B", "C", "D"), path);
    }

    @Test
    public void testNoPathWithPriorityQueue() {
        List<String> sources = Arrays.asList("A", "B");
        List<String> destinations = Arrays.asList("B", "C");
        List<Double> weights = Arrays.asList(1.0, 1.0);

        assertThrows(IllegalArgumentException.class, () ->
                GraphUtility.shortestWeightedPathWithPriorityQueue(sources, destinations, weights, "A", "D")
        );
    }

    @Test
    public void testSimpleNameGraph() {
        List<String> sources = Arrays.asList("Alice", "Bob");
        List<String> destinations = Arrays.asList("Bob", "Charlie");
        List<Double> weights = Arrays.asList(3.0, 2.0);

        List<String> result = GraphUtility.shortestWeightedPathWithPriorityQueue(
                sources, destinations, weights, "Alice", "Charlie"
        );
        assertEquals(Arrays.asList("Alice", "Bob", "Charlie"), result);
    }

    @Test
    public void testNameCycleGraph() {
        List<String> sources = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> destinations = Arrays.asList("Bob", "Charlie", "Alice");
        List<Double> weights = Arrays.asList(1.0, 1.0, 1.0);

        List<String> result = GraphUtility.shortestWeightedPathWithPriorityQueue(
                sources, destinations, weights, "Alice", "Charlie"
        );
        assertEquals(Arrays.asList("Alice", "Bob", "Charlie"), result);
    }

    @Test
    public void testDisconnectedNameGraph() {
        List<String> sources = Arrays.asList("Alice", "Charlie");
        List<String> destinations = Arrays.asList("Bob", "David");
        List<Double> weights = Arrays.asList(4.0, 5.0);

        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestWeightedPathWithPriorityQueue(
                    sources, destinations, weights, "Alice", "David"
            );
        });
    }

    @Test
    public void testNameGraphWithSelfLoop() {
        List<String> sources = Arrays.asList("Alice", "Alice");
        List<String> destinations = Arrays.asList("Alice", "Bob");
        List<Double> weights = Arrays.asList(10.0, 2.0);

        List<String> result = GraphUtility.shortestWeightedPathWithPriorityQueue(
                sources, destinations, weights, "Alice", "Bob"
        );
        assertEquals(Arrays.asList("Alice", "Bob"), result);
    }

    @Test
    public void testNameGraphMultiplePaths() {
        List<String> sources = Arrays.asList("Alice", "Alice", "Bob", "Bob", "Charlie");
        List<String> destinations = Arrays.asList("Bob", "Charlie", "Charlie", "David", "David");
        List<Double> weights = Arrays.asList(1.0, 2.0, 1.0, 2.0, 1.0);

        List<String> result = GraphUtility.shortestWeightedPathWithPriorityQueue(
                sources, destinations, weights, "Alice", "David"
        );
        assertEquals(Arrays.asList("Alice", "Bob", "David"), result);
    }

    @Test
    public void testLongerNames() {
        List<String> sources = Arrays.asList("JonathanSmith", "MichaelJohnson");
        List<String> destinations = Arrays.asList("MichaelJohnson", "ElizabethWilliams");
        List<Double> weights = Arrays.asList(3.0, 2.0);

        List<String> result = GraphUtility.shortestWeightedPathWithPriorityQueue(
                sources, destinations, weights, "JonathanSmith", "ElizabethWilliams"
        );
        assertEquals(Arrays.asList("JonathanSmith", "MichaelJohnson", "ElizabethWilliams"), result);
    }

    @Test
    public void testVeryLongNames() {
        String longName1 = "AliceTheQueenOfWonderlandAndTheChampionOfJustice";
        String longName2 = "BobTheKnightOfIntegrityAndGuardianOfTheRealm";
        String longName3 = "CharlieTheBrilliantStrategistAndSageOfTheKingdom";

        List<String> sources = Arrays.asList(longName1, longName2);
        List<String> destinations = Arrays.asList(longName2, longName3);
        List<Double> weights = Arrays.asList(5.0, 7.0);

        List<String> result = GraphUtility.shortestWeightedPathWithPriorityQueue(
                sources, destinations, weights, longName1, longName3
        );
        assertEquals(Arrays.asList(longName1, longName2, longName3), result);
    }

    //Test exception
    @Test
    public void testInvalidSourceOrDestination() {
        List<String> sources = Arrays.asList("A");
        List<String> destinations = Arrays.asList("B");
        List<Double> weights = Arrays.asList(1.0);

        // Invalid source
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestWeightedPathWithPriorityQueue(sources, destinations, weights, "X", "B");
        });

        // Invalid destination
        assertThrows(IllegalArgumentException.class, () -> {
            GraphUtility.shortestWeightedPathWithPriorityQueue(sources, destinations, weights, "A", "Y");
        });
    }

    // Edge Cases

    @Test
    public void testDisconnectedGraphWithPriorityQueue() {
        List<String> sources = Arrays.asList("A", "C");
        List<String> destinations = Arrays.asList("B", "D");
        List<Double> weights = Arrays.asList(3.0, 4.0);

        assertThrows(IllegalArgumentException.class, () ->
                GraphUtility.shortestWeightedPathWithPriorityQueue(sources, destinations, weights, "A", "D")
        );
    }
}
