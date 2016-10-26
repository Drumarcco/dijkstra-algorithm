/**
 * Created by marco on 25/10/16.
 */
public class Edge {
    private final String id;
    private final Vertex origin;
    private final Vertex destination;
    private final int weight;


    public Edge(String id, Vertex origin, Vertex destination, int weight) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public Vertex getOrigin() {
        return origin;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return origin + " " + destination;
    }
}
