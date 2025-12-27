public enum OtherSmallObjectType {
    MATERIALS,
    COALDUST,
    GUTTER,
    BENCH;

    @Override
    public String toString() {
        return switch (this) {
            case BENCH -> "скамейка";
            case GUTTER -> "желоб";
            case COALDUST -> "угольная пыль";
            case MATERIALS -> "материалы";
            default -> "неизвесный небольшой объект " + this.name();
        };
    }
}
