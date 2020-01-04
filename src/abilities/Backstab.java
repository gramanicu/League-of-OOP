package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Backstab extends Ability {
    private static final int DAMAGE = 200;
    private static final int SCALING = 20;
    private static final int CRITICAL_PERIOD = 3;
    private static final float CRITICAL_PERCENT = 1.5f;
    private static final float ROGUE_BONUS = 1.2f;
    private static final float KNIGHT_BONUS = 0.9f;
    private static final float PYROMANCER_BONUS = 1.25f;
    private static final float WIZARD_BONUS = 1.25f;

    public Backstab(final Rogue hero) {
        caster = hero;
    }

    /**
     * Method that reduces code in visitors.
     * @param target The target to attack
     * @return The damage the target will take
     */
    @Override
    protected float attack(final Hero target) {
        float damage = DAMAGE + SCALING * caster.getLevel();
        if (((Rogue) caster).getAttacksCount() % CRITICAL_PERIOD == 0) {
            if (caster.getTerrainBonus() != 1f) {
                damage *= CRITICAL_PERCENT;
            }
        }
        damage *= getTerrainBonus();
        return damage;
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.setLastTotalDamage(damage);
        damage *= (KNIGHT_BONUS + caster.getStrategyModifier());
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        float damage = attack(target);
        target.setLastTotalDamage(damage);
        damage *= PYROMANCER_BONUS;
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        float damage = attack(target);
        target.setLastTotalDamage(damage);
        damage *= WIZARD_BONUS;
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        float damage = attack(target);
        target.setLastTotalDamage(damage);
        damage *= ROGUE_BONUS;
        target.takeDamage(Math.round(damage));
    }
}
