import java.util.HashMap;

public abstract class LocationObject {
    protected Location currentLocation;
    protected HashMap<Dimension, Float> coordinates;
    public LocationObject(Location currentLocation, float x, float y, float z) {
        this.currentLocation = currentLocation;
        this.coordinates = new HashMap<>();
        this.coordinates.put(Dimension.X, x);
        this.coordinates.put(Dimension.Y, y);
        this.coordinates.put(Dimension.Z, z);
    }
    public Location getLocation() {
        return currentLocation;
    }
    public void setLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public HashMap<Dimension, Float> getCoordinates() {
        return coordinates;
    }

    @Override
    public int hashCode() {
        return currentLocation.hashCode() + coordinates.hashCode();
    }
}
