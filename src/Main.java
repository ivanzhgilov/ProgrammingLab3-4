import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.*;

public class Main {

    static private final Random random = new Random();
    static private final float percentageOfPeopleAppearingInCourtyard = 0.1F;

    static private final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.log(Level.INFO, "Генерация локаций");
        ArrayList<Location> locations = createLocations();
        Deepening deepening = new Deepening(20F, locations.get(3), 350, 150, 19.3F);
        Stone stone = new Stone(locations.get(3), 350, 150, 20, 24.57F);
        ArrayList<PreciousThing> allEvidence = new ArrayList<>();
        allEvidence.add(new PreciousThing(PreciousThingType.WALLET, 15F));
        allEvidence.add(new PreciousThing(PreciousThingType.MEDAL, 2F));
        allEvidence.add(new PreciousThing(PreciousThingType.CASE, 3F));
        allEvidence.add(new PreciousThing(PreciousThingType.CHAINLET, 1F));
        int countAllEvidence = allEvidence.size();
        locations.get(3).addLocationObject(stone);
        locations.get(3).addLocationObject(deepening);
        MainHero hero = new MainHero(GenderType.MALE, 23, 30F, locations.get(1), 0, 0, 20);
        logger.log(Level.INFO, "Раскольников взял улики");
        for (PreciousThing preciousThing : allEvidence) {
            try {
                hero.addPreciousThing(preciousThing);
            } catch (TooLargePreciousThing e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        String message = "улики не остались дома";
        if (hero.getAllPreciousThings().size() < countAllEvidence) {
            message = "Часть улик осталась дома";
        }
        logger.log(Level.INFO, message);
        hero.setEmotionState(EmotionType.NERVOUS);
        logger.log(Level.INFO, "Раскольников ищет место чтобы спрятать улики");
        hero.moveToLocation(locations.get(0));
        hero.moveToLocation(locations.get(1));
        hero.moveToCoordinates(400, 100, 20);
        hero.moveToCoordinates(400, 100, 20);
        hero.moveToCoordinates(400, 100, 20);
        hero.setCondition(ConditionType.STAND);
        ArrayList<LocationObject> objectsLoc = hero.lookAround();
        boolean arePeopleInLocation = false;
        for (LocationObject locationObject : objectsLoc) {
            if (locationObject instanceof Passerby) {
                arePeopleInLocation = true;
                break;
            }
        }
        hero.setCondition(ConditionType.MOVING);
        if (arePeopleInLocation) {
            logger.log(Level.INFO, hero.getName() + " не может спрятать улики здесь, тут есть люди");
            hero.setEmotionState(EmotionType.SAD);
            hero.setEmotionState(EmotionType.NERVOUS);
        } else {
            hero.moveToCoordinates(350, 150, 20);
            hero.setCondition(ConditionType.STAND);
            hero.setCondition(ConditionType.TILT);
            hero.grabObject(stone);
            hero.letGoObject(350.02F, 150, 20);
            hero.hideAllPreciousThings(deepening);
            if (hero.getAllPreciousThings().isEmpty()) {
                logger.log(Level.INFO, hero.getName() + " спрятал все улики");
                hero.setEmotionState(EmotionType.JOYFUL);
            } else {
                logger.log(Level.INFO, hero.getName() + " не смог спрятать все улики");
            }
            hero.grabObject(stone);
            hero.letGoObject(350, 150, 20.02F);
            hero.setCondition(ConditionType.STAND);
            hero.setCondition(ConditionType.MOVING);
            hero.moveToLocation(locations.get(2));
            hero.moveToCoordinates(500, 50, 20);
            hero.laugh();
            hero.setEmotionState(EmotionType.REMORSEFUL);
            hero.laugh();
        }
        hero.moveToLocation(locations.get(4));
        hero.moveToCoordinates(1000, 50, 20);
        objectsLoc = hero.lookAround();
        OtherSmallObject bench = null;
        for (LocationObject locationObject : objectsLoc) {
            if (locationObject instanceof OtherSmallObject obj) {
                if (obj.type == OtherSmallObjectType.BENCH) {
                    bench = obj;
                    break;
                }
            }
        }
        if (bench != null) {
            HashMap<Dimension, Float> coordinates = bench.getCoordinates();
            hero.moveToCoordinates(coordinates.get(Dimension.X), coordinates.get(Dimension.Y), coordinates.get(Dimension.Z));
            hero.setCondition(ConditionType.STAND);
            hero.setCondition(ConditionType.SITTING);
        }
        else {
            hero.setCondition(ConditionType.STAND);
        }
        hero.rememberingPast();


    }

    static private ArrayList<Location> createLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        Location loc_1 = new Location("Петербургские", LocationType.ISLANDS);
        createLocationObjects(loc_1);
        locations.add(loc_1);
        Location loc_2 = new Location("Вознесенский", LocationType.BOULEVARD);
        createLocationObjects(loc_2);
        locations.add(loc_2);
        Location loc_3 = new Location("Городская", LocationType.SQUARE);
        createLocationObjects(loc_3);
        locations.add(loc_3);
        Location loc_4 = new Location("Внутренний", LocationType.COURTYARD);
        createLocationObjects(loc_4);
        locations.add(loc_4);
        Location loc_5 = new Location("Конногвардейская", LocationType.STREET);
        createLocationObjects(loc_5);
        locations.add(loc_5);
        return locations;
    }

