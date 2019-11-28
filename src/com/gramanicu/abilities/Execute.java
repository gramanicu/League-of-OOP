package com.gramanicu.abilities;

import com.gramanicu.heroes.Rogue;
import com.gramanicu.heroes.Knight;
import com.gramanicu.heroes.Wizard;
import com.gramanicu.heroes.Pyromancer;
import com.gramanicu.heroes.Hero;

public class Execute extends Ability {
    private static final int DAMAGE = 200;
    private static final int SCALING = 30;
    private static final int PERCENT_BASE = 20;
    private static final int PERCENT_SCALING = 1;
    private static final int PERCENT_MAXIMUM = 40;
    private static final int PYROMANCER_BONUS = 10;
    private static final int ROGUE_BONUS = 15;
    private static final int WIZARD_BONUS = -20;


    public Execute(final Knight hero) {
        caster = hero;
    }

    /**
     * Method that reduces code in visitors.
     * @param target The target to attack
     * @return The damage the target will take
     */
    @Override
    protected int attack(final Hero target) {
        int percentage = Math.min(PERCENT_BASE
                + PERCENT_SCALING * caster.getLevel(), PERCENT_MAXIMUM);
        int executeThreshold = getPercentage(percentage, target.getMaxHp());
        if (target.getHp() < executeThreshold) {
           return executeThreshold;
        } else {
            int damage = DAMAGE + SCALING * caster.getLevel();
            damage += getTerrainBonus(damage);
            return damage;
        }
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        target.takeDamage(attack(target));
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        int damage = attack(target);
        damage += getPercentage(PYROMANCER_BONUS, damage);
        target.takeDamage(damage);
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        int damage = attack(target);
        damage += getPercentage(WIZARD_BONUS, damage);
        target.takeDamage(damage);
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        int damage = attack(target);
        damage += getPercentage(ROGUE_BONUS, damage);
        target.takeDamage(damage);
    }
}
