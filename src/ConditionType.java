public enum ConditionType {
    MOVING,
    SITTING,
    STAND,
    TILT;

    @Override
    public String toString() {
        return switch (this) {
            case MOVING -> "идет";
            case SITTING -> "сел";
            case STAND -> "встал";
            case TILT -> "наклолнился";
            default -> "неизвестное состояние " + this.name();
        };
    }
}
