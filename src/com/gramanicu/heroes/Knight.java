package com.gramanicu.heroes;

import com.gramanicu.maps.Point;

public class Knight extends Hero {
    public Knight(final int hp, final int hpScaling, final Point position) {
        super(HeroType.KNIGHT, hp, hpScaling, position);
    }
}
