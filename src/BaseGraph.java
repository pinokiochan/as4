import java.util.HashMap;
import java.util.Map;

public abstract class BaseGraph<V> {
    protected Map<V, Vertex<V>> vertices;

    public BaseGraph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        vertices.put(data, new Vertex<>(data));
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }

    public abstract void addEdge(V source, V dest, double weight);
}