public enum ObstacleType {
    FENCE,
    WALL;

    public String toString() {
        return switch (this) {
            case WALL -> "стена";
            case FENCE -> "забор";
            default -> "неизвестное препятствие " + this.name();
        };
    }
}