    static private void createLocationObjects(Location loc) {
        int countAdditionalObjects = random.nextInt(10) + 1;
        int countPeople = random.nextInt(100) + 1;
        ArrayList<LocationObject> objects = new ArrayList<>();
        if (loc.type == LocationType.ISLANDS) {
            float x = 2000;
            float y = 2000;
            float z = 30;
            for (int i = 0; i < countAdditionalObjects; i++) {
                LocationObject building = new Building(loc, x, y + 100 * i, z, random.nextInt(4) + 1, BuildingType.RESIDENTIAL);
                objects.add(building);
            }
        } else if (loc.type == LocationType.BOULEVARD) {
            float x = 0;
            float y = 0;
            float z = 20;
            for (int i = 0; i < countAdditionalObjects; i++) {
                LocationObject building = new Building(loc, x + 100 * i, y, z, random.nextInt(4) + 1, BuildingType.RESIDENTIAL);
                objects.add(building);
            }
        } else if (loc.type == LocationType.SQUARE) {
            float x = 0;
            float y = 500;
            float z = 20;
            for (int i = 0; i < countPeople + 50; i++) {
                Passerby passerby = new Passerby(random.nextInt(2) == 0 ? GenderType.MALE : GenderType.FEMALE, random.nextInt(70) + 15, loc, x + random.nextFloat() * 100, y + random.nextFloat(), z);
                objects.add(passerby);
            }
            for (int i = 0; i < countAdditionalObjects; i++) {
                OtherSmallObject object = new OtherSmallObject(loc, x + 10 * i, y, z, OtherSmallObjectType.BENCH, 5);
                objects.add(object);
            }
        } else if (loc.type == LocationType.STREET) {
            float x = 1000;
            float y = 50;
            float z = 20;
            OtherSmallObject bench = new OtherSmallObject(loc, x + 50, y + 10, z, OtherSmallObjectType.BENCH, 5);
            objects.add(bench);
            for (int i = 0; i < countAdditionalObjects; i++) {
                Building building = new Building(loc, x, y + 100 * i, z, random.nextInt(4) + 1, BuildingType.RESIDENTIAL);
                objects.add(building);
            }
            for (int i = 0; i < countPeople; i++) {
                Passerby passerby = new Passerby(random.nextInt(2) == 0 ? GenderType.MALE : GenderType.FEMALE, random.nextInt(70) + 15, loc, x + random.nextFloat() * 100, y + random.nextFloat() * 100, z);
                objects.add(passerby);
            }
        } else if (loc.type == LocationType.COURTYARD) {
            float x = 400;
            float y = 100;
            float z = 20;
            if (random.nextFloat() < percentageOfPeopleAppearingInCourtyard) {
                countPeople = random.nextInt(10) + 1;
            } else countPeople = 0;
            for (int i = 0; i < countPeople; i++) {
                Passerby passerby = new Passerby(random.nextInt(2) == 0 ? GenderType.MALE : GenderType.FEMALE, random.nextInt(70) + 15, loc, x + random.nextFloat() * 10, y + random.nextFloat() * 10, z);
                objects.add(passerby);
            }
            for (int i = 0; i < 4; i++) {
                Obstacle obstacle = new Obstacle(loc, x + 10, y, z, ObstacleType.WALL, 10);
                objects.add(obstacle);
            }
            objects.add(new Building(loc, x, y + 10, z, 4, BuildingType.RESIDENTIAL));
            Obstacle fence = new Obstacle(loc, x + 10, y + 10, z, ObstacleType.FENCE, 20);
            Lettering lettering = new Lettering(LetteringType.FORBIDDING, "Сдесь становитца воз прещено");
            fence.setLettering(lettering);
            objects.add(fence);
            objects.add(new OtherSmallObject(loc, x + 9, y + 9, z, OtherSmallObjectType.GUTTER, 3));
            objects.add(new OtherSmallObject(loc, x - 10, y, z, OtherSmallObjectType.COALDUST, 10));
            objects.add(new OtherSmallObject(loc, x + 50, y + 50, z, OtherSmallObjectType.MATERIALS, 10.5F));
            objects.add(new Building(loc, x + 15, y + 15, z, random.nextInt(2) + 1, BuildingType.LOCKSMITH));
        }
        loc.setLocationObjects(objects);
    }
}
