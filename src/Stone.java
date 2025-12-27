public class Stone extends LocationObject implements MovableObject {
    protected float weight;
    public Stone(Location currentLocation, float x, float y, float z, float weight) {
        super(currentLocation, x, y, z);
        this.weight = weight;
    }
    public void moveToCoordinates(float x, float y, float z) {
        this.coordinates.replace(Dimension.X, x);
        this.coordinates.replace(Dimension.Y, y);
        this.coordinates.replace(Dimension.Z, z);
    }

    @Override
    public void moveToLocation(Location loc) {
        this.currentLocation = loc;
    }

    @Override
    public String toString() {
        return "Камень весом " + weight + " килограмм";
    }
    @Override
    public int hashCode() {
        return super.hashCode() * (int) weight;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Stone other) || this.hashCode() != obj.hashCode()) {
            return false;
        }
        return super.equals(other) && this.weight == other.weight;
    }
}
