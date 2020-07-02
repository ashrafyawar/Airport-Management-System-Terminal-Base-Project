package DataStructures;

import Airline.Destination;

import java.util.Objects;

public class Edge {

    private int dest;
    private int source;

    private double weight;

    public Edge(int source , int dest ) throws Exception {

        if( source < 0 || dest < 0) {
            throw new Exception("Source or destination cannot be represented with negative number.");
        }
        this.source = source;
        this.dest = dest;
        this.weight = Double.POSITIVE_INFINITY;

    }

    public Edge( int source , int dest, double w) throws Exception {
        this(source , dest);
        this.weight = w;
    }

    public double getWeight() {
        return weight;
    }

    public int getDest() {
        return dest;
    }

    public int getSource() {
        return source;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dest, source);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return dest == edge.dest &&
                source == edge.source;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return " ( source : " + source + ", dest : " + dest + " )";
    }
}
