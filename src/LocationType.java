public enum LocationType {
    COURTYARD,
    BOULEVARD,
    SQUARE,
    ISLANDS,
    STREET;

    @Override
    public String toString() {
        return switch (this) {
            case SQUARE -> "площадь";
            case STREET -> "улица";
            case ISLANDS -> "острова";
            case BOULEVARD -> "проспект";
            case COURTYARD -> "двор";
            default -> "неизвестная локация " + this.name();
        };
    }
}
