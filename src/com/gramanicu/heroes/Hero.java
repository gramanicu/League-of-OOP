package com.gramanicu.heroes;

import com.gramanicu.maps.Point;

public abstract class Hero {
    private final HeroType type;
    private int xp;
    private int level;
    private int hp;
    private final int hpScaling;
    private int maxHp;

    private Point position;

    public Hero(final HeroType type, final int hp, final int hpScaling, final Point position) {
        this.type = type;
        this.xp = 0;
        this.level = 0;
        this.hp = hp;
        this.hpScaling = hpScaling;
        this.maxHp = hp;
        this.position = position;
    }
}
