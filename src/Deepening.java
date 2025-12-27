import java.util.ArrayList;
import java.util.List;

public class Deepening extends LocationObject implements StorableObject {

    protected ArrayList<PreciousThing> storage = new ArrayList<PreciousThing>();
    protected final float volume;
    protected float currentVolume;

    public Deepening(float volume, Location currentLocation, float x, float y, float z) {
        super(currentLocation, x, y, z);
        this.volume = volume;
        this.currentVolume = volume;
    }

    @Override
    public void addPreciousThing(PreciousThing preciousThing) throws TooLargePreciousThing {
        if (currentVolume >= preciousThing.size) {
            storage.add(preciousThing);
            currentVolume -= preciousThing.size;
        } else {
            throw new TooLargePreciousThing("слишком большой для этого места", preciousThing);
        }
    }

    @Override
    public ArrayList<PreciousThing> getAllPreciousThings() {
        return storage;
    }

    @Override
    public void removePreciousThing(PreciousThing preciousThing) {
        this.storage.remove(preciousThing);
    }

    @Override
    public String toString() {
        return "углубление с отставшимся объемом " + currentVolume + " сангтиметров квадратных";
    }

    @Override
    public int hashCode() {
        return storage.hashCode() + (int) volume * (int) currentVolume + super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deepening other) || this.hashCode() != obj.hashCode()) {
            return false;
        }
        return storage.equals(other.storage) && volume == other.volume && currentVolume == other.currentVolume;
    }
}
