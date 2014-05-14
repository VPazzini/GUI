package elements;

public class Force {
    private final String axis;
    private final int forceValue;

    public Force(String axis, int forceValue) {
        this.axis = axis;
        this.forceValue = forceValue;
    }

    public String getAxis() {
        return axis;
    }

    public int getForceValue() {
        return forceValue;
    }

    @Override
    public String toString() {
        return this.axis + ":" + this.forceValue;
    }
    
    
    
}
