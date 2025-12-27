public class OtherSmallObject extends LocationObject {
    protected OtherSmallObjectType type;
    protected float weight;
    public OtherSmallObject(Location currentLocation, float x, float y, float z, OtherSmallObjectType type, float weight) {
        super(currentLocation, x, y, z);
        this.type = type;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return type + " весом " + weight + " килограмм";
    }

    @Override
    public int hashCode() {
        return type.hashCode() * (int) weight;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OtherSmallObject other) ||  this.hashCode() != obj.hashCode()) {
            return false;
        }
        return this.weight == other.weight && this.type.equals(other.type) && super.equals(other);
    }
}
