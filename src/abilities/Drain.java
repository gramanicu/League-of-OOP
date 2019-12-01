package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Drain extends Ability {
    private static final int PERCENT = 20;
    private static final int PERCENT_SCALING = 5;
    private static final int FACTOR_PERCENT = 30;
    private static final int ROGUE_BONUS = -20;
    private static final int KNIGHT_BONUS = 20;
    private static final int PYROMANCER_BONUS = -10;
    private static final int WIZARD_BONUS = 5;

    public Drain(final Wizard hero) {
        caster = hero;
    }

    /**
     * Method that reduces code in visitors.
     * @param target The target to attack
     * @return The damage the target will take
     */
    @Override
    protected float attack(final Hero target) {
        float percentage = PERCENT + PERCENT_SCALING * caster.getLevel();
        percentage += getTerrainBonus(percentage);
        float baseHP = Math.min(getPercentage(FACTOR_PERCENT, target.getMaxHp()),
                target.getHp());
        return getPercentage(percentage, baseHP);
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.addLastTotalDamage(Math.round(damage));
        damage += getPercentage(KNIGHT_BONUS, damage);
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
