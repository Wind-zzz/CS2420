package assign07;

/**
 * This class defines the edge of the graph
 *
 * @author Tien Phong Le, Quang Khai Huynh
 * @version 10/24/2024
 */
public class Edge<Type> {
    private Vertex<Type> dst;
    private double weight;

    /**
     * This constructor creates an Edge
     *
     * @param dst destination of the edge
     */
    public Edge(Vertex<Type> dst, double weight) {
        this.dst = dst;
        this.weight = weight;
    }

    /**
     * This method set the destination for an edge
     *
     * @return the destination of the edge
     */
    public Vertex<Type> getDestination() {
        return this.dst;
    }

    /**
     * This method get the weight for an edge
     *
     * @return the weight of the edge
     */
    public double getWeight() {
        return this.weight;
    }
}
