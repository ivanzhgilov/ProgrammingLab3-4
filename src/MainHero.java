import java.util.ArrayList;
import java.util.logging.*;


public class MainHero extends Human implements StorableObject, MovableObject {
    private static final Logger logger = Logger.getLogger(MainHero.class.getName());
    protected ArrayList<PreciousThing> pocket = new ArrayList<PreciousThing>();
    protected float currentPocketSize;
    protected final float pocketSize;
    protected MovableObject grabbingObject;
    protected Memories memories;

    public MainHero(GenderType gender, int age, float pocketSize, Location curLoc, float x, float y, float z) {
        super(gender, age, curLoc, x, y, z);
        this.pocketSize = pocketSize;
        this.currentPocketSize = pocketSize;
        memories = new Memories((int) (Math.random() * 40), (int) (Math.random() * 45), (int) (Math.random() * 50));
    }

    @Override
    public void setEmotionState(EmotionType emotionState) {
        super.setEmotionState(emotionState);
        logger.log(Level.INFO, name + " " + emotionState);
    }

    @Override
    public void setCondition(ConditionType condition) {
        super.setCondition(condition);
        logger.log(Level.INFO, name + " " + condition);
    }

    public void addPreciousThing(PreciousThing preciousThing) throws TooLargePreciousThing {
        if (currentPocketSize >= preciousThing.getSize()) {
            pocket.add(preciousThing);
            currentPocketSize -= preciousThing.getSize();
        } else {
            throw new TooLargePreciousThing(" слишком большой, не влазит в карман", preciousThing);
        }
    }

    @Override
    public ArrayList<PreciousThing> getAllPreciousThings() {
        return pocket;
    }

    @Override
    public void removePreciousThing(PreciousThing preciousThing) {
        this.pocket.remove(preciousThing);
    }


    @Override
    protected void createName() {
        this.name = "Раскольников";
    }

    @Override
    public void moveToCoordinates(float x, float y, float z) {
        this.coordinates.replace(Dimension.X, x);
        this.coordinates.replace(Dimension.Y, y);
        this.coordinates.replace(Dimension.Z, z);
        logger.log(Level.INFO, name + " передвинулся, теперь " + Dimension.X + " " + x + " метров " + Dimension.Y + " метров " + y + " " + Dimension.Z + " " + z + " метров");
    }

    @Override
    public void moveToLocation(Location loc) {
        if (loc.type == LocationType.ISLANDS) {
            logger.log(Level.INFO, name + " не суждено отправиться на " + loc);
        } else {
            this.currentLocation = loc;
            if (this.grabbingObject != null) {
                this.grabbingObject.moveToLocation(loc);
            }
            logger.log(Level.INFO, name + " перешел в " + loc);
        }
    }

    public void hideAllPreciousThings(StorableObject obj) {
        ArrayList<PreciousThing> removePreciousThings = new ArrayList<>();
        for (PreciousThing preciousThing : pocket) {
            try {
                obj.addPreciousThing(preciousThing);
                removePreciousThings.add(preciousThing);
            } catch (TooLargePreciousThing e) {
                logger.log(Level.WARNING, e.toString());
            }
        }
        pocket.removeAll(removePreciousThings);
    }

    public void takeAllPreciousThings(StorableObject obj) {
        ArrayList<PreciousThing> removePreciousThings = new ArrayList<>();
        for (PreciousThing preciousThing : obj.getAllPreciousThings()) {
            try {
                this.addPreciousThing(preciousThing);
                removePreciousThings.add(preciousThing);
            } catch (TooLargePreciousThing e) {
                logger.log(Level.WARNING, e.toString());
            }
        }
        pocket.removeAll(removePreciousThings);
    }

    public ArrayList<LocationObject> lookAround() {
        logger.log(Level.INFO, name + " оглядывается");
        return this.currentLocation.getLocationObjects();
    }

    public void grabObject(MovableObject obj) {
        logger.log(Level.INFO, name + " подбирает " + obj);
        this.grabbingObject = obj;
    }

    public void letGoObject(float x, float y, float z) {
        try {
            this.grabbingObject.moveToCoordinates(x, y, z);
            logger.log(Level.INFO, name + " положил " + this.grabbingObject);
            this.grabbingObject = null;
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Руки пустые, нечего класть");
        }
    }

    public void laugh() {
        String message = "смеется";
        if (emotionState == EmotionType.NERVOUS) {
            message = "*нервно* " + message;
        }
        else if (emotionState == EmotionType.REMORSEFUL) {
            message = "*истерично* " + message;
        }
        else if  (emotionState == EmotionType.JOYFUL) {
            message = "*радостно* "  + message;
        }
        logger.log(Level.INFO, name + " " + message);
    }

    public void rememberingPast() {
        int countHappy = memories.countHappy();
        int countNervous = memories.countNervous();
        int countSad = memories.countSad();
        logger.log(Level.INFO, name + " вспоминает былое");
        if (countHappy >= countNervous && countHappy >= countSad) {
            this.setEmotionState(EmotionType.HAPPY);
        } else if (countNervous <= countSad) {
            this.setEmotionState(EmotionType.SAD);
        } else {
            this.setEmotionState(EmotionType.NERVOUS);
        }
    }
}
