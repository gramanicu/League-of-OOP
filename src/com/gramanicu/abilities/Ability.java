package com.gramanicu.abilities;

import com.gramanicu.heroes.Hero;
import com.gramanicu.heroes.Knight;
import com.gramanicu.heroes.Pyromancer;
import com.gramanicu.heroes.Rogue;
import com.gramanicu.heroes.Wizard;

public abstract class Ability {
    private static final float PERCENT = 100.0f;

    protected static Hero caster;

    protected static int getPercentage(final int percent, final int of) {
        float res = ((float) percent) / PERCENT * ((float) of);
        return Math.round(res);
    }

    /**
     * Amplifies the damage using the terrain bonus.
     * @param damage The initial damage
     * @return The amplified damage
     */
    protected int getTerrainBonus(final int damage) {
        return getPercentage(caster.getTerrainBonus(), damage);
    }

    /**
     * Method that reduces code in visitors.
     * @param target The target to attack
     * @return The damage the target will take
     */
    protected abstract int attack(Hero target);

    /**
     * @param target The knight to be affected by the ability
     */
    public abstract void affect(Knight target);

    /**
     * @param target The pyromancer to be affected by the ability
     */
    public abstract void affect(Pyromancer target);

    /**
     * @param target The wizard to be affected by the ability
     */
    public abstract void affect(Wizard target);

    /**
     * @param target The rogue to be affected by the ability
     */
    public abstract void affect(Rogue target);
}
