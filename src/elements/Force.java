package elements;

public class Force {
    private final String axis;
    private float forceValue;

    public Force(String axis, int forceValue) {
        this.axis = axis;
        this.forceValue = forceValue;
    }

    public String getAxis() {
        return axis;
    }

    public float getForceValue() {
        return forceValue;
    }
    
    public void addForce(float force){
        this.forceValue += force;
    }

    @Override
    public String toString() {
        return this.axis + ":" + this.forceValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof Force)){
            return false;
        }
        Force f = (Force) obj;
        if(f.getAxis().equals(this.axis)){
            return true;
        }
        return false;
    }
    
    
    
}
