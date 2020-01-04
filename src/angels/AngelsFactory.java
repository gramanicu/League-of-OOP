package angels;

import maps.Point;

public final class AngelsFactory {
    private static AngelsFactory instance = null;

    private AngelsFactory() { }

    public static AngelsFactory getInstance() {
        if (instance == null) {
            instance = new AngelsFactory();
        }
        return instance;
    }

    public Angel getAngel(final String type, final Point position) {
        switch (type) {
            case "DamageAngel":
                return new DamageAngel(position);
            case "DarkAngel":
                return new DarkAngel(position);
            case "Dracula":
                return new Dracula(position);
            case "GoodBoy":
                return new GoodBoy(position);
            case "LevelUpAngel":
                return new LevelUPAngel(position);
            case "LifeGiver":
                return new LifeGiver(position);
            case "SmallAngel":
                return new SmallAngel(position);
            case "Spawner":
                return new Spawner(position);
            case "TheDoomer":
                return new TheDoomer(position);
            case "XPAngel":
                return new XPAngel(position);
            default:
                return null;
        }
    }

}
