public class Lettering {
    protected String content;
    protected LetteringType type;

    public Lettering(LetteringType type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LetteringType getType() {
        return type;
    }

    public void setType(LetteringType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " надпись: '" + content + "'";
    }

    @Override
    public int hashCode() {
        return content.hashCode() * type.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Lettering other) || this.hashCode() != other.hashCode()) {
            return false;
        }
        return content.equals(other.content) && type.equals(other.type);
    }
}