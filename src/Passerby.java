public class Passerby extends Human {
    static private final String[] listMaleNames = {"Владислав", "Кирилл", "Константин"};
    static private final String[] listFemaleNames = {"Анна", "Анастасия", "Мария"};

    public Passerby(GenderType gender, int age, Location curLoc, float x, float y, float z) {
        super(gender, age, curLoc, x, y, z);
        setCondition(ConditionType.MOVING);
        createEmotionState();
    }

    @Override
    protected void createName() {
        int index = (int) (Math.random() * 3);
        if(this.gender == GenderType.MALE) {
            this.name = listMaleNames[index];
        } else if (this.gender == GenderType.FEMALE) {
            this.name = listFemaleNames[index];
        }
        else {
            this.name = "неизвестное имя";
        }
    }

    protected void createEmotionState() {
        int index = (int) (Math.random() * 5);
        switch (index) {
            case 0 -> setEmotionState(EmotionType.NERVOUS);
            case 1 -> setEmotionState(EmotionType.HAPPY);
            case 2 -> setEmotionState(EmotionType.SAD);
            case 3 -> setEmotionState(EmotionType.JOYFUL);
            case 4 -> setEmotionState(EmotionType.REMORSEFUL);
            case 5 -> setEmotionState(EmotionType.NORMAL);
        }
    }
}
