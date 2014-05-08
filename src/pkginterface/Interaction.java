package pkginterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class Interaction extends JPanel implements ActionListener {

    private Graphics2D g2d;
    private final ArrayList<Node> nodes = new ArrayList<>();
    private final ArrayList<Edge> edges = new ArrayList<>();
    private boolean moving = false;
    private Node movingNode = null;
    private Node selectedNode = null;
    private final int nodeSize = 20;
    private final MainWindow jFrame;
    private int nodeNumber = 1;

    public Interaction(MainWindow jFrame) {
        this.setSize(500, 600);
        setLayout(new BorderLayout());
        this.jFrame = jFrame;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        for (Node n : nodes) {
            if (n.equals(selectedNode)) {
                g2d.setColor(Color.red);
            }
            g2d.drawOval(n.getPos().x - nodeSize / 2, n.getPos().y - nodeSize / 2, nodeSize, nodeSize);
            g2d.setColor(Color.black);
        }
        for (Edge e : edges) {
            g2d.drawLine(e.getNode1().getPos().x,
                    e.getNode1().getPos().y,
                    e.getNode2().getPos().x,
                    e.getNode2().getPos().y);

            double dist = e.getNode1().getPos().distance(e.getNode2().getPos());
            double xDist = Math.abs(e.getNode1().getPos().x - e.getNode2().getPos().x);
            double yDist = Math.abs(e.getNode1().getPos().y - e.getNode2().getPos().y);
            double cos = Math.toDegrees(Math.acos(xDist / dist));

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);

            if (moving || jFrame.isShowNumbers()) {
                Point strPoint = new Point();
                strPoint.setLocation(
                        Math.min(e.getNode1().getPos().x, e.getNode2().getPos().x) + xDist / 2,
                        Math.min(e.getNode1().getPos().y, e.getNode2().getPos().y) + yDist / 2);

                g2d.drawString(df.format(dist) + "(" + df.format(cos) + "º)", strPoint.x - 5, strPoint.y - 5);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    public void newNode(Point p) {
        if (!moving) {

            Node cSelected = selectedNode;

            Node temp = this.getNode(p);
            if (temp != null) {
                if (temp.equals(cSelected)) {
                    this.selectedNode = null;
                } else {
                    if (cSelected != null) {
                        this.newEdge(cSelected, temp);
                        selectedNode = null;
                    } else {
                        this.selectedNode = temp;
                    }
                }
                repaint();
                return;
            }

            //p.setLocation(p.x - nodeSize / 2, p.y - nodeSize / 2);
            this.nodes.add(new Node(p, nodeNumber++));
            this.repaint();
        } else {
            moving = false;
            movingNode = null;
            repaint();
        }
    }

    public void moveNode(Point p) {
        if (moving) {
            Point temp = new Point();
            //temp.setLocation(p.x - nodeSize / 2, p.y - nodeSize / 2);
            temp.setLocation(p.x, p.y);
            movingNode.setPos(temp);
        } else {
            for (Node n : nodes) {
                if (isInside(n, p)) {
                    this.moving = true;
                    Point temp = new Point();
                    //temp.setLocation(p.x - nodeSize / 2, p.y - nodeSize / 2);
                    temp.setLocation(p.x, p.y);
                    movingNode = n;
                    n.setPos(temp);
                    break;
                }
            }
        }
        repaint();
    }

    public void showRest(Point p) {
        Node temp = this.getNode(p);

        if (temp == null) {
            return;
        }

        JDialog rest = new JDialog();
        rest.setSize(224, 131);
        rest.setModal(true);
        rest.setResizable(false);
        rest.setLocationRelativeTo(null);
        rest.setTitle("Node " + temp.getNumber());
        Restraints r = new Restraints(rest);
        rest.add(r);

        rest.setVisible(true);

        temp.setX(r.isX());
        temp.setY(r.isY());
        temp.setZ(r.isZ());
        temp.setRx(r.isRX());
        temp.setRy(r.isRY());
        temp.setRz(r.isRZ());

    }

    private Node getNode(Point p) {
        for (Node n : nodes) {
            if (isInside(n, p)) {
                return n;
            }
        }
        return null;
    }

    private boolean isInside(Node n, Point p) {
        Point temp = (Point) n.getPos().clone();
        //temp.setLocation(temp.x + nodeSize / 2, temp.y + nodeSize / 2);

        if (p.distance(temp) <= nodeSize / 2) {
            return true;
        }

        return false;
    }

    private void newEdge(Node n1, Node n2) {
        Edge newEdge = new Edge(n1, n2);
        for (Edge e : edges) {
            if (e.equals(newEdge)) {
                return;
            }
        }
        edges.add(newEdge);
    }

    public void delete() {
        if (selectedNode != null) {
            this.nodes.remove(selectedNode);

            ArrayList<Edge> toDelete = new ArrayList<>();
            for (Edge e : edges) {
                if (e.getNode1().equals(selectedNode)) {
                    toDelete.add(e);
                }
                if (e.getNode2().equals(selectedNode)) {
                    toDelete.add(e);
                }
            }

            for (Edge e : toDelete) {
                edges.remove(e);
            }
            this.selectedNode = null;
        }
        repaint();
    }

    public void generateFiles() {
        File file = new File("example.txt");
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (Node n : nodes) {
                String line;
                line = n.getNumber() + " "
                        + (n.isX() ? "1" : "0") + " "
                        + (n.isY() ? "1" : "0") + " "
                        + (n.isZ() ? "1" : "0") + " "
                        + (n.isRx() ? "1" : "0") + " "
                        + (n.isRy() ? "1" : "0") + " "
                        + (n.isRz() ? "1" : "0") + " "
                        + n.getPos().x + " "
                        + n.getPos().y + " 0.0\n";
                output.write(line);
                //System.out.println(line);
            }
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
