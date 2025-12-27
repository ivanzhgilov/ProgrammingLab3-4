public class PreciousThing {
    protected PreciousThingType type;
    protected float size;
    public PreciousThing(PreciousThingType type, float size) {
        this.type = type;
        this.size = size;
    }
    public float getSize() {
        return size;
    }
    public PreciousThingType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " объемом " + size + " кубических сантиметров";
    }
    @Override
    public int hashCode() {
        return type.hashCode() * (int) this.size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PreciousThing other) || this.hashCode() != obj.hashCode()) {
            return false;
        }
        return this.type.equals(other.type) && this.size == other.size;
    }
}
