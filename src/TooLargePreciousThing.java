public class TooLargePreciousThing extends Exception {
    private final PreciousThing preciousThing;
    public TooLargePreciousThing(String message, PreciousThing obj) {
        super(message);
        this.preciousThing = obj;
    }
    @Override
    public String getMessage() {
        return preciousThing + super.getMessage();
    }
}
