package pkginterface;

import java.awt.Point;
import java.util.Objects;

public class Node {
    
    private Point pos;
    private Boolean x = false;
    private Boolean y = false;
    private Boolean z = false;
    private Boolean Rx = false;
    private Boolean Ry = false;
    private Boolean Rz = false;
    private int number;

    public Node(Point pos, int number) {
        this.pos = pos;
        this.number = number;
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