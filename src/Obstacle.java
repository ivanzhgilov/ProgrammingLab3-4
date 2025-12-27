public class Obstacle extends LocationObject {
    protected Lettering lettering;
    protected float length;
    protected ObstacleType type;

    public Obstacle(Location currentLocation, float x, float y, float z, ObstacleType type, float length) {
        super(currentLocation, x, y, z);
        this.length = length;
        this.type = type;
    }

    public Lettering getLettering() {
        return lettering;
    }

    public void deleteLettering() {
        this.lettering = null;
    }

    public void setLettering(Lettering lettering) {
        this.lettering = lettering;
    }

    public float getLength() {
        return length;
    }

    public ObstacleType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        String letterString;
        if (lettering == null) {
            letterString = "без надписи";
        } else {
            letterString = "с" + lettering;
        }
        return type + "длиной " + length + "метров" + letterString;
    }

    @Override
    public int hashCode() {
        int value = 0;
        if (lettering != null) {
            value = lettering.hashCode();
        }
        return super.hashCode() + value + type.hashCode() * (int) length;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Obstacle other) || this.hashCode() != obj.hashCode()) {
            return false;
        }
        boolean flag;
        if (this.lettering != null) {
            flag = lettering.equals(other.lettering);
        } else flag = other.lettering == null;
        return super.equals(other) && this.type.equals(other.type) && this.length == other.length && flag;
    }
}
