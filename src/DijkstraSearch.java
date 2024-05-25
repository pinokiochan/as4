import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Double> distTo;
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private PriorityQueue<VertexDist<V>> pq;
    private Vertex<V> startVertex;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        pq = new PriorityQueue<>(Comparator.comparingDouble(VertexDist::getDist));
        startVertex = graph.getVertex(start);
        for (Vertex<V> v : graph.getVertices().values()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(startVertex, 0.0);
        pq.add(new VertexDist<>(startVertex, 0.0));
        while (!pq.isEmpty()) {
            Vertex<V> v = pq.poll().getVertex();
            for (Map.Entry<Vertex<V>, Double> entry : v.getAdjacentVertices().entrySet()) {
                relax(v, entry.getKey(), entry.getValue());
            }
        }
    }

    private void relax(Vertex<V> v, Vertex<V> w, double weight) {
        double newDist = distTo.get(v) + weight;
        if (newDist < distTo.get(w)) {
            distTo.put(w, newDist);
            edgeTo.put(w, v);
            pq.add(new VertexDist<>(w, newDist));
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        Vertex<V> target = new Vertex<>(vertex);
        return distTo.containsKey(target) && distTo.get(target) < Double.POSITIVE_INFINITY;
    }

    @Override
    public List<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) return null;
        List<V> path = new LinkedList<>();
        for (Vertex<V> v = new Vertex<>(vertex); v != startVertex; v = edgeTo.get(v)) {
            path.add(v.getData());
        }
        path.add(startVertex.getData());
        Collections.reverse(path);
        return path;
    }

    private static class VertexDist<V> {
        private Vertex<V> vertex;
        private double dist;

        public VertexDist(Vertex<V> vertex, double dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        public Vertex<V> getVertex() {
            return vertex;
        }

        public double getDist() {
            return dist;
        }
    }
}
