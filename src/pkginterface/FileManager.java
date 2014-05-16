package pkginterface;

import elements.Edge;
import elements.Node;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {
    private double tStart = 0;
    private double tStop = 0;
    private double deltaT = 0;
    
    private int numberNodes = 0;
    private String nodeFile = "node_file.dat";
    private String elemFile = "elem_file.dat";
    private String inputFile = "input.dat";
    private String confFile = "conf_file.dat";
    private int NELG = 0;
    private int NMAT = 0;
    private double dO = 0;
    private double dI = 0;
    
    private double teMod = 0;
    private int tDens = 0;
    private double tPoi = 0;
    
    private int direction = 0;
    private int model = 2;
    private int nFlex = 1;
    private double velocity = 1;
    private double density = 1;
    private double tf = 0.3;
    
    private int iterations = 10;
    private double beta = 0.25;
    private int type_I = 2;
    private double tolerance = 0.001;
    private double stiffness = 0.555E+07;
    private double diam = 0.127E-01;
    private boolean displayScreen = true;
    
    private Interaction inter;
    
    public FileManager(){
        inter = Interaction.getInstance();
    }
    
    private void generateNodeFile() {
        ArrayList<Node> nodes = inter.getNodes();
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
        ArrayList<Edge> edges = inter.getEdges();
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
        ArrayList<Edge> edges = inter.getEdges();
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            String line;

            output.write("&TIMING\n");
            line = "TSTART=" + tStart
                    + ", TSTOP=" + tStop
                    + ", DELTAT=" + deltaT
                    + "\n/\n";
            output.write(line);

            output.write("&NODELIST\n");
            line = "NNP=" + edges.size()
                    + ", nodefile='" + nodeFile + "',"
                    + "NELG=" + NELG
                    + ", NMAT=" + NMAT
                    + ", elemfile='" + elemFile + "'"
                    + ", conffile='" + confFile + "'"
                    + ", d_o=" + dO
                    + ", d_i=" + dI + "\n/\n";
            output.write(line);

            output.write("&MATERIAL01\n");
            line = "TEMOD=" + teMod
                    + ", TDENS=" + tDens
                    + ", TPOI=" + tPoi + "\n/\n";
            output.write(line);

            output.write("&FLUIDELASTIC\n");
            line = "DIRECTION=" + direction
                    + ", Model=" + model
                    + ", NFLEX=" + nFlex
                    + ", VELOCITY=" + velocity
                    + ", DENSITY=" + density
                    + ", TF=" + tf + "\n/\n";
            output.write(line);

            output.write("&IMPACT\n");
            line = "ITERATIONS=" + iterations + "\n"
                    + "BETA=" + beta + "\n"
                    + "TYPE_I=" + type_I + "\n"
                    + "TOLERANCE=" + tolerance + "\n"
                    + "STIFF=" + stiffness + "\n"
                    + "DIAM=" + diam + "\n"
                    + "DISPTOSCREEN=." + (displayScreen ? "TRUE" : "FALSE") + ".\n"
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

    public double gettStart() {
        return tStart;
    }

    public void settStart(double tStart) {
        this.tStart = tStart;
    }

    public double gettStop() {
        return tStop;
    }

    public void settStop(double tStop) {
        this.tStop = tStop;
    }

    public double getDeltaT() {
        return deltaT;
    }

    public void setDeltaT(double deltaT) {
        this.deltaT = deltaT;
    }

    public int getNumberNodes() {
        return numberNodes;
    }

    public void setNumberNodes(int numberNodes) {
        this.numberNodes = numberNodes;
    }

    public String getNodeFile() {
        return nodeFile;
    }

    public void setNodeFile(String nodeFile) {
        this.nodeFile = nodeFile;
    }

    public String getElemFile() {
        return elemFile;
    }

    public void setElemFile(String elemFile) {
        this.elemFile = elemFile;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getConfFile() {
        return confFile;
    }

    public void setConfFile(String confFile) {
        this.confFile = confFile;
    }

    public int getNELG() {
        return NELG;
    }

    public void setNELG(int NELG) {
        this.NELG = NELG;
    }

    public int getNMAT() {
        return NMAT;
    }

    public void setNMAT(int NMAT) {
        this.NMAT = NMAT;
    }

    public double getdO() {
        return dO;
    }

    public void setdO(double dO) {
        this.dO = dO;
    }

    public double getdI() {
        return dI;
    }

    public void setdI(double dI) {
        this.dI = dI;
    }

    public double getTeMod() {
        return teMod;
    }

    public void setTeMod(double teMod) {
        this.teMod = teMod;
    }

    public int gettDens() {
        return tDens;
    }

    public void settDens(int tDens) {
        this.tDens = tDens;
    }

    public double gettPoi() {
        return tPoi;
    }

    public void settPoi(double tPoi) {
        this.tPoi = tPoi;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getnFlex() {
        return nFlex;
    }

    public void setnFlex(int nFlex) {
        this.nFlex = nFlex;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getTf() {
        return tf;
    }

    public void setTf(double tf) {
        this.tf = tf;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public int getType_I() {
        return type_I;
    }

    public void setType_I(int type_I) {
        this.type_I = type_I;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public double getStiffness() {
        return stiffness;
    }

    public void setStiffness(double stiffness) {
        this.stiffness = stiffness;
    }

    public double getDiam() {
        return diam;
    }

    public void setDiam(double diam) {
        this.diam = diam;
    }

    public boolean isDisplayScreen() {
        return displayScreen;
    }

    public void setDisplayScreen(boolean displayScreen) {
        this.displayScreen = displayScreen;
    }
    
    
    
}
