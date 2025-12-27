public enum Dimension {
    X,
    Y,
    Z;

    @Override
    public String toString() {
        return switch (this) {
            case X -> "координата X";
            case Y -> "координата Y";
            case Z -> "координата Z";
        };
    }
}
