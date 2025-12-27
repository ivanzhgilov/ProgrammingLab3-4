public enum BuildingType {
    CARRIAGE,
    LOCKSMITH,
    RESIDENTIAL,
    OTHER;

    @Override
    public String toString() {
        return switch (this) {
            case CARRIAGE -> "колясочное";
            case LOCKSMITH -> "слесарное";
            case RESIDENTIAL -> "жилое";
            case OTHER -> "какое-то";
            default -> "неизвестное" +  this.name();
        };
    }
}
