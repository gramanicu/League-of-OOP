package abilities;

import heroes.Rogue;
import heroes.Knight;
import heroes.Wizard;
import heroes.Pyromancer;
import heroes.Hero;

public class Execute extends Ability {
    private static final int DAMAGE = 200;
    private static final int SCALING = 30;
    private static final float PERCENT_BASE = 0.2f;
    private static final float PERCENT_SCALING = 0.01f;
    private static final float PERCENT_MAXIMUM = 0.4f;
    private static final float PYROMANCER_BONUS = 1.1f;
    private static final float ROGUE_BONUS = 1.15f;
    private static final float WIZARD_BONUS = 0.8f;


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
        float percentage = Math.min(PERCENT_BASE
                + PERCENT_SCALING * caster.getLevel(), PERCENT_MAXIMUM);
        float executeThreshold = percentage * target.getMaxHp();
        if (target.getHp() < executeThreshold) {
           return executeThreshold;
        } else {
            float damage = DAMAGE + SCALING * caster.getLevel();
            damage *= getTerrainBonus();
            return damage;
        }
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.setLastTotalDamage(damage);
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
