import java.util.ArrayList;

public interface StorableObject {
    public void addPreciousThing(PreciousThing preciousThings) throws TooLargePreciousThing;
    public ArrayList<PreciousThing> getAllPreciousThings();
    public void removePreciousThing(PreciousThing preciousThing);
}
