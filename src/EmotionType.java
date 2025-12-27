public enum EmotionType {
    NERVOUS,
    JOYFUL,
    REMORSEFUL,
    NORMAL,
    HAPPY,
    SAD;

    @Override
    public String toString() {
        return switch (this) {
            case NERVOUS -> "нервничает";
            case JOYFUL -> "радуется";
            case REMORSEFUL -> "раскаивается";
            case NORMAL -> "нормализовался";
            case HAPPY -> "счастлив";
            case SAD -> "грустит";
            default -> "неизвестное состояние " + this.name();
        };
    }
}
