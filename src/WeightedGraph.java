import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    public WeightedGraph(){
        this.vertices = new HashMap<>();
    }
    public void addVertex(V data){
        vertices.put(data, new Vertex<>(data));
    }
    public void addEdge(V source, V dest, double weight) {
        Vertex<V> srcVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(dest);
        if (srcVertex != null && destVertex != null) {
            srcVertex.addAdjacentVertex(destVertex, weight);
        }
    }
    public Vertex<V> getVertex(V data){
        return vertices.get(data);
    }
    public Map<V, Vertex<V>> getVertices(){
        return vertices;
    }

}