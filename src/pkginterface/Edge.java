package pkginterface;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;

public class Edge {

    private Node node1;
    private Node node2;
    private final ArrayList<Point> points = new ArrayList<>();
    private int edgeNumber;

    public Edge(Node node1, Node node2, int number) {
        this.node1 = node1;
        this.node2 = node2;
        this.edgeNumber = number;
        points.add(node1.getPos());
        points.add(node2.getPos());
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

    public ArrayList<Point> getPoints() {
        this.points.set(0, node1.getPos());
        this.points.set(points.size() - 1, node2.getPos());
        return points;
    }

    public void insertPoint(Point p) {
        if (p != null && !points.contains(p)) {
            points.add(points.size() - 1, p);
        }
    }

    private int tolerance = 5;

    public boolean belongToEdge(Point p) {
        Point p1 = new Point();
        Point p2 = new Point();
        for (int i = 0; i < points.size() - 1; i++) {
            p1.setLocation(points.get(i));
            p2.setLocation(points.get(i + 1));
            int y;
            if ((p2.x - p1.x) != 0) {
                y = p1.y + ((p2.y - p1.y) / (p2.x - p1.x)) * (p.x - p1.x);
            } else {
                y = p.y;
            }

            if (y >= p.y - tolerance && y <= p.y + tolerance) {
                if (p.y <= Math.max(p1.y, p2.y) + tolerance && p.y >= Math.min(p1.y, p2.y) - tolerance
                        && p.x <= Math.max(p1.x, p2.x) + tolerance && p.x >= Math.min(p1.x, p2.x) - tolerance) {
                    //System.out.println("true");
                    /*System.out.println(p1);
                     System.out.println(p2);
                     System.out.println(p);*/
                    return true;
                }
            }

        }
        //System.out.println("false");
        return false;
    }

    public Edge splitEdge(Point p, Node newNode, int edgeNumber) {
        Point p1 = new Point();
        Point p2 = new Point();
        ArrayList<Point> toRemove = new ArrayList<>();
        Edge newEdge = new Edge(newNode, node2, edgeNumber);
        boolean remove = false;
        for (int i = 0; i < points.size() - 1; i++) {
            p1 = points.get(i);
            p2 = points.get(i + 1);
            int y;
            if ((p2.x - p1.x) != 0) {
                y = p1.y + ((p2.y - p1.y) / (p2.x - p1.x)) * (p.x - p1.x);
            } else {
                y = p.y;
            }

            if (remove) {
                toRemove.add(p1);
            }
            if (!remove && (y >= p.y - tolerance && y <= p.y + tolerance)) {
                if (p.y <= Math.max(p1.y, p2.y) + tolerance && p.y >= Math.min(p1.y, p2.y) - tolerance
                        && p.x <= Math.max(p1.x, p2.x) + tolerance && p.x >= Math.min(p1.x, p2.x) - tolerance) {

                    Point closest = getClosestPointOnSegment(p1.x, p1.y, p2.x, p2.y, p.x, p.y);

                    newNode.setPos(closest);
                    this.node2 = newNode;

                    remove = true;
                    toRemove.add(p2);
                }
            }

        }
        toRemove.add(p2);
        this.insertPoint(p);
        newEdge.insertPoint(p);
        for (Point point : toRemove) {
            points.remove(point);
            newEdge.insertPoint(point);
        }
        return newEdge;
    }

    public Point getClosestPointOnSegment(int sx1, int sy1, int sx2, int sy2, int px, int py) {
        double xDelta = sx2 - sx1;
        double yDelta = sy2 - sy1;

        if ((xDelta == 0) && (yDelta == 0)) {
            throw new IllegalArgumentException("Segment start equals segment end");
        }

        double u = ((px - sx1) * xDelta + (py - sy1) * yDelta) / (xDelta * xDelta + yDelta * yDelta);

        final Point closestPoint;
        if (u < 0) {
            closestPoint = new Point(sx1, sy1);
        } else if (u > 1) {
            closestPoint = new Point(sx2, sy2);
        } else {
            closestPoint = new Point((int) Math.round(sx1 + u * xDelta), (int) Math.round(sy1 + u * yDelta));
        }

        return closestPoint;
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
