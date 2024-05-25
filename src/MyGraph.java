import java.util.HashMap;
import java.util.Map;

public class MyGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean directed;

    public MyGraph(boolean directed) {
        this.vertices = new HashMap<>();
        this.directed = directed;
    }

    public void addVertex(V data) {
        vertices.put(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest) {
        addEdge(source, dest, 1.0);
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> srcVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(dest);
        if (srcVertex == null) {
            srcVertex = new Vertex<>(source);
            vertices.put(source, srcVertex);
        }
        if (destVertex == null) {
            destVertex = new Vertex<>(dest);
            vertices.put(dest, destVertex);
        }
        srcVertex.addAdjacentVertex(destVertex, weight);
        if (!directed) {
            destVertex.addAdjacentVertex(srcVertex, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }
}
