package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Drain extends Ability {
    private static final float PERCENT = 0.2f;
    private static final float PERCENT_SCALING = 0.05f;
    private static final float FACTOR_PERCENT = 0.3f;
    private static final float ROGUE_BONUS = 0.8f;
    private static final float KNIGHT_BONUS = 1.2f;
    private static final float PYROMANCER_BONUS = 0.9f;
    private static final float WIZARD_BONUS = 1.05f;

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
        percentage *= getTerrainBonus();
        float baseHP = Math.min(FACTOR_PERCENT * target.getMaxHp(),
                target.getHp());
        return percentage * baseHP;
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= (KNIGHT_BONUS + caster.getStatsModifier());
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= PYROMANCER_BONUS;
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= WIZARD_BONUS;
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= ROGUE_BONUS;
        target.takeDamage(Math.round(damage));
    }
}
