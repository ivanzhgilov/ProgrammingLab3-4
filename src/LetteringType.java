public enum LetteringType {
    FORBIDDING,
    INFORMATIONAL,
    OTHER;

    public String toString(){
        return switch (this) {
            case FORBIDDING -> "запрещающая";
            case INFORMATIONAL -> "информирующая";
            case OTHER -> "какая-то";
            default -> "неизвестная " + this.name();
        };
    }
}
