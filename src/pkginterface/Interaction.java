package pkginterface;

import elements.Edge;
import elements.Node;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Path2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import windows.Restraints;

public class Interaction extends JPanel implements ActionListener {

    private static Interaction inter = new Interaction();

    private Graphics2D g2d;
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    private boolean moving = false;
    private Node selectedNode = null;
    private final int nodeSize = 20;
    private MainWindow jFrame;
    private int nodeNumber = 1;
    private int edgeNumber = 1;
    private boolean shiftHold = false;
    private String nodeFile = "node_file.dat";
    private String elemFile = "elem_file.dat";
    private String inputFile = "input.dat";
    private String confFile = "conf_file.dat";

    public Interaction() {
        this.setSize(500, 600);

        setLayout(new BorderLayout());
        //this.jFrame = jFrame;

        this.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseReleased(MouseEvent evt) {
                        if (moving) {
                            moving = false;
                            selectedNode = null;
                        }
                        repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent evt) {

                        if (evt.getButton() == MouseEvent.BUTTON1) {

                            Node temp = getNode(evt.getPoint());

                            if (jFrame.isNewNode()) {
                                for (Edge e : edges) {
                                    if (e.belongToEdge(evt.getPoint())) {
                                        splitEdge(evt.getPoint());
                                        return;
                                    }
                                }
                                if (temp == null) {
                                    newNode(evt.getPoint());
                                }
                            }

                            if (jFrame.isNewEdge()) {
                                if (temp != null) {
                                    if (temp.equals(selectedNode)) {
                                        selectedNode = null;
                                    } else {
                                        if (selectedNode != null) {
                                            newEdge(temp, selectedNode);
                                            if (!shiftHold) {
                                                selectedNode = null;
                                            }
                                        } else {
                                            selectedNode = temp;
                                        }
                                    }
                                } else {
                                    selectedNode = null;
                                }
                                return;
                            }

                            if (temp != null) {
                                if (temp.equals(selectedNode)) {
                                    selectedNode = null;
                                } else {
                                    selectedNode = temp;
                                }
                            }

                        }
                        if (evt.getButton() == MouseEvent.BUTTON3) {
                            showRest(evt.getPoint());
                        }
                        repaint();
                    }
                });

        this.addMouseMotionListener(
                new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent evt) {
                        moving = true;
                        if (selectedNode != null) {
                            selectedNode.setPos(evt.getPoint());
                            repaint();
                        }
                        repaint();
                    }
                });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        Rectangle r = this.getBounds();
        int h = r.height - 30;
        int w = r.width - 10;

        //Drawing the coordinates symbol
        g2d.drawLine(30, h, 55, h); //X
        g2d.drawLine(55, h, 49, h - 3);
        g2d.drawLine(55, h, 49, h + 3);

        g2d.drawLine(30, h, 30, h - 25); //Y
        g2d.drawLine(30, h - 25, 33, h - 19);
        g2d.drawLine(30, h - 25, 27, h - 19);

        g2d.drawLine(30, h, 20, h + 10); //Z
        g2d.drawLine(20, h + 10, 23, h + 4);
        g2d.drawLine(20, h + 10, 25, h + 8);
        //Drawing the coordinates symbol

        for (Node n : nodes) {
            if (n.equals(selectedNode)) {
                g2d.setColor(Color.red);
            }
            g2d.drawOval(n.getPos().x - nodeSize / 2, n.getPos().y - nodeSize / 2, nodeSize, nodeSize);
            g2d.setColor(Color.black);
        }

        for (Edge e : edges) {
            for (int i = 0; i < e.getPoints().size() - 1; i++) {
                Point p1 = e.getPoints().get(i);
                Point p2 = e.getPoints().get(i + 1);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }

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

    public void drawLine(int length, int numNodes) {
        int ix = 100;
        int iy = 50;
        double elem = (length / (numNodes - 1));
        Node n1 = new Node(new Point(ix, iy), nodeNumber++);
        Node n2 = new Node(new Point(ix+length, iy), nodeNumber++);
        newEdge(n1, n2);
        nodes.add(n1);
        nodes.add(n2);
        ArrayList<Point> splitPoints = new ArrayList<>();
        for (int i = 0; i < numNodes - 2; i++) {
            double x = (ix + (i + 1) * elem);

            Point p = new Point((int) x, iy);
            /*n2 = new Node(p, nodeNumber++);
            nodes.add(n2);
            newEdge(n1, n2);
            n1 = n2;*/
            this.splitEdge(p);
        }
        this.repaint();
    }

    public void drawUbend(int length, int width, int radius, int numNodes) {
        Path2D.Double path = new Path2D.Double();
        int ix = 100;
        int iy = 50;
        path.moveTo(ix, iy);
        path.lineTo(ix + length, iy);
        path.curveTo(ix + length + radius, iy, ix + length + radius,
                iy + width, ix + length, iy + width);
        path.lineTo(ix, iy + width);
        FlatteningPathIterator f = new FlatteningPathIterator(
                path.getPathIterator(new AffineTransform()), 1);

        Node n1 = new Node(new Point(100, 50), nodeNumber++);
        Node n2 = new Node(new Point(100, iy + width), nodeNumber++);
        nodes.add(n1);
        nodes.add(n2);
        Edge edge = new Edge(n1, n2, edgeNumber++);
        edges.add(edge);

        float[] coords = new float[6];
        while (!f.isDone()) {
            f.currentSegment(coords);
            int x = (int) coords[0];
            int y = (int) coords[1];
            edge.insertPoint(new Point(x, y));
            f.next();
        }

        ArrayList<Point> splitPoints = new ArrayList<>();
        double totalLength = edge.getLength();
        double elem = totalLength / (numNodes - 1);
        totalLength = (numNodes - 1) * elem;
        double distance = 0;
        for (int i = 0; i < edge.getPoints().size() - 1; i++) {
            Point p1 = edge.getPoints().get(i);
            Point p2 = edge.getPoints().get(i + 1);
            distance += p1.distance(p2);

            while ((distance - 1) > elem) {
                int dist = (int) (elem - (distance - p1.distance(p2)));
                Point split = interpolationByDistance(p1, p2, dist);
                distance = distance - elem;
                splitPoints.add(split);

            }

        }

        for (Point sPoint : splitPoints) {
            this.splitEdge(sPoint);
        }

        this.repaint();
    }

    public Point interpolationByDistance(Point p1, Point p2, double d) {
        double len = p1.distance(p2);
        double ratio = d / len;
        int x = (int) (ratio * p2.x + (1.0 - ratio) * p1.x);
        int y = (int) (ratio * p2.y + (1.0 - ratio) * p1.y);
        //System.out.println(x + ", " + y);
        return (new Point(x, y));
    }

    private void splitEdge(Point p) {
        Edge newEdge = null;
        for (Edge e : edges) {
            if (e.belongToEdge(p)) {
                Node n = new Node(p, nodeNumber++);
                nodes.add(n);
                newEdge = e.splitEdge(p, n, edgeNumber++);
                break;
            }
        }
        if (newEdge != null) {
            edges.add(newEdge);
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    public void newNode(Point p) {
        this.nodes.add(new Node(p, nodeNumber++));
        repaint();
    }

    public void showRest(Point p) {
        Node temp = this.getNode(p);

        if (temp == null) {
            return;
        }

        JDialog rest = new JDialog();
        rest.setSize(250, 350);
        rest.setModal(true);
        rest.setResizable(false);
        rest.setLocationRelativeTo(null);
        rest.setTitle("Node " + temp.getNumber());
        Restraints r = new Restraints(rest, temp.getRest(), temp.getForces());
        rest.add(r);

        rest.setVisible(true);

        temp.setRest(r.getRest());

        temp.setForces(r.getForces());
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

        if (p.distance(temp) <= (nodeSize / 1.6)) {
            return true;
        }

        return false;
    }

    private void newEdge(Node n1, Node n2) {
        Edge newEdge1 = new Edge(n1, n2, edgeNumber);
        Edge newEdge2 = new Edge(n2, n1, edgeNumber);
        for (Edge e : edges) {
            if (e.equals(newEdge1) || e.equals(newEdge2)) {
                edges.remove(e);
                return;
            }
        }
        edges.add(newEdge1);
        edgeNumber++;
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

    public void shiftPressed() {
        shiftHold = true;
    }

    public void shiftReleased() {
        shiftHold = false;
    }

    public void generateFiles() {
        this.generateNodeFile();
        this.generateElemFile();
        this.generateInputFile();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    private void generateNodeFile() {
        if (nodes.isEmpty()) {
            return;
        }
        double lowerX = nodes.get(0).getPos().x;
        double lowerY = nodes.get(0).getPos().y;

        for (Node n : nodes) {
            if (n.getPos().x < lowerX) {
                lowerX = n.getPos().getX();
            }
            if (n.getPos().y < lowerY) {
                lowerY = n.getPos().getY();
            }
        }

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);

        File file = new File(this.nodeFile);
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
                        + df.format(n.getPos().getX() - lowerX) + " "
                        + df.format(n.getPos().getY() - lowerY) + " 0.0\n";
                output.write(line);
            }
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateElemFile() {
        File file = new File(this.elemFile);
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (Edge e : edges) {
                String line;
                line = e.getEdgeNumber() + " "
                        + e.getNode1().getNumber() + " "
                        + e.getNode2().getNumber() + "\n";
                output.write(line);
            }
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateInputFile() {
        File file = new File(this.inputFile);
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            String line;

            output.write("&TIMING\n");
            line = "TSTART=" + ((Float) jFrame.getjSpinnerTStart().getValue())
                    + ", TSTOP=" + ((Float) jFrame.getjSpinnerTStop().getValue())
                    + ", DELTAT=" + jFrame.getjTextDeltaT().getText()
                    + "\n/\n";
            output.write(line);

            output.write("&NODELIST\n");
            line = "NNP=" + edges.size() + ", nodefile='" + this.nodeFile + "',"
                    + "NELG=" + jFrame.getjTextNELG().getText()
                    + ", NMAT=" + jFrame.getjTextNMAT().getText()
                    + ", elemfile='" + this.elemFile + "'"
                    + ", conffile='" + confFile + "'"
                    + ", d_o=" + jFrame.getjTextDO().getText()
                    + ", d_i=" + jFrame.getjTextDI().getText() + "\n/\n";
            output.write(line);

            output.write("&MATERIAL01\n");
            line = "TEMOD=" + jFrame.getjTextTEMOD().getText()
                    + ", TDENS=" + jFrame.getjTextTDENS().getText()
                    + ", TPOI=" + jFrame.getjTextTIPOI().getText() + "\n/\n";
            output.write(line);

            output.write("&FLUIDELASTIC\n");
            line = "DIRECTION=" + jFrame.getjTextDirection().getText()
                    + ", Model=" + jFrame.getjTextModel().getText()
                    + ", NFLEX=" + jFrame.getjTextNFLEX().getText()
                    + ", VELOCITY=" + jFrame.getjTextVelocity().getText()
                    + ", DENSITY=" + jFrame.getjTextDensity().getText()
                    + ", TIMESTEP=" + jFrame.getjTextTimeStep().getText()
                    + ", TF=" + jFrame.getjTextTF().getText() + "\n/\n";
            output.write(line);

            output.write("&IMPACT\n");
            line = "ITERATIONS=" + jFrame.getjSpinnerIterations().getValue() + "\n"
                    + "BETA=" + jFrame.getjTextBeta().getText() + "\n"
                    + "TYPE_I=" + jFrame.getjTextType_I().getText() + "\n"
                    + "TOLERANCE=" + jFrame.getjTextTolerance().getText() + "\n"
                    + "STIFF=" + jFrame.getjTextStiffness().getText() + "\n"
                    + "DIAM=" + jFrame.getjTextDiameter().getText() + "\n"
                    + "DISPTOSCREEN=." + (jFrame.getjCheckBoxDisplay().isSelected() ? "TRUE" : "FALSE") + ".\n"
                    + "NODE=";
            output.write(line);

            line = "";
            for (Edge e : edges) {
                line += e.getEdgeNumber() + " ";
            }
            line += "\n/";
            output.write(line);

            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNodeFile(String nodeFile) {
        this.nodeFile = nodeFile;
    }

    public void setElemFile(String elemFile) {
        this.elemFile = elemFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void setConfFile(String confFile) {
        this.confFile = confFile;
    }

    public void deleteAll() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void setMainWindow(MainWindow jFrame) {
        this.jFrame = jFrame;
    }

    public static synchronized Interaction getInstance() {
        return inter;
    }

}
