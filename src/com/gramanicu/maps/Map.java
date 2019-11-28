package com.gramanicu.maps;

public final class Map {
    private static Map instance = null;
    private static char[][] terrain = new char[0][0];

    private Map() { };

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }

        return instance;
    }

    /**
     * Loads the map.
     * @param data The terrain data
     */
    public void loadMap(final char[][] data) {
        terrain = data;
    }

    /**
     * Return the terrain at the specified location.
     * @param point The location
     * @return The terrain type
     */
    public TerrainType getTerrain(final Point point) {
        char type = terrain[point.getY()][point.getX()];
        switch (Character.toUpperCase(type)) {
            case 'L':
                return TerrainType.LAND;
            case 'V':
                return TerrainType.VOLCANIC;
            case 'D':
                return TerrainType.DESERT;
            case 'W':
                return TerrainType.WOODS;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
