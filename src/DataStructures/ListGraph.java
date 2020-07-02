package DataStructures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** A ListGraph is an extension of the AbstractGraph abstract class
 that uses an array of lists to represent the edges.
 */
public class ListGraph extends AbstractGraph {

    // Data Field

    /** An array of Lists to contain the edges that
     originate with each vertex.
     */
    private List<Edge>[] edges;

    /** Construct a graph with the specified number of vertices and directionality.
     @param numV The number of vertices
     @param directed The directionality flag
     */
    @SuppressWarnings("unchecked")
    public ListGraph( int numV , boolean directed ) {

        super(numV , directed);

        edges = new List[ numV ];
        for( int i = 0 ; i < edges.length ; ++i ) {
            edges[i] = new LinkedList<>();
        }

    }

    /** Insert a new edge into the graph.
     * @param edge The new edge
     * <p>For inserting an edge to undirected graph , use INF for weight </p>
     */
    @Override
    public void insertEdge(Edge edge) throws Exception {

        edges[ edge.getSource() ].add( edge );
        if( !isDirected() ) {
            edges[ edge.getDest() ].add( new Edge( edge.getDest()
                    , edge.getSource() , edge.getWeight()));
        }
    }

    /** Determine whether an edge exists.
     @param source The source vertex
     @param dest The destination vertex
     @return true if there is an edge from source to dest
     */
    @Override
    public boolean isEdge(int source, int dest) throws Exception {
        return  edges[source].contains( new Edge( source , dest ));
    }

    /** Get the edge between two vertices.
     @param source The source
     @param dest The destination
     @return the edge between these two vertices
     or null if an edge does not exist.
     */
    @Override
    public Edge getEdge(int source, int dest) throws Exception {

        Edge target = new Edge(source, dest, Double.POSITIVE_INFINITY);

        for ( Edge edge: edges[source] ) {
            if (edge.equals(target))
                return edge;
            // Desired edge found, return it.
        }

        // Assert: All edges for source checked.
        return null; // Desired edge not found.

    }

    /**
     * Iterator that can be used to iterate
     * through the edges adjacent to a given vertex.
     * @param source The source vertex
     * @return Iterator
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return edges[source].iterator();
    }


    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();

        for( int i = 0 ; i < getNumV() ; ++i ) {
            str.append(i).append(". -> ").append( edges[i] ).append("\n");
        }

        return str.toString();
    }
}
