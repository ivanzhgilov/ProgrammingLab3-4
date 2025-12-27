import java.util.logging.*;

public abstract class Human extends LocationObject {
    protected String name;
    protected GenderType gender;
    protected int age;
    protected EmotionType emotionState = EmotionType.NORMAL;
    protected ConditionType condition = ConditionType.MOVING;

    Human(GenderType gender, int age, Location curLoc, float x, float y, float z) {
        super(curLoc, x, y, z);
        this.gender = gender;
        this.age = age;
        createName();
    }
    public void setEmotionState(EmotionType emotionState) {
        this.emotionState = emotionState;
    }
    public void setCondition(ConditionType condition) {
        this.condition = condition;
    }
    public ConditionType getCondition() {
        return this.condition;
    }
    public EmotionType getEmotionState() {
        return this.emotionState;
    }

    public String getName() {
        return this.name;
    }

    abstract protected void createName();

    @Override
    public String toString() {
        return gender + " " + name + "возрастом " + age + " лет";
    }
}

