public enum PreciousThingType {
    WALLET,
    CASE,
    CHAINLET,
    MEDAL,
    OTHER;

    @Override
    public String toString() {
        return switch (this) {
            case WALLET -> "кошелек";
            case CASE -> "коробка с украшениями";
            case CHAINLET -> "цепочка";
            case MEDAL -> "орден";
            case OTHER -> "какое-то украшение";
            default -> "неизвестное украшение " + this.name();
        };
    }
}
