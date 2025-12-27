import java.util.ArrayList;

public class Location {
    protected String name;
    protected LocationType type;
    protected ArrayList<LocationObject> objects;

    public Location(String name, LocationType type) {
        this.name = name;
        this.type = type;
    }

    public void setLocationObjects(ArrayList<LocationObject> objects) {
        this.objects = objects;
    }

    public void addLocationObject(LocationObject object) {
        this.objects.add(object);
    }

    public ArrayList<LocationObject> getLocationObjects() {
        return this.objects;
    }

    public void deleteLocationObject(LocationObject object) {
        this.objects.remove(object);
    }

    public LocationType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return name + " " + type.toString();
    }

    @Override
    public int hashCode() {
        return  name.hashCode() + type.hashCode() * objects.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Location other) || this.hashCode() != other.hashCode()) {
            return false;
        }
        return name.equals(other.name) && type.equals(other.type) && objects.equals(other.objects);
    }
}

