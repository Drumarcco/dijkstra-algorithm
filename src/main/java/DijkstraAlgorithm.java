import java.util.*;

/**
 * Created by marco on 25/10/16.
 */
public class DijkstraAlgorithm {
    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> visitedNodes;
    private Set<Vertex> nonVisitedNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        this.nodes = graph.getVertices();
        this.edges = graph.getEdges();
    }

    public void run(Vertex originNode) {
        visitedNodes = new HashSet<Vertex>();
        nonVisitedNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(originNode, 0);
        nonVisitedNodes.add(originNode);
        while (nonVisitedNodes.size() > 0) {
            Vertex node = getMinimumFromNonVisited(nonVisitedNodes);
            visitedNodes.add(node);
            nonVisitedNodes.remove(node);
            findMinimumDistances(node);
        }

    }

    private Vertex getMinimumFromNonVisited(Set<Vertex> vertices) {
        Vertex minimum = null;
        for (Vertex vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getCurrentShortestDistance(vertex) < getCurrentShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private void findMinimumDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex destination : adjacentNodes) {
            int currentShortestDistance = getCurrentShortestDistance(destination);
            int possibleShortestDistance = getCurrentShortestDistance(node) + getDistance(node, destination);
            if (currentShortestDistance > possibleShortestDistance) {
                distance.put(destination, possibleShortestDistance);
                predecessors.put(destination, node);
                nonVisitedNodes.add(destination);
            }
        }
    }

    private int getCurrentShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private int getDistance(Vertex node, Vertex destination) {
        for (Edge edge : edges) {
            if (edge.getOrigin().equals(node) && edge.getDestination().equals(destination)){
                return edge.getWeight();
            }
        }
        throw new RuntimeException("All nodes should be connected.");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getOrigin().equals(node) && !isVisited(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private boolean isVisited(Vertex destination) {
        return visitedNodes.contains(destination);
    }


    public LinkedList<Vertex> getPath(Vertex destination) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = destination;

        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }
}
