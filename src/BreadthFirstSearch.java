import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Set<Vertex<V>> visited;
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private Vertex<V> startVertex;

    public BreadthFirstSearch(WeightedGraph<V> graph, V start) {
        visited = new HashSet<>();
        edgeTo = new HashMap<>();
        startVertex = graph.getVertex(start);
        bfs(startVertex);
    }

    public BreadthFirstSearch(MyGraph<V> graph, V start) {
        visited = new HashSet<>();
        edgeTo = new HashMap<>();
        startVertex = graph.getVertex(start);
        bfs(startVertex);
    }

    private void bfs(Vertex<V> source) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);
        while (!queue.isEmpty()) {
            Vertex<V> v = queue.poll();
            for (Vertex<V> w : v.getAdjacentVertices().keySet()) {
                if (!visited.contains(w)) {
                    visited.add(w);
                    edgeTo.put(w, v);
                    queue.add(w);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        Vertex<V> target = new Vertex<>(vertex);
        return visited.contains(target);
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
}
