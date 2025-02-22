package assign07;

import java.util.List;

/**
 * This class provides utility methods for graph-related operations, including shortest path, shortest weighted path, and topological sort.
 *
 * @author Tien Phong Le, Quang Khai Huynh
 * @version 10/24/2024
 */
public class GraphUtility<Type> {

    /**
     * This method finds the shortest path (smallest number of edges) from the source vertex to the destination vertex
     * using the breadth-first search (BFS) algorithm.
     *
     * @param sources      a list of source vertices, representing the start of directed edges.
     * @param destinations a list of destination vertices, representing the end of directed edges.
     * @param srcData      the data of the source vertex to start the search from.
     * @param dstData      the data of the destination vertex to find the path to.
     * @return a list of vertices in the shortest path from the source to the destination.
     * @throws IllegalArgumentException if the number of elements in the sources and destinations lists are unequal.
     * @throws IllegalArgumentException if there does not exist a vertex with srcData or dstData in the graph.
     * @throws IllegalArgumentException if there is no path between the source and destination vertices.
     */
    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
        if (sources.size() != destinations.size() || sources.isEmpty() || destinations.isEmpty()) {
            throw new IllegalArgumentException("The two lists do not have the same size and one of them has no vertex");
        }
        if (sources.contains(null) || destinations.contains(null)) {
            throw new IllegalArgumentException("The lists cannot contain null elements");
        }
        if(!sources.contains(srcData) || !destinations.contains(dstData)) {
            throw new IllegalArgumentException("The source and destination lists do not contain their own elements");
        }

        Graph<Type> graph = new Graph<>();

        graph = createUnweightedGraph(sources, destinations);

        Vertex<Type> src = graph.vertices.get(srcData);
        Vertex<Type> dst = graph.vertices.get(dstData);

        return graph.breadthFirstSearch(src, dst);
    }

    /**
     * This method finds the shortest weighted path (smallest total weight on edges) from the source vertex to the
     * destination vertex using Dijkstra's algorithm. This method constructs a graph from the provided
     * lists of sources, destinations, and weights.
     *
     * @param sources        a list of source vertices, representing the start of directed edges.
     * @param destinations   a list of destination vertices, representing the end of directed edges.
     * @param weights        a list of weights associated with each directed edge from source to destination.
     * @param srcData        the data of the source vertex to start the search from.
     * @param dstData        the data of the destination vertex to find the path to.
     * @return               a list of vertices representing the shortest weighted path from the source to the destination.
     * @throws IllegalArgumentException if the sizes of the sources, destinations, and weights lists do not match.
     * @throws IllegalArgumentException if the sources or destinations lists are empty or contain null elements.
     * @throws IllegalArgumentException if the source or destination vertex is not found in the graph.
     * @throws IllegalArgumentException if the source data is not in the sources list or the destination data is not in the destinations list.
     */
    public static <Type> List<Type> shortestWeightedPath(List<Type> sources, List<Type> destinations, List<Double> weights, Type srcData, Type dstData) {
        if (sources.size() != destinations.size() || sources.isEmpty() || destinations.isEmpty()) {
            throw new IllegalArgumentException("The two lists do not have the same size and one of them has no vertex");
        }

        if(weights.size() != destinations.size() ) {
            throw new IllegalArgumentException("The two lists do not have the same size");
        }

        if (sources.contains(null) || destinations.contains(null)) {
            throw new IllegalArgumentException("The lists cannot contain null elements");
        }

        if(!sources.contains(srcData) || !destinations.contains(dstData)) {
            throw new IllegalArgumentException("The source and destination lists do not contain their own elements");
        }

        Graph<Type> graph = createGraph(sources, destinations, weights);
        if (!graph.vertices.containsKey(srcData) || !graph.vertices.containsKey(dstData)) {
            throw new IllegalArgumentException("Source or destination data not found");
        }

        Vertex<Type> src = graph.vertices.get(srcData);
        Vertex<Type> dst = graph.vertices.get(dstData);

        return graph.dijkstra(src, dst);
    }

    /**
     * This generates a topologically sorted ordering of the vertices in the graph using the topological sort
     * algorithm. This method constructs a graph from the provided lists of sources and destinations.
     *
     * @param sources        a list of source vertices, representing the start of directed edges.
     * @param destinations   a list of destination vertices, representing the end of directed edges.
     * @return               a list of vertices in a valid topological order.
     * @throws IllegalArgumentException if the sizes of the sources and destinations lists do not match.
     * @throws IllegalArgumentException if the sources or destinations lists are empty or contain null elements.
     * @throws IllegalArgumentException if the graph contains a cycle, making topological sorting impossible.
     */
    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
        if (sources.size() != destinations.size() || sources.isEmpty() || destinations.isEmpty())
            throw new IllegalArgumentException("The sources and the destination size does not match");
        if(sources.contains(null) || destinations.contains(null))
            throw new IllegalArgumentException("The lists cannot contain null elements");

        Graph<Type> graph = createUnweightedGraph(sources, destinations);
        return graph.topologicalSort();
    }

    /**
     * Creates unweighted graph with list of sources and destinations
     *
     * @param src list of sources
     * @param dst list of destinations
     * @return new graph
     */
    private static <Type> Graph<Type> createUnweightedGraph(List<Type> src, List<Type> dst) {
        Graph<Type> graph = new Graph<>();
        for (int i = 0; i < src.size(); i++) {
            graph.addEdge(src.get(i), dst.get(i), 1);
        }
        return graph;
    }

    /**
     * Creates weighted graph with list of sources, destinations and weights
     *
     * @param src     list of sources
     * @param dst     list of destinations
     * @param weights list of weights
     * @return new graph
     */
    private static <Type> Graph<Type> createGraph(List<Type> src, List<Type> dst, List<Double> weights) {
        Graph<Type> graph = new Graph<>();
        for (int j = 0; j < src.size(); j++) {
            graph.addEdge(src.get(j), dst.get(j), weights.get(j));
        }
        return graph;
    }

}
