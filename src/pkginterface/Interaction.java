package pkginterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int edgeNumber = 1;
    private boolean shiftHold = false;
    private String nodeFile = "node_file.dat";
    private String elemFile = "elem_file.dat";
    private String inputFile = "input.dat";
    private String confFile = "conf_file.dat";

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

        Rectangle r = this.getBounds();
        int h = r.height - 30;
        int w = r.width - 10;

        System.out.println(this.getBounds());
        g2d.drawLine(30, h, 55, h); //X
        g2d.drawLine(55, h, 49, h-3);
        g2d.drawLine(55, h, 49, h+3);
        
        g2d.drawLine(30, h, 30, h-25); //Y
        g2d.drawLine(30, h-25, 33, h-19);
        g2d.drawLine(30, h-25, 27, h-19);
        
        g2d.drawLine(30, h, 20, h+10); //Z
        g2d.drawLine(20, h+10, 23, h+4);
        g2d.drawLine(20, h+10, 25, h+8);
        

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
                        if (!shiftHold) {
                            selectedNode = null;
                        }
                    } else {
                        this.selectedNode = temp;
                    }
                }
                repaint();
                return;
            }

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
            temp.setLocation(p.x, p.y);
            movingNode.setPos(temp);
        } else {
            for (Node n : nodes) {
                if (isInside(n, p)) {
                    this.moving = true;
                    Point temp = new Point();
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
        rest.setSize(224, 141);
        rest.setModal(true);
        rest.setResizable(false);
        rest.setLocationRelativeTo(null);
        rest.setTitle("Node " + temp.getNumber());
        Restraints r = new Restraints(rest, temp.getRest());
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

        if (p.distance(temp) <= nodeSize / 2) {
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

    private void generateNodeFile() {
        if (nodes.size() == 0) {
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

}
