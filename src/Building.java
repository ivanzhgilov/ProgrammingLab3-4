public class Building extends LocationObject {
    protected int countFloors;
    protected BuildingType type;
    public Building(Location currentLocation, float x, float y, float z, int countFloors, BuildingType type) {
        super(currentLocation, x, y, z);
        this.countFloors = countFloors;
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " " + "здание с " + countFloors + " этажами";
    }

    @Override
    public int hashCode() {
        return super.hashCode() + type.hashCode() * countFloors;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Building other) || this.hashCode() != obj.hashCode()) {
            return false;
        }
        return super.equals(other) && this.type.equals(other.type) && this.countFloors == other.countFloors;
    }
}
