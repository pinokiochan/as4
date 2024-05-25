import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private Set<Vertex<V>> visited;
    private Map<Vertex<V>, Vertex<V>> edgeTo;
    private Vertex<V> startVertex;

    public DepthFirstSearch(MyGraph<V> graph, V start) {
        visited = new HashSet<>();
        edgeTo = new HashMap<>();
        startVertex = graph.getVertex(start);
        dfs(startVertex);
    }

    private void dfs(Vertex<V> v) {
        visited.add(v);
        for (Vertex<V> w : v.getAdjacentVertices().keySet()) {
            if (!visited.contains(w)) {
                edgeTo.put(w, v);
                dfs(w);
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
