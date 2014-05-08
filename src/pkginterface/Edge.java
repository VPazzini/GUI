package pkginterface;

import java.util.Objects;

public class Edge {
    private final Node node1;
    private final Node node2;
    private int edgeNumber;

    public Edge(Node node1, Node node2, int number) {
        this.node1 = node1;
        this.node2 = node2;
        this.edgeNumber = number;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public int getEdgeNumber() {
        return edgeNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (!this.node1.equals(other.node1)) {
            return false;
        }
        if (!this.node2.equals(other.node2)) {
            return false;
        }
        return true;
    }
    
    
}
