package assign07;

import java.util.LinkedList;
import java.util.List;

/**
 * This class defines the vertex of the graph
 *
 * @author Tien Phong Le, Quang Khai Huynh
 * @version 10/24/2024
 */
public class Vertex<Type> {

    private Type data;
    private LinkedList<Edge<Type>> edges;
    private int indegree;
    private double dist;
    private Vertex<Type> prev;

    /**
     * This constructor creates a vertex
     *
     * @param data data in a vertex
     */
    public Vertex(Type data) {
        this.data = data;
        this.edges = new LinkedList<>();
        this.indegree = 0;
        this.dist = 0;
        this.prev = null;
    }

    /**
     * This method gets the data from the vertex
     *
     * @return data
     */
    public Type getData() {
        return data;
    }

    /**
     * This method gets the indegree of the vertex
     *
     * @return indegree
     */
    public int getIndegree() {
        return indegree;
    }

    /**
     * This method gets the distance of the vertex to another
     *
     * @return distance to other
     */
    public double getDistance() {
        return dist;
    }

    /**
     * This method gets the previous vertex
     *
     * @return previous vertex
     */
    public Vertex<Type> getPrevious() {
        return this.prev;
    }

    /**
     * This method sets the indegree of the vertex
     *
     * @param indegree indegree of the vertex
     */
    public void setIndegree(int indegree) {
        this.indegree = indegree;
    }

    /**
     * This method sets the distance
     *
     * @param dist distance to other
     */
    public void setDistance(double dist) {
        this.dist = dist;
    }

    /**
     * This method sets the previous one of the vertex
     *
     * @param prev previous vertex
     */
    public void setPrevious(Vertex<Type> prev) {
        this.prev = prev;
    }

    /**
     * Add an edge from this vertex to a destination vertex.
     *
     * @param destination - destination vertex of edge
     */
    public void addEdge(Vertex<Type> destination, double weight) {
        this.edges.add(0, new Edge<>(destination, weight));
        destination.indegree++;
        destination.dist = this.dist + weight;
    }

    /**
     * This method returns the list of edges from this vertex
     *
     * @return List of edges connected to the vertex
     */
    public List<Edge<Type>> edges() {
        return this.edges;
    }
}

