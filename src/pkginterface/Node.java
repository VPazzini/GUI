package pkginterface;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;

public class Node {
    
    private Point pos;
    private Boolean x = false;
    private Boolean y = false;
    private Boolean z = false;
    private Boolean Rx = false;
    private Boolean Ry = false;
    private Boolean Rz = false;
    private final int number;
    private ArrayList<Force> forces = new ArrayList<>();

    public Node(Point pos, int number) {
        this.pos = pos;
        this.number = number;
    }

    public ArrayList<Force> getForces() {
        return forces;
    }
    
     public void setForces(ArrayList<Force> forces) {
        this.forces = forces;
    }
    
    
    public void addForce(String axis, int forceValue){
        this.forces.add(new Force(axis, forceValue));
    }
    
    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Boolean isX() {
        return x;
    }

    public void setX(Boolean x) {
        this.x = x;
    }

    public Boolean isY() {
        return y;
    }

    public void setY(Boolean y) {
        this.y = y;
    }

    public Boolean isZ() {
        return z;
    }

    public void setZ(Boolean z) {
        this.z = z;
    }

    public Boolean isRx() {
        return Rx;
    }

    public void setRx(Boolean Rx) {
        this.Rx = Rx;
    }

    public Boolean isRy() {
        return Ry;
    }

    public void setRy(Boolean Ry) {
        this.Ry = Ry;
    }

    public Boolean isRz() {
        return Rz;
    }

    public void setRz(Boolean Rz) {
        this.Rz = Rz;
    }
    
    public boolean[] getRest(){
        boolean[] rest = new boolean[6];
        rest[0] = x;
        rest[1] = y;
        rest[2] = z;
        rest[3] = Rx;
        rest[4] = Ry;
        rest[5] = Rz;
        return rest;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (!Objects.equals(this.pos, other.pos)) {
            return false;
        }
        return true;
    }
    
    
    
}
