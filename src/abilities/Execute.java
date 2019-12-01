package abilities;

import heroes.Rogue;
import heroes.Knight;
import heroes.Wizard;
import heroes.Pyromancer;
import heroes.Hero;

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
    protected float attack(final Hero target) {
        int percentage = Math.min(PERCENT_BASE
                + PERCENT_SCALING * caster.getLevel(), PERCENT_MAXIMUM);
        float executeThreshold = getPercentage(percentage, target.getMaxHp());
        if (target.getHp() < executeThreshold) {
           return executeThreshold;
        } else {
            float damage = DAMAGE + SCALING * caster.getLevel();
            damage += getTerrainBonus(damage);
            return damage;
        }
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.addLastTotalDamage(Math.round(damage));
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        float damage = attack(target);
        target.addLastTotalDamage(Math.round(damage));
        damage += getPercentage(PYROMANCER_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        float damage = attack(target);
        target.addLastTotalDamage(Math.round(damage));
        damage += getPercentage(WIZARD_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        float damage = attack(target);
        target.addLastTotalDamage(Math.round(damage));
        damage += getPercentage(ROGUE_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }
}
