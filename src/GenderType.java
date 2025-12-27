public enum GenderType {
    MALE,
    FEMALE;

    @Override
    public String toString() {
        return switch (this) {
            case MALE -> "мужчина";
            case FEMALE -> "женщина";
            default -> "неизвестный пол " + this.name();
        };
    }
}
