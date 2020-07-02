package DataStructures;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *<p>Linked Map structured graph representation. </p>
 * <p>Each key represents a destination of vertex which
 * has already specified as index of array of map </p>
 * <p>Each key has corresponding value which represents edge</p>
 */
public class MapGraph extends AbstractGraph implements Graph {

    /**
     * Array of map.
     */
    Map<Integer, Edge>[] outgoingEdges;

    /**
     * Initialize a MapGraph.
     * @param initialCapacity initial size of graph.
     * @param directed determine whether graph is directed or not.
     */
    public MapGraph( int initialCapacity , boolean directed ) {

        super( initialCapacity , directed );
        outgoingEdges = new Map[ getNumV() ];

        for( int i = 0 ; i < getNumV() ; ++i ) {
            outgoingEdges[i] = new LinkedHashMap<>();
        }

    }

    /**
     * Insert an edge.
     * @param edge The new edge
     * @throws Exception Edge could not contain negative properties.
     */
    @Override
    public void insertEdge(Edge edge) throws Exception {

        if( isEdge( edge.getSource() , edge.getDest() )) {
            return;
        }

        int source = edge.getSource();
        int dest = edge.getDest();
        double weight = edge.getWeight();

        outgoingEdges[source].put(dest, edge);

        if (!isDirected()) {
            Edge reverseEdge = new Edge(dest, source, weight);
            outgoingEdges[dest].put(source, reverseEdge);
        }
    }

    /**
     * Check whether MapGraph has a edge which matches
     * with given properties.
     * @param source The source vertex
     * @param dest The destination vertex
     * @return true if edge exists otherwise return false.
     */
    @Override
    public boolean isEdge(int source, int dest) {

        if( source < 0 || dest < 0 ||
                source > getNumV() || dest > getNumV() ) {
            return false;
        }

        return outgoingEdges[source].containsKey(dest);
    }

    /**
     * Return corresponding edge.
     * @param source The source vertex
     * @param dest The destination vertex
     * @return matched edge otherwise return false.
     */
    @Override
    public Edge getEdge(int source, int dest) {
        return outgoingEdges[ source ].get( dest );
    }

    /**
     * Produce an iterator with given source.
     * @param source The source vertex
     * @return iterator on success otherwise return null.
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return outgoingEdges[source].values().iterator();
    }

    /**
     * String representation of graph.
     * @return
     */
    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        for( Map<Integer , Edge> vertex : outgoingEdges ) {
            for (Edge edge : vertex.values()) {
                str.append(edge).append("\n");
            }
        }

        return str.toString();
    }
}
