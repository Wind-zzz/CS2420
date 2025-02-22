package assign07;

import java.util.*;

import static java.util.Collections.sort;

/**
 * This class defines the graph
 *
 * @author Tien Phong Le, Quang Khai Huynh
 * @version 10/24/2024
 */
public class Graph<Type> {
    public Map<Type, Vertex<Type>> vertices;

    /**
     * This constructor creates a new Graph
     */
    public Graph() {
        this.vertices = new HashMap<>();
    }

    /**
     * Add edge from a source vertex to a destination vertex.
     *
     * @param source      - value of the source vertex
     * @param destination - value of the destination vertex
     */
    public void addEdge(Type source, Type destination, double weight) {
        Vertex<Type> src = this.addVertex(source);
        Vertex<Type> dst = this.addVertex(destination);
        src.addEdge(dst, weight);
    }


    /**
     * Add and return vertex for a given value.
     *
     * @param data - data of vertex
     * @return vertex of that data
     */
    public Vertex<Type> addVertex(Type data) {
        if (!this.vertices.containsKey(data)) {
            this.vertices.put(data, new Vertex<>(data));
        }
        return this.vertices.get(data);
    }

    /**
     * This method performs the Breadth-first search algorithm
     *
     * @param src starting vertex
     * @param dst final vertex
     * @return the path
     */
    public List<Type> breadthFirstSearch(Vertex<Type> src, Vertex<Type> dst) {
        Queue<Vertex<Type>> queue = new LinkedList<>();
        Set<Vertex<Type>> visited = new HashSet<>();

        src.setDistance(0);
        visited.add(src);
        queue.offer(src);

        while (!queue.isEmpty()) {
            Vertex<Type> curr = queue.poll();
            if (curr.equals(dst)) {
                return construct(src, dst);
            }
            for (Edge<Type> edge : curr.edges()) {
                Vertex<Type> neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    neighbor.setDistance(curr.getDistance() + 1);
                    neighbor.setPrevious(curr);
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        throw new IllegalArgumentException("No path exist");
    }

    /**
     * This method performs the Dijkstra algorithm
     *
     * @param src starting vertex
     * @param dst final vertex
     * @return the path
     */
    public List<Type> dijkstra(Vertex<Type> src, Vertex<Type> dst) {
        LinkedList<Vertex<Type>> unvisited = new LinkedList<>();

        for (Vertex<Type> v : vertices.values()) {
            v.setDistance(Double.MAX_VALUE);
            unvisited.add(v);
        }

        src.setDistance(0);
        while (!unvisited.isEmpty()) {
            sort(unvisited, (o1, o2) -> Double.compare(o2.getDistance(), o1.getDistance()));
            Vertex<Type> curr = unvisited.getLast();

            if (curr.equals(dst)) {
                List<Type> path = new LinkedList<>();
                for (Vertex<Type> vert = dst; vert != null; vert = vert.getPrevious()) {
                    path.add(vert.getData());
                }
                Collections.reverse(path);
                return path;
            }

            for (Edge<Type> edge : curr.edges()) {
                Vertex<Type> neighbor = edge.getDestination();
                double temp = curr.getDistance() + edge.getWeight();
                if (temp < neighbor.getDistance()) {
                    neighbor.setDistance(temp);
                    neighbor.setPrevious(curr);
                }
            }
            unvisited.removeLast();
        }
        throw new IllegalArgumentException("No path exist");
    }

    /**
     * This method performs the topological sort
     *
     * @return the path from the source to destination
     */
    public List<Type> topologicalSort() {
        List<Type> result = new ArrayList<>();
        Queue<Vertex<Type>> queue = new LinkedList<>();

        for (Vertex<Type> v : vertices.values()) {
            if (v.getIndegree() == 0) {
                queue.offer(v);
            }
        }

        while (!queue.isEmpty()) {
            Vertex<Type> curr = queue.poll();
            result.add(curr.getData());

            for (Edge<Type> edge : curr.edges()) {
                Vertex<Type> neighbor = edge.getDestination();
                neighbor.setIndegree(neighbor.getIndegree() - 1);

                if (neighbor.getIndegree() == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (result.size() != vertices.size()) {
            throw new IllegalArgumentException("Graph contains a cycle");
        }
        return result;
    }

    /**
     * This private method constructs the path from the source to the destination
     *
     * @param src start vertex
     * @param dst final vertex
     * @return the path from source to destination
     */
    private List<Type> construct(Vertex<Type> src, Vertex<Type> dst) {
        LinkedList<Type> path = new LinkedList<>();
        Vertex<Type> curr = dst;

        if (src.equals(dst)) {
            path.add(src.getData());
            return path;
        }
        //path.addFirst(src.getData());
        while (curr != null) {
            path.addFirst(curr.getData());
            curr = curr.getPrevious();
        }
        return path;
    }
}
